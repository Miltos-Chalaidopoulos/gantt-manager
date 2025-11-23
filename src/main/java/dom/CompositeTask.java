package dom;

import java.util.ArrayList;
import java.util.Collections;

public class CompositeTask extends Task {
	ArrayList<SimpleTask> components;
	
	public CompositeTask(int id,String description,ArrayList<SimpleTask> components) {
		super(id,description);
		this.components = components;
		components = new ArrayList<SimpleTask>();
	}
	
	public void findFirstStart() {
		int x = components.get(0).getStartDate();
		for(int i=0; i<components.size(); i++) {
			if(components.get(i).getStartDate() < x) x = components.get(i).getStartDate();
		}
		startDate = x;
	}
	
	public void findLastEnd() {
		int x = components.get(0).getEndDate();
		for(int i=0; i<components.size(); i++) {
			if(components.get(i).getEndDate() > x) x = components.get(i).getEndDate();
		}
		endDate = x;
	}
	
	public void computeCost() {
		int x = 0;
		for(int i=0; i<components.size(); i++) {
			x = x + components.get(i).getCost();
		}
		cost = x;
	}
	
	public void computeAll() {
		computeCost();
		findFirstStart();
		findLastEnd();
		sort();
	}
	
	public void addToList(Task x) {
		components.add((SimpleTask)x); 
	}
	
	public boolean isCompositeTask() {
		return true;
	}
	
	public int getMama() {
		return 0;
	}
	
	public boolean isTopLevel() {
		return true;
	}
	
	public void sort() {
		for(int j=0; j<components.size()-1; j++) {
			for(int a=j+1; a<components.size()-1; a++) {
				if(components.get(j).getStartDate()>components.get(a).getStartDate()) {
					int pos1=components.indexOf(components.get(j));
					int pos2=components.indexOf(components.get(a));
					Collections.swap(components,pos1,pos2);
				}
			}
		}
	}
	
	public String[] makeString() {
		String[] p = {Integer.toString(id),description,"0",Integer.toString(startDate),Integer.toString(endDate),Integer.toString(cost)};
		return p; 
	}
	
	public int returnSize() {
		return components.size();
	}
	
	public SimpleTask returnTask(int x){
		return components.get(x);
	}
	
	public boolean hasSubTask(SimpleTask x) {
		for(int i=0; i<components.size(); i++) {
			if(components.get(i).equals(x)) return true;
		}
		return false;
	}
	
	public ArrayList<SimpleTask> getComponents(){
		return components;
	}
}
