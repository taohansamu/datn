package util;

import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;
import model.Resources;
import model.Tasks;

public class ReadInput {
	
	private Resources[] resource;
	private Tasks[] task;
	private int numResources;
	private int numTasks;
	
	public ReadInput() {
		resource = new Resources[100];
		task = new Tasks[100];
	}
	
	public Resources[] readTeam(String teamFile) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(teamFile));
		String[] line;
		int i = 0;
		while ((line=reader.readNext()) != null) {
//			System.out.println(i);
			resource[i] = new Resources();
			resource[i].ID = Integer.parseInt(line[0]);
			resource[i].agileExperience = line[1];
			resource[i].agileLevel = Integer.parseInt(line[2]);
			resource[i].skillLevel = Integer.parseInt(line[3]);
			resource[i].dailyMeeting = line[4];
			i++;
		}
		numResources = i;
		return resource;
	}
	
	public Tasks[] readTask(String taskFile) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(taskFile));
		String[] line;
		int i = 0;
		while ((line=reader.readNext()) != null) {
			task[i] = new Tasks();
			task[i].ID = Integer.parseInt(line[0]);
			task[i].prec = Integer.parseInt(line[1]);
			task[i].time = Integer.parseInt(line[2]);
			task[i].preAss = Integer.parseInt(line[3]);
			i++;
		}
		numTasks = i;
		return task;
	}
	
	
	public int getNumResources() {
		return numResources;
	}
	public void setNumResources(int value){
		numResources = value;
	}
	
	public int getNumTasks() {
		return numTasks;
	}
	public void setNumTasks(int value){
		numTasks = value;
	}
}
