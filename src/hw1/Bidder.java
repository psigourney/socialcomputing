
import java.util.HashMap;
import java.util.Map;

public class Bidder {
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
