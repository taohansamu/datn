package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.bayesserver.Network;

public class Panel extends JPanel {
	private int maxTime;
	private int time;
	private Color cl;
	private Network network;
	private ArrayList<ButtonResource> lisBtnRs;
	private ArrayList<ButtonTask> lisBtnTk;
	public Panel(int maxTime, Network network) {
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.cl = new Color((int)(Math.random() * 0x1000000));
		this.maxTime=maxTime;
		this.network = network;
		this.setLayout(null);
		this.lisBtnRs = new ArrayList<ButtonResource>();
		this.lisBtnTk = new ArrayList<ButtonTask>();
	
	}
	public void createButton(int[] arrSL, int[] arrID, int x1, int y1, int x2, int y2, double[] arrProb, int nameResource) throws IOException{
		
		 this.time=(int)( this.getSize().width/(this.maxTime+2));
		 int start =(int)((this.getSize().width- this.time*this.maxTime)/2);
		 int currentTime =0;
		 for(int i=0; i<arrSL.length;i++){
			 if(arrSL[i] !=0){
			
				 ButtonTask btn = new ButtonTask("T"+arrID[i], arrID[i], arrSL[i], arrProb[i]);
				 btn.setForeground(Color.BLACK);
				 btn.setBackground(cl);
				 
				 btn.setBounds(start +(currentTime) * this.time, y2 / 4, arrSL[i] * this.time, y2 / 2);
				 currentTime+=arrSL[i];
				this.add(btn);
				lisBtnTk.add(btn);
				ButtonResource btnResource = new ButtonResource("R"+nameResource, nameResource, arrSL[i], network);
				btnResource.setForeground(Color.BLACK);
				btnResource.setBackground(cl);
				btnResource.setBounds(0, y2 / 4, start*3/4, y2 / 2);
				lisBtnRs.add(btnResource);
				this.add(btnResource);
			 }
		 }
	}
	
	public void reloadData(double[] arrProb){
		
		for(int i=0; i<lisBtnRs.size();i++){
			lisBtnTk.get(i).reloadData(arrProb[i]);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    int height = getSize().height;
		 int width = getSize().width;
		    this.time=(int)( this.getSize().width/(maxTime+2));

	    // draw horizontal rule at bottom (left to right)
	    for (int x=0; x < width; x++) {
	        if (x % this.time == 0) {
	        	g.drawLine(x, height / 15 * 14, x, height);
	        }
	    }
	}
}
