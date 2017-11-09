package org.example.blizzardapi.Model;

import com.fasterxml.jackson.databind.JsonNode;

public class AuctionModel {
    private String auctionContent;
    private Long lastModified;

    public AuctionModel(String auctionContent, Long lastModified) {
        this.auctionContent = auctionContent;
        this.lastModified = lastModified;
    }

    public AuctionModel(AuctionModel auctionModel) {
        this.auctionContent = auctionModel.getAuctionContent();
        this.lastModified = auctionModel.getLastModified();
    }

    public String getAuctionContent() {
        return auctionContent;
    }

    // converts a specific rest call to pojo
    public AuctionModel(JsonNode inputJson) {
        this.auctionContent = inputJson.get("files").get(0).get("url").textValue();
        this.lastModified = inputJson.get("files").get(0).get("lastModified").asLong();

    }

    public void setAuctionContent(String auctionContent) {
        this.auctionContent = auctionContent;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }
}
