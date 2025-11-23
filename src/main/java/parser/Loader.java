package parser;
import java.util.ArrayList;
import java.util.Collections;
import dom.* ;
import dom2app.SimpleTableModel;
import java.io.*;
import java.util.Scanner;


public class Loader {
	private ArrayList<Task> taskList;
	private ArrayList<String[]> list;
	
	public Loader() {	
		taskList = new ArrayList<Task>();
		list = new ArrayList<String[]>();
	}
	
	public int fillList(String inputPath,String delimiter) {
		Scanner inputReader=null;
		int l = 0;
		try
	       {
	           inputReader =
	              new Scanner(new FileInputStream(inputPath));
	       }
	       catch(FileNotFoundException e)
	       {
	           System.exit(0);
	       }
		inputReader.useDelimiter(delimiter);
		
		while(inputReader.hasNextLine()) {
			list.add(inputReader.nextLine().split(delimiter));
			l++;
		}
		return l;
	}
	
	public ArrayList<Task> makeTasks() {
		ArrayList<SimpleTask> emptyList = new ArrayList<SimpleTask>();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).length < 5) {
				CompositeTask newTask = new CompositeTask(Integer.parseInt(list.get(i)[0]),list.get(i)[1],emptyList);
				taskList.add(newTask);
			}else {
				SimpleTask newTask = new SimpleTask(Integer.parseInt(list.get(i)[0]),list.get(i)[1],Integer.parseInt(list.get(i)[3]),Integer.parseInt(list.get(i)[4]),Integer.parseInt(list.get(i)[2]),Integer.parseInt(list.get(i)[5]));
				taskList.add(newTask);
			}
		}
		return taskList;
	}
	
	private void merge() {
		for(int i=0; i<taskList.size(); i++) {
			if(taskList.get(i).getClass() == CompositeTask.class) {
				for (int j=0; j<taskList.size(); j++) {
					if(taskList.get(i).getId() == taskList.get(j).getMama()) {
						taskList.get(i).addToList(taskList.get(j));
					}
				}
				taskList.get(i).computeAll();
			}
		}
	}
	
	private void sort() {
		for(int j=0; j<taskList.size()-1; j++) {
				for(int a=j+1; a<taskList.size()-1; a++) {
					if(taskList.get(j).getId()>taskList.get(a).getId()) {
						int pos1=taskList.indexOf(taskList.get(j));
						int pos2=taskList.indexOf(taskList.get(a));
						Collections.swap(taskList,pos1,pos2);
					}
			}
		}
	}

	public SimpleTableModel load(String fileName, String delimiter) {
		fillList(fileName,delimiter);
		makeTasks();
		merge();
		sort();
		String[]  columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		ArrayList<String[]> p = new ArrayList<String[]>();
		for(int i=0; i<taskList.size(); i++) {
			p.add(taskList.get(i).makeString());
		}
		return new SimpleTableModel("name","name",columNames,p);
	}
	
	public SimpleTableModel getTopLevelTasks() {
		ArrayList<String[]> p = new ArrayList<String[]>();
		String[]  columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		for(int i=0; i<taskList.size(); i++) {
			if(taskList.get(i).isTopLevel()) {
				p.add(taskList.get(i).makeString());
			}
		}
		return new SimpleTableModel("name","name",columNames,p);
	}
	
	public ArrayList<Task> getTasks(){
		return taskList;
	}
}
