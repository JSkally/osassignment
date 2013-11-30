public class CPU {

	private int PC;
	private byte mode;
	private byte[] registers;

	public CPU() {
		// Registers R0 - R3
		setRegisters(new byte[4]);
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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PC: " + this.PC + "\n Registers:\n");
		for (int i = 0; i < this.registers.length; i++) {
			sb.append("R" + i + ": " + registers[i] + "\n");
		}

		return sb.toString();
	}

}
