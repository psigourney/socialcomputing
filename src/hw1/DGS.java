

/* Social Computing - Prof Garg
    UT Fall 2018
    Patrick Sigourney
    Robert Pate

    compile with:  "javac DGS.java"
    run with: "java DGS input.txt"
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class DGS {

    static class Bidder {
        int id;     //Bidder's node ID
        Map<Integer, Double> itemValuations = new HashMap<>(); //goodsItemId, bidder's valuation (edge weight)
        int ownsItem = 0; //Item ID this bidder owns.
        double purchPrice = 0.0; //Price paid for item

        Bidder(int idParam){
            id = idParam;
        }

        @Override
        public String toString(){
            return Integer.toString(id) + ": " + itemValuations;
        }

    }

    public static Map<Integer, Bidder> LoadInput (String inputFile){
        Map<Integer, Bidder> bidders = new HashMap<>();  //bidderNodeID, Bidder object
        try{
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            int n = Integer.valueOf(br.readLine());
            for(Integer i = 1; i <= n; i++){ //For each bidder
                Bidder b = new Bidder(i);
                String line = br.readLine();
                String[] arrLine = (line.split("\\s+"));
                for(Integer j = 1; j <= n; j++){ //For each good/edge
                    b.itemValuations.put(j, Double.valueOf(arrLine[j-1]));
                }
                bidders.put(i, b);
            }
        }catch (Exception e) {System.out.println("Exception!: " + e);}
        return bidders;
    }

    public static Map<Integer, Double> LoadGoodsPrice(Integer count){
        Map<Integer, Double> goodsPrice = new HashMap<>(); //goodsItemID, price
        for(int i = 1; i <= count; i++)
            goodsPrice.put(i, 0.0);

        return goodsPrice;
    }

    public static Map<Integer, Integer> LoadGoodsOwner(Integer count){
        Map<Integer, Integer> goodsOwner = new HashMap<>(); //goodsItemID, owner's BidderID
        for(int i = 1; i <= count; i++)
            goodsOwner.put(i, 0);

        return goodsOwner;
    }

    public static Queue<Bidder> InitBidderQueue(Map<Integer, Bidder> bidders){
        Queue<Bidder> q = new LinkedList<>();
        for(int i = 1; i <= bidders.size(); i++)
            q.add(bidders.get(i));

        return q;
    }

    public static void main (String[] args){
        if(args.length != 1){
            System.out.println("java DGS <inputfile.txt>");
            return;
        }

        Map<Integer, Bidder> bidders = DGS.LoadInput(args[0]);
        assert(bidders.size() == 3);

        Map<Integer, Double> goodsPrice = DGS.LoadGoodsPrice(bidders.size()); //goodsItemID, price
        assert(goodsPrice.size() == bidders.size());

        Map<Integer, Integer> goodsOwner = DGS.LoadGoodsOwner(bidders.size()); //goodsItemID, Owner's Bidder ID
        assert(goodsOwner.size() == bidders.size());

        Queue<Bidder> bidderQueue = DGS.InitBidderQueue(bidders);
        assert(bidderQueue.size() == bidders.size());

        final double deltaVal = 1.0/(bidders.size()+1);
        assert(deltaVal > 0.0);

        int loopCounter = 0;  //TODO: Remove loopCounter after testing
        while(!bidderQueue.isEmpty() && loopCounter < 100){
            loopCounter += 1;

            Bidder currBidder = bidderQueue.poll();
            //System.out.println("\ncurrBidder: " + currBidder.id);

            int maxItem = 0;
            double maxValue = 0.0;

            //Find the item with the highest valuation to the current bidder
            for(int x = 1; x <= currBidder.itemValuations.size(); x++){
                if(currBidder.itemValuations.get(x) - goodsPrice.get(x) > maxValue){
                    maxItem = x;
                    maxValue = currBidder.itemValuations.get(x) - goodsPrice.get(x);
                }
            }
            //System.out.println("maxItem: " + maxItem + " maxValue: " + maxValue);

            if(currBidder.itemValuations.get(maxItem) - goodsPrice.get(maxItem) >= 0){
                if(goodsOwner.get(maxItem) > 0){            //If item is already owned
                    Bidder currOwner = bidders.get(goodsOwner.get(maxItem));
                    //System.out.println("currentOwner " + currOwner.id + " is out.");
                    currOwner.ownsItem = 0;      //You own nothing, sir!...
                    currOwner.purchPrice = 0.0;  //But the price is right
                    bidderQueue.add(currOwner);  //Current owner of maxItem goes back into the queue
                }
                double currPrice = goodsPrice.get(maxItem);
                currBidder.ownsItem = maxItem;                       //Bidder owns the item.
                currBidder.purchPrice = currPrice;                   //Price bidder paid for item.
                goodsOwner.put(maxItem, currBidder.id);              //BidderID now owns the item
                goodsPrice.replace(maxItem, (currPrice + deltaVal));
                //System.out.println("Bidder " + currBidder.id + " bought item " + maxItem + " for $" + currPrice + "; new price: " + (currPrice+deltaVal));
            }
        }

        //System.out.println("\nLoopCounter: " + loopCounter);
        double totalWeight = 0.0;
        for(int x = 1; x <= bidders.size(); x++){
            int ownedItem = bidders.get(x).ownsItem;
            totalWeight += bidders.get(x).itemValuations.get(ownedItem);
        }

        System.out.println("Total matching weight: " + totalWeight);
        for(int x = 1; x <= bidders.size(); x++) {
            System.out.println("(" + x + "," + bidders.get(x).ownsItem + ")");
        }
    }
}
