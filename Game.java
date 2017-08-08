import java.util.*;

public class Game
{
public Game()
{
}

public int winner=0;
public int nummoves=0;
public Agent xAgent=new Agent(1, 100);
public Agent oAgent=new Agent(-1, 100);
public int[][] grid=new int[3][3];
LinkedList<int[][]> grids1=new LinkedList<int[][]>();
LinkedList<int[][]> grids2=new LinkedList<int[][]>();
boolean game;
int game_type;
int xMove=0;
int oMove=0;
Network net;
int starting_move=-1;
int next_turn;
Scanner input;

public Game(Scanner u)
{
	input=u;
	this.nummoves=0;
	this.game=true;
	net=new Network();
	net.buildNetwork();
	this.grid=new int[3][3];
	this.winner=0;

}
public void playGame(int rand1, int rand2, int type)
{
	this.xMove=0;
	this.oMove=0;
	this.grids1=new LinkedList<int[][]>();
	this.grids2=new LinkedList<int[][]>();
	this.winner=0;
	this.game=true;
	this.game_type=type;
	if (this.game_type==2)
	{
		this.xMove=1;
		this.oMove=1;
	}
	else if (this.game_type==1)
		this.xMove=1;
	this.clear();
	xAgent.random_chance=rand1;
	oAgent.random_chance=rand2;
	this.starting_move=this.starting_move*-1;
	this.next_turn=starting_move;
	while(this.game)
	{
		this.nextMove();
		this.winner=this.checkState(this.grid);
		if (this.winner==2)
			this.winner=-1;
		if (this.winner!=0)
		{
			if (this.game_type!=2)
			{
				if (this.winner==1) {
					this.winner=this.winner*10;
					this.printBoard(this.grid);
					System.out.println("X WINS");  }
				else if (this.winner==-1) {
					this.printBoard(this.grid);
					System.out.println("O WINS");
					this.winner=this.winner*10; }
			}
			if (this.xMove==1)
				net.giveFeedback(this.winner);
			if (this.oMove==1)
				oAgent.recordResult(this.winner);
			this.game=false;
		}
		else if (this.nummoves==9)
		{
			winner=0;
			if (this.xMove==1)
				net.giveFeedback(this.winner);
			if (this.oMove==1)
				oAgent.recordResult(this.winner);
			if (this.game_type!=2)
				System.out.println("DRAW");
			this.game=false;
			break;
		}
		this.next_turn=this.next_turn*-1;

	}

}

public void nextMove()
{
	int[] move=new int[2];
	this.nummoves=this.nummoves+1;
	if (this.next_turn==1) {
		 move=new int[2];
		if (xMove==1)
			{
			net.giveInput(grid);
			move=net.getNextMove(this.getLegalMoves(), this.grid);
			}
		else {
			this.printBoard(this.grid);
			move[0]=input.nextInt();
			move[1]=input.nextInt();
			}
		this.updateGrid(move[0], move[1], 1);
		}
	else {
		move=new int[2];
		if (oMove==1)

			move=oAgent.nextMove(this.grid, this.getLegalMoves());
		else {
			this.printBoard(this.grid);
			move[0]=input.nextInt();
			move[1]=input.nextInt();
			}
		this.updateGrid(move[0], move[1], 2);
		}

}

public void updateGrid(int x, int y, int p)
{
	if (this.grid[x][y]!=0)
	{
		System.out.println("not a legal move! Player "+p);
		net.giveFeedback(-3);
	}


	else
	{
	this.grid[x][y]=p;
	}
	/*if (p==1)
		this.grids1.push(this.copyState());
	if (p==2)
		this.grids2.push(this.copyState());*/

}


public int[][] copyState()
{
	int[][] new_grid=new int[3][3];
	for(int i=0; i<3; i++)
		for(int j=0; j<3; j++)
			new_grid[i][j]=this.grid[i][j];

	return new_grid;
}

public LinkedList<int[]> getLegalMoves()
{
	LinkedList<int[]> moves=new LinkedList<int[]>();
	for(int i=0; i<3; i++)
		for(int j=0; j<3; j++)
		{
			if (this.grid[i][j]==0)
			{
			//System.out.println("legal moves: "+i+' '+j);
			int[] coord=new int[2];
			coord[0]=i;
			coord[1]=j;
			moves.push(coord); }
		}

	return moves;
}

private int checkState(int[][] state)
{
	int x=0;
	int y=0;
	int w=0;
	if(state[0][0]==state[0][1] &&  state[0][0]==state[0][2] && state[0][0]!=0){
		w=state[0][0];
		return w;}
	if(state[1][0]==state[1][1] &&  state[1][0]==state[1][2] && state[1][1]!=0){
		w=state[1][0];
		return w;}
	if(state[2][0]==state[2][1] &&  state[2][0]==state[2][2]  && state[2][2]!=0){
		w= state[2][0];
		return w;}
	if(state[0][0]==state[1][0] &&  state[0][0]==state[2][0] && state[2][0]!=0){
		w=state[0][0];
		return w;}
	if(state[0][1]==state[1][1] &&  state[0][1]==state[2][1]  && state[2][1]!=0){
		w=state[0][1];
		return w;}
	if(state[0][2]==state[1][2] &&  state[0][2]==state[2][2]  && state[2][2]!=0){
		w= state[0][2];
		return w;}
	if(state[0][0]==state[1][1] &&  state[0][0]==state[2][2] && state[2][2]!=0){
		w=state[0][0];
		return w;}
	if(state[0][2]==state[1][1] &&  state[0][2]==state[2][0]  && state[2][0]!=0){
		w=state[0][2];
		return w;}
	return 0;

}


public void clear()
{
	this.grid=new int[3][3];
	this.grids1=new LinkedList<int[][]>();
	this.grids2=new LinkedList<int[][]>();
	this.winner=0;
	this.nummoves=0;

}

public int[][] getGrid()
{
	return this.grid;
}

public void printBoard(int[][] g)
{
	for(int i=0; i<3; i++){
		for(int j=0; j<3; j++)
		{
			if (g[i][j]==1)
				System.out.print(" |X| ");
			else if (g[i][j]==2)
				System.out.print(" |O| ");
			else if (g[i][j]==0)
				System.out.print(" | | ");
		}
		System.out.println("\n---------------");
	}


}


}
