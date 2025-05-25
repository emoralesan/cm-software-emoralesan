package practica01;

public class BackstagePassUpdateStrategy implements UpdateStrategy {
    @Override
    public void update(Item item) {
        decrementSellIn(item);
        if (item.getSellIn() < 0) {
            item.setQuality(0);
            return;
        }
        increaseQuality(item);
        if (item.getSellIn() < 10) {
            increaseQuality(item);
        }
        if (item.getSellIn() < 5) {
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