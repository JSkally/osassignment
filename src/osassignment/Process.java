package osassignment;

class Process {
	private final int pid;
	private final int arrivalTime;
	private int burstTime;
	int waitTime;
	int bursts;
	private int firstBurst;
	private final double ioPercentage;
	ProcessState state;

	// Process constructor
	public Process() {
		this(0, 0, 0, 0.0);
	}

	public Process(int pid, int arrTime, int burTime, double ioPct) {
		this.pid = pid;
		this.arrivalTime = arrTime;
		this.burstTime = burTime;
		this.ioPercentage = ioPct;
		this.state = ProcessState.NEW;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}
	
	public void setFirstBurst(int firstBurst){
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
	
	public int getTimeLeft(){
		return burstTime - bursts;
	}
	
	public int getResponseTime(){
		return firstBurst - arrivalTime;
	}
	
	public void incrementWaitTime(int waitTime) {
		this.waitTime += waitTime;
	}

	public int generateInstruction() throws Exception {
		// Edge cases
		if (this.state != ProcessState.RUNNING) {
			throw new Exception("Process must be running to execute");
		}

		this.bursts++;
		return (int) Math.abs((3000 + (Math.random() * (4000 - 3000))));
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
}
