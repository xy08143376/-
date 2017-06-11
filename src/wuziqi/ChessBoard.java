/**
 * 
 */
package wuziqi;

/**
 * @author G50
 *
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class ChessBoard extends JPanel implements MouseListener{
	public static final int BIANJU =30;//�߾�
	public static final int JIANJU =35;//������
	public static final int ROWS=10;//��������
	public static final int COLS =10;//��������
	
	ChessPoint[] chessList =new ChessPoint[(ROWS+1)*(COLS+1)];//�����
	
	boolean isBlack=true;//Ĭ�Ϻ�������
	boolean gameOver=false;//��Ϸ�������
	
	int chessCount;//�������Ӹ���
	
	int xIndex,yIndex;//�������ӵ�����
	
	
	public ChessBoard(){
		this.setBackground(Color.orange);
		this.addMouseListener(this);
		this.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent e){
				
			}
			public void mouseMoved(MouseEvent e){
				int x1=(e.getX()-BIANJU+JIANJU/2)/JIANJU ;//���������λ��ת��������ĺ�����(����)
				int y1=(e.getY()-BIANJU+JIANJU/2)/JIANJU ;//                          ������
				
				if(x1<0 || x1>ROWS || y1<0 || y1>COLS ||gameOver ||findChess(x1,y1))
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//��������ó�Ĭ����״
				else
					setCursor(new Cursor(Cursor.HAND_CURSOR));//���ó�����
			}
		});
	}
	
	
	public void paint(Graphics g){
		super.paint(g);//������
		
		for(int i=0;i<=ROWS;i++){ 														//������
			g.drawLine(BIANJU, BIANJU+i*JIANJU, BIANJU+COLS*JIANJU, BIANJU+i*JIANJU);//�����̺���
			
		}
		for(int i=0;i<=COLS;i++){
			g.drawLine(BIANJU+i*JIANJU, BIANJU, BIANJU+i*JIANJU, BIANJU+ROWS*JIANJU);//����������
		}
		
		for(int i=0;i<chessCount;i++){
			int xPos=chessList[i].getX()*JIANJU+BIANJU;//���̽�����x����
			int yPos=chessList[i].getY()*JIANJU+BIANJU;//           y
			
			g.setColor(chessList[i].getColor());//������ɫ
			g.fillOval(xPos-ChessPoint.Diameter/2, yPos-ChessPoint.Diameter/2,
					ChessPoint.Diameter, ChessPoint.Diameter);
			//Ϊ���������ɫ
			
			if(i==chessCount-1){			//������һ������
				g.setColor(Color.red);
				g.drawRect(xPos-ChessPoint.Diameter/2, yPos-ChessPoint.Diameter/2, 
						ChessPoint.Diameter, ChessPoint.Diameter);
				
			}
		}
	}
	
	
	public void mousePressed(MouseEvent e){
		if(gameOver)			//��Ϸ����
			return;
		
		String colorName = isBlack ? "����" :"����";
		
		xIndex=(e.getX()-BIANJU+JIANJU/2)/JIANJU;	//�������λ�õĺ�����
		yIndex=(e.getY()-BIANJU+JIANJU/2)/JIANJU;   //				 ��
		
		if(xIndex<0 ||xIndex>ROWS ||yIndex<0 ||yIndex>COLS){	//����������
			return;
		}
		
		if(findChess(xIndex,yIndex)){		//x,yλ���Ѿ�������
			return;
		}
		
		ChessPoint ch=new ChessPoint(xIndex,yIndex,isBlack?Color.black:Color.white);
		chessList[chessCount++]=ch;
		repaint();		//���»���
		
		if(isWin()){
			String msg=String.format("��ϲ��%sӮ�ˣ�", colorName);//String.format��API
			JOptionPane.showMessageDialog(this, msg);		//��API
			gameOver=true;
		}
		
		isBlack=!isBlack;
	}
	
	
	public void mouseClicked(MouseEvent e){
		
	}
	public void mouseEntered(MouseEvent e){
		
	}
	public void mouseExited(MouseEvent e){
		
	}
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean findChess(int x,int y){			//�����������в����Ƿ�������Ϊx,y�Ĵ��ڣ�
		for(ChessPoint c : chessList){				//for(s:v)��ʾ��������VȻ�󸳸�����S
			if(c!=null && c.getX()==x && c.getY()==y){
				return true;
			}
		}
		return false;
	}
	
	
	private boolean isWin(){
		int continueCount = 1;		//�������ӵĸ���
		
		for(int x=xIndex-1;x>=0;x--){		//��������Ѱ��
			Color c = isBlack ? Color.black :Color.white;
			if(getChess(x,yIndex,c)!= null){
				continueCount++;
			}
			else
				break;
		}
		
		for(int x=xIndex+1;x<=ROWS;x++){		//������Ѱ��
			Color c = isBlack ? Color.black :Color.white;
			if(getChess(x,yIndex,c)!= null){
				continueCount++;
			}
			else
				break;
		}
		
		if(continueCount >= 5){
			return true;
		}
		else{			//����û�гɹ���������������
			continueCount =1;
		}
		
		for(int y=yIndex-1;y>=0;y--){		//����������
			Color c = isBlack ? Color.black :Color.white;
			if(getChess(xIndex,y,c)!= null){
				continueCount++;
			}
			else
				break;
		}
		
		for(int y=yIndex+1;y<=COLS;y++){		//������������
			Color c = isBlack ? Color.black :Color.white;
			if(getChess(xIndex,y,c)!= null){
				continueCount++;
			}
			else
				break;
		}
		
		if(continueCount >= 5){
			return true;
		}
		else{			//����û�ɹ���������������
			continueCount = 1;
		}
		
		for(int x=xIndex-1,y=yIndex-1;x >= 0&&y >= 0;x--,y--){	//������������
			Color c = isBlack ? Color.black :Color.white;
			if(getChess(x,y,c)!= null){
				continueCount++;
			}
			else
				break;
		}
		
		for(int x=xIndex+1,y=yIndex+1;x <= ROWS && y <= COLS;x++,y++){	//���Ϸ�������
			Color c = isBlack ? Color.black :Color.white;
			if(getChess(x,y,c)!= null){
				continueCount++;
			}
			else
				break;
		}
		
		if(continueCount >=5){
			return true;
		}
		else{						//135'����û�ɹ�������������
			continueCount = 1;
		}
		
		for(int x=xIndex+1,y=yIndex-1;x <=COLS && y >=0;x++,y--){	//������������
			Color c = isBlack ? Color.black :Color.white;
			if(getChess(x,y,c)!= null){
				continueCount++;
			}
			else
				break;
		}
		
		for(int x=xIndex-1,y=yIndex+1;x >=0 && y <= COLS;x--,y++){
			Color c = isBlack ? Color.black :Color.white;
			if(getChess(x,y,c)!= null){
				continueCount++;
			}
			else
				break;
		}
		
		if(continueCount >=5){
			return true;
		}
		else{		//45'����û�ɹ�
			continueCount = 1;
		}
		
		return false;	//���з����������
	}
	
	
	private ChessPoint getChess(int xIndex,int yIndex,Color color){
		for(ChessPoint c : chessList){
			if(c != null && c.getX()==xIndex && c.getY()==yIndex && c.getColor()==color){
				return c;
			}
		}
		return null;
	}
	
	
	public void restartGame(){			//���¿�ʼ
		for(int i=0;i< chessList.length;i++){
			chessList[i] = null;			//�������
		}
		
		isBlack = true;				//�ָ���ʼֵ
		gameOver = false;
		chessCount =0;
		repaint();
	}
	
	public void goback(){
		if(chessCount ==0)		//û�ӣ��޷�����
			return;
		
		chessList[chessCount-1] =null;
		chessCount--;
		if(chessCount >0){
			xIndex = chessList[chessCount-1].getX();
			yIndex = chessList[chessCount-1].getY();
		}
		isBlack = !isBlack;
		repaint();
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(BIANJU *2 +JIANJU * COLS,BIANJU *2 +JIANJU * ROWS);
	}
}
