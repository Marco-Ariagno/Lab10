package it.polito.tdp.bar.model;

public class Model {
	Simulator sim;

	public Model() {
		sim = new Simulator();
	}

	public void run() {
		sim.run();
	}

	public int getClienti() {
		return sim.getClienti();
	}
	
	public int getSoddisfatti() {
		return (int)sim.getSoddisfatti();
	}
	
	public int getInsoddisfatti() {
		return (int)sim.getInsoddisfatti();
	}
}
