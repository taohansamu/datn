package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Button extends JButton {
	private String message;

	public Button(String label, int name, int time, double prob) {
		this.setText(label);
		this.message = "";
		this.message += "Task ID: " + name + "\n";
		this.message += "Thời gian thực hiện: " + "" + time + "s" + "\n";
		
		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				msgbox(message);
			}
		});
	}

	private void msgbox(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}
