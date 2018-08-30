package hw1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class DGS {


    Map<Integer, Integer> goodsOwner; //goodsItemID, owner (bidderNodeID)
    Queue<Bidder> bidderQueue;

    public static Map<Integer, Bidder> LoadInput (String inputFile){
        Map<Integer, Bidder> bidders = new HashMap<>();  //bidderNodeID, Bidder object
        try{
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            int n = Integer.valueOf(br.readLine());
            for(int i = 0; i < n; i++){ //For each bidder
                Bidder b = new Bidder(i+1);
                String line = br.readLine();
                String[] arrLine = (line.split("\\s+"));
                for(int j = 0; j < n; j++){ //For each good/edge
                    b.itemValuations.put(j+1, Integer.valueOf(arrLine[j]));
                }
                bidders.put(i+1, b);
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

}