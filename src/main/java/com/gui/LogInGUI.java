package com.gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Reseau.Client.Client;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

/*
 * @author Rebecca
 */

public class LogInGUI extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JLabel NameLabel;
	private JTextField NameTextField;
	private JPasswordField passwordField;
	private JLabel WarningMessageLabel = new JLabel("");

	/**
	 * Create the dialog.
	 */
	public LogInGUI() {

		setTitle("The-Travelers");
		setBounds(100, 100, 450, 300);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.setActionCommand("cancelButton");
					cancelButton.addActionListener(this);

					JButton loginButton = new JButton("Login");
					loginButton.setHorizontalAlignment(SwingConstants.LEFT);
					loginButton.setActionCommand("loginButton");
					loginButton.addActionListener(this);
					buttonPane.add(loginButton);
					getRootPane().setDefaultButton(loginButton);

					JButton btnNew = new JButton("New");
					btnNew.setActionCommand("NewButton");
					btnNew.addActionListener(this);

					btnNew.setHorizontalAlignment(SwingConstants.LEFT);

					buttonPane.add(btnNew);
					buttonPane.add(cancelButton);
				}
			}
		}
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
				.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)));
		{
			NameLabel = new JLabel("Name");
			NameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

		NameTextField = new JTextField();
		NameTextField.setColumns(10);

		passwordField = new JPasswordField();

		JLabel LoginLabel = new JLabel("Login");
		LoginLabel.setFont(new Font("Tahoma", Font.BOLD, 30));

		WarningMessageLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		WarningMessageLabel.setForeground(Color.RED);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(183)
								.addComponent(LoginLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGap(196))
						.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(NameLabel)
										.addComponent(PasswordLabel))
								.addGap(22)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(WarningMessageLabel)
										.addComponent(NameTextField, GroupLayout.PREFERRED_SIZE, 223,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(passwordField, 223, 223, 223))
								.addContainerGap(105, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(LoginLabel).addGap(20)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(NameTextField, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(passwordField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(PasswordLabel)))
								.addComponent(NameLabel))
						.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
						.addComponent(WarningMessageLabel).addContainerGap()));
		contentPanel.setLayout(gl_contentPanel);
		getContentPane().setLayout(groupLayout);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				Globals.clnt.close();
				}
			
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "loginButton":
				boolean login = true;

				Globals.UserName = NameTextField.getText();
				Globals.Passwd = passwordField.getPassword();

				System.out.println(new String(Globals.Passwd));
				if (Globals.UserName.length() != 0 && Globals.Passwd.length != 0) {
					login = Globals.clnt.connect(Globals.UserName, new String(Globals.Passwd));
					System.out.println(login);
					if (login) {
						WarningMessageLabel.setText(" ");

						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									Globals.chatGUI = new ChatGUI();
									Globals.chatGUI.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				}

				else {// login = false
					WarningMessageLabel.setText("Login Failed");

				}
				break;

			case "cancelButton":
				Globals.clnt.close();
				System.exit(0);
				break;

			case "NewButton":
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Globals.UserName = NameTextField.getText();
							Globals.Passwd = passwordField.getPassword();
							RegisterGUI frame = new RegisterGUI();
							frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				break;
			
		}
		
	}

	public static void main(String arg[]) {
		boolean isconnect=false;
		int port = 6666;
		while (!isconnect) {
			
			try {
				Globals.clnt = new Client("localhost", port);
				isconnect=true;
			} catch (ConnectException e1) {
				port += 1;
				if(port>6680){
					System.out.println("too many connection");
					System.exit(-1);
				}
			}
		}
		new Thread(Globals.clnt).start();
		try {
			Globals.clnt.getLisGroup();
			Globals.GroupCode = Globals.clnt.list;
			LogInGUI dialog = new LogInGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		} // end

	}
}
