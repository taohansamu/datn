package riskgui;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.bayesserver.Node;

public class ButtonNode extends JButton {
	private String text;
	public int x1;
	public int width;
	public int y1;
	public int height;
	private volatile int screenX = 0;
	private volatile int screenY = 0;
	private volatile int myX = 0;
	private volatile int myY = 0;
	ProbabilityTable probabilityTable;
	public ArrayList<ButtonNode> listChildButton;

	public ButtonNode(Node node, int row, int col) throws IOException {
		
		
		initiateButton(node, row, col, new Color(255, 255, 204));
		this.setForeground(Color.BLUE);

	}

	public ButtonNode(Node node, int row, int col, Boolean isSumNode, Boolean isTaskNode) throws IOException {
		if (isSumNode) {
			initiateButton(node, row, col, Color.red);
		}
		if (isTaskNode) {
			initiateButton(node, row, col, Color.green);
		}
		this.setForeground(Color.BLUE);
	}

	private void initiateButton(Node node, int row, int col, Color color) throws IOException {
		this.text = node.getName();
	
		probabilityTable = new ProbabilityTable(node);
		probabilityTable.setVisible(false);
		this.x1 = 10 + col * 150;
		this.y1 = 10 + row * 130;
		this.width = 130;
		this.height = 50;
		listChildButton = new ArrayList<ButtonNode>();
		this.setBounds(x1, y1, width, height);
		this.setBorder(new RoundedBorder(width, text, color));
		this.setMargin(new Insets(0, 0, 0, 0));
		this.setBorderPainted(true);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);

		addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				
					if(probabilityTable.isVisible()){
						probabilityTable.setVisible(false);
					} else {
						probabilityTable.setVisible(true);
					}
				
			}

			public void mousePressed(MouseEvent e) {
				screenX = e.getXOnScreen();
				screenY = e.getYOnScreen();

				myX = getX();
				myY = getY();
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

		});
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				int deltaX = e.getXOnScreen() - screenX;
				int deltaY = e.getYOnScreen() - screenY;
				setLocation(myX + deltaX, myY + deltaY);
			}

			public void mouseMoved(MouseEvent e) {
			}

		});
	}

	public int getCenterX() {
		return getX() + width / 2;
	}

	public int getCenterY() {
		return getY() + height / 2;
	}

	private void msgbox(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}