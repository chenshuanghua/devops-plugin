package com.unicom.mcloud.devops.plugin.build;

import java.util.Map;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.json.JSONException;
import org.json.JSONObject;

public class projects implements IObjectActionDelegate{
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
		// TODO Auto-generated method stub
	}

	public void run(IAction arg0) {
		Display display = Display.getDefault(); // 1.����һ��Display
		Shell shell = new Shell();// 2.����һ�����߶��Shell(shell�ǳ����������)
		shell.setBounds(100, 100, 300, 200);// 3.����Shell�Ĳ���(���������ڵĴ�С)
		shell.setText("�û���¼");
		// 4.����Shell�е����(���������û�м������,ֻ��һ���մ���)
		shell.open();// 5.��Open()������Shell���� ��shell������
		shell.layout();// shellӦ�ý��沼��

		Button okButton = new Button(shell, SWT.NONE);

		okButton.setText("��ȡ");

		okButton.setBounds(60, 100, 70, 25);
		
		
		okButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e)  {
				String url = Login.host + "/projects";
				String params = "";


					Map<String, Object> result = request.request(url,params);

					System.out.println(result.get("response").toString());
					MessageDialog.openInformation(shell, "Hello", (String) result.get("response"));
			} 
			});
		
		
	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub
		
	}

}
