package com.take6.base.user;


import java.util.Random;

import com.take6.base.card.Card;
import com.take6.base.card.Cards;
import com.take6.swing.Head;
import com.take6.swing.PlayArea;
import com.take6.system.CompareCard;
import com.take6.system.CompareCards;
import com.take6.system.SortCards;

/**
 * 创建游戏AI类
 * @author 余周锦
 * @version 1.0
 * 2014-12-10
*/
public class Ai extends Player{
	
	/**定义私有变量,AI等级*/
	private int level;
	protected String names[] = {"远坂凛","银桑","星爷","新桓结衣","小龙包",
			"小黄人","王大锤","椎名真白","神樂","如花",
			"乔巴","江户川柯南","卷福","堺雅人","三三",
			"Saber","路飞","博雅 汉库克","娜美","金馆长"};

	/** 构造函数
	 * @param id 使用id 构造唯一AI对象
	 * @param level 创建的AI的等级
	 * */
	public Ai(int id,int level) {
		super(id);
		Random rand = new Random();
		this.level = level;
		userName = names[(id-1)*5+rand.nextInt(id*5-(id-1)*5)];
		creatHead();
		
	}
	
	/**根据ai情况创建ai的头像*/
	private void creatHead(){
		
		String pathName = null;
		
		for(int i=0;i<names.length;i++){
			
			if(names[i].equals(userName)){
				
				pathName ="/com/take6/source/picture/head"+(i+1)+".gif";

			}
		}
		
		head = new Head(pathName);
		
	}

	/**定义一个出牌的算法接口
	 * @param playArea 当前的游玩区域
	 * @return card 返回一张AI认为最好的牌
	 * */
	public Card pushCard(PlayArea playArea) {
		
		BestCard  bestCard = new BestCard(playArea);
	
		if(getLevel()== 2){
			return bestCard.pushCardPeform2();		
		}
		if(getLevel() == 1){
			return bestCard.pushCardPeform1();
		}
		if(getLevel() == 0){
			return bestCard.pushCardPeform0();
		}
		return null;
		
		
	}

	
	/**
	 * 创建私有内部类,用来获得不同等级Ai出牌方法
	 * @author 余周锦
	 * @version 1.0
	 * 2014-12-25
	*/
	private class BestCard{
		
		/**定义私有变量*/
		private Card card;//AI将要打出的牌
		private Cards usefulCards = new Cards();
		private SortCards sortCard = new SortCards();
		private PlayArea playArea;
		private Random rand =new Random();
		private Card[] compareResult;
		
		/**
		 * 私有的构造函数供外部类使用
		 * @param playArea 游戏游玩区域
		 * */
		private BestCard(PlayArea playArea){
			this.playArea = playArea;
			getUsefulCards();
		}
		
		/**定义私有方法获得玩家手中可出的牌*/
		private void getUsefulCards(){
			
			Ai.this.cards = sortCard.sortCard(getCards());
			
			//获得当前四个放牌区域的最后一张牌
			Card location1card = playArea.getCardAreaA().getCards().get(playArea.getCardAreaA().getCards().size()-1);
			Card location2card = playArea.getCardAreaB().getCards().get(playArea.getCardAreaB().getCards().size()-1);
			Card location3card = playArea.getCardAreaC().getCards().get(playArea.getCardAreaC().getCards().size()-1);
			Card location4card = playArea.getCardAreaD().getCards().get(playArea.getCardAreaD().getCards().size()-1);
			
			//排列放牌区域最后一张牌
			CompareCard compare = new CompareCard();
			compareResult = compare.compareNumber(location1card, location2card, location3card, location4card);
			
			//计算ai手中可以使用的牌的数量，如果等于0，就选择最小的出牌
			for(int i=0;i<cards.size();i++){
				for(int j=0;j<compareResult.length;j++){
					if(cards.get(i).getNumber()>compareResult[j].getNumber()){
						usefulCards.add(cards.get(i));
						break;
					}		
				}		
			}
			
		}
		
		/**定义一个2级出牌的算法实现
		 * @return card 一张玩家将要出去的牌
		 *  */
		private Card pushCardPeform2(){
			
			if(usefulCards.size() == 0){
				
				card = cards.get(rand.nextInt(cards.size()));
				
			}else{
				
				//如果不等于0，判断那张牌可以放在拥有最少牌数量的区域
				CompareCards comparCards = new CompareCards();
				//返回一数组,根据牌数量从大到小排列
				Cards[] sortCards = comparCards.compareSize(playArea.getCardAreaA().getCards(),
						playArea.getCardAreaB().getCards(), 
						playArea.getCardAreaC().getCards(), 
						playArea.getCardAreaD().getCards());
							
				//得到每一组的位置
				int smallestSizeLocation = sortCards[0].get(0).getCardLocation();
				int secondSizeLocation = sortCards[1].get(0).getCardLocation();
				int thirdSizeLocation = sortCards[2].get(0).getCardLocation();
				int biggestSizeLocation = sortCards[3].get(0).getCardLocation();
								
				//定义查询范围
				int rang = 2;
				int possibleLocation = 0;
				
				for(int i=0;i<15;i++){
					//得到牌可以放的位置,并判断是最小位置上是否有牌可放,如果可放,根据条件放置
					possibleLocation = getBestCard(smallestSizeLocation,sortCards[0],rang);
			
					//得到牌可以放的位置,并判断是第二位置上是否有牌可放,如果可放,根据条件放置
					if(possibleLocation == 0){
						possibleLocation = getBestCard(secondSizeLocation,sortCards[1],rang);
					
					}
					
					//得到牌可以放的位置,并判断是第三位置上是否有牌可放,如果可放,根据条件放置
					if(possibleLocation == 0){
						possibleLocation = getBestCard(thirdSizeLocation,sortCards[2],rang);
						
					}
					
					//得到牌可以放的位置,并判断是第四位置上是否有牌可放,如果可放,根据条件放置
					if(possibleLocation == 0){
						possibleLocation = getBestCard(biggestSizeLocation,sortCards[3],rang);
						
					}
					
					if(possibleLocation==0){
					
						rang++;
						
					}
					
					if(possibleLocation !=0){
						
						break;
						
					}
				}
				
				if(possibleLocation == 0){
					 card = usefulCards.get(rand.nextInt(usefulCards.size()));
				}
				
				
			}
		
			return card;
						
		}
		
		/**定义一个1级出牌的算法实现
		 * @return card 一张玩家将要出去的牌
		 *  */
		private Card pushCardPeform1(){
			
			if(usefulCards.size() == 0){
				
				card = cards.get(rand.nextInt(cards.size()));
				
			}else{
				
				//如果不等于0，判断那张牌可以放在拥有最少牌数量的区域
				CompareCards comparCards = new CompareCards();
				//返回一数组,根据牌数量从大到小排列
				Cards[] sortCards = comparCards.compareSize(playArea.getCardAreaA().getCards(),
						playArea.getCardAreaB().getCards(), 
						playArea.getCardAreaC().getCards(), 
						playArea.getCardAreaD().getCards());
				
				//得到每一组的位置
				int smallestSizeLocation = sortCards[0].get(0).getCardLocation();
				
				int possibleLocation = 0;
				
				for(int i=0;i<usefulCards.size();i++){
					for(int j=0;j<compareResult.length;j++){
						if(usefulCards.get(i).getNumber()>compareResult[j].getNumber()){
							possibleLocation = compareResult[j].getCardLocation();
						}
					}
					
					if(possibleLocation == smallestSizeLocation){
						card = usefulCards.get(i);
						break;
					}
				}
				
				if(possibleLocation != smallestSizeLocation){
					card = usefulCards.get(rand.nextInt(usefulCards.size()));
				}
						
			}
		
			return card;
						
		}
		
		/**定义一个0级出牌的算法实现
		 * @return card 一张玩家将要出去的牌
		 *  */
		private Card pushCardPeform0(){
					
			if(usefulCards.size() == 0){
				
				card = cards.get(rand.nextInt(cards.size()));
				
			}else{
				
				card = usefulCards.get(rand.nextInt(usefulCards.size()));
			}
			
			
			return card;
						
		}
		
		/**
		 * 实现判断最好牌组的位置方法
		 * @param usefulCards 可以使用的牌组
		 * @param compareResult 每一组牌最后一个牌的数组
		 * @param bestCards 最佳出牌牌组
		 * @param bestlocation 最佳的位置
		 * @param sortCards 根据牌数量大小排列的牌组 
		 * */
		private int getBestCard(int bestlocation,Cards sortCards,int rang){
			
			//定义一个新的牌组,放置最佳牌组(已经被初始化,不可能为空)
			Cards bestCards = new Cards();
			
			int possibleLocation = 0;
			
			if(sortCards.size()<4){
				
				for(int i=0;i<usefulCards.size();i++){
					for(int j=0;j<compareResult.length;j++){
						int value=usefulCards.get(i).getNumber()-compareResult[j].getNumber();
						if(rang>value&&value>0){
							possibleLocation = compareResult[j].getCardLocation();
						}
					}
					
					if(possibleLocation == bestlocation){
						bestCards.add(usefulCards.get(i));
					}
		
				}
				
				if(bestCards.size() !=0 ){				
					card = bestCards.get(0);		
					possibleLocation = bestlocation;
				}else
					possibleLocation = 0;
			}
									
			if(sortCards.size()==4){
				if(rang<5){
					for(int i=0;i<usefulCards.size();i++){
						for(int j=0;j<compareResult.length;j++){
							int value=usefulCards.get(i).getNumber()-compareResult[j].getNumber();
							if(rang>value&&value>0){
								possibleLocation = compareResult[j].getCardLocation();
							}
						}
						
						if(possibleLocation == bestlocation){
							bestCards.add(usefulCards.get(i));
						}
			
					}
					if(bestCards.size() !=0 ){
						card = bestCards.get(bestCards.size()-1);		
						possibleLocation = bestlocation;
					}else
						possibleLocation = 0;
				}else
					possibleLocation = 0;
								
			}
			
			if(sortCards.size()==5){
				
				possibleLocation = 0;
			
			}
		
			return possibleLocation;
			
		}
				
	}

	public int getLevel() {
		return level;
	}
	

}
