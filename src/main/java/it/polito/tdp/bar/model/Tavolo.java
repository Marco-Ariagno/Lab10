package it.polito.tdp.bar.model;

public class Tavolo {
	
	private int posti;
	private boolean occupato;
	private int id;

	/**
	 * @param posti
	 */
	public Tavolo(int posti, int id) {
		super();
		this.posti = posti;
		this.id=id;
		occupato=false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosti() {
		return posti;
	}

	public void setPosti(int posti) {
		this.posti = posti;
	}

	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + posti;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tavolo other = (Tavolo) obj;
		if (id != other.id)
			return false;
		if (posti != other.posti)
			return false;
		return true;
	}
	
	
	

}
