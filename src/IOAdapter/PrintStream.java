package IOAdapter;

class PrintStream implements IOutput {

	private java.io.PrintStream stream;
	
	public PrintStream(java.io.PrintStream stream) {
		this.stream = stream;
	}
	
	@Override
	public void writeLine(String str) {
		this.stream.println(str);
	}
	
	public void write(String str) {
	    this.stream.print(str);
	}

}
