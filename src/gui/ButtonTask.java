package gui;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ButtonTask extends JButton{
	private String message;
	private int name;
	private int time;
	public ButtonTask(String label,int name, int time, double prob) {
		this.name=name;
		this.time=time;
		this.setText(label);
		this.setMargin(new Insets(0, 0, 0, 0));
//		this.setBorder(null);
		this.setText(label);
		this.message = "";
		this.message += "Task ID: " + name + "\n";
		this.message += "Thời gian thực hiện: " + "" + time + "dvtg" + "\n";
		this.message += "Xác suất hoàn thành: " + "" + (prob*100) + "%" + "\n";

		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					msgbox(message);
			}
		});
	}
	private void msgbox(String s){
		   JOptionPane.showMessageDialog(null, s);
		}
	public void reloadData(double prob) {
		message = "";
	message += "Task ID: " + name + "\n";
	message += "Thời gian thực hiện: " + "" + time + "dvtg" + "\n";
		message += "Xác suất hoàn thành: " + "" + (prob*100) + "%" + "\n";
	}
}
