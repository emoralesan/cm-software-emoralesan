package practica01;

public class NormalItemUpdateStrategy implements UpdateStrategy {
    @Override
    public void update(Item item) {
        decrementSellIn(item);
        degradeQuality(item);
        if (item.getSellIn() < 0) {
            degradeQuality(item);
        }
    }

    private void decrementSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private void degradeQuality(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }
}