package org.uid.ristonino.client.model.types;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.uid.ristonino.client.controller.OrderController;

public class OrderInterface {
    private Node node = new VBox();
    private final OrderController orderController;
    private final Order order;

    public OrderInterface(Node node, OrderController orderController, Order order) {
        this.node = node;
        this.orderController = orderController;
        this.order = order;
    }

    public OrderController getOrderController() {
        return orderController;
    }
    public Node getNode() {
        return node;
    }
    public Order getOrder() {
        return order;
    }
}
