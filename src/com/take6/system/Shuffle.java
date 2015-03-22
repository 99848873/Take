package com.take6.system;

import java.util.Random;

import com.take6.base.card.Card;
import com.take6.base.card.Cards;

/**
 * 实现洗牌算法
 * @author 余周锦
 * @version 1.0
 * 2014-12-13
*/
public class Shuffle {
	
	/**定义洗牌的私有变量*/
	private int[] cardNumber = new int[104];
	private Cards allCardList = new Cards();//洗牌后的牌组
	
	/**定义构造函数，直接完成洗牌*/
	public Shuffle(){
		creatNumberList();
		shuffleNumberList();
		creatCardList();
	}
	
	/**初始化一个牌类数字的集合*/
	private void creatNumberList(){
		for(int i=0; i<104;i++){
			cardNumber[i] = i+1;
		}
	}
	
	/**将牌类数字集合洗混*/
	private void shuffleNumberList(){
		Random rand = new Random();
		for(int i=0;i<104;i++){
			int index = rand.nextInt(104);
			int tempNumber = cardNumber[index];
			cardNumber[index] = cardNumber[i];
			cardNumber[i] = tempNumber;
		}
	}
	
	/** 根据洗好的牌类数字集合创建一个牌类容器*/
	private void creatCardList(){
		
		for(int i=0;i<104;i++){
			Card card = new Card(cardNumber[i]);
			setCardScore(card, cardNumber[i]);
			allCardList.add(card);
		}
	}
	
	/** 根据牌类的数字给牌类赋值分数
	 * @param card 当前需要被赋予分数的card
	 * @param cardNumber 当前card 的分数
	 * */
	private void setCardScore(Card card,int cardNumber){
		
		if(cardNumber%5==0&&cardNumber%10!=0&&cardNumber%11!=0){
			card.setScore(2);
		}else if(cardNumber%10==0){
			card.setScore(3);
		}else if(cardNumber%11==0&&cardNumber%5!=0){
			card.setScore(5);
		}else if(cardNumber%11==0&&cardNumber%5==0){
			card.setScore(7);
		}else{
			card.setScore(1);
		}
	}
	
	/**得到完成洗牌后的牌类容器
	 * @return allCardList 返回一个牌组
	 * */
	public Cards getCardList() {
		
		return allCardList;
		
	}

}
