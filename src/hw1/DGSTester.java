package hw1;


import org.junit.Test;

import java.util.Map;
import java.util.Queue;


public class DGSTester {

    @Test
    public void Tester(){

        Map<Integer, Bidder> bidders = DGS.LoadInput("./src/hw1/input.txt");
        assert(bidders.size() == 3);

        Map<Integer, Double> goodsPrice = DGS.LoadGoodsPrice(bidders.size()); //goodsItemID, price
        assert(goodsPrice.size() == bidders.size());

        Map<Integer, Integer> goodsOwner = DGS.LoadGoodsOwner(bidders.size()); //goodsItemID, Owner's Bidder ID
        assert(goodsOwner.size() == bidders.size());

        Map<Integer, Double> winningPrices = DGS.LoadWinningPrices(bidders.size()); //goodsItemID, winning price

        Queue<Bidder> bidderQueue = DGS.InitBidderQueue(bidders);
        assert(bidderQueue.size() == bidders.size());

        double deltaVal = 1.0/(bidders.size()+1);
        System.out.println("deltaVal: " + deltaVal);

        int loopCounter = 0;

        while(!bidderQueue.isEmpty() && loopCounter < 100){
            loopCounter += 1;

            Bidder currBidder = bidderQueue.poll();
            System.out.println("\ncurrBidder: " + currBidder.id);

            int maxItem = 0;
            double maxValue = 0;
            //Find the item with the highest valuation to the current bidder
            for(int x = 1; x <= currBidder.itemValuations.size(); x++){
                if(currBidder.itemValuations.get(x) - goodsPrice.get(x) > maxValue){
                    maxItem = x;
                    maxValue = currBidder.itemValuations.get(x) - goodsPrice.get(x);
                }
            }
            System.out.println("maxItem: " + maxItem + " maxValue: " + maxValue);

            if(currBidder.itemValuations.get(maxItem) - goodsPrice.get(maxItem) >= 0){
                if(goodsOwner.get(maxItem) > 0){            //If item is already owned
                    Bidder currOwner = bidders.get(goodsOwner.get(maxItem));
                    System.out.println("currentOwner " + currOwner.id + " is out.");
                    currOwner.ownsItem = 0;      //You own nothing, sir!
                    bidderQueue.add(currOwner);  //Current owner of maxItem goes back into the queue
                }
                currBidder.ownsItem = maxItem;                       //Bidder owns the item.
                goodsOwner.put(maxItem, currBidder.id);              //BidderID now owns the item
                double currPrice = goodsPrice.get(maxItem);
                winningPrices.replace(maxItem, currPrice);
                goodsPrice.replace(maxItem, (currPrice + deltaVal));
                System.out.println("Bidder " + currBidder.id + " bought item " + maxItem + " for $" + currPrice + "; new price: " + (currPrice+deltaVal));
            }
        }

        System.out.println("LoopCounter: " + loopCounter);
        double totalWeight = 0.0;
        for(int x = 1; x <= bidders.size(); x++){
            totalWeight += winningPrices.get(x);
        }

        System.out.println("Total matching weight: " + totalWeight);
        for(int x = 1; x <= bidders.size(); x++) {
            System.out.println("(" + x + "," + bidders.get(x).ownsItem + ")");
        }
    }
}
