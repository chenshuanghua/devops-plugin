package com.unicom.mcloud.devops.plugin.build;

import java.awt.EventQueue;

import javax.swing.JPanel;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class Build extends JDialog implements IObjectActionDelegate {

	private JPanel contentPane;
	public static String projectId;
	public static JSONArray repoList;
	public static String repoId;
	// public static JSONArray projectList;

	/**
	 * Create the frame.
	 * 
	 * @throws JSONException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Build() throws JSONException {
		if ("".equals(Request.TEMPCOOKIE)) {
			JOptionPane.showMessageDialog(null, "Please login first!");
		} else {
			String projectListUrl = Comm.projectListUrl();

			String projectListResult = Request.getRequest(projectListUrl).get("response").toString();

			// JSONObject project = new JSONObject(projectListResult);

			// JSONArray ArrayList = project.getJSONObject("data").getJSONArray("data");

			JSONArray projectList = new JSONObject(projectListResult).getJSONObject("data").getJSONArray("data");

			String[] str = Comm.jsonArrayToString(projectList, "PROJECT_NAME");

			FontClass.loadIndyFont();

			setTitle("Project and Application");
			setBounds(100, 100, 450, 400);
			contentPane = new JPanel();
			contentPane.setBackground(UIManager.getColor("ProgressBar.background"));
			contentPane.setBorder(new CompoundBorder());
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel.setBounds(-11, 313, 457, 60);
			contentPane.add(panel);

			/*
			 * JButton button_1 = new JButton("构建列表"); button_1.setBounds(220, 10, 93, 23);
			 * panel.add(button_1);
			 */

			JButton button = new JButton("下一步");
			button.setBounds(323, 10, 93, 23);
			panel.add(button);

			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setToolTipText("");
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Choose The Project",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 42, 407, 60);
			contentPane.add(panel_1);

			JComboBox comboBox = new JComboBox();
			comboBox.setEditable(true);
			comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			comboBox.setBorder(new CompoundBorder());
			comboBox.setBackground(Color.WHITE);
			comboBox.setBounds(30, 22, 352, 21);
			panel_1.add(comboBox);
			comboBox.setModel(new DefaultComboBoxModel(str));

			JPanel panel_2 = new JPanel();
			panel_2.setLayout(null);
			panel_2.setToolTipText("");
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Choose The Application",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(10, 150, 407, 60);
			contentPane.add(panel_2);

			JComboBox comboBox_3 = new JComboBox();
			comboBox_3.setEditable(true);
			comboBox_3.setBorder(new CompoundBorder());
			comboBox_3.setBackground(Color.WHITE);
			comboBox_3.setBounds(30, 22, 352, 21);
			panel_2.add(comboBox_3);

			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (ItemEvent.SELECTED == e.getStateChange()) {
						String projectName = comboBox.getSelectedItem().toString();

						try {
							projectId = Comm.getIdByName(projectList, projectName, "PROJECT_NAME", "PROJECT_ID");
						} catch (JSONException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						String repoListUrl = Comm.repoListUrl(projectId);

						String repoListResult = Request.getRequest(repoListUrl).get("response").toString();

						try {
							repoList = new JSONObject(repoListResult).getJSONObject("data").getJSONArray("codeDepoAll");

							String[] str2 = Comm.jsonArrayToString(repoList, "repoName");

							comboBox_3.setModel(new DefaultComboBoxModel(str2));

						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			});

			comboBox_3.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (ItemEvent.SELECTED == e.getStateChange()) {
						String repoName = comboBox_3.getSelectedItem().toString();

						try {
							repoId = Comm.getIdByName(repoList, repoName, "repoName", "repoId");
						} catch (JSONException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				}
			});

			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						setVisible(false);
						if (projectId==null || repoId==null) {
							JOptionPane.showMessageDialog(null, "projectId and RepoId can't be empty!");
						} else {
							CreatBuild frame = new CreatBuild(projectId, repoId);
							frame.setResizable(false);
							frame.setVisible(true);
							frame.setLocationRelativeTo(null);
						}

						// new Build(Request.TEMPCOOKIE, projectId, repoId).setVisible(true);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			/*
			 * button_1.addActionListener(new ActionListener() { public void
			 * actionPerformed(ActionEvent arg0) { try { setVisible(false);
			 * 
			 * setVisible(false); Execute frame = new Execute(projectId);
			 * frame.setVisible(true); frame.setLocationRelativeTo(null);
			 * 
			 * // new Build(Request.TEMPCOOKIE, projectId, repoId).setVisible(true); } catch
			 * (JSONException e) { // TODO Auto-generated catch block e.printStackTrace(); }
			 * } });
			 */
		}
	}

	@Override
	public void run(IAction arg0) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						Build frame = new Build();
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
