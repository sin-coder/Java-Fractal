package day0104.draw;

import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class DrawUI {	
	//显示界面的方法
	public void showUI() {
	//创建一个窗体对象
	MyJFrame drawFrame = new MyJFrame();
	//设置窗体的属性
	drawFrame.setSize(1000, 600);
	drawFrame.setLocationRelativeTo(null);
	drawFrame.setDefaultCloseOperation(MyJFrame.EXIT_ON_CLOSE);

	//设置窗体的布局
	drawFrame.setLayout(new FlowLayout());

	//创建监听器的对象
	DrawListener drawL = new DrawListener();
	//给窗体添加监听器
	drawFrame.addMouseListener(drawL);
	
	//关联两个数组
	drawFrame.oldshape = drawL.shape;
		
	//创建一个菜单栏
	JMenuBar bar=new JMenuBar();
	//在窗体中添加该菜单栏
	drawFrame.setJMenuBar(bar);
	
	//创建一个菜单
	String menuTextArr[]={"文件","编辑"};
	//其子菜单文本内容的二维数组
	String menuItem[][]={
			{"新建文件","打开文件","保存文件","关闭文件"},
			{"复制","剪切","粘贴","撤销","重做","注释"}
	};
	
	String subMenuItem[]={"Java","Python","C","C++","Go"};
	
	//遍历数组，创建菜单
	for(int i=0;i<menuTextArr.length;i++){
		//创建菜单
		JMenu menu = new JMenu(menuTextArr[i]);
		
		//记录循环次数变量
		int loopCounter=0;
		//在菜单中添加一些子选项
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
		    	loopCounter++;   //循环计数值加1
	    
	    		continue;	
	    	}
	    	JMenuItem item1=new JMenuItem(item);
	    	menu.add(item1);
	    	loopCounter++;
	    }
		
		//添加菜单
		//drawFrame.add(menu);
		//添加监听器
		//menu.addActionListener(drawL);
		//将菜单加入到菜单栏中
		bar.add(menu);
	}
	
		//创建一个按钮上文字的数组
		String btnTextArr[] = {"直线","矩形","门格海绵","分形山脉","分形地貌","三角形"};
		//遍历数组，创建按钮
		for(String text:btnTextArr) {
			//创建按钮
			MyJButton btn = new MyJButton(text);
			//添加按钮
			drawFrame.add(btn);
			//添加监听器
			btn.addActionListener(drawL);	
		}
		//设置可见
		drawFrame.setVisible(true);
		
		//获取画布对象
		Graphics g = drawFrame.getGraphics();
		//让监听器的画布对象等于界面的画布对象
		drawL.g = g; 
	}
	//主方法
	public static void main(String[] args){
		//创建一个界面的对象
		DrawUI ui = new DrawUI();
		//显示界面
		ui.showUI();
	}
}
