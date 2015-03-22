package com.take6.base.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.take6.swing.Desktop;


/**
 * 实现界面上菜单系统功能
 * @author 余周锦
 * @version 1.0
 * 2014-12-19
*/
public class SystemAction implements ActionListener{
	
	/**定义私有变量，该类功能都对桌面实现，所以需要一个私有桌面变量*/
	private Desktop desktop;
	
	/**构造函数,初始化需要的属性
	 * @param desktop 桌面
	 * */
	public SystemAction(Desktop desktop){
		this.desktop = desktop;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Exit")){
			//游戏系统退出功能
			System.exit(0);
			
		}else if(e.getActionCommand().equals("Reset")){
			//游戏reset功能		
			reset();
	
		}else if(e.getActionCommand().equals("restart")){
			//游戏reset后，重新开始游戏
			reset();
			desktop.creatPlayArea("Start");	
			
		}
		
	}
	
	/**当用户重置系统后，判断当前音乐是否关闭，如果关闭了将其打开*/
	private void reset(){
		
		try{
			
			desktop.reset();
			if(!(MusicSelectAction.musicState)){
				MusicSelectAction.playMusic.loop();
				MusicSelectAction.musicState = true;
			}
			
		}catch(NullPointerException ex){
			
			System.out.println("玩家重置游戏");
			
		}
	}

}
