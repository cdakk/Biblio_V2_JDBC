package Biblio_V2_Swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import Biblio_V2.connection.ConnectionFactory;
import Biblio_V2.dao.EmpruntEnCoursDAO;
import Biblio_V2.dao.ExemplaireDAO;
import Biblio_V2.dao.UtilisateursDAO;
import Biblio_V2.domain.*;
import Biblio_V2.exception.*;

public class EmpruntActivity extends JFrame {

	ConnectionFactory cnx = new ConnectionFactory();
	UtilisateursDAO util = new UtilisateursDAO(cnx.getConnection("jdbc_biblio@localhost.properties"));
	ExemplaireDAO exemp = new ExemplaireDAO(cnx.getConnection("jdbc_biblio@localhost.properties"));
	EmpruntEnCoursDAO emp = new EmpruntEnCoursDAO(cnx.getConnection("jdbc_biblio@localhost.properties"));
	
	// JLIST 1
		JList<EmpruntEnCoursDBB> list1 = new JList<>();
		DefaultListModel<EmpruntEnCoursDBB> modelempruntencoursDBB = new DefaultListModel<>();
		// JLIST 2
		JList<Exemplaire> list2 = new JList<>();
		DefaultListModel<Exemplaire> modelexemplaire = new DefaultListModel<>();
		// LISTS
		ArrayList<Exemplaire> exemplaires = new ArrayList<Exemplaire>(exemp.findAllExemplaires());
		ArrayList<Exemplaire> exemplairesnondisponibles = new ArrayList<Exemplaire>(exemp.findAllExemplaires2());
		ArrayList<EmpruntEnCoursDBB> empruntencourslist = new ArrayList<EmpruntEnCoursDBB>();
		// ATTRIBUTS
		static Integer KEY = ConnectionActivity.KEY;
		static String cat = ConnectionActivity.cat;
		String catemploye = "";
		Utilisateur user = new Utilisateur();
		private static int i = 0; // VARIABLE PERMETTANT DE SE POSITIONNER A LA LIGNE
		private static final long serialVersionUID = 1L;
		private final ButtonGroup buttonGroup = new ButtonGroup();
		private JTextField txtCompteDeLutilisateur;
		private JFrame empruntActivity;
		private JLabel txtListeDesExemplaires;

		EmpruntActivity() throws SQLException, BiblioException {
			if (cat.equals("ADHERENT")) {
				user = (Adherent) util.findByKey(KEY);
			} else if (cat.equals("EMPLOYE")) {
				user = (Employe) util.findByKey(KEY);
				Employe employe = (Employe) util.findByKey(KEY);
				catemploye = String.valueOf(employe.getCategorieEmploye());
			}

			JFrame emprunter = new JFrame();
			emprunter.getContentPane().setBackground(Color.WHITE);
			emprunter.setTitle("Compte / Gestion des emprunts");
			emprunter.setBounds(100, 100, 531, 349);
			emprunter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			emprunter.getContentPane().setLayout(null);
			emprunter.setVisible(true);
			setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
			setTitle("Welcome");
			setSize(500, 500);

			// CHECKBOX
			JCheckBox chckbxPremier = new JCheckBox("Disponibles");
			chckbxPremier.setBackground(new Color(255, 255, 255));
			chckbxPremier.setSelected(true);
			buttonGroup.add(chckbxPremier);
			chckbxPremier.setFont(new Font("Tahoma", Font.BOLD, 11));
			chckbxPremier.setBounds(141, 155, 97, 23);
			emprunter.getContentPane().add(chckbxPremier);
			JCheckBox chckbxSecond = new JCheckBox("Indisponibles");
			chckbxSecond.setBackground(new Color(255, 255, 255));
			chckbxSecond.setAutoscrolls(true);
			buttonGroup.add(chckbxSecond);
			chckbxSecond.setFont(new Font("Tahoma", Font.BOLD, 11));
			chckbxSecond.setBounds(242, 155, 108, 23);
			emprunter.getContentPane().add(chckbxSecond);

			JLabel listexemp = new JLabel("Exemplaires");
			listexemp.setFont(new Font("Arial", Font.BOLD, 13));
			@SuppressWarnings("unused")
			JScrollPane s = new JScrollPane(listexemp);
			listexemp.setBounds(42, 117, 97, 18);
			listexemp.setBounds(42, 117, 97, 18);

			/** METHODES CHECKBOX1 AFFICHE LES EXEMPLAIRES DISPONIBLES **/
			chckbxPremier.addActionListener(new ActionListener() {
				@SuppressWarnings("unchecked")
				@Override
				public void actionPerformed(ActionEvent event) {
					list2.setModel(modelexemplaire);
				}
			});

			/** METHODES CHECKBOX2 AFFICHE LES EXEMPLAIRES INDISPONIBLES **/
			chckbxSecond.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					JCheckBox cb = (JCheckBox) event.getSource();
					if (cb.isSelected()) {
						list2.setModel(
								new DefaultComboBoxModel(new ArrayList<Exemplaire>(exemp.findAllExemplaires2()).toArray()));
					}
				}
			});

			/** AJOUT DES EMPRUNTS EN COURS DATABASE A L'OBJET USER ET AU MODEL **/
			empruntencourslist = emp.findAllByUtilisateur(KEY);
			for (EmpruntEnCoursDBB empruntencourslist : this.empruntencourslist) {
				modelempruntencoursDBB.addElement(empruntencourslist);
			}
			
			for (Exemplaire exemplaires : this.exemplaires) {
				modelexemplaire.addElement(exemplaires);
			}
			for (EmpruntEnCoursDBB empruntencourslist : this.empruntencourslist) {
				user.addEmpruntEnCours(empruntencourslist);
			}
			

			/** JLIST1 AFFICHE LES EMPRUNTS EN COURS DE L'UTILISATEUR **/	
			list1 = new JList<EmpruntEnCoursDBB>(modelempruntencoursDBB);
			list1.setModel(modelempruntencoursDBB);
			list1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			list1.setBackground(Color.WHITE);
			list1.setBounds(0, 45, 515, 98);
			emprunter.getContentPane().add(list1);	
				
			
			/** JLIST2 AFFICHE LES EXEMPLAIRES DISPONIBLES ET NON DISPONIBLES **/		
			list2 = new JList<Exemplaire>(modelexemplaire);
			list2.setModel(modelexemplaire);
			list2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			list2.setBackground(Color.WHITE);
			list2.setBounds(20, 179, 319, 121);
			Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			list2.setBorder(loweredetched);
			emprunter.getContentPane().add(list2);

			txtListeDesExemplaires = new JLabel();
			txtListeDesExemplaires.setFont(new Font("Times New Roman", Font.BOLD, 12));
			txtListeDesExemplaires.setBackground(Color.WHITE);
			txtListeDesExemplaires.setFocusable(false);
			txtListeDesExemplaires.setText("Liste des Exemplaires");
			txtListeDesExemplaires.setBounds(20, 154, 121, 25);
			emprunter.getContentPane().add(txtListeDesExemplaires);

			// BUTTON RENDRE
			JButton Rendre = new JButton("Rendre");
			Rendre.setForeground(new Color(255, 255, 255));
			Rendre.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			Rendre.setBackground(new Color(0, 153, 102));
			Rendre.setBounds(384, 241, 108, 20);
			emprunter.getContentPane().add(Rendre);

			/**
			 * RENDRE UN EXEMPLAIRE + SUPPRIMER LEMPRUNT DE LUTILISATEUR + ARCHIVER LEMPRUNT
			 **/
			Rendre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (empruntencourslist.size() > -11) {
						i = list1.getSelectedIndex();					
						//System.out.println("taille liste" + i);
						//System.out.println("item liste" + empruntencourslist.get(i));
						EmpruntEnCoursDBB d = (EmpruntEnCoursDBB) user.getEmpruntsEnCours().get(i);
						user.getEmpruntsEnCours().remove(i);
						Exemplaire a = d.getExemplaire();
						empruntencourslist.remove(i);
						//System.out.println(d.getIdExemplaire());
						//list1.clearSelection();					
						try {
							emp.deleteEmpruntEnCours(d.getIdExemplaire());
						} catch (SQLException | BiblioException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}					
						modelempruntencoursDBB.remove(i);					
						//list1.setModel(modelempruntencoursDB);	
						modelexemplaire.addElement(a);
						exemplaires.add(a);	
						//list2.setModel(modelexemplaire);	
					}
				}
			});

			/** ENREGISTRER UN EMPRUNT **/
			JButton btnEmprunter = new JButton("Emprunter");
			btnEmprunter.setForeground(new Color(255, 255, 255));
			btnEmprunter.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			btnEmprunter.setBackground(new Color(0, 153, 102));
			btnEmprunter.setBounds(384, 197, 108, 20);
			emprunter.getContentPane().add(btnEmprunter);

			btnEmprunter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (chckbxPremier.isSelected()) {
						if (cat.equals("ADHERENT") && exemplaires.size() > 0) {
							try {
								@SuppressWarnings("unused")
								Exemplaire exemp = new Exemplaire();
								i = list2.getSelectedIndex();
								System.out.println(i);
								Exemplaire a = exemplaires.get(i);
								EmpruntEnCoursDBB d = new EmpruntEnCoursDBB(user.getIdUtilisateur(), a.getIdExemplaire(),
										LocalDate.now());

								if (((Adherent) user).isConditionsPretAcceptees()) {
									user.addEmpruntEnCours(d);
									// Jlist

									// GESTION DE LA LISTE DEXEMPLAIRES
									modelexemplaire.remove(i);
									exemplaires.remove(i);
									// GESTION DE LA LISTE DES EMPRUNTS EN COURS
									modelempruntencoursDBB.addElement(d);
									empruntencourslist.add(d);
									// inserer l'emprunt dans la database
									//ec.insertEmpruntEnCours(d);
									if ( i == 10) {
									emp.insertEmpruntEnCours(d);}

								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else if (cat.equals("EMPLOYE") && exemplaires.size() > 0) {
							try {
								Exemplaire exemp = new Exemplaire();
								i = list2.getSelectedIndex();
								Exemplaire a = exemplaires.get(i);
								EmpruntEnCoursDBB d = new EmpruntEnCoursDBB(user.getIdUtilisateur(), a.getIdExemplaire(),
										LocalDate.now());

								if (((Adherent) user).isConditionsPretAcceptees()) {
									user.addEmpruntEnCours(d);
									// Jlist
									// GESTION DE LA LISTE DEXEMPLAIRES
									modelexemplaire.remove(i);
									exemplaires.remove(i);
									// GESTION DE LA LISTE DES EMPRUNTS EN COURS
									modelempruntencoursDBB.addElement(d);
									empruntencourslist.add(d);
									// inserer l'emprunt dans la database
									emp.insertEmpruntEnCours(d);
								}

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			});

			txtCompteDeLutilisateur = new JTextField();
			txtCompteDeLutilisateur.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			txtCompteDeLutilisateur.setBackground(Color.WHITE);
			txtCompteDeLutilisateur
					.setText("Compte n°" + KEY + " " + "Type : " + " " + util.findCatByKey(KEY) + " " + catemploye);
			txtCompteDeLutilisateur.setEditable(false);
			txtCompteDeLutilisateur.setBounds(119, 11, 302, 23);
			emprunter.getContentPane().add(txtCompteDeLutilisateur);
			txtCompteDeLutilisateur.setColumns(10);
		}

		public static void main(String[] args) throws SQLException, BiblioException {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						EmpruntActivity window = new EmpruntActivity();
						window.empruntActivity.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		}
	}
