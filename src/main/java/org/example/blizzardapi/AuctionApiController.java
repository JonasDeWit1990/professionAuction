package org.example.blizzardapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class AuctionApiController {

    private JsonNode callURL (String urlString) throws IOException  {
        URL url = new URL(urlString);

        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }

        // Buffer the result into a string
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        conn.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(sb.toString());

    }

    private AuctionModel ConvertAuctionResponseToPojo(JsonNode inputJson) {
        return new AuctionModel(inputJson.get("files").get(0).get("url").textValue(),
                                inputJson.get("files").get(0).get("lastModified").asLong());

    }

    @RequestMapping("/test")
    public JsonNode getCallAuctionAPI() throws IOException {
       //retrieve part 1 (auction content link + lastmodified)
        JsonNode ReturnJson =  callURL("https://eu.api.battle.net/wow/auction/data/medivh?locale=en_GB&apikey=4p5gufr82emm2mr9um26mddxvxyg677n");
        AuctionModel linkToProcess = new AuctionModel(ConvertAuctionResponseToPojo(ReturnJson));

        //retrieve Auction house content data
        JsonNode dataToProcess = callURL(linkToProcess.getAuctionContent());

        return dataToProcess;
    }

}
