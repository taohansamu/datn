package riskgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.bayesserver.Link;
import com.bayesserver.Network;
import com.bayesserver.NetworkNodeCollection;
import com.bayesserver.Node;

public class RiskFrame extends JFrame implements ComponentListener {

	private int maxTime;
	private String message;
	private NetworkNodeCollection listNode;
	private Network net;
	private JScrollPane scrollPane;
	public RiskFrame(Network net) throws IOException {
		this.listNode = net.getNodes();
		this.net =net;
		this.setTitle("Bayes Frame");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(true);
		this.setLayout(null);
		//
		RiskPanel panel = new RiskPanel();
		panel.setBackground(new Color(150, 127, 143));
		createButton(panel);
		this.setVisible(false);
		
		panel.setPreferredSize(new Dimension(1000, 1600));
		scrollPane= new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 900, 900);
		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(900, 900));
		contentPane.add(scrollPane);
		this.setContentPane(contentPane);
		this.addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {
				int height =getHeight();
			    int width =getWidth();
			    scrollPane.setBounds(0, 0, width-15,height-50);
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
		this.pack();

//		this.setVisible(true);
	}

	private void createButton(RiskPanel panel) throws IOException {
		ArrayList<ButtonNode> listButton = panel.getListButton();
		int[][] arrLocation = { { 0, 0 }, { 3, 0 }, { 2, 3 }, { 4, 2 }, { 3, 2 }, { 2, 2 }, { 5, 1 }, { 6, 2 },
				{ 4, 3 }, { 4, 1 }, { 1, 5 }, { 3, 3 }, { 4, 5 }, { 5, 4 }, { 6, 0 }, { 3, 5 }, { 6, 4 }, { 5, 3 },
				{ 1, 1 }, {7,3}

		};
		for (int i = 0; i < arrLocation.length-1; i++) {
			listButton.add(new ButtonNode(listNode.get(i), arrLocation[i][0], arrLocation[i][1]));
		}	// for 19 node dau
		listButton.add(new ButtonNode(listNode.get(19), arrLocation[arrLocation.length-1][0], arrLocation[arrLocation.length-1][1], true,false) );
		
		for(int i=20; i<listNode.size();i++){
			int row = 8+(int)((i-20)/6);
			int col = (i-20)%6;
			listButton.add(new ButtonNode(listNode.get(i), row,col,false,true));
		}
		 int numLinks = net.getLinks().size();
		int[][] arrLink = new int[numLinks][2];
		for (int i=0; i<numLinks; i++) {
			Link link = net.getLinks().get(i);
			String parent = link.getFrom().getName(); // lấy node nguon trong link này
			String child = link.getTo().getName();        // lấy node đích trong link này
			arrLink[i][0]=convertName(parent, listNode.size());
			arrLink[i][1] = convertName(child, listNode.size());
//			System.out.println("("+arrLink[i][0]+","+arrLink[i][1]+")");
		}
		for (int i = 0; i < arrLink.length; i++) {

			listButton.get(arrLink[i][0] - 1).listChildButton.add(listButton.get(arrLink[i][1] - 1));
		}

		for (int i = 0; i < panel.getListButton().size(); i++) {
			panel.add(listButton.get(i));
		}

	}

	private int convertName(String nameNode, int size){
		if(nameNode.startsWith("node")){
			return Integer.parseInt(nameNode.substring(4));
		}
		if(nameNode.startsWith("task")){
			return Integer.parseInt(nameNode.substring(4))+21;
		}
		if(nameNode.startsWith("risk")){
			return 20;
		}
		return size;
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
