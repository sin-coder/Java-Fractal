package day0104.draw;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Math;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Color;
import java.util.Random;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.util.HashMap;

public class DrawListener implements MouseListener,ActionListener{
	
	//记录坐标
	int startx,starty,endx,endy;
	int index=0;     //每个所画图形在Shape数组中的位置  
	Shape shape[] = new Shape[1000];
	HashMap<Point, Point> hashmap=new HashMap<Point, Point>(100);
	//画布
	Graphics g; 
	
	//记录图形文字信息的字符串
	String shapeTypeStr = "";	
	//
	
	public void actionPerformed(ActionEvent e) {
		//获取按钮的信息
		String action = e.getActionCommand();
		//记录
		shapeTypeStr = action;
		
	}

    public void mousePressed(MouseEvent e) {
    	//记录按下的坐标
    	startx = e.getX();
    	starty = e.getY();	
    }
    public void mouseReleased(MouseEvent e) {
    	//记录松开的坐标
    	endx = e.getX();
    	endy = e.getY();

    	
    	if("直线".equals(shapeTypeStr)) {
    		//画线
         	shape[index]=new Shape();
        	shape[index].startX = startx;
        	shape[index].startY = starty;
        	shape[index].endX = endx;
        	shape[index].endY = endy;
    		shape[index].shapeText = "直线";
    		System.out.println(shape[index].shapeText);
    		index++; 
        	g.drawLine(startx, starty, endx, endy);
        	
    	}else if("矩形".equals(shapeTypeStr)){
    		g.drawRect(startx, starty, endx-startx, endy-starty);
    		g.fillRect(startx, starty, endx-startx, endy-starty);
    	}else if("门格海绵".equals(shapeTypeStr))
    	{
    		//画出门格海绵函数
    		paint(g);
    			
    	}else if("分形山脉".equals(shapeTypeStr))
    	{
    		//画出地形函数
    		drawMoutain(g);		
    	}else if("分形地貌".equals(shapeTypeStr))
    	{
    		hashmap.clear();
    		//画出地形函数
    		drawEarthSurface(g);
    		
    	}
    	else{
     		drawTri(100,500,400,7);
    	}		
    }
	public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    //绘制地表函数
    public void drawEarthSurface(Graphics g){
    	
    	Point p1=new Point(248,113);  //1号点
    	Point p2=new Point(821,113); //2好点
    	Point p3=new Point(773,546); //3号点
    	Point p4=new Point(128,546);  //4号点
    	
    	int dy=200;    //y轴方向的一个偏离值
    	int count=0;  //定义递归的次数
    	paintEarthSurface(p1,p2,p3,p4,dy,count);
    }
    //画出地表函数
    public void paintEarthSurface(Point p1,Point p2,Point p3,Point p4,int dy,int count){
    	
    		Point pnew[]=new Point[5];
    		pnew[0]=new Point((p1.x+p4.x)/2,(p1.y+p4.y)/2);  //新的pnew[0]点是p1和p4的中点
    		pnew[1]=new Point((p1.x+p2.x)/2,(p1.y+p2.y)/2);  //新的pnew[1]点是p1和p2的中点
    		pnew[2]=new Point((p2.x+p3.x)/2,(p2.y+p3.y)/2);  //新的pnew[2]点是p2和p3的中点
    		pnew[3]=new Point((p3.x+p4.x)/2,(p3.y+p4.y)/2);  //新的pnew[3]点是p3和p4的中点
    		pnew[4]=new Point((pnew[0].x+pnew[2].x)/2,(pnew[0].y+pnew[2].y)/2); //新的pnew[4]点是pnew[0]和pnew[2]的中点   		
    		
    		count++;                   //递归次数
    		//新生成的点偏离原来坐标并连线
			Point pfinal[]=new Point[5];
    		for(int i=0;i<5;i++){
    			pfinal[i] = new Point();
    			//判断pnew[i]是否在HashMap中，如果在将value值赋给pfinal[i],否则将波动y坐标后再进行存储
    			if(hashmap.get(pnew[i])==null){
            		Random ran1=new Random();
            		int temp=ran1.nextInt(dy)*2-dy;
            		
            		pfinal[i].x=pnew[i].x;
            		pfinal[i].y=pnew[i].y+temp;

            		hashmap.put(pnew[i],pfinal[i]);
    			}
    			//该点在HashMap()中存在
    			else{
    				pfinal[i]=hashmap.get(pnew[i]);
    			}	
    		}
    		
    		dy=dy/2;
    		
    		if(count>6){
            	//开始连线
            	g.drawLine(p1.x, p1.y, pfinal[1].x, pfinal[1].y);
            	g.drawLine(pfinal[1].x, pfinal[1].y, p2.x, p2.y);
            		
            	g.drawLine(p1.x, p1.y, pfinal[0].x, pfinal[0].y);
            	g.drawLine(pfinal[1].x, pfinal[1].y, pfinal[4].x, pfinal[4].y);
            	g.drawLine(p2.x, p2.y, pfinal[2].x, pfinal[2].y);
            		
            	g.drawLine(pfinal[0].x, pfinal[0].y, pfinal[4].x, pfinal[4].y);
            	g.drawLine(pfinal[4].x, pfinal[4].y, pfinal[2].x, pfinal[2].y);
            		
            	g.drawLine(pfinal[0].x, pfinal[0].y, p4.x, p4.y);
            	g.drawLine(pfinal[3].x, pfinal[3].y, pfinal[4].x, pfinal[4].y);
            	g.drawLine(pfinal[2].x, pfinal[2].y, p3.x,p3.y);
            		
            	g.drawLine(p4.x, p4.y, pfinal[3].x, pfinal[3].y);
            	g.drawLine(pfinal[3].x, pfinal[3].y, p3.x,p3.y);
    		}
    		else{
        		paintEarthSurface(p4,pfinal[0],pfinal[4],pfinal[3],dy,count); //每个正方形生成新的四边形
        		paintEarthSurface(pfinal[0],p1,pfinal[1],pfinal[4],dy,count);
        		paintEarthSurface(pfinal[1],p2,pfinal[2],pfinal[4],dy,count);
        		paintEarthSurface(pfinal[2],p3,pfinal[3],pfinal[4],dy,count);
    		}
}
    
  
    //画出山脉函数
    public void drawMoutain(Graphics g){
    	int count=0;   //定义递归次数
    	int length=500; //定义初始长度
    	Point p0=new Point(50,350); //起点
    	Point p1=new Point(950,350); //终点
    	
    	//画出起点和终点
    	g.drawLine(p0.x, p0.y, p0.x, p0.y);
    	g.drawLine(p1.x, p1.y, p1.x, p1.y);
    	//描线函数
    	paintShape(p0,p1,length,count);
    }
    //绘制外形函数
    public void paintShape(Point P0,Point P1,int len,int count){
    	//先判断递归次数是否满足要求
    	if(count>7){
    		return;
    	}
    	else{
    		Point mid=new Point((P0.x+P1.x)/2,(P0.y+P1.y)/2);
    		Random ran=new Random();
    		int temp=ran.nextInt(len)+1;  //获取随机值
    		int dy;     //偏离值
    		
    		//随机坐标为偶数,新点在上方，否则在下方
    		if(temp%2==0){
    			dy=mid.y+temp;
    		}
    		else{
    			dy=mid.y-temp;
    		}
    	    Point newpoint=new Point(mid.x,dy);
    		//从第二次递归开始划线
    		if(count>0){
				g.drawLine(P0.x, P0.y, newpoint.x, newpoint.y);
    	    	g.drawLine(P1.x, P1.y, newpoint.x, newpoint.y);
    		}
    		len=len/2;
    		count++; //递归次数增加
    		//递归绘线
    		paintShape(P0,newpoint,len,count);
    		paintShape(P1,newpoint,len,count);	
    	}
    	
    }
    
    private void drawTri(int x, int y, int len,int times){
    	if(times==0)
    	{
    		return;        //三角形的长度小于2时，就返回
    	}
    	else
    	{
    		int ytemp=y-(int)(Math.sqrt(3.00)*(len/2));
    		int xArr[]={x,x+len,(x+x+len)/2};
    		int yArr[]={y,y,ytemp};
    		
    		int xNextArr[]={(xArr[0]+xArr[1])/2,(xArr[0]+xArr[2])/2,(xArr[1]+xArr[2])/2};
    		int yNextArr[]={yArr[0],(yArr[0]+yArr[2])/2,(yArr[1]+yArr[2])/2};
    		
    		g.drawPolygon(xArr,yArr,3);
     		g.fillPolygon(xNextArr, yNextArr,3);
     		
     		len=len/2;
     		times=times-1;
     		drawTri(xArr[0],yArr[0],len,times);
     		drawTri(xNextArr[1],yNextArr[1],len,times);
     		drawTri(xNextArr[0],yNextArr[0],len,times);
    	}	
    }
    
    Point pn[]=new Point[20];  //一个单元格是由20个小正方体组成
    
    //画出门格海绵的函数
    public void paint(Graphics g){
    	int x=400,y=300,d=300,dx=150,dy=120;
    	Point p0=new Point(x,y); //起点
        
    	drawBottomLevel(g,p0,d,dx,dy);       //画出底层
    	drawMidLevel(g,p0,d,dx,dy);          //画出中间层
    	drawTopLevel(g,p0,d,dx,dy);          //画出顶层
    	
      	//递归画出每个单元块
      	for(int i=0;i<pn.length;i++){
    		Point pitem=pn[i];
    		if(pitem!=null){
    			drawBottomLevel(g,pitem,d/3,dx/3,dy/3);
    			drawMidLevel(g,pitem,d/3,dx/3,dy/3);
    			drawTopLevel(g,pitem,d/3,dx/3,dy/3);
    		}
    	}
}
   
   //画出中间层的图形
   private void drawMidLevel(Graphics g,Point p0,int d,int dx,int dy ){
	   
	   Point p1=new Point(p0.x,p0.y+d/3); //12点的坐标
	   
	   Point ps8[]=getOtherPointByP0(p1,d/3,dx/3,dy/3);  //获取12号点组
	   Point ps7[]=getOtherPointByP0(ps8[1],d/3,dx/3,dy/3);
	   Point ps6[]=getOtherPointByP0(ps7[1],d/3,dx/3,dy/3);//获取11号点组
	   Point ps5[]=getOtherPointByP0(ps8[5],d/3,dx/3,dy/3);
	   Point ps4[]=getOtherPointByP0(ps6[5],d/3,dx/3,dy/3);
	   Point ps3[]=getOtherPointByP0(ps5[5],d/3,dx/3,dy/3);//获取10号点组
	   Point ps2[]=getOtherPointByP0(ps3[1],d/3,dx/3,dy/3);
	   Point ps1[]=getOtherPointByP0(ps2[1],d/3,dx/3,dy/3);//获取9号点组
	  
	   //画出正方体,从后向前画
	   drawCube(g,ps1[0],ps1[1],ps1[2],ps1[3],ps1[4],ps1[5],ps1[6]); 
	   drawCube(g,ps3[0],ps3[1],ps3[2],ps3[3],ps3[4],ps3[5],ps3[6]);
	   drawCube(g,ps6[0],ps6[1],ps6[2],ps6[3],ps6[4],ps6[5],ps6[6]);
	   drawCube(g,ps8[0],ps8[1],ps8[2],ps8[3],ps8[4],ps8[5],ps8[6]);

   }
   
   //画顶层图形
   private void drawTopLevel(Graphics g,Point p0,int d,int dx,int dy){
	   
	   Point p1=new Point(p0.x,p0.y);//获取20号点
	   
	   Point ps20[]=getOtherPointByP0(p1,d/3,dx/3,dy/3);  //获取20号点组
	   Point ps19[]=getOtherPointByP0(ps20[1],d/3,dx/3,dy/3);//获取7号点组
	   Point ps18[]=getOtherPointByP0(ps19[1],d/3,dx/3,dy/3);//获取6号点组
	   Point ps17[]=getOtherPointByP0(ps20[5],d/3,dx/3,dy/3);//获取5号点组
	   Point ps16[]=getOtherPointByP0(ps18[5],d/3,dx/3,dy/3);//获取4号点组
	   Point ps15[]=getOtherPointByP0(ps17[5],d/3,dx/3,dy/3);//获取3号点组
	   Point ps14[]=getOtherPointByP0(ps15[1],d/3,dx/3,dy/3);//获取2号点组
	   Point ps13[]=getOtherPointByP0(ps14[1],d/3,dx/3,dy/3);//获取1号点组
	   
	   //画出正方体
	   drawCube(g,ps13[0],ps13[1],ps13[2],ps13[3],ps13[4],ps13[5],ps13[6]); 
	   drawCube(g,ps14[0],ps14[1],ps14[2],ps14[3],ps14[4],ps14[5],ps14[6]);
	   drawCube(g,ps15[0],ps15[1],ps15[2],ps15[3],ps15[4],ps15[5],ps15[6]);
	   drawCube(g,ps16[0],ps16[1],ps16[2],ps16[3],ps16[4],ps16[5],ps16[6]);
	   drawCube(g,ps17[0],ps17[1],ps17[2],ps17[3],ps17[4],ps17[5],ps17[6]);
	   drawCube(g,ps18[0],ps18[1],ps18[2],ps18[3],ps18[4],ps18[5],ps18[6]);
	   drawCube(g,ps19[0],ps19[1],ps19[2],ps19[3],ps19[4],ps19[5],ps19[6]); 
	   drawCube(g,ps20[0],ps20[1],ps20[2],ps20[3],ps20[4],ps20[5],ps20[6]);
   }
   
   //画底层图形
   private void drawBottomLevel(Graphics g,Point p0,int d,int dx,int dy){
	   
	   Point p1=new Point(p0.x,p0.y+(int)(2*d/3));//获取8号点
	   
	   Point ps8[]=getOtherPointByP0(p1,d/3,dx/3,dy/3);  //获取8号点组
	   Point ps7[]=getOtherPointByP0(ps8[1],d/3,dx/3,dy/3);//获取7号点组
	   Point ps6[]=getOtherPointByP0(ps7[1],d/3,dx/3,dy/3);//获取6号点组
	   Point ps5[]=getOtherPointByP0(ps8[5],d/3,dx/3,dy/3);//获取5号点组
	   Point ps4[]=getOtherPointByP0(ps6[5],d/3,dx/3,dy/3);//获取4号点组
	   Point ps3[]=getOtherPointByP0(ps5[5],d/3,dx/3,dy/3);//获取3号点组
	   Point ps2[]=getOtherPointByP0(ps3[1],d/3,dx/3,dy/3);//获取2号点组
	   Point ps1[]=getOtherPointByP0(ps2[1],d/3,dx/3,dy/3);//获取1号点组
	   
	   //画出正方体
	   drawCube(g,ps1[0],ps1[1],ps1[2],ps1[3],ps1[4],ps1[5],ps1[6]); 
	   drawCube(g,ps2[0],ps2[1],ps2[2],ps2[3],ps2[4],ps2[5],ps2[6]);
	   drawCube(g,ps3[0],ps3[1],ps3[2],ps3[3],ps3[4],ps3[5],ps3[6]);
	   drawCube(g,ps4[0],ps4[1],ps4[2],ps4[3],ps4[4],ps4[5],ps4[6]);
	   drawCube(g,ps5[0],ps5[1],ps5[2],ps5[3],ps5[4],ps5[5],ps5[6]);
	   drawCube(g,ps6[0],ps6[1],ps6[2],ps6[3],ps6[4],ps6[5],ps6[6]);
	   drawCube(g,ps7[0],ps7[1],ps7[2],ps7[3],ps7[4],ps7[5],ps7[6]); 
	   drawCube(g,ps8[0],ps8[1],ps8[2],ps8[3],ps8[4],ps8[5],ps8[6]);  
   }
   
   //根据0号点得出另外几个点的坐标
   private Point[] getOtherPointByP0(Point p0,int d,int dx,int dy){
	   
	   //计算其他几个点的坐标
	   Point p1=new Point(p0.x-d,p0.y);
	   Point p2=new Point(p0.x-d,p0.y+d);
	   Point p3=new Point(p0.x,p0.y+d);
	   Point p4=new Point(p3.x+dx,p3.y-dy);
	   Point p5=new Point(p0.x+dx,p0.y-dy);
	   Point p6=new Point(p0.x-d+dx,p0.y-dy);
	   
	   //返回所有点坐标的数组
	   Point ps[]=new Point[7];
	   ps[0]=p0;
	   ps[1]=p1;
	   ps[2]=p2;
	   ps[3]=p3;
	   ps[4]=p4;
	   ps[5]=p5;
	   ps[6]=p6;
	  
	   return ps;
   }
   
   int count=0;   //递归次数
   
   //给定7个点，画出一个正方体
   private void drawCube(Graphics gra,Point p0,Point p1,Point p2,Point p3,Point p4,Point p5,Point p6){
	   if(count<20){
		   pn[count]=p0;
	   }
	   count++;   //递归次数加1
	   Graphics2D g=(Graphics2D)gra;
	   
	   Stroke stroke=new BasicStroke(2.0f);//设置线宽为3.0 
	   g.setStroke(stroke);
	   
	   g.setPaint(Color.black);
	   //先画出9条线
	   g.drawLine(p0.x, p0.y, p1.x, p1.y);
	   g.drawLine(p1.x, p1.y, p2.x, p2.y);
	   g.drawLine(p2.x, p2.y, p3.x, p3.y);
	   g.drawLine(p3.x, p3.y, p0.x, p0.y);
	   g.drawLine(p1.x, p1.y, p6.x, p6.y);
	   g.drawLine(p6.x, p6.y, p5.x, p5.y);
	   g.drawLine(p0.x, p0.y, p5.x, p5.y);
	   g.drawLine(p5.x, p5.y, p4.x, p4.y);
	   g.drawLine(p4.x, p4.y, p3.x, p3.y);
	   
	   //给第二个界面填充颜色
	   Polygon pon2=new Polygon();
	   pon2.addPoint(p0.x, p0.y);
	   pon2.addPoint(p1.x, p1.y);
	   pon2.addPoint(p6.x, p6.y);
	   pon2.addPoint(p5.x, p5.y);
	   g.setColor(new Color(255,69,0));
	   g.fillPolygon(pon2);
	  
	   //给第一个界面填充参数
	   Polygon pon1=new Polygon();
	   pon1.addPoint(p0.x, p0.y);
	   pon1.addPoint(p1.x, p1.y);
	   pon1.addPoint(p2.x, p2.y);
	   pon1.addPoint(p3.x, p3.y);
	   g.setColor(new Color(0,255,0));
	   g.fillPolygon(pon1);
	   
	   //给第三个面填充颜色
	   Polygon pon3=new Polygon();
	   pon3.addPoint(p0.x, p0.y);
	   pon3.addPoint(p3.x, p3.y);
	   pon3.addPoint(p4.x, p4.y);
	   pon3.addPoint(p5.x, p5.y);
	   g.setColor(new Color(255,250,250));
	   g.fillPolygon(pon3);  
   }
}
