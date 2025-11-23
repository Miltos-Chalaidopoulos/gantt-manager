package dom;

import java.util.ArrayList;

import dom2app.SimpleTableModel;

public class Filter {
	ArrayList<Task> listOfTasks;
	
	public Filter(ArrayList<Task> listOfTasks) {
		this.listOfTasks = listOfTasks;
	}
	public SimpleTableModel getTasksByPrefix(String prefix) {
		ArrayList<String[]> samePrefix = new ArrayList<String[]>();
		for(int i=0; i< listOfTasks.size(); i++) {
			if(listOfTasks.get(i).description.startsWith(prefix)) {
				samePrefix.add(listOfTasks.get(i).makeString());
			}
		}
		String[]  columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		return new SimpleTableModel("name","name",columNames,samePrefix);
	}
	
	public 	SimpleTableModel getTaskById(int id) {
		ArrayList<String[]> sameId = new ArrayList<String[]>();
		for(int i=0; i< listOfTasks.size(); i++) {
			if(listOfTasks.get(i).id == id) {
				sameId.add(listOfTasks.get(i).makeString());
				break;
			}
		}
		String[] columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		return new SimpleTableModel("name","name",columNames,sameId);
	}
	
	public void setListOfTasks(ArrayList<Task> x) {
		listOfTasks = x;
	}
}