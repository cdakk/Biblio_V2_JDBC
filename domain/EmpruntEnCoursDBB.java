package Biblio_V2.domain;

import java.time.LocalDate;

import Biblio_V2.exception.BiblioException;

public class EmpruntEnCoursDBB extends EmpruntEnCours {

	private Integer idExemplaire;
	private Integer idUtilisateur;
	
	@Override
	public String toString() {
		return super.toString() + "idExemplaire" + idExemplaire + "idUtlisateur" + idUtilisateur;
	}
	
	public EmpruntEnCoursDBB(LocalDate dateEmprunt, Utilisateur emprunteur, Exemplaire exemplaire, Integer idExemplaire,
			Integer idUtilisateur) throws BiblioException{
		super(dateEmprunt, emprunteur, exemplaire);
		this.idExemplaire = idExemplaire;
		this.idUtilisateur = idUtilisateur;
	}
	
	public EmpruntEnCoursDBB(Integer idExemplaire, Integer idUtilisateur, LocalDate dateEmprunt) {
		super();
		this.idExemplaire = idExemplaire;
		this.idUtilisateur = idUtilisateur;
	}
	
	public EmpruntEnCoursDBB(Integer idExemplaire, Integer idUtilisateur) {
		super();
		this.idExemplaire = idExemplaire;
		this.idUtilisateur = idUtilisateur;
	}
	
	public EmpruntEnCoursDBB() {
		super();
	}
	
	// GETTERS
	
	public Integer getIdExemplaire() {
		return idExemplaire;
	}
	
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	//SETTERS
	public void setIdExemplaire(Integer idExemplaire) {
		this.idExemplaire = idExemplaire;
	}
	
	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
}
	


