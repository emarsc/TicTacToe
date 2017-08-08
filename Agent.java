import java.util.*;

public class Agent
{

	private LinkedList<Integer> results;
	private LinkedList<Integer> points;
	private int playertype;
	int wins=0;
	public int random_chance;
	int losses=0;
	int draws=0;
	int mark;
	int total_games;
	int boards=0;
	private LinkedList<Integer> tempResults=new LinkedList<Integer>();

	public Agent(int t, int r)
	{
		this.results=new LinkedList<Integer>();
		this.points=new LinkedList<Integer>();
		 this.random_chance=r;
		 this.playertype=t;
		 if (this.playertype==-1)
		 	this.mark=2;
		 else
		 	this.mark=1;

	}
	public void clearList()
	{
		this.results=new LinkedList<Integer>();
		this.points=new LinkedList<Integer>();
	}

	public int parseGrid(int[][] g)
	{
		String grid_string="";
		int grid_int;
		for (int i=0; i<3; i++) {
			for(int j=0; j<3; j++)
			{
				//System.out.print(g[i][j]+" ");
				grid_string=grid_string+Integer.toString(g[i][j]);
			}
		//System.out.println();
		}

		grid_int=Integer.parseInt(grid_string);
		return grid_int;
	}

	public int[][] parseIntKey(int grid_key)
	{
		int[][] g=new int[3][3];
		for(int i=2; i>-1; i--)
			for (int j=2; j>-1; j--)
				{
					int digit=grid_key%10;
					grid_key=grid_key/10;
					g[i][j]=digit;
				}

		return g;
	}

	public boolean compareGrids(int[][] grid1, int[][] grid2)
	{
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
			{
				if (grid1[i][j]!=grid2[i][j])
					return false;
			}
		return true;
	}
	public void pruneList()
	{
		if (this.results.size()>2800)
		{
			int i=0;
			while (i<this.results.size())
			{
				if(this.points.get(i)<0) {
					this.results.remove(i);
				this.points.remove(i); }
				i++;
			}
		}
	}
	public void recordResult(int w)
	{
		//System.out.println("Winner: "+w);
		this.total_games++;
		if (w==this.playertype)
			this.wins++;
		else if (w==this.playertype*-1)
			this.losses++;
		else if (w==0)
			this.draws++;
		while (!this.tempResults.isEmpty())
		{
			int flag=1;
			Integer temp_r =this.tempResults.pop();
				if (this.results.contains(temp_r))
				{
					flag=0;
					int index=this.results.indexOf(temp_r);
					int r=this.points.get(index);
					r=r+w;
					Integer temp=new Integer(r);
					this.points.set(index, temp);
				}

			if (flag==1)
			{ //System.out.println("pushed");
				this.results.push(temp_r);
			Integer t=new Integer(w);
			this.points.push(t); }
		}
		this.tempResults=new LinkedList<Integer>();
		//this.pruneList();
	}
	public void printData()
	{
		/*Game g=new Game();
		int i=0;
		int interval;
		if (results.size()>=500)
		 interval=results.size()/40;
		 else
		 	interval=1;
		while (i<this.results.size())
		{
				g.printBoard(this.parseIntKey(this.results.get(i)));
				System.out.println(Integer.toString(this.points.get(i)));
			i++;
		}*/
		System.out.println("Wins: "+this.wins+" Losses: "+this.losses+" Draws: "+this.draws+" Total Games: "+this.total_games+" Boards: "+this.results.size());

	}

	public int findBoardResult(int result)
	{
		Integer r=new Integer(result);
		if (this.results.contains(r))
		{
			int index=this.results.indexOf(r);
			//System.out.println("Board Found");
			return this.points.get(index);
		}

		return 0;
	}

	public int[] nextMove(int[][] grid, LinkedList<int[]> legalMoves)
	{
		Integer int_key=this.parseGrid(grid);
		this.tempResults.push(int_key);
		int rand_index=(int)(Math.random()*legalMoves.size());
		int[] default_move=legalMoves.get(rand_index);
		int rand_flag=(int)(Math.random()*this.random_chance);
		if (rand_flag==0 || this.random_chance<0) {
			grid[default_move[0]][default_move[1]]=this.mark;
		    int_key=this.parseGrid(grid);
			this.tempResults.push(int_key);
			grid[default_move[0]][default_move[1]]=0;
			return default_move;
			 }
		//System.out.println("random: "+rand_index);
		int[] max_move=new int[2];
		max_move=default_move;
		int flag=0;
		int max=-99999;
		while (!legalMoves.isEmpty())
		{
			int[] move=new int[2];
			int net=0;
			move=legalMoves.pop();
			grid[move[0]][move[1]]=this.mark;
			int int_key2=this.parseGrid(grid);
			int result=this.findBoardResult(int_key2);
				if ((result*this.playertype)>max)
				{ max=result*this.playertype;
				flag=1;
				  max_move=move;
				}

			grid[move[0]][move[1]]=0;

		}
		/*if (flag==0)
			System.out.println("flag");*/

		//System.out.println(max*this.playertype);
		grid[max_move[0]][max_move[1]]=this.mark;
		 int_key=this.parseGrid(grid);
		this.tempResults.push(int_key);
		grid[max_move[0]][max_move[1]]=0;

		return max_move;
	}

}