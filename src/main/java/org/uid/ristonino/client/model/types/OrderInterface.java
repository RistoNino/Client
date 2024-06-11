package org.uid.ristonino.client.model.types;

import javafx.scene.Node;
import org.uid.ristonino.client.controller.OrderController;

public record OrderInterface(Node node, OrderController orderController, Order order) {
}
