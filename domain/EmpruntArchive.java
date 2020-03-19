package Biblio_V2.domain;

import java.time.LocalDate;

public class EmpruntArchive {

	private LocalDate dateEmprunt;
	private LocalDate dateRestitutionEff;
	private Utilisateur emprunteur;
	private Exemplaire exemplaire;

	/**
	 * @param dateEmprunt
	 * @param dateRestitutionEff
	 */

	public EmpruntArchive(LocalDate dateEmprunt, LocalDate dateRestitutionEff, Utilisateur emprunteur,
			Exemplaire exemplaire) {
		super();
		this.dateEmprunt = dateEmprunt;
		this.dateRestitutionEff = dateRestitutionEff;
		this.emprunteur = emprunteur;
		this.exemplaire = exemplaire;
	}

	public EmpruntArchive(LocalDate dateEmprunt, LocalDate dateRestitutionEff, Utilisateur emprunteur) {
		super();
		this.dateEmprunt = dateEmprunt;
		this.dateRestitutionEff = dateRestitutionEff;
		this.emprunteur = emprunteur;
	}

	public EmpruntArchive(LocalDate dateEmprunt, LocalDate dateRestitutionEff) {
		super();
		this.dateEmprunt = dateEmprunt;
		this.dateRestitutionEff = dateRestitutionEff;
	}

	public EmpruntArchive() {
		super();
	}

	// GETTERS-----------------------------------------------

	public Utilisateur getEmprunteur() {
		return emprunteur;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	public LocalDate getDateRestitutionEff() {
		return dateRestitutionEff;
	}

	// SETTERS-----------------

	public void setEmprunteur(Utilisateur emprunteur) {
		this.emprunteur = emprunteur;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public void setDateRestitutionEff(LocalDate dateRestitutionEff) {
		this.dateRestitutionEff = dateRestitutionEff;
	}

	// TOSTRING--------------------------
	@Override
	public String toString() {
		return "EmpruntArchive" + " [" + "dateEmprunt=" + dateEmprunt + ", dateRestitutionEff=" + dateRestitutionEff
				+ "]";
	}
}
