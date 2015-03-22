package com.take6.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.*;

import javax.swing.*;

import com.take6.base.action.CreatInternalWindows;
import com.take6.base.action.CreatPlayArea;
import com.take6.base.action.ChangeHead;
import com.take6.base.action.InputPlayerInformation;
import com.take6.base.action.SelectHead;

/**
 * 谁是牛头王程序内部窗体
 * @author 余周锦
 * @version 1.0
 * 2014-12-7
*/
@SuppressWarnings("serial")
public class InternalWindows extends JInternalFrame {

	/** 创建rule 和 about 内部窗体构造函数
	 * @param path 当前窗体的现实内容的路径
	 * @param title 创建的内部窗体的题目
	 * */
	public InternalWindows(String title,String path){
		super();
		setTitle(title);
		setName(title);
		setClosable(true);
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		InputStream filePath = this.getClass().getResourceAsStream(path);
		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(filePath, "UTF-8"));
			String content = null;
			while((content = bufferRead.readLine())!=null){
				textArea.append(content);
				textArea.append("\n");
			}
			textArea.setCaretPosition(0); 
			bufferRead.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		JScrollPane scrollPane = new JScrollPane();	
		scrollPane.setViewportView(textArea);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		getContentPane().add(scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 2, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -2, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 2, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -2, SpringLayout.SOUTH, getContentPane());
		
		setVisible(true);
		
		
	}
	
	/** 创建单机和在线游玩确认内部窗体构造函数
	 * @param desktop 当前使用的桌面对象
	 * @param title 创建的内部窗体的题目
	 * */
	public InternalWindows(String title,Desktop desktop){
		super();
		setTitle(title);
		setClosable(true);
		setName(title);
		
		if(title.equals("Single Play")){
			//如果是单人游玩，直接创建游玩区域进行开始游戏
			setBounds(500,200,280,120);
			JLabel textlabel = new JLabel("确认进行"+title);
			textlabel.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(textlabel,BorderLayout.CENTER);
			JPanel buttonPanel = new JPanel();
			JButton stratButton = new JButton("Start");
			stratButton.addActionListener(new CreatPlayArea(desktop));
			buttonPanel.add(stratButton);
			getContentPane().add(buttonPanel,BorderLayout.SOUTH);
		}else if(title.equals("Online Play")){
			//如果是多人在线游玩，提示玩家进行创建主机或者查询局域网中的服务器
			setBounds(500,200,280,120);
			JLabel textlabel = new JLabel("创建主机或查询局域网中的主机");
			textlabel.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(textlabel,BorderLayout.CENTER);
			JPanel buttonPanel = new JPanel();
			JButton creatButton = new JButton("创建主机");
			JButton searchButton = new JButton("查找主机");
			creatButton.setActionCommand("creat");
			searchButton.setActionCommand("search");
			creatButton.addActionListener(new CreatPlayArea(desktop));
			searchButton.addActionListener(new CreatPlayArea(desktop));
			buttonPanel.add(creatButton);
			buttonPanel.add(searchButton);
			getContentPane().add(buttonPanel,BorderLayout.SOUTH);
			
		}
		
		setVisible(true);
	}
	
	/** 创建玩家修改信息内部窗体
	 *  @param desktop 当前使用的桌面对象
	 *  @param state 当前内部窗体是否创建查看规则按钮
	 * */
	public InternalWindows(Desktop desktop,boolean state) {
		//创建内部窗口
		super();
		setTitle("提示");
		setName("change");
		//根据创建状态确认是否需要提供关闭功能
		if(state){
			setClosable(false);
		}else
			setClosable(true);

		setBounds(490,150,250,270);
		
		//创建提示信息，提醒用户创建用户名及上传头像
		JPanel hintPanel = new JPanel();
		JLabel hintlabel = new JLabel("您可以修改昵称以及头像");		
		hintlabel.setHorizontalAlignment(SwingConstants.CENTER);
		hintPanel.add(hintlabel);
		getContentPane().add(hintPanel,BorderLayout.NORTH);
			
		//创建头像及用户名信息区域
		JPanel inforPanel = new JPanel();
		inforPanel.setLayout(null);
		
		String pathName = null;
		String userName = null;
		
		File file = new File("./config/userInfo");
		
		try {
			
			FileReader fileRead = new FileReader(file);
			BufferedReader bufferRead = new BufferedReader(fileRead);
			userName = bufferRead.readLine();
			pathName = bufferRead.readLine();
			bufferRead.close();
			fileRead.close();
					
		} catch (Exception e) {
			
			System.out.println("第一次启动游戏，没有创建用户信息文档");
		}
		Head head = null;
		//创建头像区域
		if(pathName==null){
			head = new Head("/com/take6/source/picture/head5.gif");	
		}else
			head = new Head(pathName);		
		head.setLocation(70,10);
		head.setHorizontalAlignment(SwingConstants.CENTER);
		
		//创建修改图像button
		JButton selectHead = new JButton("修改");
		selectHead.setActionCommand("change");
		selectHead.setBounds(90, 90, 60, 20);
		selectHead.addActionListener(new ChangeHead(desktop, head));
	
		//创建用户名区域
		JPanel textPanel = new JPanel();
		textPanel.setLayout(null);
		textPanel.setBounds(0, 120, 300, 50);
		
		JLabel nikiName = new JLabel("昵称:");
		nikiName.setBounds(40, 0, 30, 30);
		
		JTextField nameText;
		if(userName==null){
			nameText = new JTextField("小龙包");
		}else
			nameText = new JTextField(userName);
		nameText.setBounds(80, 0, 100, 30);

		textPanel.add(nikiName);
		textPanel.add(nameText);
		
		//将组建添加进信息区域
		inforPanel.add(selectHead);
		inforPanel.add(head);
		inforPanel.add(textPanel);
		
		//将信息区域添加进
		getContentPane().add(inforPanel,BorderLayout.CENTER);
				  
		//创建按钮区域
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		JButton confirmButton = new JButton("确认信息");
		confirmButton.setActionCommand("confirm");
		confirmButton.addActionListener(new InputPlayerInformation(desktop, head, nameText, this));
		
		buttonPanel.add(confirmButton);
		
		if(state){
			JButton hintButton = new JButton("查看规则");
			hintButton.setActionCommand("Rule");
			hintButton.addActionListener(new CreatInternalWindows(desktop));
			buttonPanel.add(hintButton);
		}
		
		getContentPane().add(buttonPanel,BorderLayout.SOUTH);
			
		setVisible(true);
	}
	
	/** 创建有参数构造函数
	 * @param desktop 将要添加内部窗口的桌面
	 * @param head 将要被修改内容的head
	 * */
	public InternalWindows(Desktop desktop,Head head) {
		
		setBounds(300,50,600,500);		
		setTitle("选择头像");
		setName("select");
		setClosable(true);
		setVisible(true);
		
		JPanel hintPanel = new JPanel();
		JLabel hintlabel = new JLabel("双击你想要选择的头像进行修改");		
		hintlabel.setHorizontalAlignment(SwingConstants.CENTER);
		hintPanel.add(hintlabel);
		getContentPane().add(hintPanel,BorderLayout.NORTH);
		
		//可以改变窗体大小的scrollpanel
		JScrollPane scrollheadPanel = new JScrollPane();
		//放置头像的panel
		JPanel headPanel = new JPanel();
		scrollheadPanel.setViewportView(headPanel);
		headPanel.setLayout(new GridLayout(4, 5, 0, 5));
		
		for(int i=1; i<21;i++){
			Head readyHead =  new Head("/com/take6/source/picture/head"+i+".gif");
			readyHead.setHorizontalAlignment(SwingConstants.CENTER); 
			readyHead.addMouseListener(new SelectHead(desktop,readyHead, head, this));
			headPanel.add(readyHead);
		}
		
		getContentPane().add(scrollheadPanel,BorderLayout.CENTER);
		
		
	}
	/** 创建无参数构造函数，用来创建排名列表*/
	public InternalWindows() {
		
		setBounds(490, 100, 300, 350);
		setLayout(null);		
		setTitle("排名");
		setName("List");
		setClosable(true);
		setVisible(true);
		
	}
	
}
