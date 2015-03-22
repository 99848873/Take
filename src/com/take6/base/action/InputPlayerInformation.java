package com.take6.base.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextField;

import com.take6.swing.Desktop;
import com.take6.swing.Head;
import com.take6.swing.InternalWindows;
import com.take6.swing.MyDialog;

/**
 * 实现输出被储存玩家信息
 * @author 余周锦
 * @version 1.0
 * 2014-12-27
*/
public class InputPlayerInformation implements ActionListener{
	
	/**定义私有变量*/
	private Desktop desktop;
	private Head head;
	private JTextField nameText;
	private InternalWindows hintWindow;
	
	/**定义构造函数
	 * @param desktop 当前桌面
	 * @param head 玩家选择的头像
	 * @param nameText 玩家输入的昵称
	 * @param hintWindow 当前窗口
	 * */
	public InputPlayerInformation(Desktop desktop,Head head,JTextField nameText,InternalWindows hintWindow){
		this.desktop = desktop;
		this.head = head;
		this.nameText = nameText;
		this.hintWindow = hintWindow;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
	
		File file = new File("./config/userInfo");
		String regex = "\\w{1,17}";
		String name = nameText.getText();
		if(name.matches(regex)||(name.length()<10&&name.length()>0)){
			try {
				
				FileWriter write = new FileWriter(file);
				BufferedWriter bufferWrite = new BufferedWriter(write);
				
				bufferWrite.write(name);
				bufferWrite.newLine();
				bufferWrite.write(head.getPath().toString());	
				
				bufferWrite.flush();
				bufferWrite.close();
				write.close();
				
				desktop.remove(hintWindow);
				desktop.repaint();
				
			} catch (IOException e1) {
				
				System.out.println("没有找到用户信息的文档，请检查代码");
				
			}
		}else{
			MyDialog myDialog = new MyDialog();
			myDialog.getWarning().setText("中文字符1~9个，英文字符1~16个");
			myDialog.setVisible(true);
		}
				
	}

}
