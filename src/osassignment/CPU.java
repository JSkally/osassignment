package osassignment;

public class CPU {

	private byte PC;
	private byte mode;
	private byte R0;
	private byte R1;
	private byte R2;
	private byte R3;

	public byte getPC() {
		return PC;
	}

	public void setPC(byte pC) {
		PC = pC;
	}

	public byte getMode() {
		return mode;
	}

	public void setMode(byte mode) {
		this.mode = mode;
	}

	public byte getR0() {
		return R0;
	}

	public void setR0(byte r0) {
		R0 = r0;
	}

	public byte getR1() {
		return R1;
	}

	public void setR1(byte r1) {
		R1 = r1;
	}

	public byte getR2() {
		return R2;
	}

	public void setR2(byte r2) {
		R2 = r2;
	}

	public byte getR3() {
		return R3;
	}

	public void setR3(byte r3) {
		R3 = r3;
	}

}
