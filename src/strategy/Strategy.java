package strategy;

import java.util.*;
import scheduler.*;
import scheduler.Process;

abstract public class Strategy {
	
	//process list
	protected ArrayList<Process> proc = null;
	
	protected Status[] procStatus = null; //status of each project stored
	
	protected int running = -1;
	
	protected ArrayList<Integer> readyQ = new ArrayList<Integer>();
	
	protected ArrayList<Integer> blockedQ = new ArrayList<Integer>();
	
	protected int cycle = 0; //cpu cycle
	
	protected int idleCycle = 0; //idle cycle not used till now
	
	public boolean isPreemptive = true;
	
	protected StringBuilder buf = new StringBuilder();//output buffer
	
	private void deepCopy(ArrayList<Process> originProc, Status[] originProcStatus) {
		this.proc = new ArrayList<Process>();
		this.procStatus = new Status[originProcStatus.length];
		
		for(Process p : originProc) {
			this.proc.add(p.clone());
		}
		
		for(int i = 0; i < originProcStatus.length; i ++) {
			this.procStatus[i] = originProcStatus[i];
		}
	}
	
	public ArrayList<Process> start(ArrayList<Process> originProc, Status[] originProcStatus, boolean needDeepCopy) {
		
		
		
		if(!needDeepCopy) {
			this.proc = originProc;
			this.procStatus = originProcStatus;
		}
		else {
			deepCopy(originProc, originProcStatus);
		}
		for(; ; cycle ++) {
			handleNew();
			handleBlockedQ();
			handleRunning();
			if(endChk()) {
				end();
				break;
			}
			Tool.writeBuffer(cycle + buf.toString());
			buf.setLength(0);
		}
		return proc;
	}
	
	protected void handleNew() {
		
		for(int i = 0; i < proc.size(); i ++) {
			Process curProc = proc.get(i);
			if(curProc.arrivalTime <= cycle && procStatus[curProc.id] == Status.NEW) {
				procStatus[curProc.id] = Status.READY;
				this.insertIntoQueue(curProc.id, readyQ);
				curProc.startCycle = cycle;
			}
		}
	}
	
	protected void handleBlockedQ() {
		if(blockedQ == null || blockedQ.size() == 0) {
			return;
		}
		boolean remove = false;
		for(int i = 0; i < blockedQ.size(); i ++) {
			if(proc.get(blockedQ.get(i)).ioTime-- <= 0) {
				this.insertIntoQueue(blockedQ.get(i), readyQ);
				this.procStatus[proc.get(blockedQ.get(i)).id] = Status.READY;		//send to ready queue if io time finished
				blockedQ.set(i, null);
				remove = true;
			}
		}
		if(!remove) {
			return;
		}
		this.removeElesFromList(blockedQ);
	}
	
	abstract protected void handleRunning();
	
	abstract protected boolean chkPreemptive();
	
	abstract protected void insertIntoQueue(int procId, ArrayList<Integer> queue);
	
	protected void removeElesFromList(ArrayList<Integer> list) {
		for(int i = 0; i < list.size(); i ++) {
			if(list.get(i) == null) {
				list.remove(i);
				i = 0;
			}
		}
	}
	protected boolean endChk() {
		boolean end = true;
		for(int i = 0; i < procStatus.length; i++) {
			Status s = procStatus[i];
			if(s == null) {
				end &= true;
			}
			else {
				end &= false;
				if(s == Status.NEW) {
					continue;
				}
				buf.append(" " + i + ":" + s);
			}
		}
		if(buf.length() == 0) {
			buf.append(" CPU IDLE");
		}
		return end;
	}

	protected void end() {
		Tool.writeBuffer("Various Parameters to compare different scheduling policies");
		Tool.writeBuffer("");
		Tool.writeBuffer("Finishing time: " + --cycle);
		java.text.DecimalFormat format = new java.text.DecimalFormat("#0.00");
		Double predic = (new Double(cycle) / new Double(proc.size()));
		Tool.writeBuffer("Predictability: " + format.format(predic));
		Double put = (new Double(proc.size()) / new Double(cycle));
		Tool.writeBuffer("Throughput: " + format.format(put));
		//Tool.writeBuffer("Average Response Time: " + (cycle/proc.size()));
		//new addition //extra Info
		Double util = (1 - new Double((idleCycle - 1)) / new Double(cycle + 1));
		Tool.writeBuffer("CPU utilization: " + format.format(util));
		int turnsum = 0;
		Tool.writeBuffer("");
		for(int i = 0; i < proc.size(); i ++) {
			int turnaround = proc.get(i).finishCycle - proc.get(i).arrivalTime + 1;
			turnsum = turnsum + turnaround;
			Tool.writeBuffer("Turnaround time for process " + proc.get(i).id + ": " + turnaround);
		}
		Tool.writeBuffer("Average Turnaround Time: " + (turnsum/proc.size()));
		Tool.writeBuffer("");
		int ressum =0;
		for(int i = 0; i < proc.size(); i ++) {
			int turnaround = proc.get(i).finishCycle - proc.get(i).arrivalTime + 1;
			int response = turnaround - (proc.get(i).ioTime + proc.get(i).cpuTime);
			Tool.writeBuffer("Response time for process " + proc.get(i).id + ": " + response);
			ressum = ressum + response;
		}
		Tool.writeBuffer("Average Response Time: " + (ressum/proc.size()));
	}
}