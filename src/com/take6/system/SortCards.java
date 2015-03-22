package com.take6.system;

import com.take6.base.card.Card;
import com.take6.base.card.Cards;

/**
 * 实现玩家手牌从小到大排列
 * @author 余周锦
 * @version 1.0
 * 2014-12-13
*/
public class SortCards {
	
	/** 排列玩家出牌的牌组方法接口
	 * @param cards 需要被排列的牌组
	 * @return cards 返回一个排列好大小的牌组
	 *  */
	public Card[] sortCard(Card[] cards){
		
		sortCardPerform(cards);
		return cards;
		
	}
	
	/** 排列玩家手牌的牌组方法接口
	 * @param cards 需要被排列的牌组
	 * @return cards 返回一个排列好大小的牌组
	 *  */
	public Cards sortCard(Cards cards){
		
		sortCardPerform(cards);
		return cards;
		
	}
	
	/** 排列玩家出牌的牌组方法实现
	 * @param cards 需要被排列的牌组
	 *  */
	private void sortCardPerform(Card[]  cards){
		
		for(int i=0;i<cards.length;i++){
			for(int j=0;j<cards.length-1;j++){
				if(cards[j].getNumber()>cards[j+1].getNumber()){
					Card tempCard = cards[j];
					cards[j] = cards[j+1];
					cards[j+1] = tempCard;
				}
			}
		}
	}
	
	/** 排列玩家手牌的牌组方法实现
	 * @param cards 需要被排列的牌组
	 *  */
	private void sortCardPerform(Cards cards){
		
		for(int i=0;i<cards.size();i++){
			for(int j=0;j<cards.size()-1;j++){
				if(cards.get(j).getNumber()>cards.get(j+1).getNumber()){
					Card tempCard = cards.get(j);
					cards.set(j, cards.get(j+1));
					cards.set(j+1, tempCard);
				}
			}
		}	
	}

}
