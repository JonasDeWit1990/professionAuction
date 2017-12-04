package org.example.blizzardapi.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.blizzardapi.Model.*;
import org.example.blizzardapi.Service.auctionListService;
import org.example.blizzardapi.Service.restCallService;
import org.example.dataObjects.controller.ServerPolledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class AuctionApiController {
    private final restCallService restCallService;
    private final auctionListService auctionListService;
    private final ServerPolledService serverPollService;

    @Autowired
    public AuctionApiController(restCallService RestCallService, auctionListService AuctionListService, ServerPolledService pollService) {
        this.restCallService = RestCallService;
        this.auctionListService = AuctionListService;
        this.serverPollService = pollService;
    }

    @RequestMapping("/test")
    public String getCallAuctionAPI() throws IOException {
        String serverName = "ravencrest";
       //retrieve part 1 (auction content link + last modified) and convert to class
        JsonNode ReturnJson =  restCallService.callURL("https://eu.api.battle.net/wow/auction/data/ravencrest?locale=en_GB&apikey=4p5gufr82emm2mr9um26mddxvxyg677n");
        AuctionModel linkToProcess = new AuctionModel(ReturnJson);

        Long LastModified = serverPollService.GetModifiedForServer(serverName);

        if(LastModified == null || !LastModified.equals(linkToProcess.getLastModified())) {
            //retrieve Auction house content data and convert to class
            JsonNode dataToProcess = restCallService.callURL(linkToProcess.getAuctionContent());
            AuctionContentModel processed = new AuctionContentModel(auctionListService.ProcessAuctionList(dataToProcess));

            //aggregate data
            ArrayList<DbAggregateItem> aggregateItems = auctionListService.AggregateData(processed);

            //Save last modified to server
            serverPollService.SaveModifiedForServer(serverName,linkToProcess.getLastModified());
            return "Data processed successfully";
        }
        else
            return "Data is up to date";




    }

}
