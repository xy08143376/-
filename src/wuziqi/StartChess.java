/**
 * 
 */
package wuziqi;

/**
 * @author G50
 *
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class StartChess extends JFrame{
	Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	final int WIDTH=screensize.width;
	final int HEIGH=screensize.height;
	
	private ChessBoard chessBoard;
	private JPanel toolBar;
	private JMenuBar menuBar; //�˵���,�������ò˵�
	private JMenu sysMenu; //ϵͳ�˵�
	
	private JButton startButton;	//���¿�ʼ
	private JButton backButton; //����
	private JButton exitButton; //�˳�
	
	private JMenuItem startMenuItem; //�˵���
	private JMenuItem backMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem aboutGame;
	
	public StartChess(){
		this.setTitle("����������");
		
		chessBoard = new ChessBoard();//��ʼ��
		menuBar = new JMenuBar();//��ʼ���˵���
		sysMenu = new JMenu("ϵͳ");//��ʼ���˵�
		
		startMenuItem = new JMenuItem("���¿�ʼ");//��ʼ���˵���
		backMenuItem = new JMenuItem("����");
		exitMenuItem = new JMenuItem("�˳�");
		aboutGame = new JMenuItem("������Ϸ");
		
		sysMenu.add(startMenuItem);//���˵�����ӵ��˵�
		sysMenu.add(backMenuItem);
		sysMenu.add(exitMenuItem);
		sysMenu.add(aboutGame);
		
		MyItemListener itemLis = new MyItemListener();//�¼��������ڲ���
		
		this.startMenuItem.addActionListener(itemLis);//�˵���ע�������
		this.backMenuItem.addActionListener(itemLis);
		this.exitMenuItem.addActionListener(itemLis);
		this.aboutGame.addActionListener(itemLis);
		
		menuBar.add(sysMenu);//���˵���ӵ��˵���
		
		this.setJMenuBar(menuBar);//��Ӳ˵���
		
		toolBar = new JPanel();//ʵ����������
		
		startButton = new JButton("���¿�ʼ");//��ʼ�����߰�ť
		backButton = new JButton("����");
		exitButton = new JButton("�˳�");
		
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		toolBar.add(startButton);
		toolBar.add(backButton);
		toolBar.add(exitButton);
		
		startButton.addActionListener(itemLis);
		backButton.addActionListener(itemLis);
		exitButton.addActionListener(itemLis);//ע�������
		
		this.add(toolBar, BorderLayout.SOUTH);//��ӹ�����
		
		this.add(chessBoard);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//setSize(WIDTH,HEIGH);	
		setLocation(WIDTH/3, HEIGH/6);
		
		pack();//����Ӧ��С
		
	}
	
	private class MyItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Object obj = e.getSource();//��ȡ�¼�Դ
			
			if(obj == startMenuItem ||obj == startButton){
				//���¿�ʼ
				//JFiveFrame.this�ڲ��������ⲿ��
				System.out.println("���¿�ʼ������");
				chessBoard.restartGame();
				
			} 
			else if(obj == exitMenuItem || obj == exitButton){
				System.exit(0);
				//����Ӧ�ó���
			}
			else if(obj == backMenuItem || obj == backButton){
				System.out.println("���塣����");
				chessBoard.goback();
			}
			else if(obj == aboutGame){
				String msg = "����ϷΪ�������֣�����һ��ΪӮ��";
				JOptionPane.showMessageDialog(null, msg);
			}
		}
	}
	
	public static void main(String[] args){
		StartChess ss = new StartChess();
		
	}
}
