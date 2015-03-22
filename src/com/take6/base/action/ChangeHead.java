package com.take6.base.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.take6.swing.Desktop;
import com.take6.swing.Head;

/**
 * 实现选择头像内部窗体
 * @author 余周锦
 * @version 1.0
 * 2014-12-27
*/
public class ChangeHead implements ActionListener{
	
	/**定义本类的私有变量*/
	private Desktop desktop;
	private Head head;
	
	/**构造函数重写
	 * @param desktop 本参数用于表示本类用所有方法所需要用到的desktop对象
	 * @param head 需要被更换的头像引用
	 * */
	public ChangeHead(Desktop desktop,Head head){
		this.desktop = desktop;
		this.head = head;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("change")){
			String title = "select";
			desktop.creatSelectWindow(head,title);
			
		}
		
	}

}
