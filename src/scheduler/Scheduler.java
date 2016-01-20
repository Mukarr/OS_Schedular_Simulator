package scheduler;

import java.util.*;

import strategy.*;


public class Scheduler {
	static int flag = 0;
	public static void main(String[] args) {
		ArrayList<Process> proc = null;
		Status[] procStatus = null;
		String inputFile = "";
		int strategy = -1;
		inputFile = "/home/mukarram/Desktop/input1.txt";
		//strategy = 1;
		try {
            if(args == null || args.length == 0) {
            		flag = 1;
                    throw new Exception();
            }
            inputFile = args[0];
            if(args.length == 2) {
                    strategy = Integer.parseInt(args[1]);
            }
            if((strategy > 2) || (strategy < 0 && strategy != -1)) {
            		flag = 1;
                    throw new Exception();
            }
	    } catch(Exception e) {
	            invalidParam();
	    }
		proc = Tool.fetch(Tool.read(inputFile));
		procStatus = new Status[proc.size()];
		for(int i = 0; i < procStatus.length; i ++) {
			procStatus[i] = Status.NEW;
		}
		//strategy = -1;
		if(flag==0){
			if(strategy >= 0) {
				//System.out.println("sdaaf");
				exec(strategy, proc, procStatus, false);
			}
			else if(strategy == -1) {
				/**
				 * if perform multi-strategies once, need deep copy
				 */
				System.out.println("I am here");
				exec(0, proc, procStatus, true);
				exec(1, proc, procStatus,true);
				exec(2, proc, procStatus,true);
			}
		}
		}
	
	
	private static void invalidParam() {
        System.out.println("Input format: <input file path> [strategy]");
        System.out.println(" strategy: ");
        System.out.println("           -1[default]: all");
        System.out.println("           0: fcfs");
        System.out.println("           1: rr");
        System.out.println("           2: srjf");
	}

	
	public static void exec(int strategy, ArrayList<Process> proc, Status[] procStatus, boolean needDeepCopy) {
		String outputFile = "";
		Strategy ctrl = null;
		switch(strategy) {
		case 0:
			//System.out.println("I anss sfa");
			ctrl = new FCFS();
			ctrl.isPreemptive = true;
			outputFile = "output1_fcfs.txt";
			
			Tool.writeBuffer("Uniprocessor OS Scheduling Timeline");
			Tool.writeBuffer("Policy Used: First-Come-First-Serve");
			Tool.writeBuffer("");
			Tool.writeBuffer("Time : Status of Process");
			
			break;
		case 1:
			//System.out.println("I anss sasdasfa");
			ctrl = new RR();
			ctrl.isPreemptive = false;
			outputFile = "output1_rr.txt";
			Tool.writeBuffer("Uniprocessor OS Scheduling Timeline");
			Tool.writeBuffer("Policy Used: Round Robin");;
			Tool.writeBuffer("");
			Tool.writeBuffer("Time : Status of Process");
			break;
		case 2:
			ctrl = new SRJF();
			ctrl.isPreemptive = true;
			outputFile = "output1_srjf.txt";
			Tool.writeBuffer("Uniprocessor OS Scheduling Timeline");
			Tool.writeBuffer("Policy Used: Shortest Ready Job First");
			Tool.writeBuffer("");
			Tool.writeBuffer("Time : Status of Process");
			break;
		default:
			break;
		}
		ctrl.start(proc, procStatus, needDeepCopy);
		Tool.writeFile(outputFile);
		return;
	}
}