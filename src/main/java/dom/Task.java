package dom;

import java.util.ArrayList;

public abstract class Task {
	protected int id;
	protected String description;
	protected int startDate;
	protected int endDate;
	protected int cost;
	
	public Task(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public abstract boolean isCompositeTask();
	public abstract int getMama();
	public abstract void addToList(Task x);
	public abstract boolean isTopLevel();
	public abstract String[] makeString();
	public abstract int returnSize();
	public abstract SimpleTask returnTask(int x);
	public abstract void findFirstStart();
	public abstract void findLastEnd();
	public abstract void computeCost();
	public abstract void computeAll();
	public abstract boolean hasSubTask(SimpleTask x);
	public abstract ArrayList<SimpleTask> getComponents();
	
	public int computeDuration(){
		return endDate - startDate;
	}
	
	public int getEndDate() {
		return endDate;
	}
	
	public int getStartDate() {
		return startDate;
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getId() {
		return id;
	}
	
}