package org.example.dataObjects.model;

import javax.persistence.*;

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

    public ItemAuctionData(Long itemId, int count, Long buyOutMax, Long buyOutMin, Long buyOutAverage) {
        this.itemId = itemId;
        this.count = count;
        this.buyOutMax = buyOutMax;
        this.buyOutMin = buyOutMin;
        this.buyOutAverage = buyOutAverage;
    }
}
