package reporter;

import java.io.*;
import java.util.ArrayList;

import backend.ReportType;
import dom.*;

public class Reporter {
	private ArrayList<Task> listOfTasks;
	
	public Reporter(ArrayList<Task> listOfTasks) {
		this.listOfTasks = listOfTasks;
	}
	
	public int saveAsTxt(String path,String delimiter) throws IOException{
		String[] columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		int lines=0;
		for(int j=0; j<listOfTasks.size(); j++) {
			stringList.add(listOfTasks.get(j).makeString());
		}
		PrintWriter outputWriter = null;
		try
        {
            outputWriter = new PrintWriter(new FileOutputStream(path));
        }
        catch(FileNotFoundException e)
        {
            System.exit(0);
        }
        for(int i=0; i<columNames.length; i++) {
        	outputWriter.print(columNames[i]);
        	outputWriter.print(delimiter);
        }
    	outputWriter.println();
    	lines++;
    	for (int i=0; i<stringList.size(); i++) {
    		for (int j=0; j<columNames.length; j++) {
    			outputWriter.print(stringList.get(i)[j]);
    			outputWriter.print(delimiter);
    		}
    		outputWriter.println();
    		lines++;
    	}
    	outputWriter.close();
    	return lines;
	}
	
	public int saveAsHtml(String path,String delimiter)throws IOException{
		String[] columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		int lines = 0;
		for(int j=0; j<listOfTasks.size(); j++) {
			stringList.add(listOfTasks.get(j).makeString());
		}
		PrintWriter outputWriter = null;
		try
        {
            outputWriter = new PrintWriter(new FileOutputStream(path));
        }
        catch(FileNotFoundException e)
        {
        	
            System.exit(0);
        }
		outputWriter.println("<!doctype html>");
		outputWriter.println("<html>");
		outputWriter.println("<head>");
		outputWriter.println("<meta http-equiv=\"Content-Type\" content\"text/html; charset=windows-1253\">");
		outputWriter.println("<title>Gantt Project Data</title>");
		outputWriter.println("</head>");
		outputWriter.println("<body>");
		outputWriter.println();
		outputWriter.println("<table>");
		outputWriter.println("<tr>");
		for(int i=0; i<columNames.length; i++) {
			outputWriter.print("<td>");
			outputWriter.print(columNames[i]);
			outputWriter.print("</td>");
			outputWriter.print(" ");
		}
		outputWriter.println("</tr>");
		outputWriter.println();
		lines = 12;
		for (int i=0; i<stringList.size(); i++) {
    		for (int j=0; j<columNames.length; j++) {
    			outputWriter.println("<tr>");
    			i++;
    			outputWriter.print("<td>");
    			outputWriter.print(stringList.get(i)[j]);
    			outputWriter.print("</td>");
    			outputWriter.print(" ");
    		}
    		outputWriter.println("</tr>");
    		i++;
    		outputWriter.println();
    		i++;
    	}
		outputWriter.println("</table></body>");
		outputWriter.print("</html>");
		outputWriter.close();
		return lines;
	}
	
	public int saveAsMd(String path,String delimiter)throws IOException{
		String[] columNames = {"taskid","TaskText","mamaId","Start","End","Cost"};
		ArrayList<String[]> stringList = new ArrayList<String[]>();
		int lines = 0;
		for(int j=0; j<listOfTasks.size(); j++) {
			stringList.add(listOfTasks.get(j).makeString());
		}
		PrintWriter outputWriter = null;
		try
        {
            outputWriter = new PrintWriter(new FileOutputStream(path));
        }
        catch(FileNotFoundException e)
        {
            System.exit(0);
        }
		for(int i=0; i<columNames.length; i++) {
			outputWriter.print("*");
			outputWriter.print(columNames[i]);
			outputWriter.print("*");
			outputWriter.print(delimiter);
		}
		outputWriter.println();
		lines++;
		for(int i=0; i<stringList.size(); i++) {
			if(listOfTasks.get(i).isTopLevel()) {
				for(int j=0; j<columNames.length; j++) {
					outputWriter.print("**");
					outputWriter.print(stringList.get(i)[j]);
					outputWriter.print("**");
					outputWriter.print(delimiter);
				}
				outputWriter.println();
			}else {
				for(int j=0; j<columNames.length; j++) {
					outputWriter.print(stringList.get(i)[j]);
					outputWriter.print(delimiter);
				}
				outputWriter.println();
				lines++;
			}
		}
		outputWriter.close();
		return lines;
	}
	
	public int createReport(String path, ReportType type) {
		if(type == ReportType.TEXT) {
			try {
				saveAsTxt(path,"\t");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(type == ReportType.HTML){
			try {
				saveAsHtml(path,"\t");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(type == ReportType.MD) {
			try {
				saveAsMd(path,"\t");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			return -1;
		}
		return listOfTasks.size();
	}
	
	public void setListOfTasks(ArrayList<Task> x) {
		listOfTasks = x;
	}
}
