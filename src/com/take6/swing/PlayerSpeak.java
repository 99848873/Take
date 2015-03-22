package com.take6.swing;

import java.applet.AudioClip;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.take6.base.user.Player;

/**
 * 实现当AI玩家得分后，显示随机说的话，尚未完成，需要修改
 * @author 余周锦
 * @version 1.0
 * 2014-12-21
*/
public class PlayerSpeak{
	
	/**定义私有变量*/
	private JPanel speakPanel = new JPanel();

	//获取当前玩家和当前游玩区域
	private Player owner;
	private PlayArea playArea;
	
	//将要显示对话的位置
	private int x = 0;
	private int y = 90;
	
	//启动说话线程
	private Thread speak;
	
	/**构造函数，实现简单初始化
	 * @param owner 当前得分的玩家
	 * @param playArea 显示对话框的区域
	 * */
	public PlayerSpeak(Player owner, PlayArea playArea) {
		
		this.owner = owner;
		this.playArea = playArea;		
		speak();
		
	}
	
	/**实现对话框的接口*/
	private void speak(){
		
		speakPerform();	
		
	}
	
	/**实现对话框的显示的实现*/
	private void speakPerform(){
		
		//随机选择出的对话
		Random rand = new Random();
		String pathName = "/com/take6/source/picture/duihuakuang"+(1+rand.nextInt(13))+".png";
		URL path = this.getClass().getResource(pathName);
		ImageIcon icon = new ImageIcon(path);
		
		//创建对话框
		speakPanel.setOpaque(false);
		speakPanel.setSize(100, 80);
		
		JLabel contentLabel = new JLabel();
		contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentLabel.setIcon(icon);	
		speakPanel.add(contentLabel);
		
		//根据当前得分玩家设置对话框位置
		if(owner.getId() == 2){
			x = 100;
			startSpeak();
		}else if(owner.getId() == 3){
			x = 450;
			startSpeak();
		}else if(owner.getId() == 4){
			x = 830;
			startSpeak();
		}else{
			x = 0;
			y = 550;
			
			startSpeak();
			
		}
		
	}
	
	/**实现对话框的显示的实现的线程*/
	private void startSpeak(){
		
		speak = new Thread(new Runnable(){
			
			@Override
			public void run() {
				
				//暂停一秒让牌放置后,角色说话
				try{
					
					Thread.sleep(1000);
					
					
				}catch(Exception e){
					
					System.out.println("不能正确说话");
					
				}
				
				speakPanel.setLocation(x, y);
								
				playArea.add(speakPanel,0);
				
				//如果是玩家，就播放音乐
				if(owner.getId() == 1){
					
					URL musicPath = this.getClass().getResource("/com/take6/source/music/good.wav");
					try{
						AudioClip playMusic = JApplet.newAudioClip(musicPath);
						playMusic.play();
					}catch(Exception e){
						System.out.println("不能播放背景音乐");
					}
					
				}
					
				
				playArea.repaint();
				
				//对话框停留
				try{
					
					Thread.sleep(3000);
					
					
				}catch(Exception e){
					
					System.out.println("不能正确说话");
					
				}
				
				playArea.remove(speakPanel);
				
				playArea.repaint();
		
				
			}
			
		});
		
		speak.start();
	}
	
}
