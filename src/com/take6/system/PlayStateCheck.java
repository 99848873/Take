package com.take6.system;

import java.applet.AudioClip;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JLayeredPane;

import com.take6.base.action.MusicSelectAction;
import com.take6.base.action.SelectCard;
import com.take6.base.user.Ai;
import com.take6.base.user.Player;
import com.take6.swing.EndDisplay;


/**
 * 编写一个线程类，用来表现监控玩家的牌是否已经出完，实现下一次发牌
 * @version 1.0
 * @author 余周锦
 * 2014-12-17
*/
public class PlayStateCheck implements Runnable{
	
	/**定义私有变量， 包含需要发牌的玩家和需要显示发牌区域*/
	private StartPlay stratPlay;
	private Deal deal;
	private Player player;
	private Ai ai1; 
	private Ai ai2;
	private Ai ai3;
	private JLayeredPane playerArea;
	
	private boolean runState = true;

	
	/**
	 * 构造函数，将所需要的对象进行初始化操作
	 * @param startPlay 游玩区域的引用
	*/
	public PlayStateCheck(StartPlay startPlay){
		this.stratPlay = startPlay;
		deal = stratPlay.getDeal();
		player = stratPlay.getPlayer();
		playerArea = stratPlay.getPlayArea().getPlayerArea();
		ai1 = stratPlay.getAi1();
		ai2 = stratPlay.getAi2();
		ai3 = stratPlay.getAi3();
	}

	@Override
	public void run() {
		
		//实现线程方法，进行循环检查，当前玩家手牌是否为空是否需要发牌
		while(true){
			//如果玩家reset，停止进程
			if(runState == false){
				
				System.out.println("玩家重置系统，监听停止");
				break;
				
			}
			
			//如果游戏结束，计算当前所有玩家分数，显示得分界面
			if(deal.getAllCardList().size()==0
					&&player.getCards().size() ==0
					&&ai1.getCards().size() ==0
					&&ai2.getCards().size() ==0
					&&ai3.getCards().size() ==0
					&&stratPlay.getPlayArea().isReadyState() == true){
				
				if(MusicSelectAction.musicState){
					System.out.println();
					MusicSelectAction.playMusic.stop();
					MusicSelectAction.musicState = false;
				}
				new EndDisplay(stratPlay);
				System.out.println("完成游戏");
				break;
				
			}else if(deal.getAllCardList().size()!=0){
				
	
				//给玩家发牌，需要在界面上显示，并给每一张牌添加监听器
				if(player.getCards().size() ==0&&stratPlay.getPlayArea().isReadyState() == true){

					URL musicPath;
					
					stratPlay.getPlayArea().setReadyState(false);
					
					if(deal.getAllCardList().size() <= 20){
						musicPath = this.getClass().getResource("/com/take6/source/music/distribute1.wav");
					}else
						musicPath = this.getClass().getResource("/com/take6/source/music/distribute2.wav");
					
					speakVoice(musicPath);
				
					//发牌给玩家
					deal.dealCardListToPlayer(player);	
					
					//根据发牌数确认当前放置第一张牌的位置
					int count = 10-(player.getCards().size());
		
					int x = 0+count*45;
					int y = 10;	
					
					//将玩家的手牌进行从小到大的排序后再显示在界面上
					SortCards sortCards = new SortCards();
					sortCards.sortCard(player.getCards());
					
					//将玩家的手牌显示在界面上，并添加监听器
					for(int i=0; i<player.getCards().size();i++){

						player.getCards().get(i).setLocation(x+i*90, y);
						player.getCards().get(i).addMouseListener(new SelectCard(stratPlay, player.getCards().get(i)));
						playerArea.add(player.getCards().get(i),0);
						playerArea.repaint();
						
						try{
							Thread.sleep(180);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					stratPlay.getPlayArea().setReadyState(true);
				}
				
				//如果ai1没有牌给ai1发牌
				if(ai1.getCards().size() ==0){
					deal.dealCardListToPlayer(ai1);
				}
				//如果ai2没有牌给ai2发牌
				if(ai2.getCards().size() ==0){
					deal.dealCardListToPlayer(ai2);
				}
				//如果ai3没有牌给ai3发牌
				if(ai3.getCards().size() ==0){	
					deal.dealCardListToPlayer(ai3);
				}	
				
				
			}
			
			try{
				Thread.sleep(500);
			}catch (Exception e){
				System.out.println("监听失败");
			}
			
			//后台显示当前进程正常工作
//			System.out.println("正在监听当前游戏进度");
			
		
		}
	
	}

	public void setRunState(boolean runState) {
		this.runState = runState;
	}
	
	/**实现播放声音的实现的线程*/
	private void speakVoice(URL musicPath){
				
		Thread play = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try{
					AudioClip playMusic = JApplet.newAudioClip(musicPath);
					playMusic.play();
				}catch(Exception e){
					System.out.println("不能播放背景音乐");
				}
			}
		});
		play.setPriority(Thread.NORM_PRIORITY);
		play.start();
		
	}

}
