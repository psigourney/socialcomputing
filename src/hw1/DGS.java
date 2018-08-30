package hw1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class DGS {

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
                    b.itemValuations.put(j, Integer.valueOf(arrLine[j-1]));
                }
                bidders.put(i, b);
            }
        }catch (Exception e) {System.out.println("Exception!: " + e);}
        return bidders;
    }

    public static Map<Integer, Integer> LoadGoodsPrice(Integer count){
        Map<Integer, Integer> goodsPrice = new HashMap<>(); //goodsItemID, price
        for(int i = 1; i <= count; i++)
            goodsPrice.put(i, 0);

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

}
