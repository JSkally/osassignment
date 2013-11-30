import java.util.*;

class Process {
    private final int pid;
    private final int arrivalTime;
    private int turnAroundTime = 0;
    private int burstTime;
    int waitTime;
    int bursts;
    private int firstBurst;
    private final double ioPercentage;
    private ProcessState state;
    private Random rng;

    // Process constructor
    public Process() {
        this(0, 0, 0, 0.0);
    }

    public Process(int pid, int arrTime, int burTime, double ioPct) {
        this.pid = pid;
        this.arrivalTime = arrTime;
        this.burstTime = burTime;
        this.ioPercentage = ioPct;
        this.setState(ProcessState.NEW);
        this.rng = new Random();
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setFirstBurst(int firstBurst) {
        this.firstBurst = firstBurst;
    }

    public int getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public double getIoPercentage() {
        return ioPercentage;
    }

    public int getCurrentTime() {
        return bursts;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public int getTimeLeft() {
        return burstTime - bursts;
    }

    public int getResponseTime() {
        return firstBurst - arrivalTime;
    }

    public int setTurnAroundTime(int ticker) {
        return ticker - arrivalTime;
    }

    public int getTurnAroundTime(){
          return turnAroundTime;
        }

    public void incrementWaitTime(int waitTime) {
        this.waitTime += waitTime;
    }

    public void incrementBurstTime() {
        this.bursts++;
    }

    public int generateInstruction() throws Exception {
        // Edge cases
        if (this.getState() != ProcessState.RUNNING) {
            throw new Exception("Process must be running to execute");
        }

        if (this.bursts == this.burstTime - 1) {
            this.setState(ProcessState.TERMINATED);
        }

        return 3000 + this.rng.nextInt(4000);
    }

    public byte[] generateRegisters() throws Exception {
        // Edge cases
        if (this.state != ProcessState.RUNNING) {
            throw new Exception("Process must be running to execute");
        }

        byte[] registers = new byte[4];

        for (int i = 0; i < registers.length; i++) {
            registers[i] = (byte) Math.abs((Math.random() * 256));
        }
        return registers;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

}
