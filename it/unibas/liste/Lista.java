package it.unibas.liste;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lista {
	
	private Logger logger = LoggerFactory.getLogger(Lista.class);
	private String nome;
	private String slogan;
	private String promessa;
	private List<Candidato> listaCandidati = new ArrayList<Candidato>();


	public Lista(String nome, String slogan, String promessa) {
		this.nome = nome;
		this.slogan = slogan;
		this.promessa = promessa;
	}

	public String getNome() {
		return this.nome;
	}

	public String getSlogan() {
		return this.slogan;
	}

	public String getPromessa() {
		return this.promessa;
	}

	public void aggiungiCandidato(Candidato candidato) {
		this.listaCandidati.add(candidato);
	}

	public List<Candidato> getListaCandidati() {
		return this.listaCandidati;
	}

	public boolean isEsisteCandidatoSindaco() {
		for(Candidato candidato : this.listaCandidati) {
			if(candidato.isSindaco()) {
				return true;
			}
		}
		return false;
	}

	//VARIANTE 1
	public boolean contieneDuplicati() {
		for(Candidato candidato : this.listaCandidati) {
			if(contaOccorrenzeNomeEta(candidato.getNome(), candidato.getEta()) > 1) {
				logger.debug("Ho trovato un duplicato: " + candidato.toString());
				return true;
			}
		}
		return false;
	}

	private int contaOccorrenzeNomeEta(String nome, int eta) {
		int conta = 0;
		for(Candidato candidato : this.listaCandidati) {
			if(candidato.getNome().equalsIgnoreCase(nome) && candidato.getEta() == eta) {
				conta++;
			}
		}
		return conta;
	}

	//VARIANTE 2
	/*
	public boolean contieneDuplicatiVersione2() {
		Set<Candidato> insiemeCandidati = new HashSet<Candidato>(this.listaCandidati);
		return this.listaCandidati.size() != insiemeCandidati.size();
	}*/

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Lista: ").append(this.nome);
		sb.append(" Slogan: ").append(this.slogan);
		sb.append(" Promessa: ").append(this.promessa);
		sb.append(" Candidati: \n");
		for(Candidato candidato : this.listaCandidati) {
			sb.append(candidato.toString()).append("\n");
		}
		return sb.toString();
	}

}