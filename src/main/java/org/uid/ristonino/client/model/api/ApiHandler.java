package org.uid.ristonino.client.model.api;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import org.uid.ristonino.client.model.Debug;
import org.uid.ristonino.client.model.types.Item;


public class ApiHandler {
    private final Vertx vertx = Vertx.vertx();
    private final HttpClient client = vertx.createHttpClient();
    public static ApiHandler INSTANCE = new ApiHandler();

    private ApiHandler() {
        try {
            client.request(HttpMethod.GET, 8080, "localhost", "/")
                    .compose(req -> req.send()
                            .compose(resp -> resp.body()
                                    .onSuccess(body -> System.out.println("GET Response: " + body.toString()))));
                                    //.onFailure(Throwable::printStackTrace)))
                    //.onFailure(Throwable::printStackTrace);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ApiHandler getInstance() {
        return INSTANCE;
    }

    public void sendOrder(JsonObject order) {
        try {
            client.request(HttpMethod.POST, 8080, "localhost", "/")
                    .compose(req -> req.putHeader("content-type", "application/json")
                            .send(Buffer.buffer(order.encode()))
                            .compose(resp -> resp.body()
                                    .onSuccess(body -> {
                                        System.out.println("POST Response: " + body.toString());
                                    })
                                    .onFailure(Throwable::printStackTrace)))
                    .onFailure(Throwable::printStackTrace);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        vertx.close();
    }
}
