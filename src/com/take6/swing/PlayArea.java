package com.take6.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.take6.system.StartPlay;

/**
 * 实现桌面的游玩区域,当玩家选择开始后，创建当前游玩区域
 * @author 余周锦
 * @version 1.0
 * 2014-12-9
*/
@SuppressWarnings("serial")
public class PlayArea extends JLayeredPane {
	
	/** 定义游玩区域的成员变量*/
	private CardArea cardAreaA;//界面上放置玩家打出的牌的区域A
	private CardArea cardAreaB;//界面上放置玩家打出的牌的区域B
	private CardArea cardAreaC;//界面上放置玩家打出的牌的区域C
	private CardArea cardAreaD;//界面上放置玩家打出的牌的区域D

	private JLayeredPane playerArea;//界面上放置用户牌的区域
	
	//显示ai信息的区域及ai盖牌区域
	private JPanel aiPanel;
	private JLabel ai1InfDisplay;
	private JLabel ai1InfName;
	private JPanel ai1ReadyPanle;
	
	private JLabel ai2InfDisplay;
	private JLabel ai2InfName;
	private JPanel ai2ReadyPanle;
	
	private JLabel ai3InfDisplay;
	private JLabel ai3InfName;
	private JPanel ai3ReadyPanle;

	//显示玩家信息的区域及玩家盖牌区域
	private JPanel playerPanel;
	private JLabel playerInfName;
	private JLabel playerInfDisplay;
	private JPanel playerReadyPanle;

	private StartPlay startPlay;

	private boolean readyState = true;//当前区域是否尚未进入待出牌阶段
	
	
	
	/** 定义游玩区域的构造函数，在构造游玩区域同时，将界面所有区域布局在界面上
	 *  @param title 游玩区域的题目
	 * */
	public PlayArea(String title){
		
		super();
		setName(title);
		setOpaque(false);
		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		creatCardPanel();
		creatPlayerCardArea();
		creatPlayPanel();
		creatAiPanel();
		
		startPlay = new StartPlay(this);
		
	}
	
	/**启动清空本类内容方法，用于reset功能*/
	public void reset(){
		
		removeAll();
		startPlay.reset();
		repaint();
		setStartPlay(null);
		
	}


	/** 定义玩家区域的创建方法 */
	private void creatPlayPanel(){
	
		playerPanel = new JPanel();
		playerPanel.setBounds(30, 500, 150, 150);
		playerPanel.setOpaque(false);
		playerPanel.setLayout(null);
				
		playerInfName = new JLabel();
		playerInfName.setBounds(0, 100, 150, 25);
		playerInfName.setHorizontalAlignment(SwingConstants.CENTER);
		playerInfName.setForeground(Color.YELLOW);
		playerInfName.setFont(new Font("name",Font.BOLD,15));
		
		playerInfDisplay = new JLabel();
		playerInfDisplay.setBounds(0,125, 150, 25);
		playerInfDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		playerInfDisplay.setForeground(Color.YELLOW);
		playerInfDisplay.setFont(new Font("name",Font.BOLD,15));
		
		
		playerPanel.add(playerInfName);
		playerPanel.add(playerInfDisplay);
		add(playerPanel,1);
		
	}

	/** 定义Ai区域的创建方法 */
	private void creatAiPanel(){
		
		aiPanel = new JPanel();
		aiPanel.setBounds(160, 20, 1100, 150);
		aiPanel.setLayout(null);
		aiPanel.setOpaque(false);
		//ai1部分		
		ai1InfName = new JLabel("名字");
		ai1InfName.setBounds(10, 100, 100, 25);
		ai1InfName.setHorizontalAlignment(SwingConstants.CENTER);
		ai1InfName.setForeground(Color.YELLOW);
		ai1InfName.setFont(new Font("name",Font.BOLD,15));
		
		ai1InfDisplay = new JLabel("AI1信息区域");	
		ai1InfDisplay.setBounds(10, 125, 100,25);
		ai1InfDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		ai1InfDisplay.setForeground(Color.YELLOW);
		ai1InfDisplay.setFont(new Font("name",Font.BOLD,15));
		
		ai1ReadyPanle = new JPanel();
		ai1ReadyPanle.setBounds(120, 0, 100, 140);
		ai1ReadyPanle.setLayout(null);
		ai1ReadyPanle.setOpaque(false);
		
		//ai2部分	
		ai2InfName = new JLabel("名字");
		ai2InfName.setBounds(380, 100, 100, 25);
		ai2InfName.setHorizontalAlignment(SwingConstants.CENTER);
		ai2InfName.setForeground(Color.YELLOW);
		ai2InfName.setFont(new Font("name",Font.BOLD,15));
		
		ai2InfDisplay = new JLabel("AI2信息区域");
		ai2InfDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		ai2InfDisplay.setBounds(380, 125, 100, 25);
		ai2InfDisplay.setForeground(Color.YELLOW);
		ai2InfDisplay.setFont(new Font("name",Font.BOLD,15));
		
		ai2ReadyPanle = new JPanel();
		ai2ReadyPanle.setBounds(490, 0, 100, 140);
		ai2ReadyPanle.setLayout(null);
		ai2ReadyPanle.setOpaque(false);
		
		
		//ai3部分
		ai3InfName = new JLabel("名字");
		ai3InfName.setBounds(750, 100, 100, 25);
		ai3InfName.setHorizontalAlignment(SwingConstants.CENTER);
		ai3InfName.setForeground(Color.YELLOW);
		ai3InfName.setFont(new Font("name",Font.BOLD,15));
		
		ai3InfDisplay = new JLabel("AI3信息区域");
		ai3InfDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		ai3InfDisplay.setBounds(750, 125, 100, 25);
		ai3InfDisplay.setForeground(Color.YELLOW);
		ai3InfDisplay.setFont(new Font("name",Font.BOLD,15));

		ai3ReadyPanle = new JPanel();
		ai3ReadyPanle.setBounds(860, 0, 100, 140);
		ai3ReadyPanle.setLayout(null);
		ai3ReadyPanle.setOpaque(false);
		
	
		aiPanel.add(ai1InfName);
		aiPanel.add(ai1InfDisplay);
		aiPanel.add(ai1ReadyPanle);
		
	
		aiPanel.add(ai2InfName);
		aiPanel.add(ai2InfDisplay);
		aiPanel.add(ai2ReadyPanle);
		
		
		aiPanel.add(ai3InfName);
		aiPanel.add(ai3InfDisplay);	
		aiPanel.add(ai3ReadyPanle);
		
		add(aiPanel,1);
	}

	/** 定义卡牌区域的创建方法 */
	private void creatCardPanel(){
		
		cardAreaA = new CardArea(1);
		cardAreaA.setLayout(null);
		cardAreaA.setOpaque(false);
		cardAreaA.setBounds(20, 185, 520, 150);
		
		cardAreaB = new CardArea(2);
		cardAreaB.setLayout(null);
		cardAreaB.setOpaque(false);
		cardAreaB.setBounds(740, 185, 520, 150);
		
		cardAreaC = new CardArea(3);
		cardAreaC.setLayout(null);
		cardAreaC.setOpaque(false);
		cardAreaC.setBounds(20, 340, 520, 150);
		
		cardAreaD = new CardArea(4);
		cardAreaD.setLayout(null);
		cardAreaD.setOpaque(false);
		cardAreaD.setBounds(740, 340, 520, 150);
		
		add(cardAreaA,1);
		add(cardAreaC,1);
		add(cardAreaB,1);
		add(cardAreaD,1);
	}
	
	/** 定义玩家手牌区域的创建方法 */
	private void creatPlayerCardArea(){
		
		playerArea = new JLayeredPane();
		playerArea.setBounds(190, 500, 960, 150);
		playerArea.setLayout(null);
		playerArea.setOpaque(false);
		add(playerArea,1);
		
		playerReadyPanle = new JPanel();
		playerReadyPanle.setBounds(1120, 510, 100, 140);
		playerReadyPanle.setLayout(null);
		playerReadyPanle.setOpaque(false);
		add(playerReadyPanle, 1);
	
	}
	
	public CardArea getCardAreaA() {
		return cardAreaA;
	}

	public CardArea getCardAreaB() {
		return cardAreaB;
	}

	
	public CardArea getCardAreaC() {
		return cardAreaC;
	}

	public CardArea getCardAreaD() {
		return cardAreaD;
	}

	public JLayeredPane getPlayerArea() {
		return playerArea;
	}

	public JPanel getAiPanel() {
		return aiPanel;
	}

	public JPanel getPlayerPanel() {
		return playerPanel;
	}

	public boolean isReadyState() {
		return readyState;
	}

	public void setReadyState(boolean readyState) {
		this.readyState = readyState;
	}

	public JLabel getAi1InfDisplay() {
		return ai1InfDisplay;
	}

	public JLabel getAi2InfDisplay() {
		return ai2InfDisplay;
	}

	public JLabel getAi3InfDisplay() {
		return ai3InfDisplay;
	}

	public JLabel getPlayerInfDisplay() {
		return playerInfDisplay;
	}

	public void setStartPlay(StartPlay startPlay) {
		this.startPlay = startPlay;
	}

	public JLabel getAi1InfName() {
		return ai1InfName;
	}

	public JLabel getAi2InfName() {
		return ai2InfName;
	}

	public JLabel getAi3InfName() {
		return ai3InfName;
	}

	public JLabel getPlayerInfName() {
		return playerInfName;
	}
	
	public JPanel getAi1ReadyPanle() {
		return ai1ReadyPanle;
	}

	public JPanel getAi2ReadyPanle() {
		return ai2ReadyPanle;
	}

	public JPanel getAi3ReadyPanle() {
		return ai3ReadyPanle;
	}

	public JPanel getPlayerReadyPanle() {
		return playerReadyPanle;
	}
	
	public void setAi1InfHead(JLabel ai1InfHead) {
	
		ai1InfHead.setBounds(10, 0, 100, 100);
		ai1InfHead.setHorizontalAlignment(SwingConstants.CENTER);
		aiPanel.add(ai1InfHead);
	}

	public void setAi2InfHead(JLabel ai2InfHead) {
	
		ai2InfHead.setBounds(380, 0, 100, 100);
		ai2InfHead.setHorizontalAlignment(SwingConstants.CENTER);
		aiPanel.add(ai2InfHead);
	}

	public void setAi3InfHead(JLabel ai3InfHead) {

		ai3InfHead.setBounds(750, 0, 100, 100);
		ai3InfHead.setHorizontalAlignment(SwingConstants.CENTER);
		aiPanel.add(ai3InfHead);
	}

	public void setPlayerInfHead(JLabel playerInfHead) {

		playerInfHead.setBounds(0,0, 150, 100);
		playerInfHead.setHorizontalAlignment(SwingConstants.CENTER);
		playerPanel.add(playerInfHead);
	}
}
