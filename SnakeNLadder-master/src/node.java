import java.util.*;

public class node {
	private int cord,moves;
	private ArrayList<node> path;
	
	node(int a,int b)
	{
		path=new ArrayList<>();
		this.cord=a;
		this.moves=b;
	}

	public void setcord(int cord) {
		this.cord = cord;
	}

	public void setmoves(int moves) {
		this.moves = moves;
	}

	public int getcord() {
		return cord;
	}

	public int getmoves() {
		return moves;
	}

	public ArrayList<node> getpath() {
		return path;
	}
	
	public void setpath(ArrayList<node> path) {
		this.path.addAll(path);
	}

	public void addpath(node n) {
		path.add(n);
	}
}
