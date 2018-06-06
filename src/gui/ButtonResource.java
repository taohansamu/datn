package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import com.bayesserver.Network;

import riskgui.RiskFrame;

public class ButtonResource extends JButton{
	private String message;
	private  RiskFrame riskFrame;
	private Network network;
	public ButtonResource(String label,int name, int time, Network net) throws IOException {
		this.network=net;
		this.setText(label);
		this.setBorder(null);
		 this.message = "";
		 this.message+="Tên tài nguyên: " +  name+"\n";
		 riskFrame = new RiskFrame(net);
		 riskFrame.setVisible(false);
//		 System.out.println("OK");
		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
//				System.out.println(network.getNodes().size());
					if(riskFrame.isVisible()){
						riskFrame.setVisible(false);
					} else{
						riskFrame.setVisible(true);
					}
			}
		});
	}

}
