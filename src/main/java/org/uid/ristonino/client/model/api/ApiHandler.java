package org.uid.ristonino.client.model.api;

import io.github.cdimascio.dotenv.Dotenv;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import org.uid.ristonino.client.model.Settings;
import org.uid.ristonino.client.model.types.Flag;
import org.uid.ristonino.client.model.types.Item;
import org.uid.ristonino.client.model.types.Order;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;

public class ApiHandler {
    private final int idTavolo;
    private final int massimoCoperti;
    private JsonArray jsonCategories;
    private JsonArray jsonItems;
    private JsonArray jsonFlags;

    private final Vertx vertx = Vertx.vertx();
    private final HttpClient client = vertx.createHttpClient();
    public static ApiHandler INSTANCE = new ApiHandler();

    private final ReentrantLock lock = new ReentrantLock();

    private ApiHandler() {
        Dotenv dotenv = Dotenv.configure()
                .directory(String.valueOf(getClass().getResource("/org/uid/ristonino/client")))
                .filename("config.env")
                .load();
        idTavolo = Integer.parseInt(dotenv.get("TABLE_ID"));
        massimoCoperti = Integer.parseInt(dotenv.get("MAX_COPERTI"));
        getMenu();
    }

    public int getMassimoCoperti() {
        return massimoCoperti;
    }

    public static ApiHandler getInstance() {
        return INSTANCE;
    }

    public void accessApi(int numCoperti) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                JsonObject tableInfo = new JsonObject();
                tableInfo.put("idTable", idTavolo);
                tableInfo.put("numberCovers", numCoperti);
                tableInfo.put("maxCovers", massimoCoperti);

                try {
                    client.request(HttpMethod.POST, 8080, "localhost", "/api/tables")
                            .compose(req -> req.putHeader("content-type", "application/json")
                                    .send(Buffer.buffer(tableInfo.encode()))
                                    .compose(resp -> resp.body()
                                            .onSuccess(body -> {
                                                System.out.println("POST Response: " + body.toString());
                                            })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    public Task<InputStream> getItemImage(String itemId) {
        Task<InputStream> task = new Task<>() {
            @Override
            protected InputStream call() throws Exception {
                CompletableFuture<InputStream> future = new CompletableFuture<>();

                client.request(HttpMethod.GET, 8080, "localhost", "/api/images/" + itemId + ".png")
                        .compose(req -> req.send()
                                .compose(resp -> {
                                    if (resp.statusCode() == 200) {
                                        return resp.body();
                                    } else {
                                        future.completeExceptionally(new Exception("Failed to fetch image, status code: " + resp.statusCode()));
                                        return Future.failedFuture(new Exception("Failed to fetch image, status code: " + resp.statusCode()));
                                    }
                                }))
                        .onSuccess(body -> {
                            InputStream inputStream = new ByteArrayInputStream(body.getBytes());
                            future.complete(inputStream);
                        })
                        .onFailure(err -> {
                            future.completeExceptionally(err);
                        });

                return future.get(); // Blocking call to wait for the async result
            }
        };
        return task;
    }

    public void getMenu() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                try {
                    client.request(HttpMethod.GET, 8080, "localhost", "/api/menu")
                            .compose(req -> req.send()
                                    .compose(resp -> resp.body()
                                            .onSuccess(body -> {
                                                JsonObject jsonObject = body.toJsonObject();
                                                lock.lock();
                                                try {
                                                    jsonCategories = jsonObject.getJsonArray("categories");
                                                    jsonItems = jsonObject.getJsonArray("items");
                                                    jsonFlags = jsonObject.getJsonArray("flags");
                                                } finally {
                                                    lock.unlock();
                                                }
                                            })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    public HashMap<Integer, Flag> getFlags() {
        HashMap<Integer, Flag> flags = new HashMap<>();
        lock.lock();
        try {
            for (int i = 0; i < jsonFlags.size(); i++) {
                JsonObject jsonFlag = jsonFlags.getJsonObject(i);
                String name = jsonFlag.getString("name");
                Integer id = jsonFlag.getInteger("id");
                String imagePath = jsonFlag.getString("pathImage");
                Flag flag = new Flag(id, name, null);
                flags.put(id, flag);

                Task<InputStream> task = getItemImage(imagePath);
                task.setOnSucceeded(event -> {
                    InputStream inputStream = task.getValue();
                    Image image;
                    if (inputStream != null) {
                        image = new Image(inputStream);
                    } else {
                        System.out.println("Failed to retrieve image for flag: " + id);
                        image = new Image(Settings.DEFAULT_IMAGE);
                    }
                    flag.setFlagImage(image);
                });
                new Thread(task).start();
            }
        } finally {
            lock.unlock();
        }
        return flags;
    }

    public HashMap<Integer, String> getCategories() {
        HashMap<Integer, String> retCategories = new HashMap<>();
        lock.lock();
        try {
            for (int i = 0; i < jsonCategories.size(); i++) {
                JsonObject categoria = jsonCategories.getJsonObject(i);
                retCategories.put(categoria.getInteger("id"), categoria.getString("name"));
            }
        } finally {
            lock.unlock();
        }
        return retCategories;
    }

    public List<Item> getItems() {
        List<Item> retItems = new ArrayList<>();
        HashMap<Integer, Flag> flags = getFlags();
        HashMap<Integer, String> categories = getCategories();

        lock.lock();
        try {
            for (int i = 0; i < jsonItems.size(); i++) {
                JsonObject jsonItem = jsonItems.getJsonObject(i);
                int id = jsonItem.getInteger("id");
                String name = jsonItem.getString("name");
                Integer categoryKey = jsonItem.getInteger("category");
                String description = jsonItem.getString("description");
                double price = jsonItem.getDouble("price");
                String imagePath = jsonItem.getString("pathImage");
                //List<Integer> flagsKey = jsonItem.getJsonArray("flags").getList();
                List<String> ingredients = jsonItem.getJsonArray("ingredients").getList();

                String category = categories.get(categoryKey);
                List<Flag> flagsList = new ArrayList<>();
//                for (Integer key : flagsKey) {
//                    flagsList.add(flags.get(key));
//                }
                Item item = new Item(id, name, category, ingredients, description, price, null, flagsList);
                retItems.add(item);
                Task<InputStream> task = getItemImage(imagePath);
                task.setOnSucceeded(event -> {
                    InputStream inputStream = task.getValue();
                    Image image;
                    if (inputStream != null) {
                        image = new Image(inputStream);
                    } else {
                        System.out.println("Failed to retrieve image for item: " + id);
                        image = new Image(Settings.DEFAULT_IMAGE);
                    }
                    item.setImage(image);
                });
                new Thread(task).start();
            }
        } finally {
            lock.unlock();
        }
        return retItems;
    }

    public void sendOrder(List<Order> orders) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                JsonObject order = jsonOrder(orders);
                try {
                    client.request(HttpMethod.POST, 8080, "localhost", "/api/orders")
                            .compose(req -> req.putHeader("content-type", "application/json")
                                    .send(Buffer.buffer(order.encode()))
                                    .compose(resp -> resp.body()
                                            .onSuccess(body -> {
                                                System.out.println("POST Response: " + body.toString());
                                            })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    private JsonObject jsonOrder(List<Order> orders) {
        JsonObject json = new JsonObject();
        json.put("idTavolo", idTavolo);
        List<JsonObject> ordersList = new ArrayList<>();
        orders.forEach(order -> {
            JsonObject orderJson = new JsonObject();
            orderJson.put("id", order.getId());
            orderJson.put("quantity", order.getQuantity());
            orderJson.put("notes", order.getNotes());
            orderJson.put("removedIngredients", order.getRemovedIngredients());
            ordersList.add(orderJson);
        });
        json.put("items", ordersList);
        return json;
    }

    public void closeConnection() {
        vertx.close();
    }
}
