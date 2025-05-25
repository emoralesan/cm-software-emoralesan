package practica01;

import java.util.HashMap;
import java.util.Map;

public class GildedRose {
    private Item[] items;

    private Map<String, UpdateStrategy> strategies;

    public GildedRose(Item[] items) {
        this.items = items;
        strategies = new HashMap<>();
        // Mapear nombre de ítem a su estrategia específica
        strategies.put("Aged Brie", new AgedBrieUpdateStrategy());
        strategies.put("Backstage passes to a TAFKAL80ETC concert", new BackstagePassUpdateStrategy());
        strategies.put("Sulfuras, Hand of Ragnaros", new SulfurasUpdateStrategy());
    }

    public void updateQuality() {
        for (Item item : items) {
            UpdateStrategy strategy = strategies.getOrDefault(item.getName(), new NormalItemUpdateStrategy());
            strategy.update(item);
        }
    }
}