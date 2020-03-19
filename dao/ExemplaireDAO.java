package Biblio_V2.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Biblio_V2.domain.EnumStatutExemplaire;
import Biblio_V2.domain.Exemplaire;

public class ExemplaireDAO {

	private Connection cnx1;

	public ExemplaireDAO(Connection cnx1) {
		this.cnx1 = cnx1;
	}

	public Exemplaire findByKey(Integer id) throws SQLException {
		PreparedStatement pstmt;

		Exemplaire exemplaire = null;
		Integer idExemplaire = null;
		LocalDate dateAchat = null;
		String status = null;
		String Isbn = "";

		try {
			pstmt = cnx1.prepareStatement("select exemplaire.idexemplaire, exemplaire.dateachat,"
					+ "exemplaire.status, exemplaire.isbn from exemplaire where exemplaire.idexemplaire=?");

			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				idExemplaire = result.getInt(1);
				Date d = result.getDate(2);
				dateAchat = d.toLocalDate();
				status = result.getString(3);
				EnumStatutExemplaire status2 = EnumStatutExemplaire.valueOf(status);
				Isbn = result.getString(4);

				exemplaire = new Exemplaire(idExemplaire, dateAchat, status2, Isbn);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exemplaire;
	}

	public ArrayList<Exemplaire> findAllExemplaires() {

		PreparedStatement pstmt;

		Exemplaire exemplaire = null;
		Integer idExemplaire = null;
		String titre = "";
		ArrayList<Exemplaire> Exemplaires = new ArrayList<Exemplaire>();

		try {

			pstmt = cnx1.prepareStatement("SELECT exemplaire.idexemplaire,livre.titre, "
					+ "exemplaire.dateachat, exemplaire.isbn, exemplaire.status"
					+ " from exemplaire join livre on exemplaire.isbn = livre.isbn and exemplaire.status = 'DISPONIBLE' ");

			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				idExemplaire = result.getInt(1);
				titre = result.getString(5);
				exemplaire = new Exemplaire(idExemplaire, titre);
				Exemplaires.add(exemplaire);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Exemplaires;
	}

	public ArrayList<Exemplaire> findAllExemplaires2() {

		PreparedStatement pstmt;

		Exemplaire exemplaire = null;
		Integer idExemplaire = null;
		String titre = "";
		ArrayList<Exemplaire> Exemplaires = new ArrayList<Exemplaire>();

		try {

			pstmt = cnx1.prepareStatement("SELECT exemplaire.idexemplaire,livre.titre, "
					+ "exemplaire.dateachat, exemplaire.isbn, exemplaire.status"
					+ " from exemplaire join livre on exemplaire.isbn = livre.isbn and exemplaire.status = 'PRETE' ");

			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				idExemplaire = result.getInt(1);
				titre = result.getString(5);
				exemplaire = new Exemplaire(idExemplaire, titre);
				Exemplaires.add(exemplaire);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Exemplaires;
	}

}
