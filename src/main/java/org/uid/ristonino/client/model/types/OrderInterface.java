package org.uid.ristonino.client.model.types;

import javafx.scene.Node;
import org.uid.ristonino.client.controller.OrderController;

public class OrderInterface {
    private Node node;
    private OrderController controller;
    private Order order;
    public OrderInterface(Node node, OrderController orderController, Order order) {
        this.node = node;
        this.controller = orderController;
        this.order = order;

    }

    public Node getNode() {
        return node;
    }

    public OrderController getController() {
        return controller;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
