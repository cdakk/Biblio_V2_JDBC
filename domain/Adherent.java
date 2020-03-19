package Biblio_V2.domain;

import java.time.LocalDate;
import java.util.ArrayList;

import Biblio_V2.exception.BiblioException;

public class Adherent extends Utilisateur {

	private String telephone;
	private static int nbMaxPrets = 3;
	private static int dureeMaxPrets = 15;
	
	private int nbRetards;
	private boolean pretEnRetard; //En plus
	
	/**
	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param sexe
	 * @param idUtilisateur
	 * @param pwd
	 * @param pseudonyme
	 * @param telephone
	 */

	public Adherent(String nom, String prenom, LocalDate dateNaissance, String sexe, int idUtilisateur, String pwd,
			String pseudonyme, ArrayList<EmpruntEnCours> empruntEnCours, String telephone, int nbRetards,
			boolean pretEnRetard) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme, empruntEnCours);
		this.telephone = telephone;
		this.nbRetards = nbRetards;
		this.pretEnRetard = pretEnRetard;
	}
	
	public Adherent(String nom, String prenom, LocalDate dateNaissance, String sexe, int idUtilisateur, String pwd,
			String pseudonyme, String telephone) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme);
		this.telephone = telephone;
	}
	
	public Adherent(String nom, String prenom, LocalDate dateNaissance, String sexe, int idUtilisateur, String pwd,
			String pseudonyme) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme);
	}
	
	//new Adherent(nom, prenom, id, pwd, tel);
	public Adherent(String nom, String prenom, int idUtilisateur, String pwd, String telephone) {
		super(nom, prenom, idUtilisateur, pwd);
		this.telephone = telephone;
	}
	
	public Adherent() {
		super();
	}
	
	//GETTERS--------------------------------------------------
	
	public String getTelephone() {
		return telephone;
	}

	public static int getDureeMaxPrets() {
		return dureeMaxPrets;
	}

	public static int getNbMaxPrets() {
		return nbMaxPrets;
	}
	
	//SETTERS-----------------------------------------------
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public static void setNbMaxPrets(int nbMaxPrets) {
		Adherent.nbMaxPrets = nbMaxPrets;
	}

	public static void setDureeMaxPrets(int dureeMaxPrets) {
		Adherent.dureeMaxPrets = dureeMaxPrets;
	}

	//TOSTRING----------------------------------------
	
	@Override
	public String toString() {
		return "Adherent"
				+ " ["
				+ "nom=" + this.getNom()
				+ ", prenom=" + this.getPrenom()
				+ ", telephone=" + telephone
				+ ", nbEmpruntsEnCours=" + this.getNbEmpruntsEnCours()
				+ ", nbRetards=" + this.getNbRetards()
				+ "]";
	}
	
	//METHODS------------------------------------------
	
	public int getNbRetards() {	
		nbRetards = 0;
		for (int i = 0; i < this.getEmpruntsEnCours().size(); i++) {
			if (this.isPretEnRetard(this.getEmpruntsEnCours().get(i)) == true) {
				nbRetards++;
			}
		}
		return nbRetards;
	}
	
	public boolean isPretEnRetard(EmpruntEnCours empruntEnCours)
	{
		pretEnRetard=false;
		if(empruntEnCours!=null && empruntEnCours.getDateEmprunt()!=null)
		{
			LocalDate today=LocalDate.now();
			LocalDate limite=empruntEnCours.getDateEmprunt().plusDays(dureeMaxPrets);
			if(limite.compareTo(today)<0)
				pretEnRetard=true;
		}		
		return pretEnRetard;
	}

	public boolean isConditionsPretAcceptees() {
		boolean accepte = true;			
		try {
			if (getNbRetards() > 0) {
				accepte = false;
				throw new BiblioException("En retard de prêt !");
			}	
		} catch (BiblioException e) {
			System.out.println(e);
		}
						
		try {
			if (super.getNbEmpruntsEnCours() >= nbMaxPrets) {
				accepte = false;
				throw new BiblioException("Déjà 3 emprunts en cours !");
			}
		} catch (BiblioException e) {
			System.out.println(e);
		}		
		return accepte;		
	}
	
	@Override
	public void addEmpruntEnCours(EmpruntEnCours ep) {
		if (this.isConditionsPretAcceptees()) {
			super.addEmpruntEnCours(ep);
		}
	}
}
