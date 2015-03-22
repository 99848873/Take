package com.take6.base.card;

import java.util.ArrayList;
/**
 * 创建一个牌组的类，在该类中可以添加和删除Card，在添加和删除的同时可以获得当前牌组的总分
 * @author 余周锦
 * @version 1.0
 * 2014-12-15
*/
@SuppressWarnings("serial")
public class Cards extends ArrayList<Card>{
	
	/**定义牌组的总分*/
	private int totalScore = 0;
	
	/**重构其add方法，在添加新拍的时候获得牌的分数，并添加进总分,并且将当前牌组的location传给card
	 * @param card 被添加进牌组的新牌
	 * */	
	public boolean add(Card card) {
		totalScore += card.getScore();
		return super.add(card);
	}
	
	/**重构其remove方法，在remove掉一张牌的同时，将本组的总分减去响应的分值
	 * @param card 被删除掉的牌
	 * @return boolean 返回一个状态值
	 * */	
	public boolean remove(Card card) {
		
		totalScore = totalScore-card.getScore();
		return super.remove(card);
	}
	
	/**重构其clear 方法，在清除牌组中所有内容同时将牌组分数设置为0*/	
	public void clear() {
		
		setTotalScore(0);
		super.clear();
	}
	
	//获得当前总分
	public int getTotalScore() {
		return totalScore;
	}

	private void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
}
