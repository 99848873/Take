package com.take6.base.action;

import java.awt.event.*;

import com.take6.swing.Desktop;

/**
 * 谁是牛头王程序菜单响应功能
 * @author 余周锦
 * @version 1.0
 * 2014-12-6
*/
public class CreatInternalWindows implements ActionListener{

	/**定义本类的私有变量*/
	private Desktop desktop = null;
	
	/**构造函数重写
	 * @param desktop 本参数用于表示本类用所有方法所需要用到的desktop对象
	 * */
	public CreatInternalWindows(Desktop desktop){
		this.desktop = desktop;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Rule")){	
			
			String path = "/com/take6/source/txt/Rule";
			String title = "Rule";
			int length = 600;
			desktop.creatInternalWindow(title, path, length);	
			
		}else if(e.getActionCommand().equals("About")){		
			
			String path = "/com/take6/source/txt/About";
			String title = "About";
			int length = 300;
			desktop.creatInternalWindow(title, path, length);	
			
		}else if(e.getActionCommand().equals("changeUserInfo")){
			
			boolean state = false;
			String name = "change";
			desktop.creatChangeWindow(state, name);
			
		}
		
	}

	
}
