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
	
	//��¼����
	int startx,starty,endx,endy;
	int index=0;     //ÿ������ͼ����Shape�����е�λ��  
	Shape shape[] = new Shape[1000];
	HashMap<Point, Point> hashmap=new HashMap<Point, Point>(100);
	//����
	Graphics g; 
	
	//��¼ͼ��������Ϣ���ַ���
	String shapeTypeStr = "";	
	//
	
	public void actionPerformed(ActionEvent e) {
		//��ȡ��ť����Ϣ
		String action = e.getActionCommand();
		//��¼
		shapeTypeStr = action;
		
	}

    public void mousePressed(MouseEvent e) {
    	//��¼���µ�����
    	startx = e.getX();
    	starty = e.getY();	
    }
    public void mouseReleased(MouseEvent e) {
    	//��¼�ɿ�������
    	endx = e.getX();
    	endy = e.getY();

    	
    	if("ֱ��".equals(shapeTypeStr)) {
    		//����
         	shape[index]=new Shape();
        	shape[index].startX = startx;
        	shape[index].startY = starty;
        	shape[index].endX = endx;
        	shape[index].endY = endy;
    		shape[index].shapeText = "ֱ��";
    		System.out.println(shape[index].shapeText);
    		index++; 
        	g.drawLine(startx, starty, endx, endy);
        	
    	}else if("����".equals(shapeTypeStr)){
    		g.drawRect(startx, starty, endx-startx, endy-starty);
    		g.fillRect(startx, starty, endx-startx, endy-starty);
    	}else if("�Ÿ���".equals(shapeTypeStr))
    	{
    		//�����Ÿ��ຯ��
    		paint(g);
    			
    	}else if("����ɽ��".equals(shapeTypeStr))
    	{
    		//�������κ���
    		drawMoutain(g);		
    	}else if("���ε�ò".equals(shapeTypeStr))
    	{
    		hashmap.clear();
    		//�������κ���
    		drawEarthSurface(g);
    		
    	}
    	else{
     		drawTri(100,500,400,7);
    	}		
    }
	public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    //���Ƶر���
    public void drawEarthSurface(Graphics g){
    	
    	Point p1=new Point(248,113);  //1�ŵ�
    	Point p2=new Point(821,113); //2�õ�
    	Point p3=new Point(773,546); //3�ŵ�
    	Point p4=new Point(128,546);  //4�ŵ�
    	
    	int dy=200;    //y�᷽���һ��ƫ��ֵ
    	int count=0;  //����ݹ�Ĵ���
    	paintEarthSurface(p1,p2,p3,p4,dy,count);
    }
    //�����ر���
    public void paintEarthSurface(Point p1,Point p2,Point p3,Point p4,int dy,int count){
    	
    		Point pnew[]=new Point[5];
    		pnew[0]=new Point((p1.x+p4.x)/2,(p1.y+p4.y)/2);  //�µ�pnew[0]����p1��p4���е�
    		pnew[1]=new Point((p1.x+p2.x)/2,(p1.y+p2.y)/2);  //�µ�pnew[1]����p1��p2���е�
    		pnew[2]=new Point((p2.x+p3.x)/2,(p2.y+p3.y)/2);  //�µ�pnew[2]����p2��p3���е�
    		pnew[3]=new Point((p3.x+p4.x)/2,(p3.y+p4.y)/2);  //�µ�pnew[3]����p3��p4���е�
    		pnew[4]=new Point((pnew[0].x+pnew[2].x)/2,(pnew[0].y+pnew[2].y)/2); //�µ�pnew[4]����pnew[0]��pnew[2]���е�   		
    		
    		count++;                   //�ݹ����
    		//�����ɵĵ�ƫ��ԭ�����겢����
			Point pfinal[]=new Point[5];
    		for(int i=0;i<5;i++){
    			pfinal[i] = new Point();
    			//�ж�pnew[i]�Ƿ���HashMap�У�����ڽ�valueֵ����pfinal[i],���򽫲���y������ٽ��д洢
    			if(hashmap.get(pnew[i])==null){
            		Random ran1=new Random();
            		int temp=ran1.nextInt(dy)*2-dy;
            		
            		pfinal[i].x=pnew[i].x;
            		pfinal[i].y=pnew[i].y+temp;

            		hashmap.put(pnew[i],pfinal[i]);
    			}
    			//�õ���HashMap()�д���
    			else{
    				pfinal[i]=hashmap.get(pnew[i]);
    			}	
    		}
    		
    		dy=dy/2;
    		
    		if(count>6){
            	//��ʼ����
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
        		paintEarthSurface(p4,pfinal[0],pfinal[4],pfinal[3],dy,count); //ÿ�������������µ��ı���
        		paintEarthSurface(pfinal[0],p1,pfinal[1],pfinal[4],dy,count);
        		paintEarthSurface(pfinal[1],p2,pfinal[2],pfinal[4],dy,count);
        		paintEarthSurface(pfinal[2],p3,pfinal[3],pfinal[4],dy,count);
    		}
}
    
  
    //����ɽ������
    public void drawMoutain(Graphics g){
    	int count=0;   //����ݹ����
    	int length=500; //�����ʼ����
    	Point p0=new Point(50,350); //���
    	Point p1=new Point(950,350); //�յ�
    	
    	//���������յ�
    	g.drawLine(p0.x, p0.y, p0.x, p0.y);
    	g.drawLine(p1.x, p1.y, p1.x, p1.y);
    	//���ߺ���
    	paintShape(p0,p1,length,count);
    }
    //�������κ���
    public void paintShape(Point P0,Point P1,int len,int count){
    	//���жϵݹ�����Ƿ�����Ҫ��
    	if(count>7){
    		return;
    	}
    	else{
    		Point mid=new Point((P0.x+P1.x)/2,(P0.y+P1.y)/2);
    		Random ran=new Random();
    		int temp=ran.nextInt(len)+1;  //��ȡ���ֵ
    		int dy;     //ƫ��ֵ
    		
    		//�������Ϊż��,�µ����Ϸ����������·�
    		if(temp%2==0){
    			dy=mid.y+temp;
    		}
    		else{
    			dy=mid.y-temp;
    		}
    	    Point newpoint=new Point(mid.x,dy);
    		//�ӵڶ��εݹ鿪ʼ����
    		if(count>0){
				g.drawLine(P0.x, P0.y, newpoint.x, newpoint.y);
    	    	g.drawLine(P1.x, P1.y, newpoint.x, newpoint.y);
    		}
    		len=len/2;
    		count++; //�ݹ��������
    		//�ݹ����
    		paintShape(P0,newpoint,len,count);
    		paintShape(P1,newpoint,len,count);	
    	}
    	
    }
    
    private void drawTri(int x, int y, int len,int times){
    	if(times==0)
    	{
    		return;        //�����εĳ���С��2ʱ���ͷ���
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
    
    Point pn[]=new Point[20];  //һ����Ԫ������20��С���������
    
    //�����Ÿ���ĺ���
    public void paint(Graphics g){
    	int x=400,y=300,d=300,dx=150,dy=120;
    	Point p0=new Point(x,y); //���
        
    	drawBottomLevel(g,p0,d,dx,dy);       //�����ײ�
    	drawMidLevel(g,p0,d,dx,dy);          //�����м��
    	drawTopLevel(g,p0,d,dx,dy);          //��������
    	
      	//�ݹ黭��ÿ����Ԫ��
      	for(int i=0;i<pn.length;i++){
    		Point pitem=pn[i];
    		if(pitem!=null){
    			drawBottomLevel(g,pitem,d/3,dx/3,dy/3);
    			drawMidLevel(g,pitem,d/3,dx/3,dy/3);
    			drawTopLevel(g,pitem,d/3,dx/3,dy/3);
    		}
    	}
}
   
   //�����м���ͼ��
   private void drawMidLevel(Graphics g,Point p0,int d,int dx,int dy ){
	   
	   Point p1=new Point(p0.x,p0.y+d/3); //12�������
	   
	   Point ps8[]=getOtherPointByP0(p1,d/3,dx/3,dy/3);  //��ȡ12�ŵ���
	   Point ps7[]=getOtherPointByP0(ps8[1],d/3,dx/3,dy/3);
	   Point ps6[]=getOtherPointByP0(ps7[1],d/3,dx/3,dy/3);//��ȡ11�ŵ���
	   Point ps5[]=getOtherPointByP0(ps8[5],d/3,dx/3,dy/3);
	   Point ps4[]=getOtherPointByP0(ps6[5],d/3,dx/3,dy/3);
	   Point ps3[]=getOtherPointByP0(ps5[5],d/3,dx/3,dy/3);//��ȡ10�ŵ���
	   Point ps2[]=getOtherPointByP0(ps3[1],d/3,dx/3,dy/3);
	   Point ps1[]=getOtherPointByP0(ps2[1],d/3,dx/3,dy/3);//��ȡ9�ŵ���
	  
	   //����������,�Ӻ���ǰ��
	   drawCube(g,ps1[0],ps1[1],ps1[2],ps1[3],ps1[4],ps1[5],ps1[6]); 
	   drawCube(g,ps3[0],ps3[1],ps3[2],ps3[3],ps3[4],ps3[5],ps3[6]);
	   drawCube(g,ps6[0],ps6[1],ps6[2],ps6[3],ps6[4],ps6[5],ps6[6]);
	   drawCube(g,ps8[0],ps8[1],ps8[2],ps8[3],ps8[4],ps8[5],ps8[6]);

   }
   
   //������ͼ��
   private void drawTopLevel(Graphics g,Point p0,int d,int dx,int dy){
	   
	   Point p1=new Point(p0.x,p0.y);//��ȡ20�ŵ�
	   
	   Point ps20[]=getOtherPointByP0(p1,d/3,dx/3,dy/3);  //��ȡ20�ŵ���
	   Point ps19[]=getOtherPointByP0(ps20[1],d/3,dx/3,dy/3);//��ȡ7�ŵ���
	   Point ps18[]=getOtherPointByP0(ps19[1],d/3,dx/3,dy/3);//��ȡ6�ŵ���
	   Point ps17[]=getOtherPointByP0(ps20[5],d/3,dx/3,dy/3);//��ȡ5�ŵ���
	   Point ps16[]=getOtherPointByP0(ps18[5],d/3,dx/3,dy/3);//��ȡ4�ŵ���
	   Point ps15[]=getOtherPointByP0(ps17[5],d/3,dx/3,dy/3);//��ȡ3�ŵ���
	   Point ps14[]=getOtherPointByP0(ps15[1],d/3,dx/3,dy/3);//��ȡ2�ŵ���
	   Point ps13[]=getOtherPointByP0(ps14[1],d/3,dx/3,dy/3);//��ȡ1�ŵ���
	   
	   //����������
	   drawCube(g,ps13[0],ps13[1],ps13[2],ps13[3],ps13[4],ps13[5],ps13[6]); 
	   drawCube(g,ps14[0],ps14[1],ps14[2],ps14[3],ps14[4],ps14[5],ps14[6]);
	   drawCube(g,ps15[0],ps15[1],ps15[2],ps15[3],ps15[4],ps15[5],ps15[6]);
	   drawCube(g,ps16[0],ps16[1],ps16[2],ps16[3],ps16[4],ps16[5],ps16[6]);
	   drawCube(g,ps17[0],ps17[1],ps17[2],ps17[3],ps17[4],ps17[5],ps17[6]);
	   drawCube(g,ps18[0],ps18[1],ps18[2],ps18[3],ps18[4],ps18[5],ps18[6]);
	   drawCube(g,ps19[0],ps19[1],ps19[2],ps19[3],ps19[4],ps19[5],ps19[6]); 
	   drawCube(g,ps20[0],ps20[1],ps20[2],ps20[3],ps20[4],ps20[5],ps20[6]);
   }
   
   //���ײ�ͼ��
   private void drawBottomLevel(Graphics g,Point p0,int d,int dx,int dy){
	   
	   Point p1=new Point(p0.x,p0.y+(int)(2*d/3));//��ȡ8�ŵ�
	   
	   Point ps8[]=getOtherPointByP0(p1,d/3,dx/3,dy/3);  //��ȡ8�ŵ���
	   Point ps7[]=getOtherPointByP0(ps8[1],d/3,dx/3,dy/3);//��ȡ7�ŵ���
	   Point ps6[]=getOtherPointByP0(ps7[1],d/3,dx/3,dy/3);//��ȡ6�ŵ���
	   Point ps5[]=getOtherPointByP0(ps8[5],d/3,dx/3,dy/3);//��ȡ5�ŵ���
	   Point ps4[]=getOtherPointByP0(ps6[5],d/3,dx/3,dy/3);//��ȡ4�ŵ���
	   Point ps3[]=getOtherPointByP0(ps5[5],d/3,dx/3,dy/3);//��ȡ3�ŵ���
	   Point ps2[]=getOtherPointByP0(ps3[1],d/3,dx/3,dy/3);//��ȡ2�ŵ���
	   Point ps1[]=getOtherPointByP0(ps2[1],d/3,dx/3,dy/3);//��ȡ1�ŵ���
	   
	   //����������
	   drawCube(g,ps1[0],ps1[1],ps1[2],ps1[3],ps1[4],ps1[5],ps1[6]); 
	   drawCube(g,ps2[0],ps2[1],ps2[2],ps2[3],ps2[4],ps2[5],ps2[6]);
	   drawCube(g,ps3[0],ps3[1],ps3[2],ps3[3],ps3[4],ps3[5],ps3[6]);
	   drawCube(g,ps4[0],ps4[1],ps4[2],ps4[3],ps4[4],ps4[5],ps4[6]);
	   drawCube(g,ps5[0],ps5[1],ps5[2],ps5[3],ps5[4],ps5[5],ps5[6]);
	   drawCube(g,ps6[0],ps6[1],ps6[2],ps6[3],ps6[4],ps6[5],ps6[6]);
	   drawCube(g,ps7[0],ps7[1],ps7[2],ps7[3],ps7[4],ps7[5],ps7[6]); 
	   drawCube(g,ps8[0],ps8[1],ps8[2],ps8[3],ps8[4],ps8[5],ps8[6]);  
   }
   
   //����0�ŵ�ó����⼸���������
   private Point[] getOtherPointByP0(Point p0,int d,int dx,int dy){
	   
	   //�������������������
	   Point p1=new Point(p0.x-d,p0.y);
	   Point p2=new Point(p0.x-d,p0.y+d);
	   Point p3=new Point(p0.x,p0.y+d);
	   Point p4=new Point(p3.x+dx,p3.y-dy);
	   Point p5=new Point(p0.x+dx,p0.y-dy);
	   Point p6=new Point(p0.x-d+dx,p0.y-dy);
	   
	   //�������е����������
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
   
   int count=0;   //�ݹ����
   
   //����7���㣬����һ��������
   private void drawCube(Graphics gra,Point p0,Point p1,Point p2,Point p3,Point p4,Point p5,Point p6){
	   if(count<20){
		   pn[count]=p0;
	   }
	   count++;   //�ݹ������1
	   Graphics2D g=(Graphics2D)gra;
	   
	   Stroke stroke=new BasicStroke(2.0f);//�����߿�Ϊ3.0 
	   g.setStroke(stroke);
	   
	   g.setPaint(Color.black);
	   //�Ȼ���9����
	   g.drawLine(p0.x, p0.y, p1.x, p1.y);
	   g.drawLine(p1.x, p1.y, p2.x, p2.y);
	   g.drawLine(p2.x, p2.y, p3.x, p3.y);
	   g.drawLine(p3.x, p3.y, p0.x, p0.y);
	   g.drawLine(p1.x, p1.y, p6.x, p6.y);
	   g.drawLine(p6.x, p6.y, p5.x, p5.y);
	   g.drawLine(p0.x, p0.y, p5.x, p5.y);
	   g.drawLine(p5.x, p5.y, p4.x, p4.y);
	   g.drawLine(p4.x, p4.y, p3.x, p3.y);
	   
	   //���ڶ������������ɫ
	   Polygon pon2=new Polygon();
	   pon2.addPoint(p0.x, p0.y);
	   pon2.addPoint(p1.x, p1.y);
	   pon2.addPoint(p6.x, p6.y);
	   pon2.addPoint(p5.x, p5.y);
	   g.setColor(new Color(255,69,0));
	   g.fillPolygon(pon2);
	  
	   //����һ������������
	   Polygon pon1=new Polygon();
	   pon1.addPoint(p0.x, p0.y);
	   pon1.addPoint(p1.x, p1.y);
	   pon1.addPoint(p2.x, p2.y);
	   pon1.addPoint(p3.x, p3.y);
	   g.setColor(new Color(0,255,0));
	   g.fillPolygon(pon1);
	   
	   //���������������ɫ
	   Polygon pon3=new Polygon();
	   pon3.addPoint(p0.x, p0.y);
	   pon3.addPoint(p3.x, p3.y);
	   pon3.addPoint(p4.x, p4.y);
	   pon3.addPoint(p5.x, p5.y);
	   g.setColor(new Color(255,250,250));
	   g.fillPolygon(pon3);  
   }
}
