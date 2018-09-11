package com.unicom.mcloud.devops.plugin.build;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.Point;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import java.awt.Color;

@SuppressWarnings("serial")
public class BuildInstance extends JFrame {

	private JPanel contentPane;
	private static int progessValue = 0;
	private static int progess = 0;

	/**
	 * Create the frame.
	 * 
	 * @throws JSONException
	 * @throws InterruptedException
	 */

	public BuildInstance(String buildInstanceId) throws JSONException, InterruptedException {
		setLocation(new Point(100, 0));
		setTitle("构建实例");
		setBounds(100, 100, 450, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 165, 434, 408);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblGitpull = new JLabel("git-pull");
		lblGitpull.setHorizontalAlignment(SwingConstants.CENTER);
		lblGitpull.setBounds(10, 27, 170, 15);
		contentPane.add(lblGitpull);

		JLabel lblMavenexec = new JLabel("maven-exec");
		lblMavenexec.setHorizontalAlignment(SwingConstants.CENTER);
		lblMavenexec.setBounds(10, 64, 170, 15);
		contentPane.add(lblMavenexec);

		JLabel lblSonarqube = new JLabel("sonarqube");
		lblSonarqube.setHorizontalAlignment(SwingConstants.CENTER);
		lblSonarqube.setBounds(10, 104, 170, 15);
		contentPane.add(lblSonarqube);

		JLabel lblDockerbuildbaseimage = new JLabel("docker-build-baseimage");
		lblDockerbuildbaseimage.setHorizontalAlignment(SwingConstants.CENTER);
		lblDockerbuildbaseimage.setBounds(10, 140, 170, 15);
		contentPane.add(lblDockerbuildbaseimage);

		JLabel lblUnexcuted = new JLabel("unexecuted");
		lblUnexcuted.setBounds(231, 27, 74, 15);
		contentPane.add(lblUnexcuted);

		JLabel lblUnexcuted_1 = new JLabel("unexecuted");
		lblUnexcuted_1.setBounds(231, 64, 74, 15);
		contentPane.add(lblUnexcuted_1);

		JLabel lblUnexcuted_2 = new JLabel("unexecuted");
		lblUnexcuted_2.setBounds(231, 104, 74, 15);
		contentPane.add(lblUnexcuted_2);

		JLabel lblUnexcuted_3 = new JLabel("unexecuted");
		lblUnexcuted_3.setBounds(231, 140, 74, 15);
		contentPane.add(lblUnexcuted_3);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 10, 414, 367);
		// 设置自动换行
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(10, 31, 414, 367);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// panel.add(textArea);
		panel.add(scroll);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.BLUE);
		progressBar.setBounds(10, 10, 414, 14);
		panel.add(progressBar);

		new Thread(new Runnable() {
			@Override
			public void run() {
				Timer timer = new Timer();

				// 定时器，定时任务，每1秒钟请求一次
				timer.schedule(new TimerTask() {

					public void run() {

						// 解析实例任务url
						String stageinstancesListUrl = Comm.stageinstancesListUrl(buildInstanceId);

						String stageinstancesListResult = Request.getRequest(stageinstancesListUrl).get("response")
								.toString();

						JSONArray stageinstanceList = null;
						try {
							stageinstanceList = new JSONObject(stageinstancesListResult).getJSONArray("data");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// 解析json串，将数据打印到label中
						String[] instanceStatuses = null;
						// JLabel [] labels ={label,label_1,label_2,label_3};

						try {
							instanceStatuses = Comm.jsonArrayToString(stageinstanceList, "instanceStatus");

							for (int i = 0; i < instanceStatuses.length; i++) {
								lblUnexcuted.setText(instanceStatuses[1]);
								lblUnexcuted_1.setText(instanceStatuses[2]);
								lblUnexcuted_2.setText(instanceStatuses[3]);
								lblUnexcuted_3.setText(instanceStatuses[4]);

								// System.out.println("---------->"+instanceStatuses[i]);
							}
						} catch (JSONException e) {

							e.printStackTrace();
						}

						// 打印构建log日志
						try {
							String stageinstancesListLogsUrl = Comm.stageinstancesListLogsUrl(buildInstanceId, 0);
							JSONObject response = new JSONObject(
									Request.getRequest(stageinstancesListLogsUrl).get("response").toString());
							String logs = response.getJSONObject("data").getString("fetchedLog").toString();
							// System.out.println("+++++++++++++>"+logs);
							textArea.setText(logs);

						} catch (JSONException e) {

							e.printStackTrace();
						}

						// 跳出任务 ,当构建实例状态结束跳出请求。
						// 获取构建实例url
						String BuildInstanceUrl = Comm.buildinstancesUrl(buildInstanceId);
						JSONObject BuildInstanceResult = null;
						try {
							BuildInstanceResult = new JSONObject(
									Request.getRequest(BuildInstanceUrl).get("response").toString());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// 获取构建实例的状态
						String BuildInstanceStatus = null;
						try {
							BuildInstanceStatus = (BuildInstanceResult).getJSONObject("data")
									.getString("instanceStatus");
							double buildProgess = Double
									.parseDouble(BuildInstanceResult.getJSONObject("data").getString("buildProgress"));
							
						    progess = (int) (buildProgess * 100);
						    if(progess>=progessValue) {
						    	progessValue = progess;
						    	progressBar.setValue(progess);
						    }
							progressBar.setStringPainted(true);
							//System.out.println(">>>>>>>>>>>>progess:"+progess);
							//System.out.println(">>>>>>>>>>>>progessValue:"+progessValue);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// System.out.println("-------------------》"+BuildInstanceStatus);

						if (BuildInstanceStatus.equals("success") || BuildInstanceStatus.equals("failure")) {
							timer.cancel();
							/*try {

								Thread.sleep(10);
								timer.cancel();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/
						}
						if (BuildInstanceStatus.equals("success")) {
							lblUnexcuted_3.setText("success");
							progressBar.setForeground(new Color(0, 255, 0));
						}
					}
				}, 2000, 1000);
			}
		}).start();

	}
}
