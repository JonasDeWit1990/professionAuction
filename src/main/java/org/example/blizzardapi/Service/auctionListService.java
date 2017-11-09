package org.example.blizzardapi.Service;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.blizzardapi.Model.AuctionContentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



// takes in the auctionList and processes it.

@Service
public class auctionListService {

    public AuctionContentModel ProcessAuctionList(JsonNode auctionList) {
        AuctionContentModel auctionContent = new AuctionContentModel();


        return auctionContent;
    }
}
