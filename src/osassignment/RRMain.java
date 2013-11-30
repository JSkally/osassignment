package osassignment;

import java.util.LinkedList;
import java.util.Queue;

public class RRMain {

  public static void main(String[] args) {
    final String PROCESSES_FILE = "processes.txt";
    final int TIME_QUANTUM = 2;

    // Read processes in from the file and add them to the ready queue.
    Queue<Process> readyQueue = new LinkedList<Process>();

    for (int i = 0; i < 20; i++) {
      readyQueue.add(new Process(i, i + 20, i + 13, 0.3));
    }

    int ticker = 0;
    CPU cpu = new CPU();

    Process process = readyQueue.poll();

    while (readyQueue.size() > 0) {

      if (process.getState() == ProcessState.TERMINATED) {
        readyQueue.remove(process);
        process = readyQueue.poll();
      } else if (process.getBurstTime() % TIME_QUANTUM == 0) {
        // Put the current process in ready state and add it back to
        // the queue
        process.setState(ProcessState.READY);
        readyQueue.add(process);
        process = readyQueue.poll();
      }

      process.setState(ProcessState.RUNNING);

      try {
        cpu.setPC(process.generateInstruction());
        cpu.setRegisters(process.generateRegisters());
        process.incrementBurstTime();
        System.out.print("CPU at " + ticker + ": " + cpu + " on pid: "
            + process.getPid() + "\n");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }

      ticker++;

    }

  }
}
