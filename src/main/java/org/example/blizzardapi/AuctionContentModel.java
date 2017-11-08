package org.example.blizzardapi;

import java.util.List;

public class AuctionContentModel {
    //list of realms
    private List<realm> realms;
    //list of auctionitems
    private List<auction> auctions;

}

class realm {
    private String name;
    private String slug;

    public realm(realm Realm) {
        this.name = Realm.getName();
        this.slug = Realm.getSlug();
    }

    public realm(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}

class auction {
    private Long auc;
    private Long item;
    private Long bid;
    private Long buyout;
    private String timeLeft;

    public auction(auction Auction) {
        this.auc = Auction.getAuc();
        this.item = Auction.getItem();
        this.bid = Auction.getBid();
        this.buyout = Auction.getBuyout();
        this.timeLeft = Auction.getTimeLeft();
    }

    public auction(Long auc, Long item, Long bid, Long buyout, String timeLeft) {
        this.auc = auc;
        this.item = item;
        this.bid = bid;
        this.buyout = buyout;
        this.timeLeft = timeLeft;
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