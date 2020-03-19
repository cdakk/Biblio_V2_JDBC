package Biblio_V2.tests;

import java.sql.SQLException;

import Biblio_V2.connection.ConnectionFactory;
import Biblio_V2.dao.ExemplaireDAO;
import Biblio_V2.dao.UtilisateursDAO;

public class Test1_1_Demande_des_objets_aux_DAO {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory cnx = new ConnectionFactory();
		UtilisateursDAO util = new UtilisateursDAO(cnx.getConnection("jdbc_biblio@localhost.properties"));
		ExemplaireDAO exemp = new ExemplaireDAO(cnx.getConnection("jdbc_biblio@localhost.properties"));

		System.out.println("Demande de deux exemplaires par leur id aux Dao:");	
		System.out.println(exemp.findByKey(22)+ "\n" + exemp.findByKey(23) );	

	}

}
