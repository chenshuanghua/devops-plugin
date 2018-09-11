package com.unicom.mcloud.devops.plugin.build;

import java.awt.EventQueue;

import javax.swing.JPanel;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Login extends JDialog implements IObjectActionDelegate{

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;

	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("static-access")
	public Login() {
		
		FontClass.loadIndyFont();
				
		setTitle("Login");
		setBounds(100, 100, 450, 400);
		
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ProgressBar.background"));
		contentPane.setBorder(new CompoundBorder());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(-11, 312, 458, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton button = new JButton("登录");
		button.setBounds(308, 10, 93, 23);
		panel.add(button);
		button.setToolTipText("");
		
		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("");
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "IPAdress\uFF08Non - compulsory\uFF09", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 20, 414, 124);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(20, 79, 384, 21);
		panel_1.add(textField_1);
		textField_1.setBorder(UIManager.getBorder("ProgressBar.border"));
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("<html><body>Enter the IP address and port number. If not, the default is the test environment address.<body></html>");
		lblNewLabel.setBounds(20, 25, 384, 49);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setToolTipText("");
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Login", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 154, 414, 129);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblUsername = new JLabel("UserName :");
		lblUsername.setBounds(20, 45, 79, 15);
		panel_2.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(20, 91, 79, 15);
		panel_2.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(99, 42, 305, 21);
		panel_2.add(textField);
		textField.setBorder(UIManager.getBorder("ProgressBar.border"));
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(99, 88, 305, 21);
		panel_2.add(passwordField);
		passwordField.setBorder(UIManager.getBorder("ProgressBar.border"));
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println(">>>>>>>>"+textField_1.getText());
				if(!textField_1.getText().isEmpty()) {
					Comm.host = "http://"+textField_1.getText()+"/api/v1";
					System.out.println(">>>>>>>>"+Comm.host);
				}
				
				@SuppressWarnings("deprecation")
				String password = HashUtil.byte2hex(HashUtil.getMD5(passwordField.getText().getBytes()));

				String loginUrl = Comm.loginUrl();
				
				JSONObject params = new JSONObject();
				
				try {
					params.put("account", textField.getText());
					params.put("password", password);
					params.put("verificaCode", "qweasd");
					
					Map<String, Object> loginResult = new HashMap<String, Object>();
					loginResult = Request.postRequest(loginUrl, params);
																									
					JSONObject response = new JSONObject(loginResult.get("response").toString());
					
					if("200".equals(response.get("code"))) {
						JOptionPane.showMessageDialog(null, "User "+textField.getText()+" login success");
						setVisible(false);
						
						/*ProjectList frame = new ProjectList();
						frame.setResizable(false);
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);*/
						
						//new ProjectList(Request.TEMPCOOKIE).setVisible(true);
						
						//MessageDialog.openInformation(shell, "Hello", "Login success!");
					}else {
						JOptionPane.showMessageDialog(null, (String)response.get("message"));
					}
					
					
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}

	@Override
	public void run(IAction arg0) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setResizable(false);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
		// TODO Auto-generated method stub
		
	}
}
