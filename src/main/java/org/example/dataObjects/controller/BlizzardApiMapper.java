package org.example.dataObjects.controller;

import org.example.blizzardapi.Model.DbAggregateItem;
import org.example.dataObjects.model.ItemAuctionData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlizzardApiMapper {
    public ItemAuctionData FromBlizzardDbAggregate(DbAggregateItem item) {
        return new ItemAuctionData(item.getItemId(), item.getCount(), item.getBuyOutMax(),
                item.getBuyOutMin(), item.getBuyOutAverage());
    }

    public ArrayList<ItemAuctionData> fromBlizzardDbAggregateList(ArrayList<DbAggregateItem> itemList) {
        ArrayList<ItemAuctionData> auctionList = new ArrayList<>();
        for(DbAggregateItem item: itemList ) {
            auctionList.add(FromBlizzardDbAggregate(item));
        }

        return auctionList;
    }
}
