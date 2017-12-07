package org.example.blizzardapi.Service;

import org.example.blizzardapi.Model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.blizzardapi.Model.AuctionContentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


// takes in the auctionList and processes it.

@Service
public class auctionListService {

    public AuctionContentModel ProcessAuctionList(JsonNode auctionList) {
        AuctionContentModel auctionContent = new AuctionContentModel();
        ObjectMapper jsonObjectMapper = new ObjectMapper();

        //retrieve Realms
        JsonNode realms = auctionList.get("realms");
        if(realms.isArray()) {
            for (JsonNode childNode: realms) {
                try {
                    Realm realm = jsonObjectMapper.treeToValue(childNode, Realm.class);
                    auctionContent.addRealm(realm);
                }
                catch (Exception e){
                    System.out.print(e);
                }
            }
        }

        //retrieve Auctions
        JsonNode auctions = auctionList.get("auctions");
        if(auctions.isArray()) {
            int counter = 0;
            for (JsonNode childNode: auctions) {
                try {
                    counter++;
                    AuctionItem auctionItem = jsonObjectMapper.treeToValue(childNode, AuctionItem.class);
                    auctionContent.addAuction(auctionItem);
                }
                catch (Exception e){
                    System.out.print(e);
                }
            }
        }
        return auctionContent;
    }

    // process and aggregate data
    public ArrayList<DbAggregateItem> AggregateData(AuctionContentModel auctionList) {
        ArrayList<DbAggregateItem> aggregateItems = new ArrayList<>();

        //TODO: rework to java 8 stream
        for(AuctionItem auctionItem : auctionList.getAuctions()) {
            if (aggregateItems.size() == 0) {
                aggregateItems.add(new DbAggregateItem(auctionItem.getItem(), auctionItem.getBuyout()));
            }
            else {
                boolean itemFound = false;
                for(DbAggregateItem dbAggregateItem : aggregateItems) {
                    if(auctionItem.getItem().equals(dbAggregateItem.getItemId())) {
                        dbAggregateItem.addEntry(auctionItem.getBuyout());
                        itemFound = true;
                    }
                }
                if(!itemFound) {
                    aggregateItems.add(new DbAggregateItem(auctionItem.getItem(), auctionItem.getBuyout()));
                }
            }
        }

        return aggregateItems;
    }
}
