package com.take6.system;

import com.take6.base.card.Card;

/**
 * 创建一个比对牌数值大小的类，用来比对四张牌的数值的大小，返回一个牌类数组
 * @version 1.0
 * @author 余周锦
 * 2014-12-13
*/
public class CompareCard {
	
	/**定义私有变量，一个牌类集合，用来放排列后的固定大小牌组*/
	private Card[] compareCards;
	
	/**
	 * 创建一个接口方法返回一个已经排列好的数组
	 * @param playerCard 玩家的牌
	 * @param ai1Card 第一个AI的牌
	 * @param ai2Card 第二个AI的牌
	 * @param ai3Card 第三个AI的牌
	 * @return compareCards 返回一个排列好的牌组
	*/
	public Card[] compareNumber(Card playerCard,Card ai1Card,Card ai2Card,Card ai3Card) {
		
		comparePerform(playerCard,ai1Card,ai2Card,ai3Card);
				
		return compareCards;
	}
	
	/**
	 * 创建一个实现用来实现方法返回一个已经排列好的数组
	 * @param playerCard 玩家的牌
	 * @param ai1Card 第一个AI的牌
	 * @param ai2Card 第二个AI的牌
	 * @param ai3Card 第三个AI的牌
	 * @author 余周锦
	 * 2014-12-13
	*/
	private void comparePerform(Card playerCard,Card ai1Card,Card ai2Card,Card ai3Card){
		
		compareCards  = new Card[]{playerCard,ai1Card,ai2Card,ai3Card};
		
		for(int i=0;i<compareCards.length;i++){
			for(int j=0;j<compareCards.length-1;j++){
				if(compareCards[j].getNumber()>compareCards[j+1].getNumber()){
					Card tempCard = compareCards[j];
					compareCards[j] = compareCards[j+1];
					compareCards[j+1] = tempCard;
				}
				
			}

		}
				
	}

}
