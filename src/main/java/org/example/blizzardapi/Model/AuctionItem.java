package org.example.blizzardapi.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuctionItem {
    private Long auc;
    private Long item;
    private Long bid;
    private Long buyout;
    private String timeLeft;

    public AuctionItem(AuctionItem auction) {
        this.auc = auction.getAuc();
        this.item = auction.getItem();
        this.bid = auction.getBid();
        this.buyout = auction.getBuyout();
        this.timeLeft = auction.getTimeLeft();
    }

    public AuctionItem(Long auc, Long item, Long bid, Long buyout, String timeLeft) {
        this.auc = auc;
        this.item = item;
        this.bid = bid;
        this.buyout = buyout;
        this.timeLeft = timeLeft;
    }

    public AuctionItem() {

    }

    public Long getAuc() {
        return auc;
    }

    public void setAuc(Long auc) {
        this.auc = auc;
    }

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Long getBuyout() {
        return buyout;
    }

    public void setBuyout(Long buyout) {
        this.buyout = buyout;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }
}