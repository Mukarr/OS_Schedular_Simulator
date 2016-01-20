package strategy;

import java.util.ArrayList;

import scheduler.Process;
import scheduler.Status;

public class FCFS extends Strategy{
	// check if running process is to be swapped 
	//after half of cpu time go for io
	protected void handleRunning() {
		if(running != -1) {
			if(isPreemptive) {
				chkPreemptive();
			}
			Process curP = proc.get(running);
			if(curP.runCycle == curP.cpuTime / 2 && curP.ioTime > 0) {
				insertIntoQueue(running, blockedQ);
				procStatus[running] = Status.BLOCKED;
				running = -1;
				curP.ioTime --;
			}
			else if(curP.runCycle == curP.cpuTime) {
				procStatus[running] = null;
				curP.finishCycle = cycle - 1;
				running = -1;
			}
			else {
				curP.runCycle ++;
			}
		}
		if(running == -1) {
			if(readyQ != null && readyQ.size() > 0) {
				for(int i = 0; i < readyQ.size(); i ++) {
					int curId = readyQ.get(i);
					if(proc.get(curId).runCycle == proc.get(curId).cpuTime / 2 && proc.get(curId).ioTime > 0) {
						procStatus[proc.get(curId).id] = Status.BLOCKED;
						proc.get(curId).ioTime --;
						blockedQ.add(curId);
						readyQ.set(i, null);
					}
					else if(proc.get(curId).runCycle == proc.get(curId).cpuTime) {
						procStatus[curId] = null;
						readyQ.set(i, null);
					}
					else {
						running = curId;
						procStatus[curId] = Status.RUNNING;
						proc.get(curId).runCycle ++;
						readyQ.set(i, null);
						break;
					}
				}
				removeElesFromList(readyQ);
			}
			else {
				idleCycle ++;
			}
		}
	}
	// if arrival(ist in ready) < arrival(running) then swap
	protected boolean chkPreemptive() {
		if(readyQ == null || readyQ.size() <= 0) {
			return false;
		}
		Process ready = proc.get(readyQ.get(0));
		Process cur = proc.get(running);
		if(ready.arrivalTime < cur.arrivalTime) {
			running = ready.id;
			procStatus[ready.id] = Status.RUNNING;
			readyQ.remove(0);
			this.insertIntoQueue(cur.id, readyQ);
			procStatus[cur.id] = Status.READY;
			return true;
		}
		return false;
	}
	
	//queue sorted on basis of arrival time
	protected void insertIntoQueue(int procId, ArrayList<Integer> queue) {
		if(queue.size() == 0) {
			queue.add(0, procId);
			return;
		}
		Process curP = proc.get(procId);
		for(int i = 0; i < queue.size(); i ++) {
			if(proc.get(queue.get(i)).arrivalTime > curP.arrivalTime) {
				queue.add(i, procId);
				break;
			}
			else if(i == queue.size() - 1) {
				queue.add(procId);
				break;
			}
		}
		return;
	}
}
