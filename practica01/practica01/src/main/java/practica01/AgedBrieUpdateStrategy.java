package practica01;

public class AgedBrieUpdateStrategy implements UpdateStrategy {
    @Override
    public void update(Item item) {
        decrementSellIn(item);
        increaseQuality(item);
        if (item.getSellIn() < 0) {
            increaseQuality(item);
        }
    }

    private void decrementSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private void increaseQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }
}