package com.take6.system;

import java.util.Random;

import com.take6.base.card.Card;
import com.take6.base.user.Ai;
import com.take6.base.user.Player;
import com.take6.swing.PlayArea;


/**
 * 开始游玩后，需要获得玩家信息，当前版本下自动直接创建玩家，后期网络版本需要从网络中获取玩家信息
 * @version 1.0
 * @author 余周锦
 * 2014-12-13
*/
public class StartPlay {
	
	/** 定义开始游玩后当前类的属性*/
	//创建随机构造ai的随机对象
	Random rand = new Random();
	//创建玩家
	private Player player = new Player(1);
	private Ai ai1 = new Ai(2,rand.nextInt(3));
	private Ai ai2 = new Ai(3,rand.nextInt(3));
	private Ai ai3 = new Ai(4,rand.nextInt(3));
	
	//获得游玩显示区域
	private PlayArea playArea;
	
	//获得已经洗好牌的牌组
	private Deal deal = new Deal();
	
	//开始新线程进行当前局面的监控,如果玩家和AI手中均没有牌,进行相应的操作
	private Thread checkState;
	private PlayStateCheck playStateCheck;
	

	
	/**
	 * 构造函数，将所需要的对象进行初始化操作
	 * @param playArea 游玩显示区域的引用
	 * @version 1.0
	 * @author 余周锦
	 * 2014-12-17
	*/
	public StartPlay(PlayArea playArea){
		this.playArea = playArea;
		startPlay();
	}
	
	/**启动清空本类内容方法，用于reset功能*/
	public void reset(){
		
		playStateCheck.setRunState(false);
		setPlayStateCheck(null);
		setCheckState(null);
		setDeal(null);
		setPlayArea(null);
		setAi3(null);
		setAi2(null);
		setAi1(null);
		setPlayer(null);
		
	}

	/**初始化游玩界面，并且开启发牌监控线程*/
	private void startPlay(){
		initialize();
		initializePlayerInf();
		checkState();
	}
	
	/**初始化游玩界面，将初始对比牌组放在相应的位置上*/
	private void initialize(){
		SortCards sortCards = new SortCards();
		Card[]  baseCards = sortCards.sortCard(deal.getCompareCards());
		baseCards[0].setLocation(0, 5);
		playArea.getCardAreaA().add(baseCards[0]);
		baseCards[1].setLocation(0, 5);
		playArea.getCardAreaB().add(baseCards[1]);
		baseCards[2].setLocation(0, 5);
		playArea.getCardAreaC().add(baseCards[2]);
		baseCards[3].setLocation(0, 5);
		playArea.getCardAreaD().add(baseCards[3]);
	}
	
	/**启动发牌监控线程*/
	private void checkState(){
		playStateCheck = new PlayStateCheck(this); 
		checkState = new Thread(playStateCheck);
		checkState.setPriority(Thread.MAX_PRIORITY);
		checkState.start();
	}
	
	/**初始化每个玩家的头像*/
	private void initializePlayerInf(){
				
		playArea.setAi1InfHead(ai1.getHead());
		playArea.getAi1InfName().setText(ai1.getUserName());
		playArea.getAi1InfDisplay().setText("分数为:"+ai1.getScore());
		
		playArea.setAi2InfHead(ai2.getHead());
		playArea.getAi2InfName().setText(ai2.getUserName());
		playArea.getAi2InfDisplay().setText("分数为:"+ai2.getScore());
		
		playArea.setAi3InfHead(ai3.getHead());
		playArea.getAi3InfName().setText(ai3.getUserName());
		playArea.getAi3InfDisplay().setText("分数为:"+ai3.getScore());
		
		playArea.setPlayerInfHead(player.getHead());
		playArea.getPlayerInfName().setText(player.getUserName());
		playArea.getPlayerInfDisplay().setText("分数为:"+player.getScore());
		
	}
	
	public PlayStateCheck getPlayStateCheck() {
		return playStateCheck;
	}

	public void setPlayStateCheck(PlayStateCheck playStateCheck) {
		this.playStateCheck = playStateCheck;
	}

	public void setCheckState(Thread checkState) {
		this.checkState = checkState;
	}

	public Player getPlayer() {
		return player;
	}

	public Ai getAi1() {
		return ai1;
	}

	public Ai getAi2() {
		return ai2;
	}

	public Ai getAi3() {
		return ai3;
	}

	public PlayArea getPlayArea() {
		return playArea;
	}


	public Deal getDeal() {
		return deal;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setAi1(Ai ai1) {
		this.ai1 = ai1;
	}

	public void setAi2(Ai ai2) {
		this.ai2 = ai2;
	}

	public void setAi3(Ai ai3) {
		this.ai3 = ai3;
	}

	public void setPlayArea(PlayArea playArea) {
		this.playArea = playArea;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}


}
