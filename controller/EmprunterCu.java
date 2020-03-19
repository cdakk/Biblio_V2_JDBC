package Biblio_V2.controller;

import Biblio_V2.connection.ConnectionFactory;
import Biblio_V2.dao.ExemplaireDAO;
import Biblio_V2.dao.UtilisateursDAO;

public class EmprunterCu {
	ConnectionFactory cnx = new ConnectionFactory();
	UtilisateursDAO util = new UtilisateursDAO(cnx.getConnection("jdbc_biblio@localhost.properties"));
	ExemplaireDAO exemp = new ExemplaireDAO(cnx.getConnection("jdbc_biblio@localhost.properties"));

}
