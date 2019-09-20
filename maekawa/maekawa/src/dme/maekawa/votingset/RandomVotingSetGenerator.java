package dme.maekawa.votingset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RandomVotingSetGenerator {
	
	public static List<VotingSet> createVotingSetList(int n, int k){
		List<VotingSet> votingSets = new ArrayList<VotingSet>();
		
		
		List<Integer> p = RandomVotingSetGenerator.createRandomNumbers(n);
	
		for(int i = 0;i < p.size(); i++) {
			System.out.print(p.get(i) + " ");
		}
		System.out.println();
		
		
		
		SlideWindow w = new SlideWindow(n,k);
		
	
		for(int i = 0; i < n; i++) {
			List<Integer> numsInWindow = w.numbersInWindow();
			VotingSet vs = new VotingSet();
			for(int j = 0; j < numsInWindow.size(); j++) {
					
				int index = numsInWindow.get(j);
				int nodeId = p.get(index);
				if(j == 0) {
			
					vs.setNodeId(nodeId);
				}
				vs.add(nodeId);
			}
			
			votingSets.add(vs);
			w.move();
		}
	
		
		return votingSets;
	}
	
	
	
	
	public static List<Integer> createRandomNumbers(int n, int k){
		List<Integer> list = new ArrayList<Integer>();
		while(list.size() < k) {
			Integer  randomNum = 1 + (int)(Math.random() * (n)); //generate a random number in [1, n]
			if(!list.contains(randomNum)) {  //if list doesn't contains randomNum, add to the list
				list.add(randomNum);
			}
		}
		
		return list;
	}
	
	
	public static List<Integer> createRandomNumbers(int n){
		return RandomVotingSetGenerator.createRandomNumbers(n, n);
	}
	
	
	
	public static void main(String[] args) {

		
		List<VotingSet> list = createVotingSetList(10,4);
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
}
