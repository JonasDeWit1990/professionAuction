package org.example.blizzardapi.Model;

public class DbAggregateItem {
    private Long ItemId;
    private int count;
    private Long buyOutMax;
    private Long buyOutMin;
    private Long buyOutAverage;

    public DbAggregateItem(Long itemId, Long buyOut) {
        ItemId = itemId;
        this.buyOutMax = buyOut;
        this.buyOutMin = buyOut;
        this.buyOutAverage = buyOut;
        this.count = 1;
    }

    public Long getItemId() {
        return ItemId;
    }

    public void setItemId(Long itemId) {
        ItemId = itemId;
    }

    public Long getBuyOutMax() {
        return buyOutMax;
    }

    public void setBuyOutMax(Long buyOutMax) {
        this.buyOutMax = buyOutMax;
    }

    public Long getBuyOutMin() {
        return buyOutMin;
    }

    public void setBuyOutMin(Long buyOutMin) {
        this.buyOutMin = buyOutMin;
    }

    public Long getBuyOutAverage() {
        return buyOutAverage;
    }

    public void setBuyOutAverage(Long buyOutAverage) {
        this.buyOutAverage = buyOutAverage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addEntry(Long buyOutPrice) {
        if(this.buyOutMin > buyOutPrice)
            this.buyOutMin = buyOutPrice;

        if(this.buyOutMax < buyOutPrice)
            this.buyOutMax = buyOutPrice;

        this.buyOutAverage = (this.buyOutAverage * this.count + buyOutPrice ) / (++this.count);

    }
}
