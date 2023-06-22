package it.unibas.liste;

public class Candidato {

	private String nome;
	private String ruolo;
	private String sesso;
	private int eta;

	public Candidato(String nome, String ruolo, String sesso, int eta) {
		this.nome = nome;
		this.ruolo = ruolo;
		this.sesso = sesso;
		this.eta = eta;
	}

	public String getNome() {
		return this.nome;
	}

	public String getRuolo() {
		return this.ruolo;
	}

	public String getSesso() {
		return this.sesso;
	}

	public int getEta() {
		return this.eta;
	}

	public boolean isSindaco() {
		return this.ruolo.equalsIgnoreCase(Costanti.RUOLO_SINDACO);
	}

	public boolean isDonna() {
		return this.sesso.equalsIgnoreCase("F");
	}

	/* NECESSARIO PER UTILIZZARE IL CANDIDATO ALL'INTERNO DI UN SET
	public int hashCode() {
		return this.toShortString().hashCode();
	}

	public boolean equals(Object o) {
		Candidato altroCandidato = (Candidato) o;
		return this.toShortString().equals(altroCandidato.toShortString());
	}

	private String toShortString() {
		return this.nome + " - " + this.eta;
	} */

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Candidato: ").append(this.nome);
		sb.append(" Ruolo: ").append(this.ruolo);
		sb.append(" Sesso: ").append(this.sesso);
		sb.append(" Eta': ").append(this.eta);
		return sb.toString();
	}
	
} 