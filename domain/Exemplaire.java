package Biblio_V2.domain;

import java.time.LocalDate;

public class Exemplaire {
	
	private int idExemplaire;
	private LocalDate dateAchat;
	private EnumStatutExemplaire statutExemplaire;
	private String isbn;
	
	private EmpruntEnCours empruntEnCours;
	
	/**
	 * @param idExemplaire
	 * @param dateAchat
	 * @param statutExemplaire
	 * @param isbn
	 * @param empruntEnCours
	 */
	public Exemplaire(int idExemplaire, LocalDate dateAchat, EnumStatutExemplaire statutExemplaire, String isbn) {
		super();
		this.idExemplaire = idExemplaire;
		this.dateAchat = dateAchat;
		this.statutExemplaire = statutExemplaire;
		this.isbn = isbn;
	}

	public Exemplaire(int idExemplaire, LocalDate dateAchat, EnumStatutExemplaire statutExemplaire, String isbn,
			EmpruntEnCours empruntEnCours) {
		super();
		this.idExemplaire = idExemplaire;
		this.dateAchat = dateAchat;
		this.statutExemplaire = statutExemplaire;
		this.isbn = isbn;
		this.empruntEnCours = empruntEnCours;
	}
	
	public Exemplaire(int idExemplaire, LocalDate dateAchat, String isbn) {
		super();
		this.idExemplaire = idExemplaire;
		this.dateAchat = dateAchat;
		this.isbn = isbn;
	}
	
	public Exemplaire(int idExemplaire, String titre) {
		super();
		this.idExemplaire=idExemplaire;
	}
	
	public Exemplaire () {
		super();
	}

	public int getIdExemplaire() {
		return idExemplaire;
	}

	public void setIdExemplaire(int idExemplaire) {
		this.idExemplaire = idExemplaire;
	}

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}

	public EnumStatutExemplaire getStatutExemplaire() {
		return statutExemplaire;
	}

	public void setStatutExemplaire(EnumStatutExemplaire statutExemplaire) {
		this.statutExemplaire = statutExemplaire;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public EmpruntEnCours getEmpruntEnCours() {
		return this.empruntEnCours;
	}

	public void setEmpruntEnCours(EmpruntEnCours empruntEnCours) {
		this.empruntEnCours = empruntEnCours;
	}

	@Override
	public String toString() {
		LocalDate emprunteLe = null;
		if (empruntEnCours != null) {
			emprunteLe = empruntEnCours.getDateEmprunt();
		}
		
		return "Exemplaire"
				+ " ["
				+ "idExemplaire=" + idExemplaire
				+ ", dateAchat=" + dateAchat
				+ ", statutExemplaire=" + statutExemplaire
				+ ", isbn=" + isbn
				+ ", emprunteLe=" + emprunteLe
				+ "]";
	}		
}
