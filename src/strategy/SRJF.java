package strategy;

import java.util.*;

import scheduler.Process;
import scheduler.*;
//shortest remaining job first
public class SRJF extends Strategy{
	
	//////premptive is allowed for now.(Dont know if required)
	
	//when half cpu time spent go for io
	//check if 
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
	
	//sort on the basis of cpu time
	protected void insertIntoQueue(int procId, ArrayList<Integer> queue) {
		if(queue.size() == 0 || queue.isEmpty()) {
			queue.add(0, procId);
			return;
		}
		Process curP = proc.get(procId);
		for(int i = 0; i < queue.size(); i ++) {
			Process qP = proc.get(queue.get(i));
			if(qP.cpuTime - qP.runCycle > curP.cpuTime - curP.runCycle) {
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
	// if cpu(1st in ready)< cpu(running) then swap
	protected boolean chkPreemptive() {
		if(readyQ == null || readyQ.size() <= 0) {
			return false;
		}
		Process ready = proc.get(readyQ.get(0));
		Process cur = proc.get(running);
		if(ready.cpuTime - ready.runCycle < cur.cpuTime - cur.runCycle) {
			insertIntoQueue(cur.id, readyQ);
			procStatus[cur.id] = Status.READY;
			readyQ.remove(0);
			running = ready.id;
			procStatus[ready.id] = Status.RUNNING;
			return true;
		}
		return false;
	}
}
