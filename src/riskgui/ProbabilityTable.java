package riskgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.bayesserver.Network;
import com.bayesserver.Node;
import com.bayesserver.Table;
import com.bayesserver.TableIterator;

import model.BayesNet;

public class ProbabilityTable extends JFrame implements ComponentListener{
	private  JPanel panel;
	public ArrayList<ArrayList<JTextField>> listTextField;
	public ArrayList<ArrayList<JTextField>> listDataTextField;
	private Node node;
	public ProbabilityTable(Node node) throws IOException {
		this.node = node;
		this.setTitle("Probability "+node.getName());
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	
		this.listTextField = new ArrayList<ArrayList<JTextField>>();
		this.listDataTextField = new ArrayList<ArrayList<JTextField>>();
		
		
        this.setSize(640, 480);
        panel = new JPanel();
      
        this.add(panel);
        
        this.setVisible(false);
      
       panel.setBounds(0, 0, 600, 400);
      panel.setLayout(null);
      addTextField(panel);
      this.addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {
				int height =getHeight();
			    int width =getWidth();
			    panel.setBounds(0, 0, width-15,height-50);
			    for (int i = 0; i < listTextField.size(); i++) {
					ArrayList<JTextField> rowTextField = listTextField.get(i);
					int col = rowTextField.size();
					rowTextField.get(0).setBounds(0, i * 50,100, 50);
					
					
					for (int j = 1; j < rowTextField.size(); j++) {
						rowTextField.get(j).setBounds((j-1) * (panel.getWidth() -100)/ (col-1)+100, i * 50, (panel.getWidth()-100) / (col-1), 50);
						
					}
				}
			}

			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void addTextField(JPanel panel) throws IOException {
		String[] parentNodes = BayesNet.getParentNodes(node);
		for (int i = 0; i < parentNodes.length; i++) {
			ArrayList<JTextField> rowTextField = new ArrayList<JTextField>();
			rowTextField.add(new JTextField(parentNodes[i]));
			listTextField.add(rowTextField);
		}
		String[] values = getValue(node.getName());
		for (int i = 0; i < values.length; i++) {
			ArrayList<JTextField> rowTextField = new ArrayList<JTextField>();
			rowTextField.add(new JTextField(values[i]));
			listTextField.add(rowTextField);
			ArrayList<JTextField> rowDataTextField = new ArrayList<JTextField>();
			listDataTextField.add(rowDataTextField);
		}

		for (int i = 0; i < parentNodes.length; i++) {
			if (i == 0) {
				String[] valueParent = getValue(parentNodes[i]);
				for (int j = 0; j < valueParent.length; j++) {
					listTextField.get(i).add(new JTextField(valueParent[j]));
				}
			} else {
				for (int n = 0; n < listTextField.get(i - 1).size() - 1; n++) {
					String[] valueParent = getValue(parentNodes[i]);
					for (int j = 0; j < valueParent.length; j++) {
						listTextField.get(i).add(new JTextField(valueParent[j]));
					}
				}
			}
		}

		for (int i = 0; i < values.length; i++) {
			if (parentNodes.length >= 1) {
				for (int j = 0; j < listTextField.get(parentNodes.length - 1).size() - 1; j++) {
					listTextField.get(i + parentNodes.length).add(new JTextField());
					listDataTextField.get(i).add(listTextField.get(i + parentNodes.length).get(j+1));
				}
			} else {
				listTextField.get(i).add(new JTextField());
				listDataTextField.get(i).add(listTextField.get(i).get(1));
			}
		}
		
		for (int i = 0; i < listTextField.size(); i++) {
			ArrayList<JTextField> rowTextField = listTextField.get(i);
			int col = rowTextField.size();
			rowTextField.get(0).setBounds(0, i * 50,100, 50);
			
			panel.add(rowTextField.get(0));
			for (int j = 1; j < rowTextField.size(); j++) {
				rowTextField.get(j).setBounds((j-1) * (panel.getWidth() -100)/ (col-1)+100, i * 50, (panel.getWidth()-100) / (col-1), 50);
				panel.add(rowTextField.get(j));
			}
			
		}
		JButton btnSave = new JButton("SAVE");
		btnSave.setBounds((panel.getWidth()-100)/2, listTextField.size()*50+50, 100, 60);
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				double[][] dataFromTextField = new double[listDataTextField.size()][listDataTextField.get(0).size()];
				for(int i=0; i<dataFromTextField.length;i++){
					for(int j=0; j<dataFromTextField[0].length;j++){
						dataFromTextField[i][j] = Double.parseDouble(listDataTextField.get(i).get(j).getText());
//						System.out.print(" "+ dataFromTextField[i][j]);
					}
//					System.out.println();
				}
				int height = dataFromTextField.length;
				int width = dataFromTextField[0].length;
				double[] reshapeArr = new double[height*width];
				for (int i=0; i<height; i++) {
					for (int j=0; j<width; j++) {
						reshapeArr[i*width+j] = dataFromTextField[i][j];
					}
				}
				String name = node.getName();
				Network net = node.getNetwork();
				Node node1 = net.getNodes().get("node1");
				Node node2 = net.getNodes().get("node2");
				Node node3 = net.getNodes().get("node3");
				Node node4 = net.getNodes().get("node4");
				Node node5 = net.getNodes().get("node5");
				Node node6 = net.getNodes().get("node6");
				Node node7 = net.getNodes().get("node7");
				Node node8 = net.getNodes().get("node8");
				Node node9 = net.getNodes().get("node9");
				Node node10 = net.getNodes().get("node10");
				Node node11 = net.getNodes().get("node11");
				Node node12 = net.getNodes().get("node12");
				Node node13 = net.getNodes().get("node13");
				Node node14 = net.getNodes().get("node14");
				Node node15 = net.getNodes().get("node15");
				Node node16 = net.getNodes().get("node16");
				Node node17 = net.getNodes().get("node17");
				Node node18 = net.getNodes().get("node18");
				Node node19 = net.getNodes().get("node19");
				Node risk = net.getNodes().get("risk");

				Table table = node.newDistribution().getTable();
				if (name.compareTo("node1")==0) {
			        TableIterator iterator = new TableIterator(table, new Node[]{node});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node2")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node, node1});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node3")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node1,node9});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node4")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node3,node6,node9,node18});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node5")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node1,node6,node12});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node6")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node19});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node7")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node2,node11});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node8")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node3,node4,node12,node14});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node9")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node3,node12});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node10")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node2,node11});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node11")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node12")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node11});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node13")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node11});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node14")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node9,node13,node16});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node15")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node7,node19});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node16")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node17")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node12,node13,node16,node18});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node18")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("node19")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node1});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				} else if (name.compareTo("risk")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,node5,node7,node8,node10,node15,node17});
			        iterator.copyFrom(reshapeArr);
			        node.setDistribution(table);
				}
				int numTasks = net.getNodes().size() - 20;
				for (int i=1; i<numTasks-1;i++) {
					if (name.compareTo("task"+Integer.toString(i))==0) {
						Node previousNode = net.getNodes().get("task"+Integer.toString(i-1));
						TableIterator iterator = new TableIterator(table, new Node[]{node,previousNode, risk});
						iterator.copyFrom(reshapeArr);
						node.setDistribution(table);
					}
				}
				if (name.compareTo("task0")==0) {
					TableIterator iterator = new TableIterator(table, new Node[]{node,risk});
					iterator.copyFrom(reshapeArr);
					node.setDistribution(table);
				} else if (name.compareTo("lastTask")==0) {
					Node previousNode = net.getNodes().get("task"+Integer.toString(numTasks-2));
					TableIterator iterator = new TableIterator(table, new Node[]{node,previousNode,risk});
					iterator.copyFrom(reshapeArr);
					node.setDistribution(table);
				}
				setVisible(false);
			}
		});
		panel.add(btnSave);
		addDataFromBayesNet(listDataTextField);
	
	}

	private void addDataFromBayesNet(ArrayList<ArrayList<JTextField>> listDataText) throws IOException {
		double[][] distribution = BayesNet.getDistribution(node);
		if (node.getName().compareTo("node15")==0) {
		/*for (int i=0; i<distribution.length; i++) {
			for (int j=0; j<distribution[0].length; j++)
				System.out.print(distribution[i][j] + " ");
			System.out.println();
		}*/
		}
		for(int i=0; i<distribution.length;i++){
			for(int j=0; j<distribution[i].length;j++){
				listDataText.get(i).get(j).setText(distribution[i][j]+"");
			}
		}
		
	}

	public String[] getValue(String nameNode) {
		if (nameNode.equals("node7")) {
			return new String[] { "Very high", "high", "normal", "low", "very low" };
		} else if (nameNode.equals("node16")) {
			return new String[] { "very good", "good", "average", "poor", "very poor" };
		} else {
			return new String[] { "true", "false" };
		}
	}

	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
