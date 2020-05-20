package it.polito.tdp.bar.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event implements Comparable<Event>{

	public enum EventType{
		NEW_CLIENTS, TABLE_FREE
	}
	
	private LocalDateTime time;
	private EventType type;
	private int numPersone;
	private int durata;
	private float tolleranza;
	private Tavolo occupato;
	
	/**
	 * @param time
	 * @param type
	 * @param numPersone
	 * @param durata
	 * @param tolleranza
	 */
	public Event(LocalDateTime time, EventType type, int numPersone, int durata, float tolleranza) {
		super();
		this.time = time;
		this.type = type;
		this.numPersone = numPersone;
		this.durata = durata;
		this.tolleranza = tolleranza;
	}
	/**
	 * @param time
	 * @param type
	 */
	public Event(LocalDateTime time, EventType type, Tavolo occupato) {
		super();
		this.time = time;
		this.type = type;
		this.occupato=occupato;
		
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	
	
	public int getNumPersone() {
		return numPersone;
	}
	public void setNumPersone(int numPersone) {
		this.numPersone = numPersone;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public float getTolleranza() {
		return tolleranza;
	}
	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}
	
	public Tavolo getOccupato() {
		return occupato;
	}
	public void setOccupato(Tavolo occupato) {
		this.occupato = occupato;
	}
	public int compareTo(Event other) {
		return this.time.compareTo(other.getTime());
	}
	@Override
	public String toString() {
		return "Event [time=" + time + ", type=" + type + ", numPersone=" + numPersone + ", durata=" + durata
				+ ", tolleranza=" + tolleranza + "]";
	}
	
	
	
}
