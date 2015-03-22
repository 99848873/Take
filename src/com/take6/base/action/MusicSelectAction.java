package com.take6.base.action;

import java.applet.AudioClip;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JRadioButtonMenuItem;


/**
 * 实现播放背景音乐并根据菜单情况响应开关背景音乐
 * @author 余周锦
 * @version 1.0
 * 2014-12-26
*/
public class MusicSelectAction implements ItemListener{
	
	/**播放背景音乐的引用*/
	public static AudioClip playMusic = null;
	public static boolean musicState = true;
	JRadioButtonMenuItem musicoption;

	/**播放背景音乐构造函数
	 * 让游戏一开始自动播放背景音乐
	 * */
	public MusicSelectAction(JRadioButtonMenuItem musicoption) {
		playMusic();
		this.musicoption = musicoption;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {

		//如果当前item 是被选中状态，播放背景音乐
		if(e.getStateChange() == ItemEvent.SELECTED){
			playMusic.loop();
			musicoption.setText("关闭背景音乐");
			musicState = true;
		//如果当前item 是取消状态，播放背景音乐
		}else if(e.getStateChange() == ItemEvent.DESELECTED){
			playMusic.stop();
			musicoption.setText("开启背景音乐");
			musicState = false;
		}
		
	}
	
	//播放音乐方法，开启一个线程，直接开始播放音乐，在菜单中对播放音乐开关进行控制
	private void playMusic(){
			
		URL musicPath = this.getClass().getResource("/com/take6/source/music/backMusic.wav");
			
		Thread play = new Thread(new Runnable() {
				
			@Override
			public void run() {
					
				try{
					playMusic = JApplet.newAudioClip(musicPath);
					playMusic.loop();
				}catch(Exception e){
					System.out.println("不能正常播放背景音乐");
				}
			}
		});
		play.setPriority(Thread.MAX_PRIORITY);
		play.start();
			
	}

}
