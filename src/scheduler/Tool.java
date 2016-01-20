package scheduler;

import java.util.*;
import java.io.*;

public class Tool {
	private static StringBuilder outputBuffer = new StringBuilder();
	
	public static void writeBuffer(String info) {
		outputBuffer.append(info + "\n");
	}
	
	public static void writeFile(String fileName) {
		try {
			File file = new File(fileName);
			file.delete();
			BufferedWriter out = new BufferedWriter(new FileWriter(new File(fileName)));
			out.write(outputBuffer.toString());
			out.close();
			outputBuffer = new StringBuilder();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> read(String fileName) {
		ArrayList<String> proc = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
			String curLine = null;
			while(true) {
				curLine = in.readLine();
				if(curLine == null || curLine.length() == 0) {
					break;
				}
				proc.add(curLine);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return proc;
	}
	
	// fetch processes into object 
	//and sort based on arrival time
	 
	public static ArrayList<Process> fetch(ArrayList<String> input) {
		ArrayList<Process> proc = new ArrayList<Process>();
		for(int i = 0; i < input.size(); i ++) {
//			proc.add(new Process(input.get(i).split(" ")));
			Process curP = new Process(input.get(i).split(" "));		//caution: please keep processes sorted wrt id
			proc.add(curP);
		}
		return proc;
	}
}
