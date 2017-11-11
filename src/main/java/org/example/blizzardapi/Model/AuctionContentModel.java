package org.example.blizzardapi.Model;

import java.util.ArrayList;
import java.util.List;

public class AuctionContentModel {
    //list of Realms
    private List<Realm> realms;
    //list of auction items
    private List<AuctionItem> auctions;

    public AuctionContentModel (AuctionContentModel auctionContentModel) {
        this.realms = auctionContentModel.realms;
        this.auctions = auctionContentModel.auctions;
    }

    public AuctionContentModel() {
        this.realms = new ArrayList<Realm>();
        this.auctions = new ArrayList<AuctionItem>();

    }

    public String GetCount() {
        return "number of realms: " + realms.size() + "<br>" + " Number of auctions: " + auctions.size();
    }

    public void addRealm(Realm realm) {
        this.realms.add(realm);
    }

    public void addAuction(AuctionItem auction) {
        this.auctions.add(auction);
    }

}



