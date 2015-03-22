package com.take6.swing;


import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 实现创建玩家头像的代码
 * @author 余周锦
 * @version 1.0
 * 2014-12-27
*/
@SuppressWarnings("serial")
public class Head extends JLabel {
	
	/**定义私有变量，获得图片位置*/
	private String pathName;
	
	/**定义私有变量，获得图片位置
	 * @param pathName 图片位置
	 * */
	public Head(String pathName){
		this.pathName = pathName;
		setSize(100,100);	
		URL path = this.getClass().getResource(pathName);		
		Image img = Toolkit.getDefaultToolkit().getImage(path);
		ImageIcon icon = new ImageIcon(img);
		setIcon(icon);
	}

	public String getPath() {
		
		return pathName;
	}
	
	public void setPath(String pathName) {
		this.pathName = pathName;
	}
	
	
	
}
