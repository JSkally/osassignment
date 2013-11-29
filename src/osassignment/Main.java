package osassignment;

import java.util.*;

public class Main {

	final static String PROCESSES_FILE = "processes.txt";

	public static void main(String[] args) {
		// Read processes in from the file and add them to the ready queue.
		Queue<Process> readyQueue = new LinkedList<Process>();

		for (int i = 0; i < 20; i++) {
			readyQueue.add(new Process(i, i + 20, i + 13, 0.3));
		}

		int ticker = 0;
		CPU cpu = new CPU();
		for (Process process : readyQueue) {
			process.state = ProcessState.RUNNING;
			for (int i = 0; i < process.getBurstTime(); i++) {
				try {
					cpu.setPC(process.generateInstruction());
					cpu.setRegisters(process.generateRegisters());
					System.out.print("CPU at " + ticker + ": " + cpu);
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}

				ticker++;
			}
		}
	}

}
