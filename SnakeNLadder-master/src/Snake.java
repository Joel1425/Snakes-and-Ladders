import java.awt.*;

public class Snake {
	private int head[],tail[],counth=0,countt=0,cur;
	
	Snake(){
		head=new int[5];
		tail=new int[5];
	}
	
	public void seth(int a) {
		head[counth++]=a;
	}
	
	public void sett(int a) {
		tail[countt++]=a;
	}
	
	public int geth(int a) {
		return head[a];
	}
	
	public int gett(int a) {
		return tail[a];
	}
	
	public boolean checkheadat(int a)
	{
		for(int i=0;i<head.length;i++) {
			if (head[i]==a)
			{
				cur=i;
				return true;
			}
		}
		return false;
	}
	
	public int gettailat()
	{
		return tail[cur];
	}
	
	public void clear() {
		counth=0;
		countt=0;
	}
	
	public void drawsnakes(Graphics g,Cord taki) {
		Graphics2D gs=(Graphics2D)g;
		for(int i=0;i<5;i++)
		{
			gs.setColor(Color.RED);
			Stroke str=new BasicStroke(5);
			gs.setStroke(str);
			gs.drawLine( taki.getx( gett(i) )+30 , taki.gety( gett(i) )+30 , taki.getx( geth(i) )+30 , taki.gety( geth(i) )+30 );
		}
	}
	
}
