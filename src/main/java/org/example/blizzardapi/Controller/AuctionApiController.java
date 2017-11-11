package org.example.blizzardapi.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.blizzardapi.Model.AuctionContentModel;
import org.example.blizzardapi.Model.AuctionModel;
import org.example.blizzardapi.Service.auctionListService;
import org.example.blizzardapi.Service.restCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;



@RestController
public class AuctionApiController {
    @Autowired
    restCallService RestCallService;

    @Autowired
    auctionListService AuctionListService;

    @RequestMapping("/test")
    public String getCallAuctionAPI() throws IOException {
       //retrieve part 1 (auction content link + lastmodified)
        JsonNode ReturnJson =  RestCallService.callURL("https://eu.api.battle.net/wow/auction/data/ravencrest?locale=en_GB&apikey=4p5gufr82emm2mr9um26mddxvxyg677n");
        AuctionModel linkToProcess = new AuctionModel(ReturnJson);

        //retrieve Auction house content data
        JsonNode dataToProcess = RestCallService.callURL(linkToProcess.getAuctionContent());
        AuctionContentModel processed = new AuctionContentModel(AuctionListService.ProcessAuctionList(dataToProcess));


        return processed.GetCount();
    }

}
