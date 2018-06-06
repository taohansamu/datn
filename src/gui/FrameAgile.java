package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.bayesserver.inference.InconsistentEvidenceException;

import model.BayesNet;
import util.CSVUtils;

public class FrameAgile extends JFrame {
	private int width = 1024;
	private int high = 200;
	private int maxTime;
	private String message;
	private BayesNet network;
	private ArrayList<Panel> listPanel;
	public FrameAgile(BayesNet net) throws IOException, InconsistentEvidenceException {
		this.network = net;
		int[][] dataSL = net.getSchedule();
		int[][] dataID = net.getMatrixTaskID();
		int nResources = net.getNumResources();
		int nTasks = network.getNumTotalTasks();
		network.construct();
		double[][] dataProb = network.infer();
//		this.setSize(width, high);

		this.setTitle("frameAgile");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLayout(null);
		this.maxTime = maxTime(dataSL);
		this.listPanel=new ArrayList<Panel>();
		
		
		double totalProb = 0;
		for (int i = 0; i <= nResources; i++) {
			
			int x1 = 0;
			int y1 = (high - 10) / (nResources + 1) * i;
			int x2 = width;
			int y2 = (high - 10) / (nResources + 1);
		
			if (i < nResources) {
				Panel panelArea = new Panel(maxTime, network.resourceNet.get(i));
				panelArea.setBounds(x1, y1, x2, y2);
				createPanel(dataSL[i], dataID[i], nTasks, panelArea, x1, y1, x2, y2, dataProb[i], i);
				int nTasksPerRes = 0;
				for (int j = 0; j < nTasks; j++)
					if (dataProb[i][j] > 0)
						nTasksPerRes++;
				totalProb = totalProb + dataProb[i][nTasksPerRes - 1];
			} else {
				totalProb = totalProb/nResources;
				JPanel panelArea = new JPanel();
				panelArea.setBounds(x1, y1, x2, y2);
				JButton okBtn = new JButton("Tổng hợp");
				message ="Xác xuất hoàn thành của cả lịch trình: " + totalProb*100 + "%";
				okBtn.addActionListener(new ActionListener() {

					
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Tổng hợp: " +message);
					}
				});
				
				JButton reload = new JButton("Cập nhật");
				reload.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							double[][] prob = network.infer();
							for(int l=0;l<listPanel.size();l++){
								listPanel.get(l).reloadData(prob[l]);
							}
							
							/*for (int j=0; j<prob[0].length; j++)
								System.out.print(prob[0][j] + " ");*/
						} catch (InconsistentEvidenceException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				panelArea.add(okBtn);
				panelArea.add(reload);
				this.add(panelArea);
			}
			FileWriter csvFileWriter = new FileWriter("output.csv");
			for(i=0; i< nResources; i++){
				CSVUtils.writeLine(csvFileWriter, Arrays.asList("R" + Integer.toString(i+1)+":"));
				ArrayList<String> row = new ArrayList<String>();
				row.add("\t");
				for(int j=0; j< dataID[i].length; j++){
					int tmp = dataID[i][j];
					if(tmp > 0){
						row.add("T"+Integer.toString(tmp));
					}else {
						CSVUtils.writeLine(csvFileWriter, row);
						row.clear();
						break;
					}
				}
				row.add("\t");
				for(int j=0; j< dataSL[i].length; j++){
					int tmp = dataSL[i][j];
					if(tmp > 0){
						row.add(Integer.toString(tmp)+"h");
					}else {
						CSVUtils.writeLine(csvFileWriter, row);
						row.clear();
						break;
					}
				}
				row.add("\t");
				for(int j=0; j< dataProb[i].length; j++){
					double tmp = dataProb[i][j];
					if(tmp > 0.0){
						row.add(Double.toString((double) Math.round(tmp * 1000000) / 10000).toString().replace('.',',')+"%");
					}else {
						CSVUtils.writeLine(csvFileWriter, row);
						row.clear();
						break;
					}
				}
			}
			CSVUtils.writeLine(csvFileWriter, Arrays.asList("Xác suất tổng:", Double.toString((double) Math.round(totalProb * 1000000) / 10000).toString().replace('.',',')+"%"));
			csvFileWriter.flush();
			csvFileWriter.close();
		}

		this.setLocationRelativeTo(null);
		this.setPreferredSize( new Dimension(width, high));
		this.pack();
		this.setVisible(true);
	}

	private int maxTime(int[][] dataSL) {
		int max = 0;
		for (int i = 0; i < dataSL.length; i++) {
			int totalTime = 0;
			for (int j = 0; j < dataSL[i].length; j++) {
				totalTime += dataSL[i][j];
			}
			if (totalTime > max) {
				max = totalTime;
			}
		}
		return max;
	}

	private void createPanel(int[] arrSL, int[] arrID, int nTasks, Panel panelArea, int x1, int y1, int x2, int y2,
			double[] arrProb, int i) throws IOException {

		panelArea.createButton(arrSL, arrID, x1, y1, x2, y2, arrProb, i+1);
		this.add(panelArea);
		listPanel.add(panelArea);
	}
}
