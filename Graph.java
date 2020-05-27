package Graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import Heap.HeapGeneric;
public class Graph {
private class vertex {
	HashMap<String ,Integer> nbrs=new HashMap<>();
}
HashMap<String,vertex> vtces;
Graph() {
vtces=new HashMap<>();	
}
public boolean containsVertex(String key) {
	return vtces.containsKey(key);
}
public int numVertex() {
	return vtces.size();
}
public void addVertex(String vname) {
	vertex nbrs=new vertex();
	vtces.put(vname, nbrs);
}
public void removeVertex(String vname) {
	vertex a=vtces.get(vname);
	ArrayList<String> keys=new ArrayList<>(a.nbrs.keySet());
	for(String key:keys) {
		vertex nbrVtx=vtces.get(key);
		nbrVtx.nbrs.remove(vname);
	}
	vtces.remove(vname);
}
public void addedge(String vname1,String vname2,int cost) {
	 vertex  a=vtces.get(vname1);
	    vertex b=vtces.get(vname2);
	    if(a==null || b==null || a.nbrs.containsKey(vname2) ) {
	    	return ;
	    }
	    a.nbrs.put(vname2, cost);
	    b.nbrs.put(vname1, cost);
}
public int numedge() {
	int count=0;
	ArrayList<String> keys=new ArrayList<>(vtces.keySet());
	for(String key:keys) {
		vertex a=vtces.get(key);
		count =count+a.nbrs.size();
	}
	return count/2;
}
public boolean containsedge(String vname1,String vname2) {
    vertex  a=vtces.get(vname1);
    vertex b=vtces.get(vname2);
    if(a==null || b==null || !a.nbrs.containsKey(vname2) ) {
    	return false;
    }
    return true;
}
public void removeedge(String vname1,String vname2) {
	 vertex  a=vtces.get(vname1);
	    vertex b=vtces.get(vname2);
	    if(a==null || b==null || !a.nbrs.containsKey(vname2) ) {
	    	return ;
	    }
	    a.nbrs.remove(vname2);
	    b.nbrs.remove(vname1);
}
public void display() {
	ArrayList<String> keys=new ArrayList<>(vtces.keySet());
	for(String key:keys) {
		vertex a=vtces.get(key);
		System.out.println(key+"=>"+a.nbrs);
	}
}
public boolean hasPath(String vname1,String vname2,HashMap<String,Boolean> processed) {
	//direct edge
	processed.put(vname1, true);
	if(containsedge(vname1,vname2)) {
		return true;
	}
	vertex vtx=vtces.get(vname1);
	ArrayList<String >nbrs=new ArrayList<>(vtx.nbrs.keySet());
	for(String nbr:nbrs) {
		if(!processed.containsKey(nbr) && hasPath(nbr,vname2,processed)) {
			return true;
		}
	}
	return false;
}
public class pair{
	String vname;
	String psf;
	
}
public boolean bfs(String src,String dest) {
	HashMap<String,Boolean>processed=new HashMap<>();

	LinkedList<pair> queue=new LinkedList<>();
	pair vtx=new pair();
	vtx.vname=src;
	vtx.psf=src;
	queue.addLast(vtx);
 while(!queue.isEmpty()) {
	 pair rv=queue.removeFirst();
	 if(processed.containsKey(rv.vname)) {
		 continue;
	 }
	 processed.put(rv.vname, true);
	 if(containsedge(rv.vname,dest)) {
			System.out.println(rv.psf+dest);
			return true;
		}
	vertex v=vtces.get(rv.vname);
	ArrayList<String> keys=new ArrayList<>(v.nbrs.keySet());
	for(String key:keys) {
		if(!processed.containsKey(key)) {
		pair vtx1=new pair();
		vtx1.vname=key;
		vtx1.psf=rv.psf+key;		
		queue.addLast(vtx1);
	}
	}
	
 }
 return false;
}
public boolean dfs(String src,String dest) {
	HashMap<String,Boolean>processed=new HashMap<>();

	LinkedList<pair> stack=new LinkedList<>();
	pair vtx=new pair();
	vtx.vname=src;
	vtx.psf=src;
	stack.addFirst(vtx);
 while(!stack.isEmpty()) {
	 pair rv=stack.removeFirst();
	 if(processed.containsKey(rv.vname)) {
		 continue;
	 }
	 processed.put(rv.vname, true);
	 if(containsedge(rv.vname,dest)) {
			System.out.println(rv.psf+dest);
			return true;
		}
	vertex v=vtces.get(rv.vname);
	ArrayList<String> keys=new ArrayList<>(v.nbrs.keySet());
	for(String key:keys) {
		if(!processed.containsKey(key)) {
		pair vtx1=new pair();
		vtx1.vname=key;
		vtx1.psf=rv.psf+key;		
		stack.addFirst(vtx1);
	}
	}
	
 }
 return false;
}
public void bft(String src) {
	HashMap<String,Boolean>processed=new HashMap<>();

	LinkedList<pair> queue=new LinkedList<>();
	ArrayList<String> keys=new ArrayList<>(vtces.keySet());
	for(String key:keys) {
		 if(processed.containsKey(key)) {
			 continue;
		 }
	pair vtx=new pair();
	vtx.vname=src;
	vtx.psf=src;
	queue.addLast(vtx);
 while(!queue.isEmpty()) {
	 pair rv=queue.removeFirst();
	
	 if(processed.containsKey(rv.vname)) {
		 continue;
	 }
	 System.out.println(rv.vname+"  via  "+rv.psf);
	 processed.put(rv.vname, true);
	vertex v=vtces.get(rv.vname);
	ArrayList<String> nbrs=new ArrayList<>(v.nbrs.keySet());
	for(String nbr:nbrs) {
		if(!processed.containsKey(nbr)) {
		pair vtx1=new pair();
		vtx1.vname=nbr;
		vtx1.psf=rv.psf+nbr;		
		queue.addLast(vtx1);
	}
	}
	
 }
}
}
public boolean dft(String src) {
	HashMap<String,Boolean>processed=new HashMap<>();

	LinkedList<pair> stack=new LinkedList<>();
	ArrayList<String> keys=new ArrayList<>(vtces.keySet());
	for(String key:keys) {
		 if(processed.containsKey(key)) {
			 continue;
		 }
	pair vtx=new pair();
	vtx.vname=src;
	vtx.psf=src;
	stack.addFirst(vtx);
 while(!stack.isEmpty()) {
	 pair rv=stack.removeFirst();
	 if(processed.containsKey(rv.vname)) {
		 continue;
	 }
	 System.out.println(rv.vname+"  via  "+ rv.psf);
	 processed.put(rv.vname, true);
	
	vertex v=vtces.get(rv.vname);
	ArrayList<String> nbrs=new ArrayList<>(v.nbrs.keySet());
	for(String nbr:nbrs) {
		if(!processed.containsKey(key)) {
		pair vtx1=new pair();
		vtx1.vname=nbr;
		vtx1.psf=rv.psf+nbr;		
		stack.addFirst(vtx1);
	}
	}
	
 }
	}
 return false;
}
public boolean isCyclic(String src) {
	HashMap<String,Boolean>processed=new HashMap<>();

	LinkedList<pair> queue=new LinkedList<>();
	ArrayList<String> keys=new ArrayList<>(vtces.keySet());
	for(String key:keys) {
		 if(processed.containsKey(key)) {
			 continue;
		 }
	pair vtx=new pair();
	vtx.vname=src;
	vtx.psf=src;
	queue.addLast(vtx);
 while(!queue.isEmpty()) {
	 pair rv=queue.removeFirst();
	
	 if(processed.containsKey(rv.vname)) {
		return true;
	 }
	// System.out.println(rv.vname+"=>"+vtces.get(rv.vname).nbrs);
	 processed.put(rv.vname, true);
	vertex v=vtces.get(rv.vname);
	ArrayList<String> nbrs=new ArrayList<>(v.nbrs.keySet());
	for(String nbr:nbrs) {
		if(!processed.containsKey(nbr)) {
		pair vtx1=new pair();
		vtx1.vname=nbr;
		vtx1.psf=rv.psf+nbr;		
		queue.addLast(vtx1);
	}
	}
	
 }
	}
 return false;
}
public boolean isConnected(String src) {
	HashMap<String,Boolean>processed=new HashMap<>();
int flag=0;
	LinkedList<pair> queue=new LinkedList<>();
	pair vtx=new pair();
	vtx.vname=src;
	vtx.psf=src;
	queue.addLast(vtx);
 while(!queue.isEmpty()) {
	 pair rv=queue.removeFirst();
	
	 if(processed.containsKey(rv.vname)) {
		 continue;
	 }
	 flag++;
	// System.out.println(rv.vname+"=>"+vtces.get(rv.vname).nbrs);
	 processed.put(rv.vname, true);
	vertex v=vtces.get(rv.vname);
	ArrayList<String> keys=new ArrayList<>(v.nbrs.keySet());
	for(String key:keys) {
		if(!processed.containsKey(key)) {
		pair vtx1=new pair();
		vtx1.vname=key;
		vtx1.psf=rv.psf+key;		
		queue.addLast(vtx1);
	}
	}
	
 }
 if(flag>=2) {
	 return false;
 }else
	 return true;
}
public boolean isTree(String src) {
	return isConnected(src) && !isCyclic(src);
}
public ArrayList<ArrayList<String>> getCC(){
	return null;
	
}
private class primsPair implements Comparable<primsPair>{
	String vname;
	String acqvname;
	int cost;
	@Override
	public int compareTo(primsPair o) {
		return o.cost-this.cost;
	}
}
public Graph prims() {
	Graph mst=new Graph();
	HashMap<String ,primsPair> map=new HashMap<>();
	HeapGeneric<primsPair> heap=new HeapGeneric<>();
	for(String key:vtces.keySet()) {
		//add to mst
		primsPair np=new primsPair();
		np.vname=key;
		np.acqvname=null;
		np.cost=Integer.MAX_VALUE;
		heap.add(np);
		map.put(key, np);
	}
	//till the heap is not empty keep on removing the pair
	while(!heap.isEmpty()) {
//remove pair
		primsPair rp=heap.remove();
		map.remove(rp.vname);
		//add to mst
		if(rp.acqvname==null) {
			mst.addVertex(rp.vname);
		}else {
			mst.addVertex(rp.vname);
			mst.addedge(rp.vname, rp.acqvname, rp.cost);
		}
		//nbrs
		for(String nbr:vtces.get(rp.vname).nbrs.keySet()) {
		if(map.containsKey(nbr)) {
			int oc=map.get(nbr).cost;
			int nc=vtces.get(rp.vname).nbrs.get(nbr);
			if(nc<oc) {
				primsPair gp=map.get(nbr);
				gp.acqvname=rp.vname;
				gp.cost=nc;
				heap.updatePriority(gp);
			}
		}
		}
	}
	
	
	
	return mst;
}
}
