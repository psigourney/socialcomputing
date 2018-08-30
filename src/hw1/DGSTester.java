package hw1;


import org.junit.Test;

import java.util.Map;
import java.util.Queue;


public class DGSTester {

    @Test
    public void Tester(){


        Map<Integer, Bidder> bidders = DGS.LoadInput("./src/hw1/input.txt");
        assert(bidders.size() == 3);

        Map<Integer, Integer> goodsPrice = DGS.LoadGoodsPrice(bidders.size()); //goodsItemID, price
        assert(goodsPrice.size() == bidders.size());

        Map<Integer, Integer> goodsOwner = DGS.LoadGoodsOwner(bidders.size()); //goodsItemID, price
        assert(goodsOwner.size() == bidders.size());

        Queue<Bidder> bidderQueue = DGS.InitBidderQueue(bidders);
        assert(bidderQueue.size() == bidders.size());

        System.out.println("bidders: " + bidders);

    }
}
