package org.example.blizzardapi.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.blizzardapi.Model.AuctionContentModel;
import org.example.blizzardapi.Model.AuctionModel;
import org.example.blizzardapi.Model.DbAggregateItem;
import org.example.blizzardapi.Service.auctionListService;
import org.example.blizzardapi.Service.restCallService;
import org.example.dataObjects.model.ServerPolled;
import org.example.dataObjects.repo.ServerPolledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;


@RestController
public class AuctionApiController {
    @Autowired
    restCallService RestCallService;

    @Autowired
    auctionListService AuctionListService;

    @Autowired
    ServerPolledRepository repository;

    @RequestMapping("/test")
    public ArrayList<DbAggregateItem> getCallAuctionAPI() throws IOException {
       //retrieve part 1 (auction content link + last modified) and convert to class
        JsonNode ReturnJson =  RestCallService.callURL("https://eu.api.battle.net/wow/auction/data/ravencrest?locale=en_GB&apikey=4p5gufr82emm2mr9um26mddxvxyg677n");
        AuctionModel linkToProcess = new AuctionModel(ReturnJson);

        ServerPolled serverPolled = repository.findByServerName("ravencrest");

        //retrieve Auction house content data and convert to class
        JsonNode dataToProcess = RestCallService.callURL(linkToProcess.getAuctionContent());
        AuctionContentModel processed = new AuctionContentModel(AuctionListService.ProcessAuctionList(dataToProcess));

        //aggregate data
        ArrayList<DbAggregateItem> aggregateItems = AuctionListService.AggregateData(processed);

        return aggregateItems;
    }

}
