package com.take6.base.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.take6.swing.Desktop;
import com.take6.swing.Head;
import com.take6.swing.InternalWindows;

/**
 * 实现选择头像功能
 * @version 1.0
 * 2014-12-27
*/
public class SelectHead implements MouseListener{
	
	/**定义私有变量*/
	private Desktop desktop;
	private Head readyHead;
	private Head head;
	private InternalWindows headWindow;
	
	/**定义构造函数
	 * @param desktop 当前桌面
	 * @param readyHead 替换用的头像
	 * @param head 被替换的头像
	 * @param headWindow 当前窗口
	 * */
	public SelectHead(Desktop desktop,Head readyHead,Head head, InternalWindows headWindow){
		this.desktop = desktop;
		this.head = head;
		this.readyHead = readyHead;
		this.headWindow = headWindow;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getClickCount() == 2){
			head.setIcon(readyHead.getIcon());
			head.setPath(readyHead.getPath());
			head.repaint();
			desktop.remove(headWindow);
			desktop.repaint();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	
	}

}
