package org.example.dataObjects.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class ItemAuctionData {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(nullable=false)
    private Long itemId;

    @Column(nullable=false)
    private int count;

    @Column(nullable=false)
    private Long buyOutMax;

    @Column(nullable=false)
    private Long buyOutMin;

    @Column(nullable=false)
    private Long buyOutAverage;

    @Column(nullable=false)
    private Timestamp TimeStamp;

    protected ItemAuctionData(){}

    public ItemAuctionData(Long itemId, int count, Long buyOutMax, Long buyOutMin, Long buyOutAverage, Long lastModified) {
        this.itemId = itemId;
        this.count = count;
        this.buyOutMax = buyOutMax;
        this.buyOutMin = buyOutMin;
        this.buyOutAverage = buyOutAverage;
        this.TimeStamp = new Timestamp(lastModified);
    }
}
