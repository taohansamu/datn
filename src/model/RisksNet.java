package model;

import com.bayesserver.*;
import com.bayesserver.inference.InconsistentEvidenceException;
import com.bayesserver.inference.Inference;
import com.bayesserver.inference.InferenceFactory;
import com.bayesserver.inference.QueryOptions;
import com.bayesserver.inference.QueryOutput;
import com.bayesserver.inference.RelevanceTreeInferenceFactory;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RisksNet {
	
	private Network riskNet;
	private ArrayList<Node> node;
	private ArrayList<State> nodeTrue;
	private ArrayList<State> nodeFalse;
	
	public double[] normalize(double[] dist, int row, int col) {
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
			for (int i=0; i<row; i++)
				newDist[i*col+j] = dist[i*col+j]/sum;
		}
		return newDist;
	}
	
	public RisksNet(String id) throws IOException {
		riskNet = new Network(id);
		node = new ArrayList<Node>();
		nodeTrue = new ArrayList<State>();
		nodeFalse = new ArrayList<State>();

        // add the nodes (variables)

        State node1True = new State("True");
        State node1False = new State("False");
        Node node1 = new Node("node1", node1True, node1False);
        riskNet.getNodes().add(node1);
        node.add(node1);
        //nodeTrue.add(node1True);
        //nodeFalse.add(node1False);

        State node2True = new State("True");
        State node2False = new State("False");
        Node node2 = new Node("node2", node2True, node2False);
        riskNet.getNodes().add(node2);
        node.add(node2);
        //nodeTrue.add(node2True);
        //nodeFalse.add(node2False);

        State node3True = new State("True");
        State node3False = new State("False");
        Node node3 = new Node("node3", node3True, node3False);
        riskNet.getNodes().add(node3);
        node.add(node3);
        //nodeTrue.add(node3True);
        //nodeFalse.add(node3False);

        State node4True = new State("True");
        State node4False = new State("False");
        Node node4 = new Node("node4", node4True, node4False);
        riskNet.getNodes().add(node4);
        node.add(node4);
        //nodeTrue.add(node4True);
        //nodeFalse.add(node4False);
        
        State node5True = new State("True");
        State node5False = new State("False");
        Node node5 = new Node("node5", node5True, node5False);
        riskNet.getNodes().add(node5);
        node.add(node5);
        //nodeTrue.add(node5True);
        //nodeFalse.add(node5False);
        
        State node6True = new State("True");
        State node6False = new State("False");
        Node node6 = new Node("node6", node6True, node6False);
        riskNet.getNodes().add(node6);
        node.add(node6);
        //nodeTrue.add(node6True);
        //nodeFalse.add(node6False);
        
        State node7_1 = new State("1");
        State node7_2 = new State("2");
        State node7_3 = new State("3");
        State node7_4 = new State("4");
        State node7_5 = new State("5");
        Node node7 = new Node("node7", node7_1, node7_2, node7_3, node7_4, node7_5);
        
        riskNet.getNodes().add(node7);
        node.add(node7);
        //nodeTrue.add(node7True);
        //nodeFalse.add(node7False);
        
        State node8True = new State("True");
        State node8False = new State("False");
        Node node8 = new Node("node8", node8True, node8False);
        riskNet.getNodes().add(node8);
        node.add(node8);
        //nodeTrue.add(node8True);
        //nodeFalse.add(node8False);
        
        State node9True = new State("no");
        State node9False = new State("yes");
        Node node9 = new Node("node9", node9True, node9False);
        riskNet.getNodes().add(node9);
        node.add(node9);
        //nodeTrue.add(node9True);
        //nodeFalse.add(node9False);
        
        State node10True = new State("True");
        State node10False = new State("False");
        Node node10 = new Node("node10", node10True, node10False);
        riskNet.getNodes().add(node10);
        node.add(node10);
        //nodeTrue.add(node10True);
        //nodeFalse.add(node10False);
        
        State node11True = new State("True");
        State node11False = new State("False");
        Node node11 = new Node("node11", node11True, node11False);
        riskNet.getNodes().add(node11);
        node.add(node11);
        //nodeTrue.add(node11True);
        //nodeFalse.add(node11False);
        
        State node12True = new State("True");
        State node12False = new State("False");
        Node node12 = new Node("node12", node12True, node12False);
        riskNet.getNodes().add(node12);
        node.add(node12);
        //nodeTrue.add(node12True);
        //nodeFalse.add(node12False);
        
        State node13True = new State("True");
        State node13False = new State("False");
        Node node13 = new Node("node13", node13True, node13False);
        riskNet.getNodes().add(node13);
        node.add(node13);
        //nodeTrue.add(node13True);
        //nodeFalse.add(node13False);
        
        State node14True = new State("True");
        State node14False = new State("False");
        Node node14 = new Node("node14", node14True, node14False);
        riskNet.getNodes().add(node14);
        node.add(node14);
        //nodeTrue.add(node14True);
        //nodeFalse.add(node14False);

        State node15True = new State("True");
        State node15False = new State("False");
        Node node15 = new Node("node15", node15True, node15False);
        riskNet.getNodes().add(node15);
        node.add(node15);
        //nodeTrue.add(node15True);
        //nodeFalse.add(node15False);
        
        State node16_1 = new State("1");
        State node16_2 = new State("2");
        State node16_3 = new State("3");
        State node16_4 = new State("4");
        State node16_5 = new State("5");
        Node node16 = new Node("node16", node16_1, node16_2, node16_3, node16_4, node16_5);
        riskNet.getNodes().add(node16);
        node.add(node16);
        //nodeTrue.add(node16True);
        //nodeFalse.add(node16False);
        
        State node17True = new State("True");
        State node17False = new State("False");
        Node node17 = new Node("node17", node17True, node17False);
        riskNet.getNodes().add(node17);
        node.add(node17);
        //nodeTrue.add(node17True);
        //nodeFalse.add(node17False);
        
        State node18True = new State("True");
        State node18False = new State("False");
        Node node18 = new Node("node18", node18True, node18False);
        riskNet.getNodes().add(node18);
        node.add(node18);
        //nodeTrue.add(node18True);
        //nodeFalse.add(node18False);
        
        State node19True = new State("no");
        State node19False = new State("yes");
        Node node19 = new Node("node19", node19True, node19False);
        riskNet.getNodes().add(node19);
        node.add(node19);
        //nodeTrue.add(node19True);
        //nodeFalse.add(node19False);
        
        State riskTrue = new State("True");
        State riskFalse = new State("False");
        Node risk = new Node("risk", riskTrue, riskFalse);
        riskNet.getNodes().add(risk);
        node.add(risk);
        nodeTrue.add(riskTrue);
        nodeFalse.add(riskFalse);

        // add some directed links

        riskNet.getLinks().add(new Link(node1, node2));
        riskNet.getLinks().add(new Link(node1, node5));
        riskNet.getLinks().add(new Link(node1, node19));
        riskNet.getLinks().add(new Link(node2, node7));
        riskNet.getLinks().add(new Link(node2, node10));
        riskNet.getLinks().add(new Link(node3, node4));
        riskNet.getLinks().add(new Link(node3, node8));
        riskNet.getLinks().add(new Link(node3, node9));
        riskNet.getLinks().add(new Link(node4, node8));
        riskNet.getLinks().add(new Link(node5, risk));
        riskNet.getLinks().add(new Link(node6, node5));
        riskNet.getLinks().add(new Link(node6, node4));
        riskNet.getLinks().add(new Link(node7, node15));
        riskNet.getLinks().add(new Link(node7, risk));
        riskNet.getLinks().add(new Link(node8, risk));
        riskNet.getLinks().add(new Link(node9, node4));
        riskNet.getLinks().add(new Link(node9, node14));
        riskNet.getLinks().add(new Link(node10, risk));
        riskNet.getLinks().add(new Link(node11, node12));
        riskNet.getLinks().add(new Link(node11, node10));
        riskNet.getLinks().add(new Link(node11, node7));
        riskNet.getLinks().add(new Link(node11, node13));
        riskNet.getLinks().add(new Link(node12, node5));
        riskNet.getLinks().add(new Link(node12, node8));
        riskNet.getLinks().add(new Link(node12, node9));
        riskNet.getLinks().add(new Link(node12, node17));
        riskNet.getLinks().add(new Link(node13, node14));
        riskNet.getLinks().add(new Link(node13, node17));
        riskNet.getLinks().add(new Link(node14, node8));
        riskNet.getLinks().add(new Link(node15, risk));
        riskNet.getLinks().add(new Link(node16, node14));
        riskNet.getLinks().add(new Link(node16, node17));
        riskNet.getLinks().add(new Link(node17, risk));
        riskNet.getLinks().add(new Link(node18, node4));
        riskNet.getLinks().add(new Link(node18, node17));
        riskNet.getLinks().add(new Link(node19, node3));
        riskNet.getLinks().add(new Link(node19, node6));
        riskNet.getLinks().add(new Link(node19, node15));
        
        CSVReader reader = new CSVReader(new FileReader("./distribution.csv"));
        String[] line;
        
        Table table1 = node1.newDistribution().getTable();
        TableIterator iterator1 = new TableIterator(table1, new Node[]{node1});
        line = reader.readNext();
        //System.out.println(line.length);
        double[] dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator1.copyFrom(dist);
        node1.setDistribution(table1);
        
        Table table2 = node2.newDistribution().getTable();
        TableIterator iterator2 = new TableIterator(table2, new Node[]{node2, node1});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator2.copyFrom(dist);
        node2.setDistribution(table2);
        
        Table table3 = node3.newDistribution().getTable();
        TableIterator iterator3 = new TableIterator(table3, new Node[]{node3, node19});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator3.copyFrom(dist);
        node3.setDistribution(table3);
        
        Table table4 = node4.newDistribution().getTable();
        TableIterator iterator4 = new TableIterator(table4, new Node[]{node4, node3, node6, node9, node18});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator4.copyFrom(dist);
        node4.setDistribution(table4);
        
        Table table5 = node5.newDistribution().getTable();
        TableIterator iterator5 = new TableIterator(table5, new Node[]{node5, node1, node6, node12});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator5.copyFrom(dist);
        node5.setDistribution(table5);
        
        Table table6 = node6.newDistribution().getTable();
        TableIterator iterator6 = new TableIterator(table6, new Node[]{node6, node19});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator6.copyFrom(dist);
        node6.setDistribution(table6);
        
        Table table7 = node7.newDistribution().getTable();
        TableIterator iterator7 = new TableIterator(table7, new Node[]{node7, node2, node11});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 5, dist.length/5);
        iterator7.copyFrom(dist);
        node7.setDistribution(table7);
        
        Table table8 = node8.newDistribution().getTable();
        TableIterator iterator8 = new TableIterator(table8, new Node[]{node8, node3, node4, node12, node14});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator8.copyFrom(dist);
        node8.setDistribution(table8);
        
        Table table9 = node9.newDistribution().getTable();
        TableIterator iterator9 = new TableIterator(table9, new Node[]{node9, node3, node12});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator9.copyFrom(dist);
        node9.setDistribution(table9);
        
        Table table10 = node10.newDistribution().getTable();
        TableIterator iterator10 = new TableIterator(table10, new Node[]{node10, node2, node11});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator10.copyFrom(dist);
        node10.setDistribution(table10);
        
        Table table11 = node11.newDistribution().getTable();
        TableIterator iterator11 = new TableIterator(table11, new Node[]{node11});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator11.copyFrom(dist);
        node11.setDistribution(table11);
        
        Table table12 = node12.newDistribution().getTable();
        TableIterator iterator12 = new TableIterator(table12, new Node[]{node12, node11});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator12.copyFrom(dist);
        node12.setDistribution(table12);
        
        Table table13 = node13.newDistribution().getTable();
        TableIterator iterator13 = new TableIterator(table13, new Node[]{node13, node11});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator13.copyFrom(dist);
        node13.setDistribution(table13);
        
        Table table14 = node14.newDistribution().getTable();
        TableIterator iterator14 = new TableIterator(table14, new Node[]{node14, node9, node13, node16});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator14.copyFrom(dist);
        node14.setDistribution(table14);
        
        Table table15 = node15.newDistribution().getTable();
        TableIterator iterator15 = new TableIterator(table15, new Node[]{node15, node7, node19});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator15.copyFrom(dist);
        node15.setDistribution(table15);
        
        Table table16 = node16.newDistribution().getTable();
        TableIterator iterator16 = new TableIterator(table16, new Node[]{node16});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 5, dist.length/5);
        iterator16.copyFrom(dist);
        node16.setDistribution(table16);
        
        Table table17 = node17.newDistribution().getTable();
        TableIterator iterator17 = new TableIterator(table17, new Node[]{node17, node12, node13, node16, node18});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator17.copyFrom(dist);
        node17.setDistribution(table17);
        
        Table table18 = node18.newDistribution().getTable();
        TableIterator iterator18 = new TableIterator(table18, new Node[]{node18});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator18.copyFrom(dist);
        node18.setDistribution(table18);
        
        Table table19 = node19.newDistribution().getTable();
        TableIterator iterator19 = new TableIterator(table19, new Node[]{node19, node1});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        iterator19.copyFrom(dist);
        node19.setDistribution(table19);
        
        Table tableRisk = risk.newDistribution().getTable();
        TableIterator iteratorRisk = new TableIterator(tableRisk, new Node[]{risk, node5, node7, node8, node10, node15, node17});
        line = reader.readNext();
        dist = new double[line.length];
        for (int i=0; i < line.length; i++) {
        	dist[i] = Double.parseDouble(line[i]);
        }
        dist = normalize(dist, 2, dist.length/2);
        
        iteratorRisk.copyFrom(dist);
        risk.setDistribution(tableRisk);
	}
	
	public Network getRiskNet() {
		
		return riskNet;
	}
	public 	 ArrayList<Node> getNode(){
		return node;
	};
}
