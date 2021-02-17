
public class Cord {
	private int corx[],cory[];
	
	Cord(){
		corx=new int[101];
		cory=new int[101];
		for(int i=1;i<=10;i++)
		{
			corx[i]   =60*(i-1);
			corx[i+20]=60*(i-1);
			corx[i+40]=60*(i-1);
			corx[i+60]=60*(i-1);
			corx[i+80]=60*(i-1);

			corx[21-i]=60*(i-1);
			corx[41-i]=60*(i-1);
			corx[61-i]=60*(i-1);
			corx[81-i]=60*(i-1);
			corx[101-i]=60*(i-1);
			
			cory[i]   =60*9;
			cory[i+20]=60*7;
			cory[i+40]=60*5;
			cory[i+60]=60*3;
			cory[i+80]=60*1;

			cory[21-i]=60*8;
			cory[41-i]=60*6;
			cory[61-i]=60*4;
			cory[81-i]=60*2;
			cory[101-i]=0;
		}
	}
	
	public int getx(int a) {
		return corx[a];
	}
	
	public int gety(int a) {
		return cory[a];
	}
	
}
