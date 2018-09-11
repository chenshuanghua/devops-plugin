package com.unicom.mcloud.devops.plugin.build;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Execute extends JFrame implements IObjectActionDelegate {

	private JPanel contentPane;
	public static String buildDefinitionId;

	/**
	 * Create the frame.
	 * 
	 * @throws JSONException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Execute(String projectId) throws JSONException {

		String buildDefinitionListUrl = Comm.buildDefinitionListUrl(projectId);
		String buildDefinitionListResult = Request.getRequest(buildDefinitionListUrl)
				.get("response").toString();
		JSONArray buildDefinitionList = new JSONObject(buildDefinitionListResult).getJSONArray("data");

		String[] str = Comm.jsonArrayToString(buildDefinitionList, "definitionName");

		setTitle("执行");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("构建列表 ：");
		label.setBounds(22, 54, 75, 15);
		contentPane.add(label);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(str));
		comboBox.setEditable(true);
		comboBox.setBounds(107, 51, 181, 21);
		contentPane.add(comboBox);

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					String repoName = comboBox.getSelectedItem().toString();

					try {
						buildDefinitionId = Comm.getIdByName(buildDefinitionList, repoName, "definitionName", "definitionId");
					} catch (JSONException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		});

		JButton button = new JButton("执行");
		button.setBounds(274, 195, 93, 23);
		contentPane.add(button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String executeUrl = Comm.executeUrl(buildDefinitionId);
				JSONObject response;
				try {
					response = new JSONObject(Request.getRequest(executeUrl).get("response").toString());
					if ("200".equals(response.get("code"))) {
						//JOptionPane.showMessageDialog(null, "构建执行成功！");
						
						/*setVisible(false);
						BuildInstance frame = new BuildInstance();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);*/
						
					} else {
						JOptionPane.showMessageDialog(null, (String) response.get("message"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public void run(IAction arg0) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Execute frame = new Execute("");
					frame.setVisible(true);
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
