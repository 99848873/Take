package com.take6.swing;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.take6.Take6;
/**
 * 实现主错误提示
 * @author 余周锦
 * @version 1.0
 * 2014-12-6
*/
@SuppressWarnings("serial")
public class MyDialog extends JDialog{
	
	/**定义一个私有变量，是dialog中的label 用来显示信息*/
	private JLabel warning = new JLabel();
	
	/**构造函数，创建一个可以看到的对话框提示*/
	public MyDialog(){
		super(Take6.display,"通知",true);
		setBounds(100,100,210,100);
		warning.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(warning,BorderLayout.CENTER);	
	}
	public JLabel getWarning() {
		return warning;
	}
	
}