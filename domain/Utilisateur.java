package Biblio_V2.domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Utilisateur extends Personne {

	private int idUtilisateur;
	private String pwd;
	private String pseudonyme;

	private ArrayList<EmpruntEnCours> empruntEnCours = new ArrayList<EmpruntEnCours>();
	private int nbEmpruntsEnCours = 0;

	/**
	 * @param idUtilisateur
	 * @param pwd
	 * @param pseudonyme
	 * @param empruntsEnCours
	 */

	public Utilisateur(String nom, String prenom, LocalDate dateNaissance, String sexe, int idUtilisateur, String pwd,
			String pseudonyme, ArrayList<EmpruntEnCours> empruntEnCours) {
		super(nom, prenom, dateNaissance, sexe);
		this.idUtilisateur = idUtilisateur;
		this.pwd = pwd;
		this.pseudonyme = pseudonyme;
		this.empruntEnCours = empruntEnCours;
	}

	public Utilisateur(String nom, String prenom, LocalDate dateNaissance, String sexe, int idUtilisateur, String pwd,
			String pseudonyme) {
		super(nom, prenom, dateNaissance, sexe);
		this.idUtilisateur = idUtilisateur;
		this.pwd = pwd;
		this.pseudonyme = pseudonyme;
	}

	public Utilisateur(String nom, String prenom, int idUtilisateur, String pwd) {
		super(nom, prenom);
		this.idUtilisateur = idUtilisateur;
		this.pwd = pwd;
	}
	
	public Utilisateur() {
		super();
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPseudonyme() {
		return pseudonyme;
	}

	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}

	@Override
	public String toString() {
		return "Utilisateur" + " [" + ", idUtilisateur=" + idUtilisateur + ", pwd=" + pwd + ", pseudonyme=" + pseudonyme
				+ ", nbEmpruntsEnCours=" + this.getNbEmpruntsEnCours() + "]";
	}

	public void addEmpruntEnCours(EmpruntEnCours ep) {
		empruntEnCours.add(ep);
	}

	public ArrayList<EmpruntEnCours> getEmpruntsEnCours() {
		return empruntEnCours;
	}

	public int getNbEmpruntsEnCours() {
		this.nbEmpruntsEnCours = this.empruntEnCours.size();
		return nbEmpruntsEnCours;
	}

	public void removeEmpruntEnCours(EmpruntEnCours ep) {
		empruntEnCours.remove(ep);
	}
}
