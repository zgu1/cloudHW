package dme.maekawa.votingset;


import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class VotingSet {
	private Set<Integer> set = new LinkedHashSet <Integer>(); 
	private Integer nodeId = null;
	
	
	
	public Set<Integer> getSet() {
		return set;
	}

	public void setSet(Set<Integer> set) {
		this.set = set;
	}

	
	public Integer getNodeId() {
		return nodeId;
	}

	
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	
	public void add(Integer e) {
		set.add(e);
	}
	
	
	public void addAll(List<Integer> numbers) {
		set.addAll(numbers);
	}
	
	
	 public Iterator<Integer> iterator(){
		 return set.iterator();
	 }
	 
	 public boolean constains(Integer e) {
		 return set.contains(e);
	 }

	@Override
	
	public String toString() {
		StringBuffer bf = new StringBuffer();
		bf.append("[ ");
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()) {
			bf.append(it.next().toString() + " ");
		}
		
		bf.append("]");
		bf.append("-> nodeId:" + nodeId);
		return bf.toString();
	}

	@Override
	
	public boolean equals(Object obj) {
		if(obj instanceof VotingSet) {
			VotingSet vs= (VotingSet)obj;
			return this.equals(vs);
		}
		else
			return false;
	}
	 
	
}
