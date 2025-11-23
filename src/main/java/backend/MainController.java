package backend;

import dom2app.SimpleTableModel;
import parser.Loader;
import dom.*;
import reporter.Reporter;

public class MainController implements IMainController {
	Loader l;
	Reporter r;
	Filter f;
	
	public MainController() {
		l = new Loader();
		r = new Reporter(l.getTasks());
		f = new Filter(l.getTasks());
	}
	
	@Override
	public SimpleTableModel load(String fileName, String delimiter) {		
		SimpleTableModel x = l.load(fileName, delimiter);
		r.setListOfTasks(l.getTasks());
		f.setListOfTasks(l.getTasks());
		return x;
	}

	@Override
	public SimpleTableModel getTasksByPrefix(String prefix) {
		return f.getTasksByPrefix(prefix);
	}

	@Override
	public SimpleTableModel getTaskById(int id) {
		return f.getTaskById(id);
	}

	@Override
	public SimpleTableModel getTopLevelTasks() {
		return l.getTopLevelTasks();
	}

	@Override
	public int createReport(String path, ReportType type) {
		return r.createReport(path, type);
	}

}
