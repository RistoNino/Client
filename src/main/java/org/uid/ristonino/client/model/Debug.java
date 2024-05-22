package org.uid.ristonino.client.model;

import io.vertx.core.json.JsonObject;
import org.uid.ristonino.client.model.api.ApiHandler;
import org.uid.ristonino.client.model.types.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Debug {
    public final static boolean IS_ACTIVE = true;
    public final static double height = 800;
    public final static double width = 1200;

    public final static List<String> categoryList = new ArrayList<String>(List.of("Antipasti", "Primi", "Secondi", "Dolci", "Minchiata"));

    private final static List<String> ingPasta = new ArrayList<>(List.of("Pasta", "Pomodoro"));
    private final static List<String> ingAntipasto = new ArrayList<>(List.of("Mozzarella", "Prosciutto"));
    private final static List<String> ingSecondo = new ArrayList<>(List.of("Pane", "Pomodoro", "Olio", "Origano"));
    private final static List<String> ingDolci = new ArrayList<>(List.of("Mascarpone", "Caffè", "Cioccolato"));
    private final static List<String> ingMinchiata = new ArrayList<>(List.of("Ing1", "Ing2", "Ing3"));

    private final static Item item1 = new Item(0, "Mozzarelle e Prosciutto Cotto", "Antipasti", ingAntipasto, "Moz e pros", 6.00);
    private final static Item item2 = new Item(1, "Pasta al pomodoro", "Primi", ingPasta, "La pasta al sugo", 15.00);
    private final static Item item3 = new Item(2, "Pane abbrustolito", "Secondi", ingSecondo, "Pane e olio", 2.00);
    private final static Item item4 = new Item(3, "Tiramisù", "Dolci", ingDolci, "Tiramisù", 20.00);
    private final static Item item5 = new Item(4, "Minchiata del giorno", "Minchiata", ingMinchiata, "MINCHI", 100.00);

    // Antipasti
    private final static Item item6 = new Item(5, "Mozzarelle e Prosciutto Crudo", "Antipasti", ingAntipasto, "Moz e pros crudo", 7.00);
    private final static Item item7 = new Item(6, "Bruschette", "Antipasti", ingAntipasto, "Bruschette classiche", 5.00);
    private final static Item item8 = new Item(7, "Caprese", "Antipasti", ingAntipasto, "Caprese con pomodoro", 8.00);
    private final static Item item9 = new Item(8, "Focaccia", "Antipasti", ingAntipasto, "Focaccia semplice", 4.00);
    private final static Item item10 = new Item(9, "Salumi misti", "Antipasti", ingAntipasto, "Salumi misti", 10.00);

    // Primi
    private final static Item item11 = new Item(10, "Spaghetti alla Carbonara", "Primi", ingPasta, "Carbonara", 12.00);
    private final static Item item12 = new Item(11, "Lasagne al ragù", "Primi", ingPasta, "Lasagne", 14.00);
    private final static Item item13 = new Item(12, "Risotto alla milanese", "Primi", ingPasta, "Risotto", 16.00);
    private final static Item item14 = new Item(13, "Pasta al pesto", "Primi", ingPasta, "Pasta al pesto", 13.00);
    private final static Item item15 = new Item(14, "Ravioli di ricotta", "Primi", ingPasta, "Ravioli", 18.00);

    // Secondi
    private final static Item item16 = new Item(15, "Pollo alla griglia", "Secondi", ingSecondo, "Pollo grigliato", 20.00);
    private final static Item item17 = new Item(16, "Bistecca alla fiorentina", "Secondi", ingSecondo, "Bistecca", 30.00);
    private final static Item item18 = new Item(17, "Salsiccia e friarielli", "Secondi", ingSecondo, "Salsiccia e friarielli", 18.00);
    private final static Item item19 = new Item(18, "Pesce spada", "Secondi", ingSecondo, "Pesce spada alla griglia", 25.00);
    private final static Item item20 = new Item(19, "Frittura di calamari", "Secondi", ingSecondo, "Calamari fritti", 22.00);

    // Dolci
    private final static Item item21 = new Item(20, "Panna cotta", "Dolci", ingDolci, "Panna cotta", 7.00);
    private final static Item item22 = new Item(21, "Cannoli siciliani", "Dolci", ingDolci, "Cannoli", 9.00);
    private final static Item item23 = new Item(22, "Torta al cioccolato", "Dolci", ingDolci, "Torta cioccolato", 8.00);
    private final static Item item24 = new Item(23, "Gelato artigianale", "Dolci", ingDolci, "Gelato", 6.00);
    private final static Item item25 = new Item(24, "Cheesecake", "Dolci", ingDolci, "Cheesecake", 10.00);

    // Minchiata
    private final static Item item26 = new Item(25, "Minchiata di carne", "Minchiata", ingMinchiata, "MINCHI carne", 150.00);
    private final static Item item27 = new Item(26, "Minchiata di pesce", "Minchiata", ingMinchiata, "MINCHI pesce", 120.00);
    private final static Item item28 = new Item(27, "Minchiata vegetariana", "Minchiata", ingMinchiata, "MINCHI veg", 80.00);
    private final static Item item29 = new Item(28, "Minchiata mista", "Minchiata", ingMinchiata, "MINCHI mista", 200.00);
    private final static Item item30 = new Item(29, "Minchiata deluxe", "Minchiata", ingMinchiata, "MINCHI deluxe", 300.00);

    public final static List<Item> items = new ArrayList<>(List.of(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12, item13, item14, item15, item16, item17, item18, item19, item20, item21, item22, item23, item24, item25, item26, item27, item28, item29, item30));


    public static void print(String args) {
        if (IS_ACTIVE) {
            System.out.println(args);
        }
    }

    public static void sendOrder() {
        JsonObject json = new JsonObject();
        json.put("items", items.get(0));
        ApiHandler.getInstance().sendOrder(json);
    }
}
