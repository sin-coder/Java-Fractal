package day0104.draw;

import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class DrawUI {	
	//��ʾ����ķ���
	public void showUI() {
	//����һ���������
	MyJFrame drawFrame = new MyJFrame();
	//���ô��������
	drawFrame.setSize(1000, 600);
	drawFrame.setLocationRelativeTo(null);
	drawFrame.setDefaultCloseOperation(MyJFrame.EXIT_ON_CLOSE);

	//���ô���Ĳ���
	drawFrame.setLayout(new FlowLayout());

	//�����������Ķ���
	DrawListener drawL = new DrawListener();
	//��������Ӽ�����
	drawFrame.addMouseListener(drawL);
	
	//������������
	drawFrame.oldshape = drawL.shape;
		
	//����һ���˵���
	JMenuBar bar=new JMenuBar();
	//�ڴ�������Ӹò˵���
	drawFrame.setJMenuBar(bar);
	
	//����һ���˵�
	String menuTextArr[]={"�ļ�","�༭"};
	//���Ӳ˵��ı����ݵĶ�ά����
	String menuItem[][]={
			{"�½��ļ�","���ļ�","�����ļ�","�ر��ļ�"},
			{"����","����","ճ��","����","����","ע��"}
	};
	
	String subMenuItem[]={"Java","Python","C","C++","Go"};
	
	//�������飬�����˵�
	for(int i=0;i<menuTextArr.length;i++){
		//�����˵�
		JMenu menu = new JMenu(menuTextArr[i]);
		
		//��¼ѭ����������
		int loopCounter=0;
		//�ڲ˵������һЩ��ѡ��
	    for(String item:menuItem[i])
	    {
	    	if(i==0 && loopCounter==0)
	    	{
	    		
		    	JMenu submenu=new JMenu(menuItem[i][loopCounter]);
		    	for(String str:subMenuItem)
		    	{
		    		JMenuItem subitem=new JMenuItem(str);
		    		submenu.add(subitem);	
		    	}
		    	
	
		    	menu.add(submenu);
		    	loopCounter++;   //ѭ������ֵ��1
	    
	    		continue;	
	    	}
	    	JMenuItem item1=new JMenuItem(item);
	    	menu.add(item1);
	    	loopCounter++;
	    }
		
		//��Ӳ˵�
		//drawFrame.add(menu);
		//��Ӽ�����
		//menu.addActionListener(drawL);
		//���˵����뵽�˵�����
		bar.add(menu);
	}
	
		//����һ����ť�����ֵ�����
		String btnTextArr[] = {"ֱ��","����","�Ÿ���","����ɽ��","���ε�ò","������"};
		//�������飬������ť
		for(String text:btnTextArr) {
			//������ť
			MyJButton btn = new MyJButton(text);
			//��Ӱ�ť
			drawFrame.add(btn);
			//��Ӽ�����
			btn.addActionListener(drawL);	
		}
		//���ÿɼ�
		drawFrame.setVisible(true);
		
		//��ȡ��������
		Graphics g = drawFrame.getGraphics();
		//�ü������Ļ���������ڽ���Ļ�������
		drawL.g = g; 
	}
	//������
	public static void main(String[] args){
		//����һ������Ķ���
		DrawUI ui = new DrawUI();
		//��ʾ����
		ui.showUI();
	}
}
