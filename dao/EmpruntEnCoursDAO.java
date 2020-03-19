package Biblio_V2.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Biblio_V2.domain.EmpruntEnCoursDBB;
import Biblio_V2.exception.BiblioException;

public class EmpruntEnCoursDAO {
	private Connection cnx1;
	
	public EmpruntEnCoursDAO(Connection cnx1) {
		super();
		this.cnx1 = cnx1;
	}

	public EmpruntEnCoursDBB findByKey(Integer idExemplaire) throws SQLException{
		
		Statement stmt = cnx1.createStatement();
		@SuppressWarnings("unused")
		ResultSet resultSet = stmt
				.executeQuery("SELECT * from empruntencours " + "where idexemplaire = " + idExemplaire);
		return null;
	}
	
	public ArrayList<EmpruntEnCoursDBB> findAllByUtilisateur(Integer idUtilisateur) throws SQLException{
		cnx1.setAutoCommit(false);
		Statement stmt = cnx1.createStatement();
		ResultSet resultSet = stmt.executeQuery("SELECT idexemplaire, idutilisateur, " + "dateemprunt from EMPRUNTENCOURS where idutilisateur =" + idUtilisateur);
		ArrayList<EmpruntEnCoursDBB> empruntEnCoursDBB = new ArrayList<EmpruntEnCoursDBB>();
		while (resultSet.next()) {
			LocalDate date = resultSet.getDate(3).toLocalDate();
			empruntEnCoursDBB.add(new EmpruntEnCoursDBB(resultSet.getInt(1), resultSet.getInt(2), date));
		}
		stmt.close();
		return empruntEnCoursDBB;
	}
	
	public void insertEmpruntEnCours(EmpruntEnCoursDBB empruntEnCours) throws SQLException{
		cnx1.setAutoCommit(false);
		Statement stmt = cnx1.createStatement();
		Integer idexemplaire = empruntEnCours.getIdExemplaire();
		Integer idutilisateur = empruntEnCours.getIdUtilisateur();
		LocalDate dateemprunt = empruntEnCours.getDateEmprunt();
		String date = dateemprunt.format(DateTimeFormatter.ofPattern("dd/MM/yyy"));
		stmt.executeUpdate("INSERT INTO EMPRUNTENCOURS (idexemplaire, idutilisateur, dateemprunt) VALUES " + "("+ idexemplaire + "," + idutilisateur + ", to_date('" + date + "' ,'DD-MM-YYYY'" + ")");
		cnx1.commit();
	}

	public boolean deleteEmpruntEnCours(int idExemplaire) throws SQLException, BiblioException, IOException{
		int sql = 0;
		
		String request = "SELECT idexemplaire, idutilisateur, dateemprunt"
						+ "FROM empruntencours WHERE idexemplaire = " + idExemplaire;
		try {
			Statement stmt = cnx1.createStatement();
			ResultSet result = stmt.executeQuery(request);
			if (result.next()) {
				request = "DELETE FROM empruntencours WHERE idexemplaire = " + idExemplaire;
				stmt.executeUpdate(request);
				sql = stmt.executeUpdate(request);
				cnx1.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sql == 1 ? true : false;
	}
}
