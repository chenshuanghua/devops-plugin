package com.unicom.mcloud.devops.plugin.build;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Cursor;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class CreatBuild extends JDialog implements IObjectActionDelegate{

	private JPanel contentPane;
	private JTextField textField;
	public static JSONObject parameters;
	public static String baseImage;
	public static String branch;
	public static BuildDefinition buildDefinition =new BuildDefinition();

	/**
	 * Create the frame.
	 * @throws JSONException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CreatBuild(String projectId,String repoId) throws JSONException {
		setForeground(SystemColor.textHighlight);
				
		String tagListUrl = Comm.tagListUrl(repoId);
		//String imageUrl = Comm.imageListUrl();
		String tagListResult = Request.getRequest(tagListUrl).get("response").toString();
		//String imageListResult = Request.getRequest(imageUrl).get("response").toString();
		JSONArray tagList = new JSONObject(tagListResult).getJSONArray("data");
		//JSONArray imageList = new JSONObject(imageListResult).getJSONArray("data");
		
		String [] tag  = Comm.jsonArrayToString(tagList, "name");
		
		//JSONObject [] image = Comm.jsonArrayToString(imageList);
		//String baseImageUrl = (String)image[1].get("imageRepoUrl")+"/"+(String)image[1].get("groupPath");
		
		/*String [] baseImageList = null;
		 * baseImageList[0] = "请选择";
		for(int i=0;i<image.length;i++) {
			JSONArray imageTags = new JSONObject(image[i]).getJSONArray("imageTags");
			String[] imageTag = Comm.jsonArrayToString(imageTags, "tag");
			for(int j=0;j<imageTag.length;j++) {
				baseImageList[i+j+1] = (String)image[i].get("imageName")+":"+imageTag[j];
			}
		}*/
		
		//FontClass.loadIndyFont();
		
		setTitle("Creat a new Build");
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ProgressBar.background"));
		contentPane.setBorder(new CompoundBorder());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuildName = new JLabel("Build Name :");
		lblBuildName.setBounds(15, 25, 94, 15);
		contentPane.add(lblBuildName);
		
		textField = new JTextField();
		textField.setBorder(UIManager.getBorder("ProgressBar.border"));
		textField.setBounds(106, 22, 318, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(-11, 312, 456, 60);
		contentPane.add(panel);
		
		
				
		JButton button = new JButton("创建并执行");
		button.setBounds(318, 10, 105, 23);
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setToolTipText("");
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Build Type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 72, 414, 65);
		contentPane.add(panel_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setEditable(true);
		comboBox_2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		comboBox_2.setBorder(new CompoundBorder());
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setBounds(219, 22, 185, 21);
		panel_1.add(comboBox_2);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"从代码构建镜像"}));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Use default execute type");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(17, 21, 203, 23);
		panel_1.add(rdbtnNewRadioButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setToolTipText("");
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tags", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 147, 414, 65);
		contentPane.add(panel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBorder(new CompoundBorder());
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(219, 22, 185, 21);
		panel_2.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(tag));
		
		JRadioButton rdbtnSelectAExecute = new JRadioButton("Select a execute tags");
		rdbtnSelectAExecute.setSelected(true);
		rdbtnSelectAExecute.setBounds(17, 21, 203, 23);
		panel_2.add(rdbtnSelectAExecute);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setToolTipText("");
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Base Images", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 222, 414, 65);
		contentPane.add(panel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setEditable(true);
		comboBox_1.setBorder(new CompoundBorder());
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(219, 22, 185, 21);
		panel_3.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"请选择","jdk:8-springboot", "tomcat:8.5.31-jdk8", "tomcat:9.0.8-jdk8", "nginx:1.14.0-alpine"}));
		//comboBox_1.setModel(new DefaultComboBoxModel(baseImageList));
		
		JRadioButton rdbtnAddBaseImage = new JRadioButton("Add base image to project");
		rdbtnAddBaseImage.setSelected(true);
		rdbtnAddBaseImage.setBounds(17, 21, 203, 23);
		panel_3.add(rdbtnAddBaseImage);
		
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					baseImage = comboBox_1.getSelectedItem().toString();
				}
			}
		});
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					branch = comboBox.getSelectedItem().toString();
				}
			}
		});
				
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Build definition is null!");
				}else if(branch==null||"请选择".equals(baseImage)){
					JOptionPane.showMessageDialog(null, "Please choose the tag!");
				}else if(baseImage==null||"请选择".equals(baseImage)) {
					JOptionPane.showMessageDialog(null, "Please choose the baseImage!");
				}else {
					/*String definitionParams = "["
							+ "{\"stageId\":\"1\",\"stageName\":\"git-pull\",\"order\":1,\"codeRepoId\":\""+ repoId+ "\",\"branch\":\""+branch+ "\"},"
							+ "{\"stageId\":\"2\",\"stageName\":\"maven-exec\",\"order\":2,\"goals\":[\"package\",\"deploy\"],\"isExecJunit\":true},"
							+ "{\"stageId\":\"3\",\"stageName\":\"sonarqube\",\"order\":3,\"isExecSonarQube\":true},"
							+ "{\"stageId\":\"4\",\"stageName\":\"docker-build-baseimage\",\"order\":4,\"baseImage\":\""+ baseImageUrl+ "/"+baseImage+"\",\"tag\":\""+branch+"\"}"
							+ "]";*/
					
					String definitionParams = "["
							+ "{\"stageId\":\"1\",\"stageName\":\"git-pull\",\"order\":1,\"codeRepoId\":\""+ repoId+ "\",\"branch\":\""+branch+ "\"},"
							+ "{\"stageId\":\"2\",\"stageName\":\"maven-exec\",\"order\":2,\"goals\":[\"package\",\"deploy\"],\"isExecJunit\":true},"
							+ "{\"stageId\":\"3\",\"stageName\":\"sonarqube\",\"order\":3,\"isExecSonarQube\":true},"
							+ "{\"stageId\":\"4\",\"stageName\":\"docker-build-baseimage\",\"order\":4,\"baseImage\":\"10.124.133.191/devops/"+baseImage+"\",\"tag\":\""+branch+"\"}"
							+ "]";
									
					buildDefinition.setProjectId(projectId);
					buildDefinition.setCodeRepoId(repoId);
					buildDefinition.setPipilineTpId("1");
					buildDefinition.setDefinitionParams(definitionParams);
					
					buildDefinition.setDefinitionName(textField.getText());
					parameters =new JSONObject(buildDefinition);
					String buildUrl = Comm.buildUrl();
					JSONObject response;
					try {
						response = new JSONObject(Request.postRequest(buildUrl,parameters).get("response").toString());
						if("200".equals(response.get("code"))) {
							JOptionPane.showMessageDialog(null, "构建定义创建成功！");
							
							/*setVisible(false);
							Execute frame = new Execute(projectId);
							frame.setVisible(true);
							frame.setLocationRelativeTo(null);*/
							
							String buildDefinitionId = ((JSONObject)(response.get("data"))).get("definitionId").toString();
							
							String executeUrl = Comm.executeUrl(buildDefinitionId);
							response = new JSONObject(Request.postRequest(executeUrl,response).get("response").toString());
							if ("200".equals(response.get("code"))) {
								//JOptionPane.showMessageDialog(null, "构建执行成功！");
								String buildInstanceId = ((JSONObject)(response.get("data"))).get("buildInstanceId").toString();
								setVisible(false);
								BuildInstance frame2 = new BuildInstance(buildInstanceId);
								frame2.setResizable(false);
								frame2.setVisible(true);
								frame2.setLocationRelativeTo(null);
								
							} else {
								JOptionPane.showMessageDialog(null, (String) response.get("message"));
							}
							
						}else {
							JOptionPane.showMessageDialog(null, (String)response.get("message"));
						}
					} catch (JSONException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		
	}

	@Override
	public void run(IAction arg0) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreatBuild frame = new CreatBuild("","");
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
