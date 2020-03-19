package Biblio_V2_Swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Biblio_V2.connection.ConnectionFactory;
import Biblio_V2.dao.UtilisateursDAO;
import Biblio_V2.domain.*;
import Biblio_V2.exception.*;

public class ConnectionActivity {

	private Connection connect;
	private JFrame connectionActivity;
	private JTextField txtEntrezVotreNom;
	static Integer KEY;
	static String cat;
	ConnectionFactory cnx = new ConnectionFactory();
	UtilisateursDAO util = new UtilisateursDAO(cnx.getConnection("jdbc_biblio@localhost.properties"));
	private JTextField textField;
	private JLabel lblConnect;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectionActivity window = new ConnectionActivity();
					window.connectionActivity.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	ConnectionActivity() throws UnknownHostException {

		connectionActivity = new JFrame();
		connectionActivity.setBackground(Color.LIGHT_GRAY);
		connectionActivity.getContentPane().setBackground(Color.WHITE);
		connectionActivity.setTitle("Biblioth�que / Se Connecter");
		connectionActivity.setBounds(100, 100, 507, 331);
		connectionActivity.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		connectionActivity.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Identifiant Utilisateur");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(40, 94, 168, 20);
		connectionActivity.getContentPane().add(lblNewLabel);

		txtEntrezVotreNom = new JTextField();
		txtEntrezVotreNom.setText("Entrez votre nom");
		// txtEntrezVotreNom.setText("1");
		txtEntrezVotreNom.setBounds(40, 125, 402, 31);
		connectionActivity.getContentPane().add(txtEntrezVotreNom);
		txtEntrezVotreNom.setColumns(10);
		txtEntrezVotreNom.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtEntrezVotreNom.getText().equals("Entrez votre nom")) {
					txtEntrezVotreNom.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if (txtEntrezVotreNom.getText().equals("")) {
					txtEntrezVotreNom.setText("Entrez votre nom");
				}
			}
		});

		// BOUTON CONNECTION
		JButton btnOk = new JButton("Se connecter");
		btnOk.setForeground(new Color(255, 255, 255));
		btnOk.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnOk.setBackground(new Color(51, 153, 102));
		btnOk.setBounds(40, 233, 402, 31);
		connectionActivity.getContentPane().add(btnOk);

		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMotDePasse.setBounds(40, 167, 168, 20);
		connectionActivity.getContentPane().add(lblMotDePasse);

		textField = new JTextField();
		textField.setText("Entrez votre mot de passe");
		// textField.setText("1");
		textField.setColumns(10);
		textField.setBounds(40, 191, 402, 31);
		connectionActivity.getContentPane().add(textField);

		lblConnect = new JLabel("Connexion");
		lblConnect.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblConnect.setBounds(159, 36, 168, 20);
		connectionActivity.getContentPane().add(lblConnect);
		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (textField.getText().equals("Entrez votre mot de passe")) {
					textField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if (textField.getText().equals("")) {
					textField.setText("Entrez votre mot de passe");
				}
			}

		});

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String i1 = String.valueOf(txtEntrezVotreNom.getText());
				Boolean s = isNumeric(String.valueOf(txtEntrezVotreNom.getText()));
				if (s) {
					KEY = Integer.valueOf(txtEntrezVotreNom.getText());
					cat = util.findCatByKey(KEY);
					if (cat.equals("ADHERENT") && textField.getText().equals(i1)) {
						Biblio_V2.domain.Adherent a1 = (Biblio_V2.domain.Adherent) util.findByKey(KEY);
						try {
							new EmpruntActivity();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (BiblioException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						connectionActivity.dispose();
					} else if (cat.equals("EMPLOYE") && textField.getText().equals(i1)) {
						Employe employe = (Employe) util.findByKey(KEY);
						try {
							new EmpruntActivity();

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (BiblioException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						connectionActivity.dispose();
					}				

					else {
						JOptionPane.showMessageDialog(null, "Utilisateur inconnu");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Saisir un num�rique");
				}
			}
		});

	}

	public static boolean isNumeric(final String str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
}