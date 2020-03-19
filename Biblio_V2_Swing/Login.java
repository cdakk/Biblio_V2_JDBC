package Biblio_V2_Swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Biblio_V2.exception.*;
import Biblio_V2.connection.*;

public class Login {
	
	Login(){
		
		final JFrame JFRAME=new JFrame("Login Form");
		JTextField t4;
		JPasswordField t5;
		JLabel l1=new JLabel("Email");
		JLabel l2=new JLabel("Password");
		JLabel l3=new JLabel("Login Page");
		l1.setBounds(30, 100, 100, 30);
		JFRAME.add(l1);
		l2.setBounds(30,150,100,20);
		JFRAME.add(l2);
		l3.setBounds(150, 50, 100, 30);
		JFRAME.add(l3);
		t4=new JTextField();
		t5=new JPasswordField();
		JButton b1=new JButton("Login");
		JFRAME.add(t4);
		t4.setBounds(100,100,200,30);
		JFRAME.add(t5);
		t5.setBounds(100,150,200,30);
		JFRAME.add(b1);
		b1.setBounds(100,200,200,30);
		JFRAME.setSize(400, 400);
		JFRAME.setLayout(null);
		JFRAME.setVisible(true);
		JFRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
						try {
							new EmpruntActivity();
						} catch (SQLException | BiblioException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JFRAME.dispose();
					
			}
		});
		
		}

	public static void main(String[] args) {
		new Login();

	}

}
