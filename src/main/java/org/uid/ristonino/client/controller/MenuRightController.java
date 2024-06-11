package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.uid.ristonino.client.model.Settings;
import org.uid.ristonino.client.model.api.ApiHandler;
import org.uid.ristonino.client.model.events.*;
import org.uid.ristonino.client.model.types.Cart;
import org.uid.ristonino.client.model.types.Order;
import org.uid.ristonino.client.model.types.OrderInterface;

import java.io.IOException;
import java.util.*;

public class MenuRightController {
    @FXML private VBox ordersList;

    private final Map<String, OrderInterface> listaOrdini = new HashMap<>();
    private final Cart cart = new Cart();
    private final UpdateCart updateCart = new UpdateCart(cart);
    private final List<Order> listaSendOrders = new ArrayList<>();

    @FXML
    public void initialize() {
        //Evento per aggiungere gli ordini alla lista a destra
        EventBus.getInstance().addEventHandler(AddOrder.ORDER_ADDED, event -> {
            String orderItemId = event.getItemId();
            Order order = event.getOrder();
            int quant = order.getQuantity();


            if (listaOrdini.containsKey(orderItemId)) {
                String notes = order.getNotes();
                String ingredients = order.getRemovedIngredients();
                if (quant > 0) {
                    cart.update(orderItemId, quant);
                    EventBus.getInstance().fireEvent(updateCart);
                    OrderController orderController = listaOrdini.get(orderItemId).orderController();
                    orderController.setNewQuantity(quant);
                    orderController.setNewNotes(notes);
                    orderController.setNewIngredients(ingredients);
                } else {
                    cart.remove(orderItemId);
                    EventBus.getInstance().fireEvent(updateCart);
                    for (int i = 0; i < ordersList.getChildren().size(); i++) {
                        VBox ordine = (VBox) ordersList.getChildren().get(i);
                        if (ordine.getId().equals(String.valueOf(orderItemId))) {
                            ordersList.getChildren().remove(i);
                            listaOrdini.remove(orderItemId);
                            break;
                        }
                    }
                }
            } else {
                if (quant > 0) {
                    cart.add(orderItemId, quant, order.getPrice());
                    EventBus.getInstance().fireEvent(updateCart);
                    OrderInterface orderInterface = loadOrder(orderItemId, order);
                    orderInterface.node().setId(String.valueOf(orderItemId));
                    ordersList.getChildren().add(orderInterface.node());
                    listaOrdini.put(orderItemId, orderInterface);
                }
            }
        });

        //Evento per aggiornare gli ordini fatti
        EventBus.getInstance().addEventHandler(UpdateOrders.EVENT_TYPE, event -> {
            Iterator<Map.Entry<String, OrderInterface>> iterator = listaOrdini.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, OrderInterface> entry = iterator.next();
                String key = entry.getKey();
                OrderInterface orderInterface = listaOrdini.get(key);
                listaSendOrders.add(orderInterface.order());
                orderInterface.orderController().removeButtonOfRemove();
                orderInterface.orderController().setOrderStatus("IN LAVORAZIONE");
                orderInterface.node().getStyleClass().add("order-status-working");

                // Rimuovere l'elemento corrente
                iterator.remove();
            }
            ApiHandler.getInstance().sendOrder(listaSendOrders);
            listaSendOrders.clear();
        });

        EventBus.getInstance().addEventHandler(RemoveOrder.EVENT_TYPE, event -> {
            String orderId = event.getId();
            for (Node node : ordersList.getChildren()) {
                if (node.getId().equals(String.valueOf(orderId))) {
                    cart.update(orderId, 0);
                    EventBus.getInstance().fireEvent(updateCart);
                    ordersList.getChildren().remove(node);
                    listaOrdini.remove(orderId);
                    break;
                }
            }

        });
    }

    private OrderInterface loadOrder(String orderId, Order order) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource((Settings.SCENE_PATH + "view/order.fxml")));
            Node node = fxmlLoader.load();
            OrderController orderController = fxmlLoader.getController();
            orderController.initialize(orderId, order.getItemName(), order.getPrice(), order.getRemovedIngredients(), order.getNotes(), order.getQuantity());
            return new OrderInterface(node, orderController, order);
        } catch (IOException ignored) {

        }
        return new OrderInterface(new VBox(), null, order);
    }

}
