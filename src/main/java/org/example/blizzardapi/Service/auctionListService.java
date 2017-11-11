package org.example.blizzardapi.Service;

import org.example.blizzardapi.Model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.blizzardapi.Model.AuctionContentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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
            System.out.println("TotalNumber: " + counter);
        }
        return auctionContent;
    }
}
