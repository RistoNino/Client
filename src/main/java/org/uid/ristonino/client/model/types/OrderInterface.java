package org.uid.ristonino.client.model.types;

import javafx.scene.Node;
import org.uid.ristonino.client.controller.OrderController;

public class OrderInterface {
    private Node node;
    private OrderController orderController;

    public OrderInterface(Node node, OrderController orderController) {
        this.node = node;
        this.orderController = orderController;
    }

    public OrderController getOrderController() {
        return orderController;
    }
    public Node getNode() {
        return node;
    }
}
