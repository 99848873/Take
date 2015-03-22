package com.take6.swing;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.take6.Take6;
import com.take6.base.action.SystemAction;
import com.take6.base.user.Ai;
import com.take6.base.user.Player;
import com.take6.system.StartPlay;

/**
 * 实现游戏结束后排名内部窗口代码
 * @author 余周锦
 * @version 1.0
 * 2014-12-20
*/
@SuppressWarnings("serial")
public class EndDisplay extends InternalWindows{
	
	/**定义私有变量*/
	private Desktop desktop = Take6.display.getDesktop();

	private JPanel backPanel;

	private StartPlay stratPlay;

	private Player[] players;
	
	
	/**
	 * 构造函数，构造一个显示排名的内部窗体
	 * @param stratPlay 当前游玩的区域
	 * */
	public EndDisplay(StartPlay stratPlay) {
		
		super();
		this.stratPlay = stratPlay;		
		creatEndDisplay();
		stratPlay.getPlayArea().repaint();
		speakVoice();
		
	}
	
	/**创建显示排名窗体*/
	private void creatEndDisplay(){
					
		creatRank();
		creatBackGround();
		creatButton();
			
		desktop.add(this,new Integer(Integer.MAX_VALUE));
		try {
			setSelected(true);
		} catch (PropertyVetoException e) {
			System.out.println("不能选中该窗口");
		}
		System.out.println("输出结果");
				
	}
	
	/**创内部窗体背景和透明容器*/
	private void creatBackGround(){
		
		JDesktopPane interDesktop = new JDesktopPane();;
		
		interDesktop.setBounds(0, 0, 300, 350);
		
		JLabel background = new JLabel("背景");
		background.setBounds(0,0,300,350);
		
		URL backgroundUrl = this.getClass().getResource("/com/take6/source/picture/background.png");		
		ImageIcon backgrodunIcon = new ImageIcon(backgroundUrl);
		background.setIcon(backgrodunIcon);	
		interDesktop.add(background,new Integer(Integer.MIN_VALUE));
		interDesktop.add(backPanel);
		add(interDesktop);
		
		
	}
	
	
	/**对玩家进行排名*/
	private void sortPlayer(){
		
		Player player = stratPlay.getPlayer();;	
		Ai ai1 = stratPlay.getAi1();
		Ai ai2 = stratPlay.getAi2();
		Ai ai3 = stratPlay.getAi3();
	
	
		players = new Player[]{player,ai1,ai2,ai3};
		for(int i=0;i<players.length;i++){
			for(int j=0;j<players.length-1;j++){
				if(players[j].getScore()>players[j+1].getScore()){
					Player tempPlayer = players[j];
					players[j] = players[j+1];
					players[j+1] = tempPlayer;
				}
			}
		}
		
		
	}
	
	/**创建排名*/	
	private void creatRank(){
		
		String[] rank = new String[]{"第一名","第二名","第三名","第四名"};
		
		backPanel = new JPanel();	
		backPanel.setBounds(0,0,300,350);
		backPanel.setOpaque(false);
		backPanel.setLayout(null);
		
		int y = 50;
		sortPlayer();
		System.out.println("进行排名");
		
		if(players[0].getId() == 1){
			
			JLabel congratulateLabel = new JLabel();
			congratulateLabel.setBounds(0, 0, 280, 50);
			congratulateLabel.setFont(new Font("Win", Font.BOLD, 30));
			congratulateLabel.setText("You Win!");
			congratulateLabel.setHorizontalAlignment(SwingConstants.CENTER);
			backPanel.add(congratulateLabel);
					
		}else{
			
			JLabel comfortLabel = new JLabel();
			comfortLabel.setBounds(0, 0, 280, 50);
			comfortLabel.setFont(new Font("Lose", Font.BOLD, 30));
			comfortLabel.setText("You Lose!");	
			comfortLabel.setHorizontalAlignment(SwingConstants.CENTER);
			backPanel.add(comfortLabel);			
			
		}
		for(int i=0;i<players.length;i++){
			
			JLabel listLabel = new JLabel();
			JLabel nameLabel = new JLabel();
			JLabel scoreLabel = new JLabel();
			
			listLabel.setFont(new Font("List", Font.BOLD, 17));
			nameLabel.setFont(new Font("List", Font.BOLD, 17));
			scoreLabel.setFont(new Font("List", Font.BOLD, 17));
			
			listLabel.setForeground(Color.DARK_GRAY);
			nameLabel.setForeground(Color.DARK_GRAY);
			scoreLabel.setForeground(Color.DARK_GRAY);
			
			listLabel.setBounds(10, y+50*i, 80, 50);
			nameLabel.setBounds(90, y+50*i, 90, 50);
			scoreLabel.setBounds(210, y+50*i, 100, 50);
			
			listLabel.setText(rank[i]+":");
			nameLabel.setText(players[i].getUserName());
			scoreLabel.setText(players[i].getScore()+"分");
			
			listLabel.setHorizontalAlignment(SwingConstants.CENTER);
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			scoreLabel.setHorizontalAlignment(SwingConstants.LEFT);
			
			if(players[i].getId()==1){
				
				listLabel.setFont(new Font("Player", Font.BOLD, 22));
				nameLabel.setFont(new Font("Player", Font.BOLD, 22));
				scoreLabel.setFont(new Font("Player", Font.BOLD, 22));
				
				listLabel.setForeground(Color.BLUE);
				nameLabel.setForeground(Color.BLUE);
				scoreLabel.setForeground(Color.BLUE);
				
			}

			backPanel.add(listLabel);
			backPanel.add(nameLabel);
			backPanel.add(scoreLabel);
			
		}
		
	}
	
	/**创建重新开始游戏按钮*/	
	private void creatButton(){
		
		JButton restart = new JButton("重新开始");
		restart.setBounds(90, 255, 100, 40);
		restart.setActionCommand("restart");
		restart.addActionListener(new SystemAction(desktop));
		backPanel.add(restart);
		
	}
	
	/**实现播放声音的实现*/
	private void speakVoice(){
	
		Thread paly = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try{
					URL musicPath=null;
					
					if(players[0].getId() == 1){
						musicPath = this.getClass().getResource("/com/take6/source/music/win.wav");
					}else
						musicPath = this.getClass().getResource("/com/take6/source/music/Lose.wav");
					
					AudioClip playMusic = JApplet.newAudioClip(musicPath);
					playMusic.play();
				}catch(Exception e){
					System.out.println("不能播放背景音乐");
				}
				
			}
		});
		paly.start();
		paly.setPriority(Thread.MAX_PRIORITY);
		try{
			paly.join();
		}catch(Exception e){
			System.out.println("未能正常播放音乐");
		}
		
		
	}
	

}
