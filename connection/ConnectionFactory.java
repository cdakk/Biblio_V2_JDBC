package Biblio_V2.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	public ConnectionFactory() {}
	
	public Connection getConnection(String chemin) {
		Properties configProperties  = new Properties();
		try {
			configProperties.load(new FileInputStream(chemin));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String nomPilote = configProperties.getProperty("driver");
		String URLBD = configProperties.getProperty("url");
		String authorizationID = configProperties.getProperty("utilisateur");
		String password = configProperties.getProperty("mdp");
		Connection cnx1 = null;
		try {
			Class.forName(nomPilote);
		} catch (ClassNotFoundException e) {
			System.err.println("classe introuvable");
		}
		try {
			cnx1 = DriverManager.getConnection(URLBD, authorizationID, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnx1;
	}
	
	public Connection getConnectionSansAutoCommit(String chemin) {
		Properties configProperties  = new Properties();
		try {
			configProperties.load(new FileInputStream(chemin));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String nomPilote = configProperties.getProperty("driver");
		String URLBD = configProperties.getProperty("url");
		String authorizationID = configProperties.getProperty("utilisateur");
		String password = configProperties.getProperty("mdp");
		Connection cnx1 = null;
		try {
			Class.forName(nomPilote);
		} catch (ClassNotFoundException e) {
			System.err.println("classe introuvable");
		}
		try {
			cnx1 = DriverManager.getConnection(URLBD, authorizationID, password);
			cnx1.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnx1;
	}

}
