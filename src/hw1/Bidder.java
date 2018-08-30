package hw1;

import java.util.Map;

public class Bidder {
    Integer id;     //NodeID
    Map<Integer, Integer> itemValuations; //goodsItemId, bidder's valuation (edge weight)

    Bidder(Integer idParam){
        id = idParam;
    }

    @Override
    public String toString(){
        return Integer.toString(id) + ": " + itemValuations;
    }
}
