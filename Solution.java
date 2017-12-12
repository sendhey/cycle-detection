import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Solution {

	public static void main(String[] args) {
		
		try{
			
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		String temp = br.readLine();
		String[] tempParts= temp.split(" ");
		
		int size = Integer.parseInt(tempParts[0]);
		Graph newGraph = new Graph(size+1);
		while ((temp = br.readLine())!= null){
			
			tempParts = temp.split(" ");
			int one = Integer.parseInt(tempParts[0]);
			int two = Integer.parseInt(tempParts[1]);
			if (two != one){
				int addOne = one;
				int addTwo = two;
				newGraph.insertEdge(addOne, addTwo);
			}
		}
		
		
		
		
		
		newGraph.iterate();
		br.close();
		}catch (IOException e){
			e.printStackTrace();
		}
		
		
	}
	static class Graph{
		public Vertex one;
		public Vertex two;
		public int size;
		public boolean[] vertexes;
		public Vertex[] vertexList;
		
		public Graph(int size){
			this.size = size;
			this.vertexes = new boolean[size++];
			this.vertexList = new Vertex[size++];
		}
		
		public void insertEdge(int one, int two){
			Vertex vertexA = null;
			Vertex vertexB = null;

			if(vertexes[one] == true){
				vertexA = vertexList[one];
			}
			
			if(vertexes[two] == true){
				vertexB = vertexList[two];
			}
			
			if(vertexes[one] == false){
				vertexes[one] = true;
				vertexA = new Vertex(one);
				vertexList[one] = vertexA;
				
			}
			
			if(vertexes[two] == false){
				vertexB = new Vertex(two);
				vertexList[two] = vertexB;
				vertexes[two] = true;
			}

			vertexA.successors.add(vertexB);
		}

		 public void iterate(){
			
			DepthFirstSearch(vertexList[1]);
			if(one == null){
				System.out.println("0");
				return;
			}
			else{
				ArrayList<Vertex> Iteration = new ArrayList<Vertex>();
				Vertex vertex = one;
				while(vertex != two){
					Iteration.add(vertex);
					vertex = vertex.parent;
				}
				Iteration.add(vertex);
				System.out.println("1");
				for (int i = Iteration.size()-1; i >= 0; i--){
					System.out.print(Iteration.get(i).val + " ");
				}
			}
		}
		
		public void DepthFirstSearch(Vertex vertex){
			vertex.color = "grey";
			for (int i = 0; i < vertex.successors.size(); i++){
				
				if (vertex.successors.get(i).color.equals("white")){
					vertex.successors.get(i).parent = vertex;
					DepthFirstSearch(vertex.successors.get(i));
				}
				if(vertex.successors.get(i).color.equals("grey")){
					one = vertex;
					two = vertex.successors.get(i);
				}
			}
			vertex.color = "black";
		}
	}
	
	static class Vertex{
		ArrayList<Vertex> successors = new ArrayList<Vertex>();
		public int val;
		public String color = "white";
		public Vertex parent;
		public Vertex(int val){
			this.val = val;
		}
	}
}