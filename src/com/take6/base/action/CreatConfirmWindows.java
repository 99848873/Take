package com.take6.base.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.take6.swing.Desktop;
import com.take6.swing.MyDialog;

/**
 * 实现创建游戏确认窗体的界面响应
 * @author 余周锦
 * @version 1.0
 * 2014-12-9
*/
public class CreatConfirmWindows implements ActionListener{
	
	/**定义本类的私有变量*/
	private Desktop desktop = null;

	/**构造函数重写
	 * @param desktop 本参数用于表示本类用所有方法所需要用到的desktop对象
	 * */
	public CreatConfirmWindows(Desktop desktop){
		this.desktop = desktop;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(desktop.isPlayAreaState() == true){
			if(e.getActionCommand().equals("Single Play")){
				String title = "Single Play";
				desktop.creatConfirmWindow(title);
			}else if(e.getActionCommand().equals("Online Play")){
				String title = "Online Play";
				desktop.creatConfirmWindow(title);
			}
		}else{
			MyDialog myDialog = new MyDialog();
			myDialog.getWarning().setText("您已经在游戏进行中");
			myDialog.setVisible(true);
		}
		
	}

}
