/**
 * 
 */
package wuziqi;

/**
 * @author G50
 *
 */

import java.awt.Color;

public class ChessPoint {
	private int x;
	private int y;//����������λ������
	
	private Color color;//������ɫ
	public static final int Diameter=30;//����ֱ��
	
	public ChessPoint(int x,int y,Color color){
		this.x=x;
		this.y=y;
		this.color=color;
		
	}
	
	public int getX(){		//��ȡx����
		return x;
	}
	
	public int getY(){		//��ȡy����
		return y;
	}
	
	public Color getColor(){	//��ȡ��ɫ
		return color;
	}
}
