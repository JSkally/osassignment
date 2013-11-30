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
               process.setState(ProcessState.READY);
        }

        int ticker = 0;
        CPU cpu = new CPU();
        System.out.println("\nTick   Process_ID   TimeLeft       PC           R0           R1           R2            R3   ProcessState   ReadyQueue");
        System.out.println("=====+=============+========+==========+============+============+============+=============+==============+=============+");
        for (Process process : readyQueue) {
            process.setState(ProcessState.RUNNING);
            //process.bursts = 0;
            process.setFirstBurst(ticker);

            for (int i = 0; i < process.getBurstTime(); i++) {
                    try {
                            cpu.setPC(process.generateInstruction());
                            cpu.setRegisters(process.generateRegisters());
                    } catch (Exception e) {
                            System.out.print(e.getMessage());
                    }
                    process.bursts = i;
                    if (process.getTimeLeft() == 1){
                            process.setState(ProcessState.TERMINATED);
                            process.setTurnAroundTime(ticker);
                            //readyQueue.remove(process);
                            process.incrementWaitTime(ticker - process.bursts);
                    }

                    int timeAfter = process.getTimeLeft()-1;

                System.out.format("%5s| %12s| %7s| %9s| %11s| %11s| %11s| %12s| %13s|",
                     ticker, Integer.toString(process.getPid()), (process.getTimeLeft()+"->"+timeAfter),

                     Integer.toString(cpu.getPC()), Math.abs(cpu.getRegisters()[0]),Math.abs(cpu.getRegisters()[1]),
                    Math.abs(cpu.getRegisters()[2]), Math.abs(cpu.getRegisters()[3]) ,process.getState());

                System.out.println("");


                //process.bursts = i;
                process.setTurnAroundTime(ticker);
                ticker++;
            }


        }
        System.out.println("\n");
        System.out.println("  Pid   Time in Ready Queue   Total Time    Wait Time   Response time  Turn Around Time");
        System.out.println("======+=====================+=============+===========+===============+=================+");

        int processTotal = 0;
        int responseTimeTotal = 0;
        int waitTimeTotal = 0;
        int totalTurnAroundTime = 0;
        int minCompletionTime = 999999;
        int maxCompletionTime = 0;

        for (Process process : readyQueue) {
             System.out.format("%6s| %20s| %13s| %12s| %11s| %16s|",
             Integer.toString(process.getPid()),process.getWaitTime(),"s", process.getWaitTime(), process.getResponseTime(), process.getTurnAroundTime());
             System.out.println("");

             processTotal++;
             responseTimeTotal = responseTimeTotal + process.getResponseTime();
             waitTimeTotal = waitTimeTotal + process.getWaitTime();
             totalTurnAroundTime = totalTurnAroundTime + process.getTurnAroundTime();
             minCompletionTime = Math.min(minCompletionTime, process.getTurnAroundTime()-process.getResponseTime());
             maxCompletionTime = Math.max(maxCompletionTime, process.getTurnAroundTime()-process.getResponseTime());
        }
        System.out.println("\n");
        System.out.println("Total simulation time: "+ ticker);
        System.out.println("Total number of processes: " + processTotal);
        System.out.println("Shortest process completion time: " + minCompletionTime);
        System.out.println("Longest process completion time: " + maxCompletionTime);

        System.out.println("Average Turnaround/Completion time: " + ((double)totalTurnAroundTime/processTotal));
        System.out.println("Average Wait time: " + ((double)waitTimeTotal/processTotal));
        System.out.println("Average Response time: " + ((double)responseTimeTotal/processTotal));
    }

}
