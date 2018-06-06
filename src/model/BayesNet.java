package model;

import com.bayesserver.*;
import com.bayesserver.inference.*;

import au.com.bytecode.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BayesNet {
	private int[][] schedule;
	private int[][] matrixTaskID;
	public ArrayList<Network> resourceNet;
	private Resources[] resource;
	private int numResources;
	private int totalTasks;
	
	public BayesNet(Resources[] resource, int numResources, Tasks[] task, int numTasks, int cap) {
		this.numResources = numResources;
		this.resource = resource;
		this.totalTasks = numTasks;
		resourceNet = new ArrayList<Network>();
		matrixTaskID = new int[numResources][numTasks];
		
		int[] effort = new int[numTasks];
		int[] ass = new int[numTasks];
		int[][] prec = new int[numTasks][numTasks];
		
		for (int i=0; i<numTasks; i++) {
			effort[i] = task[i].time;
			ass[i] = task[i].preAss;
			for (int j=0; j<numTasks; j++) {
				if (j != (task[i].prec-1)) prec[task[i].ID-1][j] = 0;
				else prec[task[i].ID-1][j] = 1;
			}
			
		}
		AlgorithmScheduling algorithm = new AlgorithmScheduling(effort, numResources, ass, prec, cap);
		algorithm.run("af");
		schedule = algorithm.getSchedule();
		int[][] id = algorithm.getMatrixTaskID();
		for (int i=0; i<numResources; i++)
			for (int j=0; j<totalTasks; j++) {
				if (id[i][j] > 0) matrixTaskID[i][j] = task[id[i][j]-1].ID;
				else matrixTaskID[i][j] = id[i][j];
			}
				
		/*for (int i=0; i<numResources; i++) {
			for (int j=0; j <numTasks; j++)
				System.out.print(matrixTaskID[i][j] + ", ");
			System.out.println();
		}*/
	}
	
	public void construct() throws IOException {
		for (int i=0; i<numResources; i++) {
			int numTasks = 0;
			for (int j=0; j<schedule[i].length; j++) if (schedule[i][j] > 0) numTasks++;
			RisksNet risknet = new RisksNet(Integer.toString(i));
			Network net = risknet.getRiskNet();
//			System.out.println(net.getNodes().size());
//			System.out.println(net.getLinks().size());
			
			State task0True = new State("True");
			State task0False = new State("False");
			Node task0 = new Node("task0", task0True, task0False);
			net.getNodes().add(task0);
			Node riskNode = net.getNodes().get("risk");
			net.getLinks().add(new Link(riskNode, task0));
			
			Table table = task0.newDistribution().getTable();
	        TableIterator iterator = new TableIterator(table, new Node[]{task0, riskNode});
	        iterator.copyFrom(new double[]{0.7, 0.95, 0.3, 0.05});
	        task0.setDistribution(table);
			
			Node previousTask = task0;
			for (int j=1; j<numTasks; j++) {
				Node taskj;
				if (j == numTasks-1) 
					taskj = new Node("lastTask", new State("True"), new State("False"));
				else
					taskj = new Node("task".concat(Integer.toString(j)), new State("True"), new State("False"));
				net.getNodes().add(taskj);
				net.getLinks().add(new Link(riskNode, taskj));
				net.getLinks().add(new Link(previousTask, taskj));
				table = taskj.newDistribution().getTable();
		        iterator = new TableIterator(table, new Node[]{taskj, previousTask, riskNode});
		        iterator.copyFrom(new double[]{0.7, 0.95, 0.1, 0.3, 0.3, 0.05, 0.9, 0.7});
		        taskj.setDistribution(table);
				previousTask = taskj;
			}
			resourceNet.add(net);
//			System.out.println("CONTRU");
//			System.out.println(resourceNet.size());
		}
	}
	
	public double[][] infer() throws InconsistentEvidenceException {
		double[][] result = new double[numResources][totalTasks];
		for (int i=0; i<numResources; i++)
			for (int j=0; j<totalTasks; j++)
				result[i][j] = 0;
		
		for (int i=0; i<numResources; i++) {
			int numTasks = 0;
			for (int j=0; j<schedule[i].length; j++) if (schedule[i][j] > 0) numTasks++;
			
			Network neti = resourceNet.get(i);
			InferenceFactory factory = new RelevanceTreeInferenceFactory();
	        Inference inference = factory.createInferenceEngine(neti);
	        QueryOptions queryOptions = factory.createQueryOptions();
	        QueryOutput queryOutput = factory.createQueryOutput();
	        
	        StateCollection stateNode19 = neti.getNodes().get("node19").getVariables().get(0).getStates();
	        StateCollection stateNode16 = neti.getNodes().get("node16").getVariables().get(0).getStates();
	        StateCollection stateNode7 = neti.getNodes().get("node7").getVariables().get(0).getStates();
	        StateCollection stateNode9 = neti.getNodes().get("node9").getVariables().get(0).getStates();
	        
	        State valueNode19 = stateNode19.get(resource[i].agileExperience);
	        State valueNode16 = stateNode16.get(Integer.toString(resource[i].agileLevel));
	        State valueNode7 = stateNode7.get(Integer.toString(resource[i].skillLevel));
	        State valueNode9 = stateNode9.get(resource[i].dailyMeeting);
	        
	        inference.getEvidence().setState(valueNode19);
	        inference.getEvidence().setState(valueNode16);
	        inference.getEvidence().setState(valueNode7);
	        inference.getEvidence().setState(valueNode9);
	        
	        for (int j=0; j<numTasks-1; j++) {
	        	String nameTask = "task"+Integer.toString(j);
	        	Table queryTaskj = new Table(neti.getNodes().get(nameTask));
	        	inference.getQueryDistributions().add(queryTaskj);
	        	inference.query(queryOptions, queryOutput);
	        	State stateTrue = neti.getNodes().get(nameTask).getVariables().get(0).getStates().get("True");
	        	result[i][j] = queryTaskj.get(stateTrue);
//	        	System.out.print(result[i][j] + ", ");
	        }
	        
	        Table queryLastTask = new Table(neti.getNodes().get("lastTask"));
	        State stateTrue = neti.getNodes().get("lastTask").getVariables().get(0).getStates().get("True");
	        inference.getQueryDistributions().add(queryLastTask);
	        inference.query(queryOptions, queryOutput);
	        result[i][numTasks-1] = queryLastTask.get(stateTrue);
//	        System.out.println(result[i][numTasks-1]);
		}
		return result;
	}
	
	public int[][] getSchedule() {
		return schedule;
	}
	
	public int[][] getMatrixTaskID() {
		return matrixTaskID;
	}
	
	public int getNumResources() {
		return numResources;
	}
	
	public int getNumTotalTasks() {
		return totalTasks;
	}

	public static String[] getParentNodes(Node node) {
		NodeLinkCollection collection = node.getLinksIn();
		int size = collection.size();
		String[] pNode = new String[size];
		
		String prename = node.getName().substring(0,4);
		if (prename.compareTo("task")==0 || prename.compareTo("last")==0) {
			if (collection.get(0).getFrom().getName().compareTo("risk") == 0) {
				if (size > 1) {
					pNode[0] = collection.get(1).getFrom().getName();
					pNode[1] = "risk";
				} else pNode[0] = "risk";
			} else {
				pNode[0] = collection.get(0).getFrom().getName();
				pNode[1] = "risk";
			}
		} else {
			int[] arr = new int[size];
			for (int i=0; i<size; i++) {
				arr[i] = Integer.parseInt(collection.get(i).getFrom().getName().substring(4));
			}
			Arrays.sort(arr);
			for (int i=0; i<size; i++) {
				pNode[i] = "node" + Integer.toString(arr[i]);
			}
		}
		return pNode;
	}
	
	public static double[] normalize(double[] dist, int row, int col) {
		int len = dist.length;
		double[] newDist = new double[500];
		if (len != (row*col)) {
			System.out.println("Error in normalize function!");
			return null;
		}
		double sum;
		for (int j=0; j<col; j++) {
			sum = 0;
			for (int i=0; i<row; i++)
				sum = sum + dist[i*col+j];
			//System.out.println("sum: " + sum);
			for (int i=0; i<row; i++)
				newDist[i*col+j] = dist[i*col+j]/sum;
		}
		return newDist;
	}
	
	public static double[][] getDistribution(Node node) throws IOException {
		int numState = node.getVariables().get(0).getStates().size();
		double[] table = new double[1000];
		int size = 0;
		if (node.getName().substring(0,4).compareTo("node")==0) {
			int index = Integer.parseInt(node.getName().substring(4));
			CSVReader reader = new CSVReader(new FileReader("./distribution.csv"));
			String[] line = null;
        
			for (int i=0; i<index; i++) line = reader.readNext();
			size = line.length;
			table = new double[line.length];
			for (int i=0; i < line.length; i++) {
				table[i] = Double.parseDouble(line[i]);
			}
			table = normalize(table, numState, table.length/numState);
		} else {
			if (node.getName().compareTo("task0")==0) {
				size = 4;
				table = new double[]{0.7, 0.95, 0.3, 0.05};
			} else if (node.getName().compareTo("risk")==0){
				CSVReader reader = new CSVReader(new FileReader("./distribution.csv"));
				String[] line = null;
	        
				for (int i=0; i<20; i++) line = reader.readNext();
				size = line.length;
				table = new double[line.length];
				for (int i=0; i < line.length; i++) {
					table[i] = Double.parseDouble(line[i]);
				}
				table = normalize(table, numState, table.length/numState);
			} else {
				size = 8;
				table = new double[]{0.7, 0.95, 0.1, 0.3, 0.3, 0.05, 0.9, 0.7};
			}
				
		}
		int length = size / numState;
		double[][] dist = new double[numState][length];
		for (int i=0; i<numState; i++) 
			for (int j=0; j<length; j++)
				dist[i][j] = table[i*length+j];
		return dist;
	}
}
