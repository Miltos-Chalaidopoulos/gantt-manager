package dom;

import java.util.ArrayList;

public class SimpleTask extends Task {
	private int mamaId;
	public SimpleTask(int id,String description,int startDate,int endDate,int mamaId,int cost) {
		super(id,description);
		this.startDate = startDate;
		this.endDate = endDate;
		this.mamaId = mamaId;
		this.cost = cost;
	}
	
	public boolean isCompositeTask() {
		return false;
	}
	
	public boolean isTopLevel() {
		if (this.mamaId == 0) return true;
		else return false;
	}
	
	public int getMama() {
		return mamaId;
	}
	
	public void addToList(Task x) {
		return;
	}
	
	public String[] makeString() {
		String[] p = {Integer.toString(id),description,Integer.toString(mamaId),Integer.toString(startDate),Integer.toString(endDate),Integer.toString(cost)};
		return p; 
	}
	
	public int returnSize() {
		return 0;
	}
	
	public SimpleTask returnTask(int x) {
		return this;
	}
	
	public void findFirstStart() {
		return;
	}
	
	public void findLastEnd() {
		return;
	}
	
	public void computeCost() {
		return;
	}
	
	public void computeAll() {
		return;
	}
	
	public boolean hasSubTask(SimpleTask x) {
		return false;
	}

	@Override
	public ArrayList<SimpleTask> getComponents() {
		ArrayList<SimpleTask> x = new ArrayList<SimpleTask>();
		x.add(this);
		return x;
	}
	
	
}