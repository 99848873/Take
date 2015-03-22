package com.take6.swing;

import java.awt.BorderLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.net.URL;

import javax.swing.*;

import com.take6.base.action.CreatConfirmWindows;
import com.take6.base.action.CreatInternalWindows;
import com.take6.base.action.MusicSelectAction;
import com.take6.base.action.SystemAction;

/**
 * 实现主界面现实与刷新
 * @author 余周锦
 * @version 1.0
 * 2014-12-6
*/
@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	
	/** 定义主界面中的桌面变量*/
	private Desktop desktop = new Desktop();;//定义一个私有的桌面对象
	
	/** 定义主界面的有参构造函数
	 * @param title 主界面的题目
	 * */
	public MainWindow(String title){
		super();//调用父类构造方法
		initialize(title);
		setVisible(true);
		judgeState();
		
	}
	
	/** 定义主界面的初始化方法
	 * @param title 初始化的主界面题目
	 * */
	private void initialize(String title){
		
		//创建主界面并添加一个desktop,需要修改系统图标
		setTitle(title);
		setBounds(0, 0, 1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		//修改主界面风格
		setLookAndFeel();
		//增加logo
		creatLogo();
		//创建菜单栏
		this.creatMenu();
		getContentPane().add(desktop, BorderLayout.CENTER);	
		//窗口不能改变大小
		setResizable(false);
	}
	
	/** 创建菜单栏的方法 */
	private void creatMenu(){
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//创建开始菜单
		JMenu strat = new JMenu("开始 (S)");
		strat.setMnemonic('S');
		menuBar.add(strat);
		//创建控制菜单
		JMenu option = new JMenu("选项 (O)");
		option.setMnemonic('C');
		menuBar.add(option);
		//创建帮助菜单
		JMenu help = new JMenu("帮助 (H)");
		help.setMnemonic('H');
		menuBar.add(help);
		
		//创建子菜单游玩模式
		JMenu mode = new JMenu("游玩模式");
		strat.add(mode);
		//创建游玩模式下的菜单项
		JMenuItem singleP = new JMenuItem("单人模式");
		singleP.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));//增加快捷键
		singleP.setActionCommand("Single Play");
		singleP.addActionListener(new CreatConfirmWindows(desktop));
		mode.add(singleP);
		//创建游玩模式下的菜单项
		JMenuItem olineP = new JMenuItem("在线模式");
		olineP.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));//增加快捷键
		olineP.setActionCommand("Online Play");
		olineP.addActionListener(new CreatConfirmWindows(desktop));
		mode.add(olineP);
		
		//创建strat下菜单项3
		JMenuItem reset = new JMenuItem("重置");
		reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));//增加快捷键
		reset.setActionCommand("Reset");
		reset.addActionListener(new SystemAction(desktop));
		strat.add(reset);
		//创建strat下菜单项4
		JMenuItem exit = new JMenuItem("退出");
		exit.setActionCommand("Exit");
		exit.addActionListener(new SystemAction(desktop));
		strat.add(exit);
		
		//创建子菜单背景音乐
		JMenu backmusic = new JMenu("背景音乐");
		option.add(backmusic);
		//创建背景音乐下菜单
		JRadioButtonMenuItem musicoption = new JRadioButtonMenuItem("关闭背景音乐");
		musicoption.setSelected(true);
		musicoption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.CTRL_MASK));
//		musicoption.addItemListener(new MusicSelectAction(musicoption));
		backmusic.add(musicoption);	
		
		JMenuItem changeUserInfo = new JMenuItem("修改用户信息");
		changeUserInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,InputEvent.CTRL_MASK));//增加快捷键
		changeUserInfo.setActionCommand("changeUserInfo");
		changeUserInfo.addActionListener(new CreatInternalWindows(desktop));
		option.add(changeUserInfo);
		//创建help下菜单项5
		JMenuItem rule = new JMenuItem("规则");
		rule.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,InputEvent.CTRL_MASK));//增加快捷键
		rule.setActionCommand("Rule");
		rule.addActionListener(new CreatInternalWindows(desktop));
		help.add(rule);
		//创建help下菜单项6
		JMenuItem about = new JMenuItem("关于");
		about.addActionListener(new CreatInternalWindows(desktop));
		about.setActionCommand("About");;
		help.add(about);
	}
	
	/** 定义判断是否第一次游玩的方法 */
	private void judgeState(){

		File file = new File("./config/userInfo");
		
		if(file.exists()){
			System.out.println("不是第一次又玩游戏");
			
		}else{
			boolean state = true;
			
			InternalWindows hintWindow = new InternalWindows(desktop,state);
			
			desktop.add(hintWindow);
			
			try{
				hintWindow.setSelected(true);
			}catch(PropertyVetoException e){
				e.printStackTrace();
			}
			try{
				file.createNewFile();
			}catch(Exception e){
				System.out.println("创建文件失败");
			}
		}

	}
	
	/**创建主界面图标*/
	private void creatLogo(){
		
		URL logoPath = this.getClass().getResource("/com/take6/source/picture/logo.png");
		ImageIcon icon = new ImageIcon(logoPath);
		setIconImage(icon.getImage());
		
	}
	
	/**修改界面皮肤*/
	private void setLookAndFeel() {
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
			 
	public Desktop getDesktop() {
		
		return desktop;
		
	}
	
}
