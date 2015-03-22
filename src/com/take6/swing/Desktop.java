package com.take6.swing;

import java.beans.PropertyVetoException;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

/**
 * 实现桌面代码
 * @author 余周锦
 * @version 1.0
 * 2014-12-9
*/
@SuppressWarnings("serial")
public class Desktop extends JDesktopPane{
	
	/** 定义桌面的拥有成员变量*/
	private InternalWindows inWindow;
	private InternalWindows confirmWindow;
	private InternalWindows changeWindow;
	private InternalWindows selectWindow;
//	private InternalWindows hostWindow;
//	private InternalWindows hostListWindow;
	private PlayArea playArea;
	private boolean playAreaState = true;

	/** 定义桌面的构造函数*/
	public Desktop(){
		
		Random rand = new Random();
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setName("background");
		backgroundLabel.setBounds(0, 0, 1280, 720);
		//创建图片背景随机的三张图片
		String pathName = "/com/take6/source/picture/background"+(1+rand.nextInt(4))+".png";
		URL backgroundUrl = this.getClass().getResource(pathName);
		ImageIcon backgrodunIcon = new ImageIcon(backgroundUrl);
		backgroundLabel.setIcon(backgrodunIcon);
		add(backgroundLabel,new Integer(Integer.MIN_VALUE));	
		
		//设置内部窗体拖拽
		setDragMode(JDesktopPane.LIVE_DRAG_MODE);
		
	}
	
	/** 创建内部窗口about和rule
	 *  @param title 当前内部窗口的题目
	 *  @param path 当前内部窗口内容储存位置
	 *  @param length 当前内部窗体显示长度
	 * */
	public void creatInternalWindow(String title,String path,int length){
		
		inWindow = new InternalWindows(title,path);
		
		//创建内部窗体前，确认是否已经创建过相同的窗体
		boolean state = checkSameInternalWindow(title);	
		
		//确认当前有多少需要排列的内部窗体
		if(!state){			
			int x = 770;
			int y = 0;
			if(this.getFrontWindow() != null){
				if(getFrontWindow().isShowing()){
					x = this.getFrontWindow().getX()-10;
					y = this.getFrontWindow().getY()+22;
				}
				
			}		
			inWindow.setBounds(x, y, 500, length);
			this.add(inWindow);	
			try{
				inWindow.setSelected(true);
			}catch(PropertyVetoException e){
				e.printStackTrace();
			}
		}
		
	}
	
	/** 创建单机游戏和在线游戏确认窗口
	 *  @param title 当前内部窗口的题目
	 * */
	public void creatConfirmWindow(String title){
		
		confirmWindow = new InternalWindows(title,this);
		//启动前将窗口内所有内部窗体删除
		this.removeAllInternalWindow();
		
		this.add(confirmWindow);

		try{
			confirmWindow.setSelected(true);
		}catch(PropertyVetoException e){
			e.printStackTrace();
		}
		
	}
	
	/** 创建选择头像窗口
	 * @param head 当前窗口中的头像
	 * @param name 当前内部窗口的名字
	 * */
	public void creatSelectWindow(Head head,String name){
		
		selectWindow = new InternalWindows(this,head);
		//启动前将窗口内所有内部窗体删除
		boolean state =checkSameInternalWindow(name);
		if(!state){
			
			this.add(selectWindow);
		
			try{
				selectWindow.setSelected(true);
			}catch(PropertyVetoException e){
				e.printStackTrace();
			}
		}
				
	}
	
	/** 创建修改用户信息窗口
	 * @param windowState 需要被创建窗口是否需要创建查看规则功能
	 *  @param name 当前内部窗口的名字
	 * */
	public void creatChangeWindow(boolean windowState,String name){
			
		changeWindow = new InternalWindows(this,windowState);
		//启动前将窗口内所有内部窗体删除
		boolean state =checkSameInternalWindow(name);
		if(!state){
			this.add(changeWindow);
			
			try{
				changeWindow.setSelected(true);
			}catch(PropertyVetoException e){
				e.printStackTrace();
			}
			
		}
			
	}
	
	/** 创建游戏区域
	 *  @param title 当前游玩区域的题目
	 * */
	public void creatPlayArea(String title){
		
		playArea = new PlayArea(title);
		//创建区域前，将所有内部窗体清除
		this.removeAllInternalWindow();
		this.add(playArea);	
		//此时palyArea不能再次被创建
		playAreaState = false;
			
	}
	
	/** 创建主机及主机显示窗口
	 *  @param title 当前内部桌面的名字
	 * */
	public void creatHost(String title) {
		
//		hostWindow = new InternalWindows();
		
		
	}
	
	/** 创建显示当前局域网内主机的列表
	 *  @param title 当前内部桌面的名字
	 * */
	public void creatHostList(String title) {
		
//		hostListWindow = new InternalWindows();
	}

	
	/**启动清空本类内容方法，用于reset功能*/
	public void reset(){
		
		playArea.reset();	
		removeAllInternalWindow();
		setPlayArea(null);
		setPlayAreaState(true);
		
	}

	/** 检查该窗口是否已经被开启
	 *  @param title 当前窗口title
	 * */
	private boolean checkSameInternalWindow(String name){
		JInternalFrame[] inWindows = getAllFrames();
		boolean state = false;
		//创建内部窗体前，确认是否已经创建过相同的窗体
		for(int i=0;i<inWindows.length;i++){

			if(name == inWindows[i].getName()){
				state = true;
				break;
			}
		}
		return state;
	}
	
	/** 移除指定内部窗口
	 *  @param title 当前需要被移除窗口的题目
	 * */
	@SuppressWarnings("unused")
	private  void removeInternalWindow(String title){
		
		JInternalFrame[] inWindows = getAllFrames();
		for(int i=0;i<inWindows.length;i++){
			if(inWindows[i].getName().equals(title)){
				remove(inWindows[i]);
				repaint();
			}
		}
	}
	
	/** 移除所有内部窗口  */
	private  void removeAllInternalWindow(){
		
		JInternalFrame[] frames = getAllFrames();
		for(int i=0;i<frames.length;i++){
			remove(frames[i]);
			repaint();
		}
	}
	
	/** 得到指定内部窗口 
	 * @param title 指定窗口的题目
	 *  */
	private  InternalWindows getInternalWindow(String title){
		
		JInternalFrame[] frames = getAllFrames();
		InternalWindows inWindow = null;
		for(int i=0;i<frames.length;i++){
			if(frames[i].getName()==title){
				inWindow = (InternalWindows) frames[i];
			}
		}
		return inWindow;
		
	}
	
	/** 确认当前有多少窗口 */
	@SuppressWarnings("unused")
	private int getInternalWindowConut(){
		
		JInternalFrame[] frames = this.getAllFrames();
		int count = frames.length;
		if(this.getInternalWindow("Sigle Play")!=null){
			count = frames.length-1;
		}else if(this.getInternalWindow("Online Play")!=null){
			count = frames.length-1;
		}
		return count;
		
	}
	
	/** 得到当前最前方的窗口 */
	private InternalWindows getFrontWindow(){
		
		JInternalFrame[] frames = getAllFrames();
		InternalWindows inWindow = null;
		for(int i=0;i<frames.length;i++){

			if(frames[i].getName().equals("Rule")||frames[i].getName().equals("About"))
				inWindow = (InternalWindows) frames[i];
			
		}
	
		return inWindow;	
	}
	
	
	public boolean isPlayAreaState() {
		return playAreaState;
	}

	public void setPlayAreaState(boolean playAreaState) {
		this.playAreaState = playAreaState;
	}
	
	public PlayArea getPlayArea() {
		return playArea;
	}

	public void setPlayArea(PlayArea playArea) {
		this.playArea = playArea;
	}




}
