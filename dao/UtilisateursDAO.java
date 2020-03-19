package Biblio_V2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Biblio_V2.domain.Adherent;
import Biblio_V2.domain.Employe;
import Biblio_V2.domain.EnumCategorieEmploye;
import Biblio_V2.domain.Utilisateur;

public class UtilisateursDAO {

	private Connection cnx1;

	public UtilisateursDAO(Connection cnx1) {
		this.cnx1 = cnx1;
	}

	public Utilisateur findByKey(int idUser) {
		PreparedStatement pstmt;
		Utilisateur user = null;
		String id = "";
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String code = "";
		String cat_employe = "";
		try {
			pstmt = cnx1
					.prepareStatement("select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, utilisateur.prenom, categorie_utilisateur, telephone, codematricule, categorieemploye "
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=?");
			pstmt.setInt(1, idUser);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				id = result.getString(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(5);
				if (cat.equals("ADHERENT")) {
					tel = result.getString(6);
					user = new Adherent(nom, prenom, null, id, idUser, pwd, tel, cat_employe);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toLowerCase());
					user = new Employe(nom, prenom, null, id, idUser, pwd, code, cat2);
				}

			}

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
	
	public String findCatByKey(int idUser) {
		PreparedStatement pstmt;
		Utilisateur user = null;
		String id = "";
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String code = "";
		String cat_employe = "";
		try {
			pstmt = cnx1
					.prepareStatement("select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, utilisateur.prenom, categorie_utilisateur, telephone, codematricule, categorieemploye "
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=?");
			pstmt.setInt(1, idUser);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				id = result.getString(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(5);
				if (cat.equals("ADHERENT")) {
					tel = result.getString(6);
					user = new Adherent(nom, prenom, null, id, idUser, pwd, tel, cat_employe);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toLowerCase());
					user = new Employe(nom, prenom, null, id, idUser, pwd, code, cat2);
				}
				if (cat.equals("GESTIONNAIRE")) {
					code = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toLowerCase());
					user = new Employe(nom, prenom, null, id, idUser, pwd, code, cat2);
				}

			}

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cat;
	}

	public ArrayList<Utilisateur> findAll() {
		ArrayList<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();
		Utilisateur user = null;
		String id = "";
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String code = "";
		String cat_employe = "";
		try {
			Statement stmt = cnx1.createStatement();
			ResultSet result = stmt
					.executeQuery("select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, utilisateur.prenom, categorie_utilisateur, telephone, codematricule, categorieemploye "
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+)");
			while (result.next()) {
				id = result.getString(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(5);
				if (cat.equals("ADHERENT")) {
					tel = result.getString(6);
					user = new Adherent(nom, prenom, null, id, 0, pwd, tel, cat_employe);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toLowerCase());
					user = new Employe(nom, prenom, null, id, 0, pwd, code, cat_employe, cat2);
				}

				listUtilisateur.add(user);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUtilisateur;
	}

}
