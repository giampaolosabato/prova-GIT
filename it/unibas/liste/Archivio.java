package it.unibas.liste;

import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Archivio {
	
	private Logger logger = LoggerFactory.getLogger(Archivio.class);
	private List<Lista> listeElettorali = new ArrayList<Lista>();

	public void aggiungiListaElettorale(Lista listaElettorale) {
		this.listeElettorali.add(listaElettorale);
	}

	public Lista cercaListaEsistente(String nome) {
		for(Lista listaElettorale : this.listeElettorali) {
			if(nome.equalsIgnoreCase(listaElettorale.getNome())) {
				return listaElettorale;
			}
		}
		return null;
	}


	public List<Candidato> cercaCandidatiSindacoDonne() {
		List<Candidato> listaFiltrata = new ArrayList<Candidato>();
		for(Lista listaElettorale : this.listeElettorali) {
			for(Candidato candidato : listaElettorale.getListaCandidati()) {
				if(candidato.isSindaco() && candidato.isDonna()) {
					logger.debug("Ho trovato un nuovo candidato donna sindaco " + candidato.toString());
					listaFiltrata.add(candidato);
				}
			}
		}
		return listaFiltrata;
	}

	public double calcolaMedia(List<Candidato> listaFiltrata) {
		double somma = 0.0;
		for(Candidato candidato : listaFiltrata) {
			somma += candidato.getEta();
		}
		logger.debug("Calcolo la media. Somma: " + somma + " - Dimensione lista: " + listaFiltrata.size());
		return somma / listaFiltrata.size();
	}

}