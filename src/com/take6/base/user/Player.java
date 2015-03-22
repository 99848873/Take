package com.take6.base.user;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.take6.base.card.Cards;
import com.take6.swing.Head;

/**
 * 创建游戏玩家类
 * @author 余周锦
 * @version 1.0
 * 2014-12-10
*/
public class Player {
	
	/** 定义成员变量*/
	protected int id = 0;
	protected String userName = null;
	protected int score = 0;
	protected Cards cards = new Cards();
	protected Head head;

	/** 构造函数
	 * @param id 使用id 构造唯一玩家对象
	 * */
	public Player(int id){
		
		this.id = id;
		creatUser();
		
	}
	
	/** 构造函数,无参数，用来临时定义一个玩家 */
	public Player(){
	
	}
	
	/**根据用户的动态信息创建用户的头像及昵称*/
	private void creatUser(){
		
		String regex = "(?i)july\\D*\\w*";
		
		Pattern pattern = Pattern.compile(regex);
			
		String pathName = null;
		
		File file = new File("./config/userInfo");
		
		try {
			
			FileReader fileRead = new FileReader(file);
			BufferedReader bufferRead = new BufferedReader(fileRead);
		
			userName = bufferRead.readLine();
			
			if(userName!=null){
				
				Matcher mather = pattern.matcher(userName);
				
				if(mather.matches()){
					userName = "Merry me";
				}
				
			}
			
			pathName = bufferRead.readLine();
		
			bufferRead.close();
			fileRead.close();
			
					
		} catch (Exception e) {
		
			System.out.println("第一次启动游戏，没有创建用户信息文档");
			
		}finally{
			
			creatHead(pathName);
			
		}
		
		
		
	}
	
	/**创建玩家头像*/
	private void creatHead(String pathName){
	
		if(pathName == null){
			pathName = "/com/take6/source/picture/head5.gif";
			userName = "小龙包";
		}
		head = new Head(pathName);
	
	}


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score += score;
	}

	public Cards getCards() {
		return cards;
	}

	public String getUserName(){
		return this.userName;
	}

	public int getId() {
		return id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	public Head getHead() {
		return head;
	}
	
}
