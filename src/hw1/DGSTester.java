package hw1;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class DGSTester {

    @Test
    public static void Tester(){
        Map<Integer, Bidder> bidders = new HashMap<>();
        bidders = DGS.LoadInput("input.txt");
        assert(bidders.size() == 3);

        Map<Integer, Integer> goodsPrice = new HashMap<>(); //goodsItemID, price
        goodsPrice = DGS.LoadGoodsPrice(bidders.size());
        assert(goodsPrice.size() == bidders.size());

        Map<Integer, Integer> goodsOwner = new HashMap<>(); //goodsItemID, price
        goodsOwner = DGS.LoadGoodsOwner(bidders.size());
        assert(goodsOwner.size() == bidders.size());





    }
}
