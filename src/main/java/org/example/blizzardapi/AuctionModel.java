package org.example.blizzardapi;

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

    String getAuctionContent() {
        return auctionContent;
    }

    public void setAuctionContent(String auctionContent) {
        this.auctionContent = auctionContent;
    }

    Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }
}
