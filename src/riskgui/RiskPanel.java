package riskgui;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RiskPanel extends JPanel  {
	public ArrayList<ButtonNode> listButton;
	public RiskPanel(){
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		listButton=new ArrayList<ButtonNode>();
		
	
		this.setLayout(null);
		Timer timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				revalidate();
				repaint();
			}
		});
		timer.start();
	}
	public ArrayList<ButtonNode> getListButton(){
		return listButton;
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
	
		g2d.setStroke(new BasicStroke(1F));
		g2d.setColor(Color.BLACK);
		
		for(int i=0; i<listButton.size();i++){
			for(int j=0; j<listButton.get(i).listChildButton.size();j++){
				int x1 = listButton.get(i).getCenterX();
				int y1 = listButton.get(i).getCenterY();
				int x2 = listButton.get(i).listChildButton.get(j).getCenterX();
				int y2 = listButton.get(i).listChildButton.get(j).getCenterY();
				int[] xy1 = findGiaoDiem(x1, y1, x2, y2);
				int [] xy2= findGiaoDiem(x2, y2, x1, y1);
				g2d.setColor(Color.BLACK);
				g2d.drawLine(xy1[0], xy1[1], xy2[0], xy2[1]);
				g2d.setColor(Color.BLUE);
				g2d.drawOval(xy2[0], xy2[1],5,5);
				g2d.fillOval(xy2[0], xy2[1],5,5);
			}
		}
		
		
	}
	public int[] findGiaoDiem(int x1, int y1, int x2, int y2){
		if(Math.abs(y1-y2)> Math.abs(x1-x2)){
			if(y1<y2){
				for(int i=0; i<y2-y1;i++){
					int y0=y1+i;
					int x0=(((y2-y1)*x1+(x1-x2)*y1)-y0*(x1-x2))/(y2-y1);
					if((x0-x1)*(x0-x1)/(50*50)+(y0-y1)*(y0-y1)/(20*20)>1){
						y0=y0-1;
						x0=(((y2-y1)*x1+(x1-x2)*y1)-y0*(x1-x2))/(y2-y1);
						return new int[]{x0,y0};
					}
				}
			} else {
				for(int i=0; i<y1-y2;i++){
					int y0=y1-i;
					int x0=(((y2-y1)*x1+(x1-x2)*y1)-y0*(x1-x2))/(y2-y1);
					if((x0-x1)*(x0-x1)/(50*50)+(y0-y1)*(y0-y1)/(20*20)>1){
						y0=y0+1;
						x0=(((y2-y1)*x1+(x1-x2)*y1)-y0*(x1-x2))/(y2-y1);
						return new int[]{x0,y0};
					}
				}
			}
		} else {
			if(x1<x2){
				for(int i=0; i<x2-x1;i++){
					int x0=x1+i;
					int y0=((y2-y1)*x1+(x1-x2)*y1-x0*(y2-y1))/(x1-x2);
					if((x0-x1)*(x0-x1)/(45*45)+(y0-y1)*(y0-y1)/(20*20)>1){
						
						return new int[]{x0,y0};
					}
				}
			} else {
				for(int i=0; i<x1-x2;i++){
					int x0=x1-i;
					int y0=((y2-y1)*x1+(x1-x2)*y1-x0*(y2-y1))/(x1-x2);
					if((x0-x1)*(x0-x1)/(45*45)+(y0-y1)*(y0-y1)/(20*20)>1){
						
						return new int[]{x0,y0};
					}
				}
			}
		}
		return new int[]{x1,y1};
	}
}