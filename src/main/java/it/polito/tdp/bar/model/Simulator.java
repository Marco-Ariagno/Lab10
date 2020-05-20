package it.polito.tdp.bar.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {

	private PriorityQueue<Event> queue = new PriorityQueue<>();

	private List<Tavolo> tavoli = new LinkedList<>();

	private int numeroEventi = 2000;

	private int clienti;
	private double soddisfatti;
	private double insoddisfatti;

	public int getClienti() {
		return clienti;
	}

	public void setClienti(int clienti) {
		this.clienti = clienti;
	}

	public double getSoddisfatti() {
		return soddisfatti;
	}

	public void setSoddisfatti(double soddisfatti) {
		this.soddisfatti = soddisfatti;
	}

	public double getInsoddisfatti() {
		return insoddisfatti;
	}

	public void setInsoddisfatti(double insoddisfatti) {
		this.insoddisfatti = insoddisfatti;
	}

	public void run() {
		tavoli.add(new Tavolo(10, 1));
		tavoli.add(new Tavolo(10, 2));
		for (int i = 1; i <= 4; i++) {
			tavoli.add(new Tavolo(8, i));
			tavoli.add(new Tavolo(6, i));
			tavoli.add(new Tavolo(4, i));
		}
		tavoli.add(new Tavolo(4, 5));

		this.clienti = 0;
		this.insoddisfatti = this.soddisfatti = 0.0;

		LocalDateTime oraArrivoCliente = LocalDateTime.of(2020, 05, 18, 19, 00);

		this.queue.clear();

		int contatore = 0;
		do {
			contatore++;
			int clienti = (int) ((Math.random() + 0.1) * 10);
			double rand = Math.random();
			int min = 0;
			if (rand < 1.0 / 2.0) {
				min = 60;
			} else
				min = 120;

			int toll = (int) (Math.random() * 10.0);
			float tolleranza = (float) (toll / 10.0);
			Event e = new Event(oraArrivoCliente, EventType.NEW_CLIENTS, clienti, min, tolleranza);
			this.queue.add(e);

			int minTraClienti = (int) ((Math.random() + 0.1) * 10);

			// System.out.println(minuti);
			oraArrivoCliente = oraArrivoCliente.plus(minTraClienti, ChronoUnit.MINUTES);

		} while (contatore < numeroEventi);

		while (!this.queue.isEmpty()) {
			// Estraggo eventi in ordine di tempo, garantita dal compareTo()
			Event e = this.queue.poll();
			System.out.println(e);

			processEvent(e);
		}
		System.out.println("Ci sono stati " + clienti + " di cui soddisfatti " + soddisfatti + " e insoddisfatti "
				+ insoddisfatti);

	}

	public Tavolo getPiuPiccoloLibero(int persone) {
		boolean tuttiOccupati = true;
		List<Tavolo> tavoliLiberi = new LinkedList<>();
		for (Tavolo t : tavoli) {
			if (!t.isOccupato()) {
				tuttiOccupati = false;
				tavoliLiberi.add(t);
			}
		}
		if (tuttiOccupati == true)
			return null;
		List<Tavolo> tavoliOk = new LinkedList<>();
		for (Tavolo t : tavoliLiberi) {
			if ((t.getPosti() / 2.0) <= persone && t.getPosti() >= persone) {
				tavoliOk.add(t);
			}
		}
		if (tavoliOk.size() == 0) {
			return null;
		}
		int min = 50;
		int indice = -1;
		for (int i = 0; i < tavoliOk.size(); i++) {
			if (tavoliOk.get(i).getPosti() < min) {
				min = tavoliOk.get(i).getPosti();
				indice = i;
			}
		}
		return tavoliOk.get(indice);
	}

	private void processEvent(Event e) {
		switch (e.getType()) {
		case NEW_CLIENTS:
			clienti = clienti + e.getNumPersone();
			Tavolo tavoloAssegnato = getPiuPiccoloLibero(e.getNumPersone());
			if (tavoloAssegnato == null) {
				if(Math.random()>e.getTolleranza()) {
					insoddisfatti+=e.getNumPersone();
				}
				else {
					soddisfatti+=e.getNumPersone();
				}
			} else {
				int index = tavoli.indexOf(tavoloAssegnato);
				tavoli.get(index).setOccupato(true);
				soddisfatti = soddisfatti + e.getNumPersone();
				Event nuovo = new Event(e.getTime().plus(e.getDurata(), ChronoUnit.MINUTES), EventType.TABLE_FREE,
						tavoloAssegnato);
				queue.add(nuovo);
			}
			break;

		case TABLE_FREE:

			int index = tavoli.indexOf(e.getOccupato());
			if (index != -1)
				tavoli.get(index).setOccupato(false);
			
			break;

		}
		

	}

}
