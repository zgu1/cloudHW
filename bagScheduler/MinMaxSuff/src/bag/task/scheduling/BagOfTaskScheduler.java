package bag.task.scheduling;

import java.util.ArrayList;
import java.util.List;

public class BagOfTaskScheduler {
	
	private int[][] matrix = null;
	private int numberOfMachines = 0; 
	private int numberOfTasks = 0; 
	
	private List<Integer> assigendTask = new ArrayList<Integer>(); 
	
	public void setMatrix(int[][] newMatrix) {
		this.matrix = newMatrix;
		if(this.matrix != null && this.matrix[0] != null) {
			numberOfMachines = matrix[0].length;  //matrix[0].length is the number of columns in row 0, this is the number of machines
			numberOfTasks = matrix.length; //matrix.length is the number of rows in matrix, this is the number of tasks
		}
	}
	
	public  int[][] findMinForEachTask(){
		int[][] minCompletionTimesForEachTask = new int[numberOfTasks][2]; 
		
		for(int task = 0; task < numberOfTasks ; task ++) { 
			if(assigendTask.contains(task)) {  
				continue;
			}
			int minCompletionTime = matrix[task][0]; 
			int minCompletionTimeMachine = 0;								
			
			for(int machine =1 ; machine < numberOfMachines; machine ++) { 
				if(matrix[task][machine] == Integer.MAX_VALUE) { 
					continue;
				}
				else {
					if(matrix[task][machine] < minCompletionTime) {  
						minCompletionTime = matrix[task][machine];
						minCompletionTimeMachine = machine;  
					}
				}
			}
			
			minCompletionTimesForEachTask[task][0] = minCompletionTime; 				
			minCompletionTimesForEachTask[task][1] = minCompletionTimeMachine;	
		}
		
		return minCompletionTimesForEachTask;
	}
	
	
	public int[][] findMinForAllTasks(int[][] minCompletionTimesForEachTask){
		int[][] minCompletionTimesAndMachineForAllTasks = new int[1][3];
		int firstUnAssignedTask = 0;
		for(int i = 0; i < minCompletionTimesForEachTask.length; i++) {
			if(assigendTask.contains(i)) {
				continue;
			}
			else {
				firstUnAssignedTask = i;
				break;
			}
		}		
		int minTimeTask = firstUnAssignedTask;																												
		int minTime = minCompletionTimesForEachTask[firstUnAssignedTask][0]; 					
		int minTimeMachine = minCompletionTimesForEachTask[firstUnAssignedTask][1]; 		
		for(int i = firstUnAssignedTask + 1; i < minCompletionTimesForEachTask.length; i++) {
			if(assigendTask.contains(i)) {  
				continue;
			}
			if( minCompletionTimesForEachTask[i][0] < minTime ) {
				minTime = minCompletionTimesForEachTask[i][0] ; 				
				minTimeMachine  = minCompletionTimesForEachTask[i][1];	
				minTimeTask = i;																				
			}
		}
		minCompletionTimesAndMachineForAllTasks[0][0] = minTimeTask;
		minCompletionTimesAndMachineForAllTasks[0][1] = minTime;
		minCompletionTimesAndMachineForAllTasks[0][2] = minTimeMachine;
		
		return minCompletionTimesAndMachineForAllTasks;
	}
	
	
	
	public int[][] findMaxForAllTasks(int[][] minCompletionTimesForEachTask){
		int[][] maxCompletionTimesAndMachineForAllTasks = new int[1][3];
		int firstUnAssignedTask = 0;
		for(int i = 0; i < minCompletionTimesForEachTask.length; i++) {
			if(assigendTask.contains(i)) {
				continue;
			}
			else {
				firstUnAssignedTask = i;
				break;
			}
		}	
		
		
		int maxTimeTask = firstUnAssignedTask;																				
		int maxTime = minCompletionTimesForEachTask[firstUnAssignedTask][0]; 	 					
		int maxTimeMachine = minCompletionTimesForEachTask[firstUnAssignedTask][1]; 	
		for(int i = firstUnAssignedTask + 1; i < minCompletionTimesForEachTask.length; i++) {
			if(assigendTask.contains(i)) { 
				continue;
			}
			if( minCompletionTimesForEachTask[i][0] > maxTime ) {
				maxTime = minCompletionTimesForEachTask[i][0] ; 				
				maxTimeMachine  = minCompletionTimesForEachTask[i][1];	
				maxTimeTask = i;																				
			}
		}
		maxCompletionTimesAndMachineForAllTasks[0][0] = maxTimeTask;
		maxCompletionTimesAndMachineForAllTasks[0][1] = maxTime;
		maxCompletionTimesAndMachineForAllTasks[0][2] = maxTimeMachine;
		
		return maxCompletionTimesAndMachineForAllTasks;
	}
	
	public int[][] findSufferageForEachTask(){
		int[][] sufferageForEachTask = new int[numberOfTasks][3]; 
		 
		for(int task = 0; task < numberOfTasks ; task ++) { 
			if(assigendTask.contains(task)) {  
				continue;
			}
			int minTime = 0; 										
			int minTimeMachine = 0;						
			int secondMinTime = 0;							
			
		
			minTime= matrix[task][0]; 					
			minTimeMachine = 0;								
			for(int machine =1 ; machine < numberOfMachines; machine ++) {
				if(matrix[task][machine] == Integer.MAX_VALUE) {
					continue;  //if  -, ignore
				}
				else {
					if(matrix[task][machine] < minTime) {  
						minTime = matrix[task][machine];
						minTimeMachine = machine;  
					}
				}
			}
			
			for(int machine = 0; machine < numberOfMachines; machine ++) {
				if(matrix[task][machine] == Integer.MAX_VALUE) { 
					continue;  //if  -, ignore
				}
				else {
					if(matrix[task][machine] != minTime) {  
						secondMinTime = matrix[task][machine];
						break;
					}
				}
			}
		
			for(int machine = 0; machine < numberOfMachines; machine ++) {
				if(matrix[task][machine] == Integer.MAX_VALUE) { 
					continue;  //if  -, ignore
				}
				else {
					if(matrix[task][machine] != minTime && matrix[task][machine] < secondMinTime) { 
						secondMinTime = matrix[task][machine] ; 
					}
				}
			}
			
			sufferageForEachTask[task][0] =  secondMinTime -  minTime; 
			sufferageForEachTask[task][1] = minTimeMachine;			
			sufferageForEachTask[task][2] = minTime;
		} 
		
		
		
		return sufferageForEachTask;
	}
	
	
	public int[][] findMaxSufferageForAllTask(int[][] sufferageAndMachineForEachTask){
		int[][] maxSufferageAndMachineForAllTask = new int[1][4];
	
		int firstUnAssignedTask = 0;
		for(int i = 0; i < sufferageAndMachineForEachTask.length; i++) {
			if(assigendTask.contains(i)) {
				continue;
			}
			else {
				firstUnAssignedTask = i;
				break;
			}
		}
		
		int maxSuffurageTask = firstUnAssignedTask;		
		int maxSufferage = sufferageAndMachineForEachTask[firstUnAssignedTask][0];	
		int maxSufferageMachine = sufferageAndMachineForEachTask[firstUnAssignedTask][1]; 
		int completeTime = sufferageAndMachineForEachTask[firstUnAssignedTask][2];	
		
		for(int i = firstUnAssignedTask + 1; i < sufferageAndMachineForEachTask.length; i++) {
			if(assigendTask.contains(i)) {  
				continue;
			}
			if( sufferageAndMachineForEachTask[i][0] > maxSufferage ) {
				maxSufferage = sufferageAndMachineForEachTask[i][0] ; 				
				maxSufferageMachine  = sufferageAndMachineForEachTask[i][1];	
				maxSuffurageTask = i;																				
				completeTime = sufferageAndMachineForEachTask[i][2];				
			}
		}
		maxSufferageAndMachineForAllTask[0][0] = maxSuffurageTask;
		maxSufferageAndMachineForAllTask[0][1] = maxSufferage;
		maxSufferageAndMachineForAllTask[0][2] = maxSufferageMachine;
		maxSufferageAndMachineForAllTask[0][3] = completeTime;
		
		return maxSufferageAndMachineForAllTask;
	}
	
	public void updateMatrix(int task, int machine, int time){
		for(int i = 0; i < matrix.length; i++) {
		
			if(assigendTask.contains(i) || i == task) { 
				continue;
			}
			
			
			if(matrix[i][machine] == Integer.MAX_VALUE) { 
				continue;
			}
			else {
				matrix[i][machine] += time; 
			}
			
		}
	}
	
	public void printCurrentMatrix() {
		System.out.print("	    ");
		for(int i = 0; i < matrix[0].length; i++) {
			
			System.out.print( "M" + i + "      	");
		}
		for(int i = 0; i < matrix.length; i++) {
			if(assigendTask.contains(i)) {
				continue;
			}
			System.out.println();
			System.out.print("	T" + i + "  ");
			for(int j = 0; j < matrix[i].length; j++) {
				String s= null;
				if(matrix[i][j] == Integer.MAX_VALUE) {
					s = "-";
				}
				else {
					s = new Integer(matrix[i][j]).toString();
				}
				System.out.print(s + "		");
			}
		}
	}
	
	public void minMinSchedule() {
		int it = 1;
		while(assigendTask.size() < matrix.length) {
			System.out.println("----------------------------Iteration " + it + " ----------------------------");
			printCurrentMatrix();
			System.out.println();
			System.out.println("	Step1:For each task determine its minimum completion time over all machines");
			int[][] minCompletionTimesAndMachineForEachTask = this.findMinForEachTask();
		
			 for(int i = 0; i < minCompletionTimesAndMachineForEachTask.length; i++) {
				 if(assigendTask.contains(i)) {
						continue;
					}
				 System.out.println("	T"+ i + "-M" + minCompletionTimesAndMachineForEachTask[i][1] + " = " +minCompletionTimesAndMachineForEachTask[i][0] );
			 }
			 
			 System.out.println("	Step2:Over all tasks find the minimum completion time");
			 int[][] minCompletionTimesAndMachineForAllTasks = this.findMinForAllTasks(minCompletionTimesAndMachineForEachTask);
			 int assigendTaskId = minCompletionTimesAndMachineForAllTasks[0][0];
			 int assignedMachine = minCompletionTimesAndMachineForAllTasks[0][2];
			 int time = minCompletionTimesAndMachineForAllTasks[0][1];
			 System.out.println("	T"+assigendTaskId+ "-M" + assignedMachine + " = " + time );
			 
			 

			 System.out.println("	Step3:Assign the task to the machine that gives this completion time");
			 assigendTask.add(assigendTaskId); 
			 System.out.println("	Assign T" + assigendTaskId + " to M" + assignedMachine);
			 

			 updateMatrix(assigendTaskId, assignedMachine, time);

			it ++;
		}
		
	}
	
	public void maxMinSchedule() {
		int it = 1;
		while(assigendTask.size() < matrix.length) {
			System.out.println("----------------------------Iteration " + it + " ----------------------------");
			
			printCurrentMatrix();
			System.out.println();
			
			System.out.println("	Step1:For each task determine its minimum completion time over all machines");
			int[][] minCompletionTimesAndMachineForEachTask = this.findMinForEachTask();

			 for(int i = 0; i < minCompletionTimesAndMachineForEachTask.length; i++) {
				 if(assigendTask.contains(i)) {
						continue;
					}
				 System.out.println("	T"+ i + "-M" + minCompletionTimesAndMachineForEachTask[i][1] + " = " +minCompletionTimesAndMachineForEachTask[i][0] );
			 }
			 
			 System.out.println("	Step2:Over all tasks find the maximum completion time");
			 int[][] maxCompletionTimesAndMachineForAllTasks = this.findMaxForAllTasks(minCompletionTimesAndMachineForEachTask);
			 int assigendTaskId = maxCompletionTimesAndMachineForAllTasks[0][0];
			 int assignedMachine = maxCompletionTimesAndMachineForAllTasks[0][2];
			 int time = maxCompletionTimesAndMachineForAllTasks[0][1];
			 System.out.println("	T"+assigendTaskId+ "-M" + assignedMachine + " = " + time );
			 
			 
			 System.out.println("	Step3:Assign the task to the machine that gives this completion time");
			 assigendTask.add(assigendTaskId); 
			 System.out.println("	Assign T" + assigendTaskId + " to M" + assignedMachine);
			 
		
			 updateMatrix(assigendTaskId, assignedMachine, time);

			it ++;
		}
	}
	
	
	public void sufferageSchedule() {
		int it = 1;
		while(assigendTask.size() < matrix.length) { 
			System.out.println("----------------------------Iteration " + it + " ----------------------------");
			
			printCurrentMatrix();
			System.out.println();
			
			System.out.println("	Step1：For each task determine the difference between its minimum and second minimum completion time over all machines (sufferage)");
			int[][] sufferageAndMachineForEachTask = this.findSufferageForEachTask();
			 for(int i = 0; i < sufferageAndMachineForEachTask.length; i++) {
				 if(assigendTask.contains(i)) {
						continue;
					}
				 System.out.println("	T"+ i + " = " +sufferageAndMachineForEachTask[i][0] );
			 }
			 
			 System.out.println("	Step2：Over all tasks find the maximum sufferage");
			 int[][] maxSufferageAndMachineForAllTask = this.findMaxSufferageForAllTask(sufferageAndMachineForEachTask);
			 int assigendTaskId = maxSufferageAndMachineForAllTask[0][0];
			 int maxSufferage = maxSufferageAndMachineForAllTask[0][1];
			 int assignedMachine = maxSufferageAndMachineForAllTask[0][2];
			 int time = maxSufferageAndMachineForAllTask[0][3];
			 System.out.println("	T"+assigendTaskId+ "-M" + assignedMachine + " has sufferage: " + maxSufferage );
			 
			 
			 
			 System.out.println("	Step3:Assign the task to the machine that gives this sufferage");
			 assigendTask.add(assigendTaskId); 
			 System.out.println("	Assign T" + assigendTaskId + " to M" + assignedMachine);
			 
			 
			 updateMatrix(assigendTaskId, assignedMachine, time);

			it ++;
		}
		
	}
	
	
	public  static void  homeworkMatrixMin() {
		 int[][] matrix = {
					{13, 79, 23, 71, 60 , 27, 2},
					{31 , 13, 14, 94, 60, 61, 57},
					{17, 1, Integer.MAX_VALUE, 23, 36, 8, 86},
					{19, 28, 10, 4, 58, 73, 40},
					{94, 75, Integer.MAX_VALUE, 58, Integer.MAX_VALUE, 68, 46},
					{8, 24, 3, 32, 4, 94, 89},
					{10, 57, 13, 1, 92, 75, 29},
					{80, 17, 38, 40, 66, 25, 88}
			};
		 
		 BagOfTaskScheduler scheduler = new BagOfTaskScheduler();
		 scheduler.setMatrix(matrix);
		 scheduler.minMinSchedule();

	}
	
	public  static void  homeworkMatrixMax() {
		 int[][] matrix = {
					{13, 79, 23, 71, 60 , 27, 2},
					{31 , 13, 14, 94, 60, 61, 57},
					{17, 1, Integer.MAX_VALUE, 23, 36, 8, 86},
					{19, 28, 10, 4, 58, 73, 40},
					{94, 75, Integer.MAX_VALUE, 58, Integer.MAX_VALUE, 68, 46},
					{8, 24, 3, 32, 4, 94, 89},
					{10, 57, 13, 1, 92, 75, 29},
					{80, 17, 38, 40, 66, 25, 88}
			};
		 
		 BagOfTaskScheduler scheduler = new BagOfTaskScheduler();
		 scheduler.setMatrix(matrix);
		 scheduler.maxMinSchedule();
	}
	
	public  static void  homeworkMatrixSuff() {
		 int[][] matrix = {
					{13, 79, 23, 71, 60 , 27, 2},
					{31 , 13, 14, 94, 60, 61, 57},
					{17, 1, Integer.MAX_VALUE, 23, 36, 8, 86},
					{19, 28, 10, 4, 58, 73, 40},
					{94, 75, Integer.MAX_VALUE, 58, Integer.MAX_VALUE, 68, 46},
					{8, 24, 3, 32, 4, 94, 89},
					{10, 57, 13, 1, 92, 75, 29},
					{80, 17, 38, 40, 66, 25, 88}
			};
		 
		 BagOfTaskScheduler scheduler = new BagOfTaskScheduler();
		 scheduler.setMatrix(matrix);
		 scheduler.sufferageSchedule();
	}
	public static void main(String[] args) {
		homeworkMatrixMin();
		homeworkMatrixMax();
		homeworkMatrixSuff();
	}

}
