package osassignment;

public class CPU {

	private int PC;
	private byte mode;
	private byte[] registers;

	public CPU() {
		// Registers R0 - R3
		setRegisters(new byte[3]);
	}

	public byte getMode() {
		return mode;
	}

	public byte[] getRegisters() {
		return registers;
	}

	public void setRegisters(byte[] registers) {
		this.registers = registers;
	}

	public int getPC() {
		return PC;
	}

	public void setPC(int pC) {
		PC = pC;
	}

}
