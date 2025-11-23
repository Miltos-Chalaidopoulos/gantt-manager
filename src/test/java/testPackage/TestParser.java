package testPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import parser.Loader;
import org.junit.Before;
import org.junit.Test;
import dom.*;
import dom2app.SimpleTableModel;

public class TestParser {
	Loader l;
	
	@Before
	public void setUp() {
		l = new Loader();
		l.fillList("Eggs.tsv","\t");
	}
	
	@Test
	public void testfillList() {
		assertEquals(" ",l.fillList("Eggs.tsv","\t"),1);
	}
	
	@Test
	public void testMakeTasks(){
		ArrayList<Task> list = new ArrayList<Task>();
		SimpleTask task = new SimpleTask(100,"Turn on burner (low)",1,1,0,10);
		list.add(task);
		assertEquals("",l.makeTasks(),list);
	}
	
	@Test
	public void testLoad() {
		ArrayList<String[]> p = new ArrayList<String[]>();
		String[]  columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		String[] d = {"100","Turn on burner (low)","0","1","1","10"};
		p.add(d);
		SimpleTableModel s =new SimpleTableModel("name","name",columNames,p);
		assertEquals("",s,l.load("Eggs.tsv", "\t"));
	}
	
	@Test
	public void testShowTopLevel() {
		ArrayList<String[]> p = new ArrayList<String[]>();
		String[]  columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		String[] d = {"100","Turn on burner (low)","0","1","1","10"};
		p.add(d);
		SimpleTableModel s =new SimpleTableModel("name","name",columNames,p);
		assertEquals("",s,l.load("Eggs.tsv", "\t"));
	}
	
	@Test
	public void testLoadNull() {
		assertNull("",l.load("null.tsv","\t"));
	}
}
