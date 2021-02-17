import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game {
	
	private static final int SCALE_SMOOTH = 0;
	JFrame frame,frame2,frame3;
	Cord taki=new Cord();
	Snake snake=new Snake();
	Ladder ladder=new Ladder();
	//JTextField jerry,tom;
	JLabel num,icon,ficon1,ficon2,start,caption,dicepng,icon1,icon2,snk,ldr,secret,rep,ldr2,snk2,back,w,fire,wt;
	Myboard board;
	JButton newboard,creator,broll,minstep,reset,tom,jerry;
	Myimg imgl;
	private int turn=1,p1=0,p2=0,winner=0,visit[],minx1=0,miny2=0,minx2=0,miny1=0,movcur,halt=0,px1=0,px2=0,py1=0,py2=0,movingp=0;
	ArrayList<node> mins;
	Timer tm,tm2;
	
	Game()
	{
		visit=new int[101];
		tm=new Timer( 100,new moving() );
		tm2=new Timer( 100,new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				float m,c;
				if ( px1!=px2 ) 
				{
					m=(float)( py2-py1 )/( px2-px1 );
					c=(float)py1-m*px1;
						
					if (px1<px2) px1=px1+20;
					if (px1>px2) px1=px1-20;
					if (py1!=py2) py1=(int)(m*px1+c);
				}
				else
				{
					if (py1>py2) py1=py1-20;
					if (py1<py2) py1=py1+20;
				}
					
				if (movingp==1)
					icon1.setBounds( px1, py1, 40, 40);
				else if (movingp==2)
					icon2.setBounds( px1, py1, 40, 40);
				
				if (px1==px2 && py1==py2 && movingp==1) {

					if ( snake.checkheadat(p1)==true )
					{
						p1=snake.gettailat();
						px2=taki.getx(p1)+10;
						py2=taki.gety(p1)+10;
					}
					if ( ladder.checkbottomat(p1)==true )
					{
						p1=ladder.gettopat();
						px2=taki.getx(p1)+10;
						py2=taki.gety(p1)+10;
					}
					
					if (px1==px2 && py1==py2 )
					{
						broll.setEnabled(true);
						tm.stop();
						movingp=0;
					}
				}
				
				if (px1==px2 && py1==py2 && movingp==2) {
					

					if ( snake.checkheadat(p2)==true )
					{
						p2=snake.gettailat();
						px2=taki.getx(p2)+10;
						py2=taki.gety(p2)+10;
					}
					if ( ladder.checkbottomat(p2)==true )
					{
						p2=ladder.gettopat();
						px2=taki.getx(p2)+10;
						py2=taki.gety(p2)+10;
					}
					
					if (px1==px2 && py1==py2 )
					{
						broll.setEnabled(true);
						tm.stop();
						movingp=0;
					}
				}
				
			}
			
			
		});
		
		frame=new JFrame("Snakes and Ladders");
		frame.setBounds(200, 5, 1000, 720);
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		genrandoms();
		board=new Myboard();
		imgl=new Myimg(new ImageIcon(new ImageIcon("theboard.png").getImage().getScaledInstance(600,600, SCALE_SMOOTH)));
		board.add(imgl);
		frame.getContentPane().add(board);
		
		tom = new JButton(new ImageIcon("p11.png"));
		//tom.setEnabled(false);
		tom.setBounds(48, 297, 195, 41);
		frame.getContentPane().add(tom);
		//tom.setColumns(10);
		
		jerry = new JButton(new ImageIcon("p22.png"));
		//jerry.setColumns(10);
		jerry.setBounds(165, 361, 187, 41);
		frame.getContentPane().add(jerry);
		
		newboard= new JButton(new ImageIcon("ng.png"));
		newboard.setBorder(new RoundedBorder(10));
		newboard.setFont(new Font("Segoe Script", Font.BOLD, 20));
		newboard.setBounds(620, 11, 170, 48);
		frame.getContentPane().add(newboard);
		newboard.addActionListener(new btn());
		
		start = new JLabel(new ImageIcon(new ImageIcon("start.png").getImage().getScaledInstance(316,80, SCALE_SMOOTH)));
		start.setBounds(48, 600, 316, 80);
		frame.getContentPane().add(start);
		
		broll = new JButton(new ImageIcon("rtd1.png"));
		broll.setBorder(new RoundedBorder(10));
		broll.setFont(new Font("MV Boli", Font.BOLD, 25));
		broll.setBounds(60, 424, 280, 47);
		frame.getContentPane().add(broll);
		broll.addActionListener(new btn());
		
		secret=new JLabel("By Jerry( Sourabh )");
		secret.setBounds(280,280,100,40);
		board.add(secret);

		icon=new JLabel(new ImageIcon(new ImageIcon("icon.png").getImage().getScaledInstance(200,200, SCALE_SMOOTH)));
		icon.setBorder(new LineBorder(new Color(0, 0, 0)));
		icon.setBounds(10, 11, 200, 200);
		frame.getContentPane().add(icon);
		
		ficon1 = new JLabel(new ImageIcon(new ImageIcon("tom.png").getImage().getScaledInstance(40,40, SCALE_SMOOTH)));
		ficon1.setBounds(295, 298, 57, 41);
		frame.getContentPane().add(ficon1);
		
		ficon2 = new JLabel(new ImageIcon(new ImageIcon("jerry.png").getImage().getScaledInstance(40,40, SCALE_SMOOTH)));
		ficon2.setBounds(49, 362, 57, 41);
		frame.getContentPane().add(ficon2);
		
		icon1 = new JLabel(new ImageIcon(new ImageIcon("tom.png").getImage().getScaledInstance(40,40, SCALE_SMOOTH)));
		imgl.add(icon1);
		
		icon2 = new JLabel(new ImageIcon(new ImageIcon("jerry.png").getImage().getScaledInstance(40,40, SCALE_SMOOTH)));
		imgl.add(icon2);
		
		rep = new JLabel(new ImageIcon(new ImageIcon("groot.png").getImage().getScaledInstance(40,40, SCALE_SMOOTH)));
		imgl.add(rep);
		
		dicepng = new JLabel(new ImageIcon("dice.gif"));
		dicepng.setBounds(48, 482, 148, 117);
		frame.getContentPane().add(dicepng);

        snk2 = new JLabel(new ImageIcon("SC.png"));
        snk2.setBounds(48, 177, 148, 117);
        frame.getContentPane().add(snk2);

        fire = new JLabel(new ImageIcon(new ImageIcon("fire.gif").getImage().getScaledInstance(450,300, SCALE_SMOOTH)));
        
        ldr2 = new JLabel(new ImageIcon("LC.png"));
        ldr2.setBounds(48, 197, 148, 117);
        frame.getContentPane().add(ldr2);
        
        back = new JLabel(new ImageIcon("crown1.png"));
        back.setBounds(278, 65, 80, 60);
        frame.getContentPane().add(back);
		
		caption = new JLabel("Snakes and Ladders");
		caption.setFont(new Font("Old English Text MT", 1, 38  ));
		caption.setBounds(232, 11, 388, 48);
		frame.getContentPane().add(caption);

        w = new JLabel("Winner");
        w.setFont(new Font("Old English Text MT", 1, 19  ));
        w.setBounds(287, 116, 78, 38);
        frame.getContentPane().add(w);
		
		num = new JLabel("");
        num.setFont(new Font("Times New Roman", 1, 65));
		num.setHorizontalAlignment(SwingConstants.CENTER);
		num.setBounds(160, 482, 150, 109);
		frame.getContentPane().add(num);

		creator = new JButton(new ImageIcon("cre.png"));
        creator.setBorder(new RoundedBorder(10));
        creator.setBackground(Color.magenta);
        //creator.setText("Creators");
        creator.setFont(new Font("Segoe Script", Font.BOLD, 20));
        creator.setBounds(798, 11, 173, 48);
		frame.getContentPane().add(creator);
		creator.addActionListener(new btn());
		
		minstep = new JButton(new ImageIcon("min.png"));
        minstep.setBorder(new RoundedBorder(10));
        minstep.setFont(new Font("Comic Sans MS", 1, 20));
        minstep.setBounds(196, 222, 163, 50);
        //minstep.setText("Mins Step");
		frame.getContentPane().add(minstep);
		minstep.addActionListener(new btn());
		
		reset = new JButton(new ImageIcon("res1.png"));
        reset.setFont(new Font("Comic Sans MS", 1, 21));
        reset.setBounds(221, 161, 133, 48);
        reset.setBorder(new RoundedBorder(10));
		frame.getContentPane().add(reset);
		reset.addActionListener(new btn());
		
		snk = new JLabel("Snakes -");
		snk.setHorizontalAlignment(SwingConstants.CENTER);
		snk.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		snk.setBounds(10, 222, 100, 25);
		frame.getContentPane().add(snk);
		
		ldr = new JLabel("Ladders -");
		ldr.setHorizontalAlignment(SwingConstants.CENTER);
		ldr.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ldr.setBounds(10, 244, 100, 25);
		frame.getContentPane().add(ldr);

		
		frame3 = new JFrame();
		frame3.setBounds(500, 220, 450, 300);
		frame3.add(fire);
		fire.setBounds(0,0,450,300);
		frame3.setResizable(false);
        frame3.setTitle(" #1 Victory Royale ");
        frame3.setLayout(null);
		
		frame2 = new JFrame();
		frame2.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame2.setTitle("Creators");
		frame2.setResizable(false);
		frame2.setBounds(500, 220, 450, 300);
		frame2.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel caption2 = new JLabel("Creators");
		caption2.setFont(new Font("Segoe Print", Font.BOLD, 20));
		caption2.setBounds(165, 11, 100, 27);
		frame2.getContentPane().add(caption2);

		JLabel cr5 = new JLabel("ITM2017002  Joel Swapnil Singh");
		cr5.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		cr5.setBounds(69, 58, 320, 27);
		frame2.getContentPane().add(cr5);
		
		JLabel cr1 = new JLabel("IIT2017138   Priyanshu Kumar");
		cr1.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		cr1.setBounds(69, 96, 320, 27);
		frame2.getContentPane().add(cr1);
		
		JLabel cr2 = new JLabel("IIT2017139  Sourabh");
		cr2.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		cr2.setBounds(69, 134, 320, 27);
		frame2.getContentPane().add(cr2);
		
		JLabel cr3 = new JLabel("IIT2017140  Jagmohan Das Bairagi");
		cr3.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		cr3.setBounds(69, 172, 320, 27);
		frame2.getContentPane().add(cr3);
		
		JLabel cr4 = new JLabel("IIT2017144  Tushar Agrawal");
		cr4.setFont(new Font("Segoe Print", Font.PLAIN, 15));
		cr4.setBounds(69, 210, 320, 27);
		frame2.getContentPane().add(cr4);
		

		
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args)
	{
		new Game();
	}
	
	public void genrandoms()
	{
		boolean wow;
		snake.clear();
		ladder.clear();
		Random r=new Random();
		int rnd[]=new int[21];

		for(int i=1;i<=20;i++)
		{
			wow=false;
			int ran=0;
			
			if (i==1)
				ran=r.nextInt(99)+1;
			
			while( wow==false && i>1 )
			{
				ran=r.nextInt(99)+1;
				for(int j=1;j<i;j++) 
				{
					if (rnd[j]==ran)  break;
					
					if ( j==(i-1) )   wow=true;
				}
			}
			
			rnd[i]=ran;
		}
		
		Arrays.sort(rnd);
		
		for(int i=1;i<=5;i++)
		{
			snake.seth(rnd[10+i*2]);                   // 12,14,16,18,20
			snake.sett(rnd[i*2-1]);                    // 1,3,5,7,9
			ladder.sett(rnd[9+i*2]);                   // 11,13,15,17,19
			ladder.setb(rnd[i*2]);                     // 2,4,6,8,10
		}
		
	}
	
	class btn implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e)
		{
			frame3.setVisible(false);
			frame2.setVisible(false);
			rep.setBounds(0,0,0,0);
			if (e.getSource()==newboard)
			{
				genrandoms();
				board.repaint();
			}
			if ( e.getSource()==broll  && w.getText().equals("Winner") )
			{
				Random r=new Random();
				int cur,dice;
				dice=r.nextInt(6)+1;
				num.setText( String.valueOf(dice) );
				if (turn==1)
				{
					turn=2;
					jerry.setEnabled(true);
					tom.setEnabled(false);
					cur=p1+dice;
					if ( cur<100 )
					{
						if (p1==0) p1=1;
						
						px1=taki.getx(p1)+10;
						py1=taki.gety(p1)+10;
						p1=cur;
						px2=taki.getx(p1)+10;
						py2=taki.gety(p1)+10;
						moveplayer(1);
					}
					else if(cur==100)
					{
						icon1.setVisible(false);
						icon2.setVisible(false);
						turn=1;
						icon1.setBounds(0,0,0,0);
						icon2.setBounds(0,0,0,0);
						p1=0;
						p2=0;
						num.setText("");
						w.setText("Minion");
						tom.setEnabled(true);
						wt=new JLabel("Minion Wins");
						fire.add( wt );
						wt.setBounds(120,100,200,30);
						wt.setForeground(Color.WHITE);
						wt.setFont(new Font("Segoe Print", Font.BOLD, 30));
				        frame3.setVisible(true);
					}
				}
				else
				{
					turn=1;
					tom.setEnabled(true);
					jerry.setEnabled(false);
					cur=p2+dice;
					if ( cur<100 )
					{
						if (p2==0) p2=1;
						px1=taki.getx(p2)+10;
						py1=taki.gety(p2)+10;
						p2=cur;
						px2=taki.getx(p2)+10;
						py2=taki.gety(p2)+10;
						moveplayer(2);
					}
					else if(cur==100)
					{
						jerry.setEnabled(true);
						icon1.setVisible(false);
						icon2.setVisible(false);
						turn=1;
						icon1.setBounds(0,0,0,0);
						icon2.setBounds(0,0,0,0);
						p1=0;
						p2=0;
						num.setText("");
						w.setText(" Jerry");
						wt=new JLabel(" Wins");
						fire.add( wt );
						wt.setBounds(120,100,200,30);
						wt.setForeground(Color.WHITE);
						wt.setFont(new Font("Segoe Print", Font.BOLD, 30));
				        frame3.setVisible(true);
					}
				}
			}
			if (e.getSource()!=broll)
			{
				winner=0;
				turn=1;
				icon1.setBounds(0,0,0,0);
				icon2.setBounds(0,0,0,0);
				p1=0;
				p2=0;
				num.setText("");
				w.setText("Winner");
				minstep.setText(null);
				tom.setEnabled(true);
				jerry.setEnabled(true);
			}
			if (e.getSource()!=minstep)
			{
				tm.stop();
				minstep.setEnabled(true);
				minstep.setIcon(new ImageIcon("min.png"));
			}
			if (e.getSource()==creator)
			{
				frame2.setVisible(true);
			}
			if (e.getSource()==minstep)
			{
				minstep.setIcon(null);
				movcur=1;
				minstep.setEnabled(false);
				findmin();
				
				mins.add(0,new node(1,0));
				
				minx1=taki.getx( mins.get(0).getcord() )+10;
				miny1=taki.gety( mins.get(0).getcord() )+10;

				minx2=taki.getx( mins.get(1).getcord() )+10;
				miny2=taki.gety( mins.get(1).getcord() )+10;
				halt=10;
				minstep.setText("Roll - "+mins.get(movcur).getmoves());
				movcur=2;
				tm.start();
			}
		}
	}
	
	@SuppressWarnings("serial")
	class Myboard extends JPanel
	{
		Myboard()
		{
			this.setLayout(null);
			this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			this.setBounds(374, 70, 600, 600);
		}
		
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			imgl.repaint();
		}
		
	}
	
	@SuppressWarnings("serial")
	class Myimg extends JLabel
	{
		Myimg(ImageIcon icon)
		{
			super(icon);
			this.setLayout(null);
			this.setBorder(BorderFactory.createLineBorder(Color.red));
			this.setBounds(0,0,600,600);
		}
		
		protected void paintChildren(Graphics g)
		{
			super.paintChildren(g);
			snake.drawsnakes(g,taki);
			ladder.drawladders(g,taki);
		}
		
	}
	
	public void moveplayer(int a) {
		broll.setEnabled(false);
		if (a==1)
		{
			movingp=1;
			tm2.start();
		}
		if (a==2)
		{
			movingp=2;
			tm2.start();
		}
		
	}
	
	class moving implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) {
			
			float m,c;
			if ( minx1!=minx2 ) 
			{
				m=(float)( miny2-miny1 )/( minx2-minx1 );
				c=(float)miny1-m*minx1;
					
				if (minx1<minx2) minx1=minx1+20;
				if (minx1>minx2) minx1=minx1-20;
				if (miny1!=miny2) miny1=(int)(m*minx1+c);
			}
			else
			{
				if (miny1>miny2) miny1=miny1-20;
			}
				
			rep.setBounds( minx1, miny1, 40, 40);
			
			if (minx1==minx2 && miny1==miny2 && movcur==mins.size() ) {
				tm.stop();
				minstep.setEnabled(true);
				frame.setBounds(200, 5, 1000, 720);
			}
			
			if ( minx1==minx2 && miny1==miny2 && (halt--)==0 )
			{
				minstep.setText("Roll - "+mins.get(movcur).getmoves());
				minx1=taki.getx( mins.get(movcur-1).getcord() )+10;
				miny1=taki.gety( mins.get(movcur-1).getcord() )+10;

				minx2=taki.getx( mins.get(movcur).getcord() )+10;
				miny2=taki.gety( mins.get(movcur).getcord() )+10;
				movcur++;
				halt=10;
			}
		}
		
	}
	
	public void findmin()
	{
		Queue<node> point=new LinkedList<>();
		boolean end=false;
		
		for(int i=0;i<=100;i++)
			visit[i]=0;
		point.add(new node(0,0));
		visit[0]=1;
		
		while( end==false )
		{
			node x=point.poll();
			int y=x.getcord();
			for(int j=y+1;j<=y+6;j++)
			{
				int move=x.getmoves()+1;
				if (visit[j]==1) continue;
				
				node z=new node(j,move);
				if (y!=0)
					z.setpath(x.getpath());
				z.addpath(new node(j,move));
				visit[j]=1;
				
				if (j==100)
				{
					end=true;
					mins=z.getpath();
					break;
				}
				
				if (snake.checkheadat(j)==true)
				{
					if (visit[snake.gettailat()]==1) continue;
						
					int a=snake.gettailat();
					z.setcord(a);
					visit[a]=1;
					z.addpath(new node(a,move));
				}
				if (ladder.checkbottomat(j)==true)
				{
					if (visit[ladder.gettopat()]==1) continue;
					
					int a=ladder.gettopat();
					z.setcord(a);
					visit[a]=1;
					z.addpath(new node(a,move));
				}
				
				point.add(z);
			}
		}
		
	}
	
	class RoundedBorder implements Border {

	    private int radius;


	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }


	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}
}
