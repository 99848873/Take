package com.take6.system;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.take6.base.action.SelectArea;
import com.take6.base.card.Card;
import com.take6.swing.CardArea;
import com.take6.swing.PlayArea;
import com.take6.swing.PlayerSpeak;

/**
 * 编写一个显示类，用来表现当玩家完成出牌后，在图形界面上实现的动画及布局
 * @version 1.0
 * @author 余周锦
 * 2014-12-17
*/
public class Display {
	
	/**定义私有变量*/
	private StartPlay stratPlay;//当前游玩区域，里面包含了所有当前活动状态下的对象
	
	private PlayArea playArea;//当前游玩区域的引用
		
	//当前区域中玩家的盖牌区域
	private JLayeredPane playerArea;
	private JPanel ai1ReadyPane;
	private JPanel ai2ReadyPane;
	private JPanel ai3ReadyPane;
	private JPanel playerReadyPane;
	
	//盖牌所用label
	JLabel ai1ReadyLabel;
	JLabel ai2ReadyLabel;
	JLabel ai3ReadyLabel;
	JLabel playerReadyLabel;
		
	//当前游玩区域中需要被操作的玩家手牌引用
	private Card playerCard;
	private Card ai1Card;
	private Card ai2Card;
	private Card ai3Card;
	
	//用来放置玩家出牌对比大小后的临时牌组
	private Card[] compareCards = new Card[4];
	
	//创建一个对比起用来对比玩家出牌的数值大小
	private CompareCard compare = new CompareCard();
		
	//实现玩家丢分后进行说话操作
	@SuppressWarnings("unused")
	private PlayerSpeak playerSpeak;
	
	//实现玩家选择自动放牌功能的标志位
	private boolean auto = false;

	/**
	 * 构造函数，将所需要的对象进行初始化操作
	 * @param stratPlay 游玩区域的引用
	 * @param card 当前玩家操作的牌
	*/
	public Display(StartPlay stratPlay, Card card) {
		
		this.stratPlay = stratPlay;
		this.playerCard = card;
		this.playArea = stratPlay.getPlayArea();
		this.ai1ReadyPane = playArea.getAi1ReadyPanle();
		this.ai2ReadyPane = playArea.getAi2ReadyPanle();
		this.ai3ReadyPane = playArea.getAi3ReadyPanle();
		this.playerArea = playArea.getPlayerArea();	
		this.playerReadyPane = playArea.getPlayerReadyPanle();
		
	}
	
	/**
	 * 完成牌类移动的接口方法
	 * @version 1.0
	 * @author 余周锦
	 * 2014-12-17
	*/
	public void moveCard(){
		
		moveCardPerform();

	}
	
	/**
	 * 完成牌类移动的实现方法
	 * @version 1.0
	 * @author 余周锦
	 * 2014-12-17
	*/
	private void moveCardPerform(){	
	
		//玩家出牌完成，首先刷新玩家手牌区域
		arrangPlayerArea();
				
		ai1Card = stratPlay.getAi1().pushCard(playArea);
		ai2Card = stratPlay.getAi2().pushCard(playArea);
		ai3Card = stratPlay.getAi3().pushCard(playArea);
		
		
		//定义线程2，实现玩家手牌从对比区域进入正确的放置区域
		 Thread displayStep2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					//将玩家的牌进行通过分值进行排列，返回一个从小到大的排列数组
					compareCards = compare.compareNumber(playerCard, ai1Card, ai2Card, ai3Card);
					displayCardToRightArea();
					playArea.setReadyState(true);
					
				}catch (NullPointerException ex){
					
					System.out.println("玩家重置游戏，刷新界面或者由于系统错误未能正确将卡牌放到准备区域");
					
				}
				
				
			}
			
		});
		//创建两个线程实现动态效果
		//定义线程1，实现玩家手牌放置到对比区域，在完成放置后，将线程2插入
		Thread displayStep1 = new Thread(new Runnable() {;
			
			@Override
			public void run() {
				
				try{
					
					displayCardToReadyArea();
					
				}catch (Exception ex){
					
					System.out.println("玩家重置游戏，刷新界面或者由于系统错误未能正确将卡牌放到指定区域");
					
				}
				
				//在县城一结束后执行线程2
					displayStep2.start();
				
			}
		});
		
		//修改线程优先级，优化代码
		displayStep1.setPriority(Thread.NORM_PRIORITY);
		displayStep2.setPriority(Thread.MIN_PRIORITY);
		
		displayStep1.start();
		
	}
	
	
	/**定义一个放在同步块中的方法，提供线程1 完成手牌放置*/
	private synchronized void displayCardToReadyArea(){	
		
		//完成盖牌
		waitSomeSecond(500);
		coverReadyArea();
		//开始倒计时
		timePlay(3);
		//完成翻牌
		reverseReadyArea();
		//放置完成后，等待1秒，让玩家确认当前状态
		waitSomeSecond(1000);
	
	}
	
	/**定义一个放在同步块中的方法，提供线程2 完成手牌放置*/
	private synchronized void displayCardToRightArea(){
	
		for(int i=0;i<compareCards.length;i++){
			waitSomeSecond(1000);
			moveCardToRightArea(compareCards[i]);
			
		}
	
	}
	
	/**定义一个实现移动的方法，实现确认手牌的放置区域
	 * @param card 当前需要被移动的卡牌
	 * */
	private void moveCardToRightArea(Card card) {
		
		//获得当前四个放牌区域的最后一张牌
		Card location1card = playArea.getCardAreaA().getCards().get(playArea.getCardAreaA().getCards().size()-1);
		Card location2card = playArea.getCardAreaB().getCards().get(playArea.getCardAreaB().getCards().size()-1);
		Card location3card = playArea.getCardAreaC().getCards().get(playArea.getCardAreaC().getCards().size()-1);
		Card location4card = playArea.getCardAreaD().getCards().get(playArea.getCardAreaD().getCards().size()-1);
		
		//按最后一张牌的数字进行从小大排列
		Card[] compareResult = compare.compareNumber(location1card, location2card, location3card, location4card);
		
		//判断并设置当前card所属的位置
		for(int i=0;i<compareResult.length;i++){
			if(card.getNumber()>compareResult[i].getNumber()){
				card.setCardLocation(compareResult[i].getCardLocation());	
			}
		}
		
		//根据当前card所属的位置进行相应的操作
		if(card.getCardLocation() == 0){
			
				if(card.getOwner().getId()==1){
					
					//创建选择区域
					creatSelect(card,8);
			
				}else if(card.getOwner().getId()!=1){
					
					setLocation(card);
					
				}
									
			
			//根据当前card所属的位置进行相应的操作
			if(card.getCardLocation() == 1){
				addTo(card,playArea.getCardAreaA());
			}else if(card.getCardLocation() == 2){
				addTo(card,playArea.getCardAreaB());
			}else if(card.getCardLocation() == 3){
				addTo(card,playArea.getCardAreaC());
			}else if(card.getCardLocation() == 4){
				addTo(card,playArea.getCardAreaD());
			}
			
		}else if(card.getCardLocation() == 1){
			moveTo(card,playArea.getCardAreaA());
		}else if(card.getCardLocation() == 2){
			moveTo(card,playArea.getCardAreaB());
		}else if(card.getCardLocation() == 3){
			moveTo(card,playArea.getCardAreaC());
		}else if(card.getCardLocation() == 4){
			moveTo(card,playArea.getCardAreaD());
		}
		
	}
	
	/**定义一个实现移动的方法，确定手牌属于哪一个玩家并将该玩家的手牌移动到指定区域
	 * @param card 需要被移动的卡牌
	 * @param cardArea 需要被移动到的位置
	 * */
	private void moveTo(Card card, CardArea cardArea) {

		if(card.getOwner().getId() == 1){
			
			autoMoveCard(card,cardArea);
			playArea.getPlayerInfDisplay().setText("分数为:"+stratPlay.getPlayer().getScore());
			playArea.getPlayerInfDisplay().repaint();
			
		}else if(card.getOwner().getId() == 2){	
			
			autoMoveCard(card,cardArea);
			stratPlay.getAi1().getCards().remove(card);
			playArea.getAi1InfDisplay().setText("分数为:"+stratPlay.getAi1().getScore());
			playArea.getAi1InfDisplay().repaint();
			
		}else if(card.getOwner().getId() == 3 ){	
			
			autoMoveCard(card,cardArea);
			stratPlay.getAi2().getCards().remove(card);	
			playArea.getAi2InfDisplay().setText("分数为:"+stratPlay.getAi2().getScore());
			playArea.getAi2InfDisplay().repaint();
			
		}else if(card.getOwner().getId() == 4){	
			
			autoMoveCard(card,cardArea);
			stratPlay.getAi3().getCards().remove(card);	
			playArea.getAi3InfDisplay().setText("分数为:"+stratPlay.getAi3().getScore());
			playArea.getAi3InfDisplay().repaint();
			
		}
	}
	
	/**定义一个实现移动的方法，将手牌移动到指定区域，并判断是否需要得分，给相应的玩家增加新的分数
	 * @param card 需要被移动的卡牌
	 * @param cardArea 需要被移动到的位置
	 * */
	private void autoMoveCard(Card card,CardArea cardArea){
		
		if(cardArea.getCards().size()<5){
			
			int x = cardArea.getCards().get(cardArea.getCards().size()-1).getX()+105;
			int y = cardArea.getCards().get(cardArea.getCards().size()-1).getY();
			card.setLocation(x, y);
			
			URL musicPath = this.getClass().getResource("/com/take6/source/music/give.wav");
			
			playSound(musicPath);
			
			cardArea.add(card);	
			repaintAll();
			
		}else if(cardArea.getCards().size() == 5){	
			
			card.getOwner().setScore(cardArea.getCards().getTotalScore());
			int x = cardArea.getCards().get(0).getX();
			int y = cardArea.getCards().get(0).getY();
			
			URL musicPath = this.getClass().getResource("/com/take6/source/music/bomb.wav");
			playSound(musicPath);
			
			cardArea.removeAll();
			card.setLocation(x, y);
			cardArea.add(card);	
			//如果得分，显示对话框
			playerSpeak = new PlayerSpeak(card.getOwner(),playArea);
			repaintAll();
			
		}	
	}
	
	/**定义一个实现移动的方法，确定该手牌属于哪个玩家，将该玩家的手牌移动到指定区域
	 * @param card 需要被移动的卡牌
	 * @param cardArea 需要被移动到的位置 
	 * */
	private void addTo(Card card, CardArea cardArea) {

		if(card.getOwner().getId() == 1){
			
			autoAddCard(card,cardArea);
			playArea.getPlayerInfDisplay().setText("分数为:"+stratPlay.getPlayer().getScore());
			playArea.getPlayerInfDisplay().repaint();
			
		}else if(card.getOwner().getId() == 2){
			
			autoAddCard(card,cardArea);
			stratPlay.getAi1().getCards().remove(card);
			playArea.getAi1InfDisplay().setText("分数为:"+stratPlay.getAi1().getScore());
			playArea.getAi1InfDisplay().repaint();
			
		}else if(card.getOwner().getId() == 3){	
			
			autoAddCard(card,cardArea);
			stratPlay.getAi2().getCards().remove(card);	
			playArea.getAi2InfDisplay().setText("分数为:"+stratPlay.getAi2().getScore());
			playArea.getAi2InfDisplay().repaint();
			
		}else if(card.getOwner().getId() == 4){		
			
			autoAddCard(card,cardArea);
			stratPlay.getAi3().getCards().remove(card);	
			playArea.getAi3InfDisplay().setText("分数为:"+stratPlay.getAi3().getScore());
			playArea.getAi3InfDisplay().repaint();
			
		}
		
	}
	
	/**定义一个实现移动的方法，将手牌移动到指定区域，给相应的玩家增加新的分数
	 * @param card 需要被移动的卡牌
	 * @param cardArea 需要被移动到的位置
	 * */
	private void autoAddCard(Card card, CardArea cardArea) {

		card.getOwner().setScore(cardArea.getCards().getTotalScore());
		int x = cardArea.getCards().get(0).getX();
		int y = cardArea.getCards().get(0).getY();
		
		URL musicPath = this.getClass().getResource("/com/take6/source/music/bomb.wav");
		
		playSound(musicPath);
		
		cardArea.removeAll();
		card.setLocation(x, y);
		cardArea.add(card);	
		//如果得分，显示对话框
		playerSpeak = new PlayerSpeak(card.getOwner(),playArea);
		repaintAll();
		
	}
	
	/**重新排列玩家的手牌，实现动画效果*/
	private void arrangPlayerArea(){
		
		stratPlay.getPlayer().getCards().remove(playerCard);	
		playerArea.remove(playerCard);
	
		int count = 10-stratPlay.getPlayer().getCards().size();
		
		int x = 0+count*45;
		int y = 10;
		
		for(int i=0; i<stratPlay.getPlayer().getCards().size();i++){
			
			stratPlay.getPlayer().getCards().get(i).setLocation(x+i*90, y);		
			playerArea.repaint();

		}
		
	}
	
	/**实现盖牌效果，实现动画效果*/
	private void coverReadyArea(){
		
		//盖牌效果使用的背景图片
		URL cardDackPath = this.getClass().getResource("/com/take6/source/picture/cardback.png");
		ImageIcon icon = new ImageIcon(cardDackPath);
		//玩家进行盖牌
		ai1ReadyLabel = new JLabel();
		ai1ReadyLabel.setBounds(0, 0, 100, 140);
		ai1ReadyLabel.setIcon(icon);
		
		ai2ReadyLabel = new JLabel();
		ai2ReadyLabel.setBounds(0, 0, 100, 140);
		ai2ReadyLabel.setIcon(icon);
		
		ai3ReadyLabel = new JLabel();
		ai3ReadyLabel.setBounds(0, 0, 100, 140);
		ai3ReadyLabel.setIcon(icon);
		
		playerReadyLabel = new JLabel();
		playerReadyLabel.setBounds(0, 0, 100, 140);
		playerReadyLabel.setIcon(icon);
		
		ai1ReadyPane.add(ai1ReadyLabel);
		ai1ReadyPane.repaint();
		ai2ReadyPane.add(ai2ReadyLabel);
		ai2ReadyPane.repaint();
		ai3ReadyPane.add(ai3ReadyLabel);
		ai3ReadyPane.repaint();
		playerReadyPane.add(playerReadyLabel);
		playerReadyPane.repaint();
				
	}
	
	/**实现翻牌效果，实现动画效果*/
	private void reverseReadyArea(){
		
		//将Ai1的牌显示出来
		ai1ReadyPane.remove(ai1ReadyLabel);
		ai1Card.setLocation(0, 0);
		ai1ReadyPane.add(ai1Card);
		ai1ReadyPane.repaint();
		
		//将Ai2的牌显示出来
		ai2ReadyPane.remove(ai2ReadyLabel);
		ai2Card.setLocation(0, 0);
		ai2ReadyPane.add(ai2Card);
		ai2ReadyPane.repaint();
		
		//将Ai3的牌显示出来
		ai3ReadyPane.remove(ai3ReadyLabel);
		ai3Card.setLocation(0, 0);
		ai3ReadyPane.add(ai3Card);
		ai3ReadyPane.repaint();
		
		//将player的牌显示出来
		playerReadyPane.remove(playerReadyLabel);
		playerCard.setLocation(0, 0);
		playerReadyPane.add(playerCard);
		playerReadyPane.repaint();
				
	}
	
	/**实现倒计时，实现动画效果*/
	private void timePlay(int i){
		
		//显示倒计时用Panel 及 label
		JPanel timePanel = new JPanel();
		timePanel.setBounds(500, 200, 280, 240);
		timePanel.setLayout(null);
		timePanel.setOpaque(false);
		
		//创建显示time 的label
		JLabel timeLabel = new JLabel(""+i);
		timeLabel.setBounds(0, 0, 280, 240);
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(new Font("time",Font.BOLD,180));
		timeLabel.setForeground(Color.RED);
		
		timePanel.add(timeLabel);
		playArea.add(timePanel,0);
		playArea.repaint();
		URL musicPath = this.getClass().getResource("/com/take6/source/music/da.wav");
		playSound(musicPath);
		waitSomeSecond(1000);
		
		//动态显示时间
		for(;i>0;i--){
			if(i>1){
				
				timeLabel.setText(""+(i-1));
				
			}else
				timeLabel.setText("Go");
			
			playSound(musicPath);
			timeLabel.repaint();
			waitSomeSecond(1000);
		}
		
		//倒数结束后删除时间panel
		playArea.remove(timePanel);
		playArea.repaint();
		
	}

	/**定义显示选取位置的按钮及倒计时的方法
	 * @param card 需要被选择放置位置的牌
	 * @param time 玩家剩余时间
	 * */
	private void creatSelect(Card card,int time){
		
		
		//创建提示玩家收取自己想要选择的那一列
		JPanel selectButtonPanel = new JPanel();;
		JLabel selectTime = new JLabel();;
		
		JButton selectButtonA = new JButton("第一组");
		JButton selectButtonB = new JButton("第二组");
		JButton selectButtonC = new JButton("第三组");
		JButton selectButtonD = new JButton("第四组");
		JButton autoSelectButton = new JButton("自动选择");
		
		selectButtonA.setBounds(550, 50, 70,40);
		selectButtonB.setBounds(660,50, 70, 40);
		selectButtonC.setBounds(550, 200, 70, 40);
		selectButtonD.setBounds(660,200, 70, 40);
		autoSelectButton.setBounds(600,260, 80, 40);
		
		selectButtonA.addActionListener(new SelectArea(playArea.getCardAreaA(),card));
		selectButtonB.addActionListener(new SelectArea(playArea.getCardAreaB(),card));
		selectButtonC.addActionListener(new SelectArea(playArea.getCardAreaC(),card));
		selectButtonD.addActionListener(new SelectArea(playArea.getCardAreaD(),card));
		autoSelectButton.setActionCommand("auto");
		autoSelectButton.addActionListener(new SelectArea(this)); 
		
		selectTime.setBounds(500, 0, 300, 300);
		selectTime.setFont(new Font("time",Font.BOLD,40));
		selectTime.setForeground(Color.YELLOW);
		selectTime.setHorizontalAlignment(SwingConstants.CENTER);
		
		selectButtonPanel.setLayout(null);
		selectButtonPanel.setOpaque(false);
		selectButtonPanel.setBounds(0, 185, 1280, 300);
		
		selectButtonPanel.add(selectButtonA);
		selectButtonPanel.add(selectButtonB);
		selectButtonPanel.add(selectButtonC);
		selectButtonPanel.add(selectButtonD);
		selectButtonPanel.add(autoSelectButton);
		selectButtonPanel.add(selectTime);
		
		playArea.add(selectButtonPanel,0);
		playArea.repaint();
						
		//进行倒计时
		for(int i=time*10;i>-1;--i){
			
			if(i%10==0){
				selectTime.setText("剩余"+time+"秒选择");
				--time;
			}
				
			waitSomeSecond(100);
						
			if(card.getCardLocation()!=0||auto == true){

				break;
				
			}
			
		}
		
		if(card.getCardLocation()==0){		
			setLocation(card);			
		}
		
		setAuto(false);
		playArea.remove(selectButtonPanel);
		playArea.repaint();
		
	}
	
	/**定义设置当前card位置方法
	 * @param card 需要被定义位置的牌
	 * */
	private void setLocation(Card card){
		
		//创建一个对比器，找到当前桌面四个区域总分最小的一个，并设置当前卡牌的位置
		CompareCards compare  = new CompareCards();
		int cardLocation = compare.compareScore(playArea.getCardAreaA().getCards(), playArea.getCardAreaB().getCards(), playArea.getCardAreaC().getCards(), playArea.getCardAreaD().getCards());
		card.setCardLocation(cardLocation);
		
	}
	
	/**定义播放音效的方法
	 * @param musicPath 需要被播放的音乐
	 * */
	private void playSound(URL musicPath){
						
			try{
				AudioClip playMusic = JApplet.newAudioClip(musicPath);
				playMusic.play();
			}catch(Exception e){
				System.out.println("不能播放背景音乐");
			}
				
	}
	
	
	/**定义一个线程的等待时间方法，实现动画效果*/
	private void waitSomeSecond(int i){
		
		try{
			Thread.sleep(i);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**定义刷新方法，将界面上所有组件刷新，避免出现图形界面bug*/
	private void repaintAll(){
		
		playArea.getPlayerReadyPanle().repaint();
		playArea.getAi1ReadyPanle().repaint();
		playArea.getAi2ReadyPanle().repaint();
		playArea.getAi3ReadyPanle().repaint();
		playArea.getCardAreaA().repaint();
		playArea.getCardAreaB().repaint();
		playArea.getCardAreaC().repaint();
		playArea.getCardAreaD().repaint();
		playArea.getPlayerArea().repaint();	
		
	}
	

	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	

}
