package org.uid.ristonino.client.model;

import org.uid.ristonino.client.model.types.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Debug {
    public final static boolean IS_ACTIVE = true;
    public final static double height = 800;
    public final static double width = 1200;

    public final static List<String> categoryList = new ArrayList<String>(List.of("Antipasti", "Primi", "Secondi", "Dolci", "Minchiata"));

    private final static List<String> ingPasta = new ArrayList<String>(List.of("Pasta", "Pomodoro"));
    private final static List<String> ingAntipasto = new ArrayList<String>(List.of("Mozzarella", "Prosciutto"));
    private final static List<String> ingSecondo = new ArrayList<String>(List.of("Pane", "Pomodoro", "Olio", "Origano"));
    private final static List<String> ingDolci = new ArrayList<>(List.of("Mascarpone", "Caffè", "Cioccolato"));
    private final static List<String> ingMinchiata = new ArrayList<>(List.of("Ing1", "Ing2", "Ing3"));
    private final static Item item1 = new Item(0, "Mozzarelle e Prosciutto Cotto", "Antipasti", ingAntipasto, "Moz e pros", 6.00);
    private final static Item item2 = new Item(1, "Pasta al pomodoro", "Primi", ingPasta, "La pasta al sugo", 15.00);
    private final static Item item3 = new Item(2, "Pane abbrustolito", "Secondi", ingSecondo, "Pane e olio", 2.00);
    private final static Item item4 = new Item(3, "Tiramisù", "Dolci", ingDolci, "Tiramisù", 20.00);
    private final static Item item5 = new Item(4, "Minchiata del giorno", "Minchiata", ingMinchiata, "MINCHI", 100.00);



    public final static List<Item> items = new ArrayList<Item>(List.of(item1,item2, item3, item4, item5));

    public static void print(String[] args) {
        if (IS_ACTIVE) {
            System.out.println(Arrays.toString(args));
        }
    }
}
