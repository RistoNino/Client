package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.uid.ristonino.client.model.Settings;
import org.uid.ristonino.client.model.events.AddOrder;
import org.uid.ristonino.client.model.events.EventBus;
import org.uid.ristonino.client.model.events.UpdateCart;
import org.uid.ristonino.client.model.types.Cart;
import org.uid.ristonino.client.model.types.Order;
import org.uid.ristonino.client.model.types.OrderInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MenuRightController {
    @FXML
    private VBox ordersList;

    private final Map<String, OrderInterface> listaOrdini = new HashMap<>();
    private Cart cart = new Cart();
    private UpdateCart updateCart = new UpdateCart(cart);

    // Id item = "Item-id"
    // Id custom item = "custom-id"

    @FXML
    public void initialize() {
        //Evento per aggiungere gli ordini alla lista a destra
        EventBus.getInstance().addEventHandler(AddOrder.ORDER_ADDED, event -> {
            String orderItemId = ((AddOrder) event).getItemId();
            Order order = ((AddOrder) event).getOrder();
            int quant = order.getQuantity();

            if (listaOrdini.containsKey(orderItemId)) {
                String notes = order.getNotes();
                String ingredients = order.getRemovedIngredients();
                if (quant > 0) {
                    cart.update(orderItemId, quant);
                    EventBus.getInstance().fireEvent(updateCart);
                    OrderController orderController = listaOrdini.get(orderItemId).getOrderController();
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
                    OrderInterface orderInterface = loadOrder(order);
                    orderInterface.getNode().setId(String.valueOf(orderItemId));
                    ordersList.getChildren().add(orderInterface.getNode());
                    listaOrdini.put(orderItemId, orderInterface);
                }
            }

        });
    }

    private OrderInterface loadOrder(Order order) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource((Settings.SCENE_PATH + "view/order.fxml")));
            Node node = fxmlLoader.load();
            OrderController orderController = fxmlLoader.getController();
            orderController.initialize(order.getItemName(), order.getPrice(), order.getRemovedIngredients(), order.getNotes(), order.getQuantity());
            return new OrderInterface(node, orderController);
        } catch (IOException ignored) {

        }
        return null;
    }

}
