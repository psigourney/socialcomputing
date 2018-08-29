package hw1;

import java.util.Map;

public class DGS {
    class Node{
        Integer id;     //NodeID
        Integer label;  //label/value
        Map<Integer, Integer> edges; //Map<DestNodeID, edgeWeight>
    }

    Map<Integer, Node> sNodes;  //S-Nodes are connected to T-Nodes

}
