package osassignment;

import java.util.*;
import java.io.*;

public class Main {

	final static String PROCESSES_FILE = "processes.txt";

	public static void main(String[] args) {
		// Read processes in from the file and add them to the ready queue.
		Scanner sn;
		Queue<Process> readyQueue = new LinkedList<Process>();
		try {
			sn = new Scanner(new FileReader(PROCESSES_FILE));
		} catch (FileNotFoundException e) {
			System.out.println("'" + PROCESSES_FILE + "' not found.");
			return;
		}
		sn.useDelimiter(" ");
		int time = 0;
		while (sn.hasNextLine()) {
			int pid = sn.nextInt();
			int arrival = sn.nextInt();
			int runtime = sn.nextInt();
			double iopct = sn.nextDouble();
			sn.nextLine();
			
			readyQueue.add(new Process(pid, arrival, runtime, iopct));
			time++;
		}
		sn.close();
		
		for (Process process : readyQueue) {
			process.state = ProcessState.READY;
		}

		int ticker = 0;
		CPU cpu = new CPU();
	     System.out.println("\nTick\tProcess_ID\tTimeLeft\tPC\tR0\tR1\tR2\tR3\tProcessState\tReadyQueue");
		for (Process process : readyQueue) {
			process.state = ProcessState.RUNNING;
			process.bursts = 0;
			process.setFirstBurst(ticker);
			
			for (int i = 0; i < process.getBurstTime()+1; i++) {
				try {
					cpu.setPC(process.generateInstruction());
					cpu.setRegisters(process.generateRegisters());
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}

				if (process.getTimeLeft() == 0){
					process.state = ProcessState.TERMINATED;
					//readyQueue.remove(process);
					process.incrementWaitTime(ticker - process.bursts);
				}
				
				int timeBefore = process.getTimeLeft()+1;
				
				System.out.println(ticker+"\t"+process.getPid()+"\t\t"+ timeBefore+"->"+ process.getTimeLeft()+"\t\t"+cpu.getPC()+"\t"+cpu.getRegisters()[0]+"\t"+cpu.getRegisters()[1]+"\t"+cpu.getRegisters()[2]+"\t"+cpu.getRegisters()[3]
	        			+"\t"+process.state + "\t\t") ;
					
				
				process.bursts = i;
				
				ticker++;
			}
			
					
	    }
	
	}

}
