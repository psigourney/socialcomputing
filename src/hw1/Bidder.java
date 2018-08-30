package hw1;

import java.util.HashMap;
import java.util.Map;

public class Bidder {
    int id;     //NodeID
    Map<Integer, Integer> itemValuations = new HashMap<>(); //goodsItemId, bidder's valuation (edge weight)

    Bidder(int idParam){
        id = idParam;
    }

    @Override
    public String toString(){
        return Integer.toString(id) + ": " + itemValuations;
    }

}
