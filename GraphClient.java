package Graph;

import java.math.BigInteger;
import java.util.HashSet;

public class GraphClient {

	public static void main(String[] args) {
		Graph g=new Graph();
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("E");
		g.addVertex("F");
		g.addVertex("G");
	    g.addedge("A", "B",2);
	    g.addedge("A", "D",6);
	    g.addedge("B", "C",3);
	    g.addedge("B", "A",2);
	    g.addedge("D", "A",6);
	    g.addedge("D", "C",1);
	    g.addedge("D", "E",8);
	    g.addedge("C", "B",3);
	    g.addedge("C", "D",1);
	    g.addedge("E", "D",8);
	    g.addedge("E", "F",5);
	    g.addedge("E", "G",7);
	    g.addedge("F", "E",5);
	    g.addedge("F", "G",9);
	    g.addedge("G", "F",9);
	    g.addedge("G", "E",7);
	    //g.display();
//	  // System.out.println( g.dfs("A", "F"));
//   System.out.println("==============================");
  g.bft("A");
//    System.out.println("========================");
//    g.dft("A");
	  //  g.prims().display();
	    
	}

}
