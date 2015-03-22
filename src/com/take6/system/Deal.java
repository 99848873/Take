package com.take6.system;

import com.take6.base.card.Card;
import com.take6.base.card.Cards;
import com.take6.base.user.Player;

/**
 * 编写一个发牌器，可以将手中的牌发给别人
 * @version 1.0
 * @author 余周锦
 * 2014-12-13
*/
public class Deal {
	
	/** 定义发牌算法的私有变量*/
	private Shuffle shuffle = new Shuffle();
	private Cards allCardList = shuffle.getCardList();//一副已经洗好的牌
	private Card[] compareCards = new Card[4];	//如果是第一次发牌，需要4张牌放在初始位置
	
	/** 定义发牌的构造函数
	 *  在构造发牌器的同时，现将牌组中的4张牌取出，用来作为起始的对比牌，放在compareCards中
	 * */
	public Deal(){
		dealCardListToCompareCards();
	}
	
	/** 给玩家发牌
	 * @param player 将牌发给指定玩家
	 *  */
	public void dealCardListToPlayer(Player player){
		if(allCardList.size() <= 20){
			for(int i=0;i<5;i++){
				allCardList.get(0).setOwner(player);
				player.getCards().add(allCardList.get(0));
				allCardList.remove(0);
			}
		}else{
			for(int i=0;i<10;i++){
				allCardList.get(0).setOwner(player);
				player.getCards().add(allCardList.get(0));
				allCardList.remove(0);
			}
		}
	}
	
	/** 给初始牌组发牌 */
	private void dealCardListToCompareCards(){
		
		for(int i=0;i<4;i++){
			compareCards[i] = allCardList.get(0);
			allCardList.remove(0);
		}	
	}

	public Cards getAllCardList() {
		return allCardList;
	}

	public Card[] getCompareCards() {
		return compareCards;
	}

}
