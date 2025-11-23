package testPackage;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import dom.*;
import dom2app.SimpleTableModel;

public class TestFilter {
	private ArrayList<Task> listOfTasks;
	Filter f;

	@Before
	public void seUp() throws Exception{
		listOfTasks = new ArrayList<Task>();
		Task first = new SimpleTask(100,"first",0,1,2,2);
		Task second = new SimpleTask(201,"second",200,2,3,3);
		ArrayList<SimpleTask> comp = new ArrayList<SimpleTask>();
		comp.add((SimpleTask) second);
		Task third = new CompositeTask(200,"sthird",comp);
		third.computeAll();
		listOfTasks.add(first);
		listOfTasks.add(third);
		listOfTasks.add(second);
		f = new Filter(listOfTasks);
	}
	
	@Test
	public void testfilterByPrefix() {
		String[]  columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		String[] taskStirng = {"100","first","2","0","1","2"};
		ArrayList<String[]> task = new ArrayList<String[]>();
		task.add(taskStirng);
		SimpleTableModel s = new SimpleTableModel("name","name",columNames,task);
		assertEquals("test if getByPrefix returns the right tasks",f.getTasksByPrefix("f"),s);
	}
	
	@Test
	public void testfilterById() {
		String[]  columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		String[] taskStirng = {"100","first","2","0","1","2"};
		ArrayList<String[]> task = new ArrayList<String[]>();
		task.add(taskStirng);
		SimpleTableModel s = new SimpleTableModel("name","name",columNames,task);
		assertEquals("test if getById returns the right task",f.getTaskById(100),s);
	}
	
	@Test
	public void testNoTasksWithPrefix() {
		String[]  columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		String[] taskStirng = null;
		ArrayList<String[]> task = new ArrayList<String[]>();
		task.add(taskStirng);
		SimpleTableModel s = new SimpleTableModel("name","name",columNames,task);
		assertEquals("There are no tasks with certain prefix",f.getTasksByPrefix("z"),s);
	}
	
	@Test
	public void testNoTasksWithId() {
		String[]  columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		String[] taskStirng = null;
		ArrayList<String[]> task = new ArrayList<String[]>();
		task.add(taskStirng);
		SimpleTableModel s = new SimpleTableModel("name","name",columNames,task);
		assertEquals("There is no task with certain id",f.getTaskById(300002),s);
	}
	
	@Test
	public void testMoreThanOneTask() {
		String[]  columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		String[] taskStirng = {"201","second","200","2","3","3"};
		String[] taskStirngtwo = {"200","sthird","0","2","3","3"};
		ArrayList<String[]> task = new ArrayList<String[]>();
		task.add(taskStirngtwo);
		task.add(taskStirng);
		SimpleTableModel s = new SimpleTableModel("name","name",columNames,task);
		assertEquals("test if getByPrefix returns the right tasks",f.getTasksByPrefix("s"),s);
	}
}
