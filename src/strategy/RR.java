package strategy;
import java.util.ArrayList;

import scheduler.Process;
import scheduler.*;

public class RR extends Strategy{
	
	//RR means chnage the current process if time exceeds 2 i.e. cycle for RR is 2
	//also it goes for I/O after half of its cpu time is done
 	protected void handleRunning() {
		if(running != -1) {
			Process curP = proc.get(running);
			if(curP.runCycle == curP.cpuTime) {
				procStatus[running] = null;
				curP.finishCycle = cycle - 1;
				running = -1;
			}
			else if(curP.ioTime > 0 && curP.runCycle == curP.cpuTime / 2) {
				insertIntoQueue(running, blockedQ);
				procStatus[running] = Status.BLOCKED;
				running = -1;
				curP.ioTime --;
			}
			else if(curP.runCycle > 0 && curP.runCycle % 2 == 0) {
				insertIntoQueue(running, readyQ);
				procStatus[running] = Status.READY;
				running = -1;
			}
			else {
				curP.runCycle ++;
			}
		}
		if(running == -1) {
			if(readyQ != null && readyQ.size() > 0) {
				int curId = readyQ.get(0);
				running = curId;
				procStatus[curId] = Status.RUNNING;
				proc.get(curId).runCycle ++;
				readyQ.remove(0);
			}
			else {
				idleCycle ++;
			}
		}
	}
 	
 	protected boolean chkPreemptive() {
		return false;
	}
	
	protected void insertIntoQueue(int procId, ArrayList<Integer> queue) {
		if(queue.size() == 0) {
			queue.add(0, procId);
			return;
		}
		Process curP = proc.get(procId);
		for(int i = 0; i < queue.size(); i ++) {
			if(proc.get(queue.get(i)).id > curP.id) {
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