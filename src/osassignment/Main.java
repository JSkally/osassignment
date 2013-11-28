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
		sn.useDelimiter(",");
		int time = 0;
		while (sn.hasNextLine()) {
			int pid = sn.nextInt();
			int runtime = sn.nextInt();
			double iopct = sn.nextDouble();
			readyQueue.add(new Process(pid, runtime, time, iopct));
			time++;
		}
		sn.close();

		int ticker = 0;
		CPU cpu = new CPU();
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
		}
	}

}
