import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Ladder {
	private int top[],bottom[],countt=0,countb=0,cur;
	
	Ladder(){
		top=new int[5];
		bottom=new int[5];
	}
	
	public void sett(int a) {
		top[countt++]=a;
	}
	
	public void setb(int a) {
		bottom[countb++]=a;
	}
	
	public int gett(int a) {
		return top[a];
	}
	
	public int getb(int a) {
		return bottom[a];
	}
	
	public boolean checkbottomat(int a)
	{
		for(int i=0;i<bottom.length;i++) {
			if (bottom[i]==a)
			{
				cur=i;
				return true;
			}
		}
		return false;
	}
	
	public int gettopat()
	{
		return top[cur];
	}
	
	public void clear() {
		countt=0;
		countb=0;
	}
	
	public void drawladders(Graphics g,Cord taki) {
		Graphics2D gl=(Graphics2D)g;
		for(int i=0;i<5;i++)
		{
			gl.setColor(Color.BLUE);
			Stroke str=new BasicStroke(5);
			gl.setStroke(str);
			g.drawLine( taki.getx( getb(i) )+30 , taki.gety( getb(i) )+30 , taki.getx( gett(i) )+30 , taki.gety( gett(i) )+30 );
		}
	}
}
