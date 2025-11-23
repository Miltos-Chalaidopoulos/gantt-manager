package testPackage;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import backend.ReportType;
import reporter.Reporter;
import dom.SimpleTask;
import dom.CompositeTask;
import dom.Task;

import java.io.IOException;
import java.util.ArrayList;

public class testReporter {
	private ArrayList<Task> listOfTasks;
	private Reporter r;
	
	@Before
	public void setUp() throws Exception{
		listOfTasks = new ArrayList<Task>();
		Task first = new SimpleTask(100,"first",0,1,2,2);
		Task second = new SimpleTask(202,"second",200,2,3,3);
		ArrayList<SimpleTask> comp = new ArrayList<SimpleTask>();
		comp.add((SimpleTask) second);
		Task third = new CompositeTask(200,"third",comp);
		third.computeAll();
		listOfTasks.add(first);
		listOfTasks.add(third);
		listOfTasks.add(second);
		r = new Reporter(listOfTasks);
	}
	
	@Test
	public void testTxt() {
		try {
			assertEquals("test if file has the right amount of lines",r.saveAsTxt("test.txt","\t"),4);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMd() {
		try {
			assertEquals("test if file has the right amount of lines",r.saveAsMd("test.txt","\t"),3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHtml() {
		try {
			assertEquals("test if file has the right amount of lines",r.saveAsHtml("test.txt","\t"),20);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateReport() {
		assertEquals("test if createReport returns the right SimpleTableModel",r.createReport("test.txt",ReportType.TEXT),3);
	}
	
	@Test
	public void testNull() {
		ArrayList<Task> f = null;
		Reporter newReporter = new Reporter(f);
		assertNull("test what createReport returns if ArrayList is null",newReporter.createReport("test.txt", ReportType.TEXT));
	}
}
