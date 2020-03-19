package Biblio_V2.domain;

import java.time.LocalDate;

public class EmpruntEnCours {
	
	private LocalDate dateEmprunt;
	private Utilisateur emprunteur;
	private Exemplaire exemplaire;
	
	/**
	 * @param dateEmprunt
	 * @param emprunteur
	 * @param exemplaire
	 */
	public EmpruntEnCours(LocalDate dateEmprunt, Utilisateur emprunteur, Exemplaire exemplaire) {
		super();
		this.dateEmprunt = dateEmprunt;
		this.emprunteur = emprunteur;
		this.exemplaire = exemplaire;
	}
	
	public EmpruntEnCours() {
		super();
	}

	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public Utilisateur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Utilisateur emprunteur) {
		this.emprunteur = emprunteur;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	@Override
	public String toString() {
		return "EmpruntEnCours"
				+ " ["
				+ "dateEmprunt=" + dateEmprunt
				+ ", emprunteur=" + emprunteur
				+ ", exemplaire=" + exemplaire
				+ "]";
	}	
}
