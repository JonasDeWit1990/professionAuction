package org.example.blizzardapi.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.blizzardapi.Model.*;
import org.example.blizzardapi.Service.auctionListService;
import org.example.blizzardapi.Service.restCallService;
import org.example.dataObjects.controller.BlizzardApiMapper;
import org.example.dataObjects.controller.ServerPolledService;
import org.example.dataObjects.model.ItemAuctionData;
import org.example.dataObjects.repo.ItemAuctionDataRepository;
import org.example.dataObjects.repo.ServerPolledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class AuctionApiController {
    private final restCallService restCallService;
    private final auctionListService auctionListService;
    private final ServerPolledService serverPollService;
    private final BlizzardApiMapper blizzMapper;
    private final ItemAuctionDataRepository itemRepository;

    @Autowired
    public AuctionApiController(restCallService RestCallService, auctionListService AuctionListService, ServerPolledService pollService,
                                BlizzardApiMapper blizzMapper, ItemAuctionDataRepository itemRepository) {
        this.restCallService = RestCallService;
        this.auctionListService = AuctionListService;
        this.serverPollService = pollService;
        this.blizzMapper = blizzMapper;
        this.itemRepository = itemRepository;

    }

    @RequestMapping("/updateserverdata/{servername}")
    public String getCallAuctionAPI(@PathVariable String servername) throws IOException {
       //retrieve part 1 (auction content link + last modified) and convert to class
        JsonNode ReturnJson =  restCallService.callURL("https://eu.api.battle.net/wow/auction/data/" + servername + "?locale=en_GB&apikey=4p5gufr82emm2mr9um26mddxvxyg677n");
        AuctionModel linkToProcess = new AuctionModel(ReturnJson);

        Long LastModified = serverPollService.GetModifiedForServer(servername);
        DateFormat df = new SimpleDateFormat("yyyy/MM/yy HH:mm:ss");


        if(LastModified == null || !LastModified.equals(linkToProcess.getLastModified())) {
            //retrieve Auction house content data and convert to class
            JsonNode dataToProcess = restCallService.callURL(linkToProcess.getAuctionContent());
            AuctionContentModel processed = new AuctionContentModel(auctionListService.ProcessAuctionList(dataToProcess));

            //aggregate data
            List<ItemAuctionData> aggregateItems = blizzMapper.fromBlizzardDbAggregateList(auctionListService.AggregateData(processed),
                    linkToProcess.getLastModified());

            //save aggregate data
            itemRepository.save(aggregateItems);


            //Save last modified to server
            serverPollService.SaveModifiedForServer(servername,linkToProcess.getLastModified());
            System.out.println(df.format(new Date()) + "Updating Data. modified: " +
                    linkToProcess.getLastModified() + " Timestamp: " + df.format(new Date(linkToProcess.getLastModified())));
            return "Data processed successfully";
        }
        else {
            System.out.println(df.format(new Date()) + "No update needed. modified: " +
                    linkToProcess.getLastModified() + " Timestamp: " + df.format(new Date(linkToProcess.getLastModified())));
            return "Data is up to date";
        }
    }
}
