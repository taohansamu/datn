package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import com.bayesserver.inference.InconsistentEvidenceException;

import model.BayesNet;
import model.Resources;
import model.Tasks;
import util.ReadInput;

public class MainView {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private File teamSelectedFile;
	private File taskSelectedFile;
	private JLabel labelTeamFile;
	private JLabel labelTaskFile;
	private String projectName;
	private String iterID;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Project Name ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(49, 35, 95, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblIterationId = new JLabel("Iteration ID");
		lblIterationId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIterationId.setBounds(49, 76, 95, 29);
		frame.getContentPane().add(lblIterationId);
		
		JLabel lblTeamData = new JLabel("Team Data");
		lblTeamData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTeamData.setBounds(49, 116, 89, 39);
		frame.getContentPane().add(lblTeamData);
		labelTeamFile = new JLabel("");
		labelTeamFile.setBounds(251, 129, 46, 14);
		frame.getContentPane().add(labelTeamFile);
		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int returnValue = jfc.showOpenDialog(null);
				// int returnValue = jfc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					teamSelectedFile = jfc.getSelectedFile();
					labelTeamFile.setText(teamSelectedFile.getAbsolutePath());
					System.out.println(teamSelectedFile.getAbsolutePath());
				}
			}
		});
		btnChooseFile.setBounds(152, 125, 89, 23);
		frame.getContentPane().add(btnChooseFile);
		
		JLabel lblTaskData = new JLabel("Task Data");
		lblTaskData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTaskData.setBounds(49, 166, 75, 29);
		frame.getContentPane().add(lblTaskData);
		labelTaskFile = new JLabel("");
		labelTaskFile.setBounds(251, 174, 46, 14);
		frame.getContentPane().add(labelTaskFile);
		JButton btnChooseFile_1 = new JButton("Choose File");
		btnChooseFile_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int returnValue = jfc.showOpenDialog(null);
				// int returnValue = jfc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					taskSelectedFile = jfc.getSelectedFile();
					labelTaskFile.setText(taskSelectedFile.getAbsolutePath());
					System.out.println(taskSelectedFile.getAbsolutePath());
				}
			}
		});
		btnChooseFile_1.setBounds(152, 170, 89, 23);
		frame.getContentPane().add(btnChooseFile_1);
		
		textField = new JTextField("Name");
		textField.setBounds(152, 45, 180, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField("1");
		textField_1.setBounds(152, 81, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		/////////////////////////
		taskSelectedFile =  new File("C:\\Users\\taohansamu\\OneDrive\\doan\\tmp\\Nhom5D\\source_code\\test_data\\tasks1.csv");
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		teamSelectedFile =  new File("C:\\Users\\taohansamu\\OneDrive\\doan\\tmp\\Nhom5D\\source_code\\test_data\\resources1.csv");
		//////////////////////////
		JButton btnSyv = new JButton("Submit");
		btnSyv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				projectName = textField.getText();
				iterID = textField_1.getText();
				int iterationLength = Integer.parseInt(textField_2.getText());
				
				ReadInput reader = new ReadInput();
				try {
					Resources[] resource = reader.readTeam(teamSelectedFile.getAbsolutePath());
					Tasks[] task = reader.readTask(taskSelectedFile.getAbsolutePath());
					BayesNet net = new BayesNet(resource, reader.getNumResources(), task, reader.getNumTasks(), iterationLength);
					frame.dispose();

					FrameAgile winResult = new FrameAgile(net);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InconsistentEvidenceException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSyv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSyv.setBounds(185, 227, 89, 23);
		frame.getContentPane().add(btnSyv);
		
		textField_2 = new JTextField("460");
		textField_2.setBounds(338, 81, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblLength = new JLabel("Length");
		lblLength.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLength.setBounds(286, 83, 46, 14);
		frame.getContentPane().add(lblLength);
		
	}
}
