package com.take6.base.action;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.take6.swing.Desktop;


/**
 * 实现创建游戏区域的界面响应
 * @author 余周锦
 * @version 1.0
 * 2014-12-9
*/
public class CreatPlayArea implements ActionListener {
	
	/**定义本类的私有变量*/
	private Desktop desktop = null;
	
	/**构造函数重写
	 * @param desktop 本参数用于表示本类用所有方法所需要用到的desktop对象
	 * */
	public CreatPlayArea(Desktop desktop){
		this.desktop = desktop;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Start")){

			desktop.creatPlayArea("Start");	
			
		}else if(e.getActionCommand().equals("creat")){

			desktop.creatHost("creat");
			
		}else if(e.getActionCommand().equals("search")){

			desktop.creatHostList("search");	
			
		}
	}
	

}
