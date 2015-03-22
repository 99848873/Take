package com.take6.system;

import com.take6.base.card.Cards;

/**
 * 创建一个比对比牌组总分大小的类，用来比当前桌面上综费最小的一列，返回一个数字表示总分最小列的位置
 * @version 1.0
 * @author 余周锦
 * 2014-12-13
*/
public class CompareCards {
	
	/**定义私有变量，一个表示位置的数字*/
	private int cardLocation;
	
	/**
	 * 创建一个接口方法返回一个最小总分组位置标示
	 * @param cardArea1 显示区域1的牌组
	 * @param cardArea2 显示区域2的牌组
	 * @param cardArea3 显示区域3的牌组
	 * @param cardArea4 显示区域4的牌组
	 * @return cardLocation 返回一个区域识别标示数字
	 * @author 余周锦
	 * 2014-12-13
	*/
	public int compareScore(Cards cardArea1,Cards cardArea2,Cards cardArea3,Cards cardArea4){

		compareScorePerform(cardArea1,cardArea2,cardArea3,cardArea4);

		return cardLocation;
		
	}
	
	/**
	 * 创建一个接口实现方法用来实现获得数列最小牌组的标示
	 * @param cardArea1 显示区域1的牌组
	 * @param cardArea2 显示区域2的牌组
	 * @param cardArea3 显示区域3的牌组
	 * @param cardArea4 显示区域4的牌组
	 * @return Card[] 返回一个牌组
	 * @author 余周锦
	 * 2014-12-13
	*/
	public Cards[] compareSize(Cards cardArea1,Cards cardArea2,Cards cardArea3,Cards cardArea4){

		return compareSizePerform(cardArea1,cardArea2,cardArea3,cardArea4);
		
	}

	/**
	 * 创建一个实现方法用来获得总分最小牌组的标示
	 * @param cardArea1 显示区域1的牌组
	 * @param cardArea2 显示区域2的牌组
	 * @param cardArea3 显示区域3的牌组
	 * @param cardArea4 显示区域4的牌组
	 * @author 余周锦
	 * 2014-12-13
	*/
	private void compareScorePerform(Cards cardArea1,Cards cardArea2,Cards cardArea3,Cards cardArea4){
		
		Cards[] cardsList = new Cards[]{cardArea1,cardArea2,cardArea3,cardArea4};
		
		for(int i=0;i<cardsList.length;i++){
			for(int j=0;j<cardsList.length-1;j++){
				if(cardsList[j].getTotalScore()>cardsList[j+1].getTotalScore()){
					Cards tempCards = cardsList[j];
					cardsList[j] = cardsList[j+1];
					cardsList[j+1] = tempCards;
				}
			}
		}
		
		cardLocation = cardsList[0].get(0).getCardLocation();
	}
	
	/**
	 * 创建一个实现方法用来获得数列最小牌组的标示
	 * @param cardArea1 显示区域1的牌组
	 * @param cardArea2 显示区域2的牌组
	 * @param cardArea3 显示区域3的牌组
	 * @param cardArea4 显示区域4的牌组
	 * @author 余周锦
	 * 2014-12-13
	*/
	private Cards[] compareSizePerform(Cards cardArea1, Cards cardArea2,Cards cardArea3, Cards cardArea4) {
		
		Cards[] cardsList = new Cards[]{cardArea1,cardArea2,cardArea3,cardArea4};
		
		for(int i=0;i<cardsList.length;i++){
			for(int j=0;j<cardsList.length-1;j++){
				if(cardsList[j].size()>cardsList[j+1].size()){
					Cards tempCards = cardsList[j];
					cardsList[j] = cardsList[j+1];
					cardsList[j+1] = tempCards;
				}
			}
		}
		
		
		return	cardsList;
	}
	
}
