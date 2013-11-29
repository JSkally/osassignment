package osassignment;

import java.util.*;
import java.io.*;

public class Main {

	final static String PROCESSES_FILE = "/home/gab/Desktop/processes.txt";

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
			System.out.println(pid);
			int arrival = sn.nextInt();
			System.out.println(arrival);
			int runtime = sn.nextInt();
			System.out.println(runtime);	
			double iopct = sn.nextDouble();
			sn.nextLine();
			
			readyQueue.add(new Process(pid, arrival, runtime, iopct));
			time++;
		}
		sn.close();

		int ticker = 0;
		CPU cpu = new CPU();
	    System.out.println("\nTick   Process_ID   TimeLeft       PC           R0           R1           R2            R3  ProcessState   ReadyQueue");
	    System.out.println("=====+=============+========+==========+============+============+============+=============+==============+=============+");
		for (Process process : readyQueue) {
			process.state = ProcessState.RUNNING;
			for (int i = 0; i < process.getBurstTime(); i++) {
				try {
					cpu.setPC(process.generateInstruction());
					cpu.setRegisters(process.generateRegisters());
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}

				ticker++;
			}
			if (ticker == process.getBurstTime()){
				process.state = ProcessState.TERMINATED;
			}
		
	        System.out.format("%5s| %12s| %7s| %9s| %11s| %11s| %11s| %12s| %13s|", 
	        				 ticker, Integer.toString(process.getPid()),Integer.toString(process.getTimeLeft()),
	        				 Integer.toString(cpu.getPC()), Integer.toString(cpu.getRegisters()[0]),cpu.getRegisters()[1],
	        				 cpu.getRegisters()[2],cpu.getRegisters()[3],process.state);
	        System.out.println("");
	    }
	
	}

}