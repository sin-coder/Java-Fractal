package day0104.draw;

import java.awt.Graphics;

import javax.swing.JFrame;

public class MyJFrame extends JFrame{
	
	Shape oldshape[];
	public void paint(Graphics g){
		super.paint(g);
		System.out.println("开始重绘图形");
		rePaintShape(g);
	}
	
	//重画界面中的图形
	public void rePaintShape(Graphics g){
		for(int i=0; i<1; i++){
			oldshape[i] = new Shape();
			if("直线".equals(oldshape[i].shapeText)){
				g.drawLine(oldshape[i].startX,oldshape[i].startY,oldshape[i].endX,oldshape[i].endY);	
			}
		}
		System.out.println("重绘图形完成");
	}
}
