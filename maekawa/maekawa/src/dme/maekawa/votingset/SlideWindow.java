package dme.maekawa.votingset;

import java.util.ArrayList;
import java.util.List;


public class SlideWindow {
	private int n; 		
	private int k; 		
	private int step = 1; 	
	private int base; 	
	
	
	public SlideWindow(int n, int k ) {
		this.n = n;
		this.k = k;
		this.base = 0;  
	}
	
	
	public void move() {
		
		base = (base + step) % n ; 
	}
	
	public List<Integer> numbersInWindow (){
		List<Integer>  nums = new ArrayList<Integer>();
		for(int i = 0; i < k; i++) {
			Integer num = (base + i) % n; 
			nums.add(num);
		}
		
		return nums;
	}
	

}
