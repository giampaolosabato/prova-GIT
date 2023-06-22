package it.unibas.liste;

import java.io.*;
import it.unibas.utilita.Console;
import java.util.List;

public class Principale {

	public void esegui() {
		Archivio archivio = new Archivio();
		while(true) {
			int scelta = this.leggiScelta();
			if(scelta == 0) {
				break;
			} else if(scelta == 1) {
				this.leggiDatiLista(archivio);
			} else if(scelta == 2) {
				this.leggiDatiCandidato(archivio);
			} else if(scelta == 3) {
				List<Candidato> listaCandidatiSindacoDonne = archivio.cercaCandidatiSindacoDonne();
				if(listaCandidatiSindacoDonne.isEmpty()) {
					System.out.println("Non esiste nessun candidato sindaco donna.");
				} else {
					double media = archivio.calcolaMedia(listaCandidatiSindacoDonne);
					System.out.println("La media dell'eta' dei candidati sindaco donna e': " + media);
				}
			} else if(scelta == 4) {
				System.out.print("Di quale lista vuoi verificare duplicati? ");
				String nomeLista = this.leggiStringaNonVuota();
				Lista listaElettorale = archivio.cercaListaEsistente(nomeLista);
				if(listaElettorale == null) {
					System.out.println("Non esiste alcuna lista chiamata " + nomeLista);
				} else {
					boolean verificaDuplicati = listaElettorale.contieneDuplicati();
					if(verificaDuplicati) {
						System.out.println("La lista contiene duplicati");
					} else {
						System.out.println("La lista non contiene duplicati");
					}
				}
			
			}
			/*
			if(scelta == 5) {
				String tutto = "abcdefghilm";
				String s = tutto.substring(0, 4);
				System.out.println(tutto.substring(0, 4));
			}*/
		}
		System.out.println("Arrivederci...");
	}

	private void leggiDatiCandidato(Archivio archivio) {
		System.out.print("Nome della lista in cui inserire il candidato: ");
		String nomeLista = this.leggiStringaNonVuota();
		Lista listaElettorale = archivio.cercaListaEsistente(nomeLista);
		if(listaElettorale == null) {
			System.out.println("Non esiste alcuna lista chiamata " + nomeLista);
			return;
		}
		System.out.print("Lista selezionata:\n" + listaElettorale.toString());
		System.out.print("Nome del candidato: ");
		String nome = this.leggiStringaNonVuota();
		System.out.print("Ruolo del candidato: ");
		String ruolo = this.leggiStringaNonVuota();
		while(ruolo.equalsIgnoreCase(Costanti.RUOLO_SINDACO) && listaElettorale.isEsisteCandidatoSindaco()) {
			System.out.print("Esiste gia' un candidato a sindaco per la lista corrente. Inserisci un nuovo ruolo: ");
			ruolo = this.leggiStringaNonVuota();
		}
		System.out.print("Sesso del candidato: ");
		String sesso = this.leggiSesso(); 
		System.out.print("Eta' del candidato: ");
		int eta = Console.leggiIntero(); //TODO: Fare la convalida dell'eta'...
		Candidato candidato = new Candidato(nome, ruolo, sesso, eta);
		listaElettorale.aggiungiCandidato(candidato);
		System.out.println("Candidato aggiunto correttamente...");
	}

	private void leggiDatiLista(Archivio archivio) {
		System.out.print("Nome della lista: ");
		String nome = this.leggiStringaNonVuota();
		while(archivio.cercaListaEsistente(nome) != null) {
			System.out.print("Nome della lista gia' esistente. Riprova: ");
			nome = this.leggiStringaNonVuota();
		}
		System.out.print("Slogan della lista: ");
		String slogan = this.leggiStringaNonVuota();
		System.out.print("Principale promessa elettorale: ");
		String promessa = this.leggiStringaNonVuota();
		Lista listaElettorale = new Lista(nome, slogan, promessa);
		archivio.aggiungiListaElettorale(listaElettorale);
		System.out.println("La nuova lista e' stata aggiunta correttamente:\n" + listaElettorale.toString());
	}

	private String leggiSesso() {
		String s = this.leggiStringaNonVuota();
		while(!s.equalsIgnoreCase("m") && !s.equalsIgnoreCase("f")) {
			System.out.print("Errore, inserire m/f: ");
			s = leggiStringaNonVuota();
		}
		return s;
	}

	private String leggiStringaNonVuota() {
		String stringa = Console.leggiStringa();
		while(stringa.trim().isEmpty()) {
			System.out.print("Il valore non puo' essere vuoto. Riprova: ");
			stringa = Console.leggiStringa();
		}
		return stringa.trim();
	}

	private int leggiScelta() {
		System.out.println("--------------------------");
		System.out.println("     Liste Elettorali");
		System.out.println("--------------------------");
		System.out.println("1) Inserisci una nuova lista");
		System.out.println("2) Inserisci un nuovo candidato");
		System.out.println("3) Calcola media eta' donne sindaco");
		System.out.println("4) Verifica duplicati");
		System.out.println("--------------------------");
		System.out.println("0) Esci");
		System.out.println("--------------------------");
		int scelta = Console.leggiIntero();
		while(scelta < 0 || scelta > 5) {
			System.out.print("Scelta non corretta. Riprova con un numero tra 0 e 4: ");
			scelta = Console.leggiIntero();
		}
		return scelta;
	}

	public static void main(String[] args) {
		new Principale().esegui();
	}
	
}