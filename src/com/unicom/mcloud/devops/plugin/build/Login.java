package com.unicom.mcloud.devops.plugin.build;

import org.eclipse.ui.IObjectActionDelegate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.json.JSONException;
import org.json.JSONObject;

public class Login implements IObjectActionDelegate {

	public static final int x = 100;
	public static final int y = 100;

	static final String host = "http://10.124.133.190:8080/api/v1";

	// http://10.124.133.190:8080/api/v1/tenant/login

	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
		// TODO Auto-generated method stub
	}

	public void run(IAction arg0) {

		Display display = Display.getDefault(); // 1.创建一个Display
		Shell shell = new Shell();// 2.创建一个或者多个Shell(shell是程序的主窗口)
		shell.setBounds(x, y, 300, 200);// 3.设置Shell的布局(设置主窗口的大小)
		shell.setText("用户登录");
		// 4.创建Shell中的组件(这个例子中没有加入组件,只是一个空窗口)
		shell.open();// 5.用Open()方法打开Shell窗口 打开shell主窗口
		shell.layout();// shell应用界面布置

		Button okButton = new Button(shell, SWT.NONE);

		okButton.setText("确定");

		okButton.setBounds(60, 100, 70, 25);

		final Text nameText = new Text(shell, SWT.NONE);
		final Text passNumber = new Text(shell, SWT.PASSWORD);
		nameText.setBounds(55, 20, 110, 20);
		passNumber.setBounds(55, 60, 110, 20);

		Label nameLabel = new Label(shell, SWT.NONE);
		nameLabel.setText("用户名：");
		nameLabel.setBounds(10, 20, 40, 20);

		Label passLabel = new Label(shell, SWT.NONE);
		passLabel.setText("密   码：");
		passLabel.setBounds(10, 60, 40, 20);

		// 输入后单击确定后的操作
		okButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e)  {

				String password = byte2hex(getMD5(passNumber.getText().getBytes()));

				String params = "";
				String url = host + "/tenant/login";

				params=params+"{";
				params=params+"\"account\":"+"\""+nameText.getText()+"\",";
				params=params+"\"password\":"+"\""+password+"\",";
				params=params+"\"verificaCode\":"+"\""+"qweasd"+"\"";
				params=params+"}";

					Map<String, Object> result = request.request(url, params);

					System.out.println(result.get("response").toString());
					
					Object succesResponse = JSON.parse(result.get("response"));
					
					HashMap<String , String> map = (HashMap<String, String>)succesResponse;
					
					
					
					if(map.get("code")==200) {
						
					}
					MessageDialog.openInformation(shell, "Hello", (String) result.get("response"));
			}
		}

		);

		// -----------------------END-----------------------------------
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();

	}

	public void selectionChanged(IAction arg0, ISelection arg1) {

		// TODO Auto-generated method stub

	}

	/**
	 * 获得字节组的md5
	 * 
	 * @param source 待转换的字节组
	 * @return 已转换的字节流
	 */
	public static byte[] getMD5(final byte[] source) {
		byte[] result = new byte[0];
		try {
			final MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(source);
			result = md5.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO 转换失败
		}
		return result;
	}

	/**
	 * 字节转16进制字符串
	 * 
	 * @param src 待转换的字节组
	 * @return 16进制字符串
	 */
	public static String byte2hex(final byte[] src) {
		final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] str = new char[src.length * 2];
		for (int i = 0; i < src.length; i++) {
			final byte b = src[i];
			str[i * 2] = hexDigits[b >>> 4 & 0xf];
			str[i * 2 + 1] = hexDigits[b & 0xf];
		}
		return new String(str);
	}

}
