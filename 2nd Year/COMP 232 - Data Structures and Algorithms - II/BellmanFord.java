import java.util.*;
public class BellmanFord {
/**
	@author : Sezgi Sensöz
*/
	public static int INFINITE = Integer.MAX_VALUE;
	
	class Edge{
		int source;
		int destination;
		int weight;
		
		public Edge(int s,int d, int w){
			this.source = s;
			this.destination = d;
			this.weight = w;
		}
	}
	
	public static void bellmanFord(Vector<Edge> edges, int nodes, int source){
		//We need to initialize first
		int[] distance = new int[nodes];
		Arrays.fill(distance, INFINITE);
		distance[source] = 0;
		
		for (int i = 0; i<nodes; i++){
			//relaxiation for every edge
			for (int j=0; j<edges.size();j++){
				if(distance[edges.get(j).source] == INFINITE)
					continue;
				int changedDistance = distance[edges.get(j).source] + edges.get(j).weight;
				if (changedDistance < distance[edges.get(j).destination])
					distance[edges.get(j).destination] = changedDistance;
			}
		}	
			//checking cycles
		for(int k =0; k<edges.size();k++){
			if (distance[edges.get(k).source] != INFINITE && distance[edges.get(k).destination] > distance[edges.get(k).source] + edges.get(k).weight){
				System.out.println("Negative Edge have found");
				return;
			}
		}
			//Distance from source to all nodes.
		for (int i = 0; i <distance.length; i++){
			if (distance[i] == INFINITE)
				System.out.println("There is no path from " + source + "and" + i);
			else
				System.out.println("The shortest path is  from" + source + "and" + i + "is " + distance[i]);
		}
	}
}
	

