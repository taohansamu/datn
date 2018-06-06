package model;

import java.util.ArrayList;

public class AlgorithmScheduling {
	
	private int[] effort;
	private int num_tasks;
	private int num_resources;
	private int[] ass;
	private int[][] prec;
	private int cap;
	private int[][] sl;
	private int[][] TaskID;
	private boolean reg;
	
	public AlgorithmScheduling(int[] effort, int num_resources, int[] ass, int[][] prec, int cap) {
		this.effort = effort;
		this.num_tasks = effort.length;
		this.num_resources = num_resources;
		this.ass = ass;
		this.prec = prec;
		this.cap = cap;
		this.reg = false;
		
		sl = new int[num_resources][num_tasks];
		TaskID = new int[num_resources][num_tasks];
		for (int i=0; i < num_resources; i++)
			for (int j=0; j < num_tasks; j++) {
				this.sl[i][j] = 0;
				this.TaskID[i][j] = 0;
			}
				
	}
	
	public void run(String schedstrategy) {
		ArrayList<Integer> rlist = new ArrayList<Integer>();
		ArrayList<Integer> slist = new ArrayList<Integer>();
		for (int j=0; j < num_tasks; j++) {
			rlist.clear();
			for (int row=0; row < num_tasks; row++) {
				int sum_col = 0;
				for (int col=0; col < num_tasks; col++) 
					sum_col = sum_col + prec[row][col];
				if (sum_col == 0) {
					if (!slist.contains(row))
						rlist.add(row);
				}
			}
			
			if (rlist.size() == 0) {
				reg = true;
				System.out.print("Infeasible problem! There is no schedulable item at step: " + j);
				System.exit(0);
			}
			
			int jobndx = 0;
			if (schedstrategy.compareTo("none") == 0) {
				jobndx = rlist.get(0);
			} else if (schedstrategy.compareTo("af") == 0) {
				int maxval = -1;
				for (int i=0; i < rlist.size(); i++) {
					if (ass[rlist.get(i)] > maxval) {
						maxval = ass[rlist.get(i)];
						jobndx = rlist.get(i);
					}
				}
			} else if (schedstrategy.compareTo("lpt") == 0) {
				int maxval = -1;
				for (int i=0; i < rlist.size(); i++) {
					if (effort[rlist.get(i)] > maxval) {
						maxval = effort[rlist.get(i)];
						jobndx = rlist.get(i);
					}
				}
			} else if (schedstrategy.compareTo("spt") == 0) {
				int minval = Integer.MAX_VALUE;
				for (int i=0; i < rlist.size(); i++) {
					if (effort[rlist.get(i)] < minval) {
						minval = effort[rlist.get(i)];
						jobndx = rlist.get(i);
					}
				}
			} else {
				System.out.println("Unknown strategy!");
				System.exit(0);
			}
			int minresou = Integer.MAX_VALUE, resoundx=-1;
			if (ass[jobndx] == 0) {
				for (int row=0; row < num_resources; row++) {
					int sum_col = 0;
					for (int col=0; col < num_tasks; col++)
						sum_col = sum_col + sl[row][col];
					if (sum_col < minresou) {
						minresou = sum_col;
						resoundx = row;
					}
				}
			} else {
				resoundx = ass[jobndx]-1;
				minresou = 0;
				for (int col=0; col < num_tasks; col++) {
					minresou = minresou + sl[resoundx][col];
				}
			}
			int posndx = 0;
			for (int col=0; col < num_tasks; col++) {
				if (sl[resoundx][col] == 0) {
					posndx = col;
					break;
				}
			}
			
			if (minresou + effort[jobndx] < cap) {
				sl[resoundx][posndx] = effort[jobndx]; 
				TaskID[resoundx][posndx] = jobndx+1;
			} else {
				reg = true;
				System.out.println("Not scheduled job: " + jobndx);
			}
			slist.add(jobndx);
			
			for (int row=0; row < num_tasks; row++)
				prec[row][jobndx] = 0;
		}
	}
	
	public int[][] getSchedule() {
		return sl;
	}
	
	public boolean isExistSchedule() {
		return reg;
	}
	
	public int[][] getMatrixTaskID() {
		return TaskID;
	}

}
