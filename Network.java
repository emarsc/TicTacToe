import java.util.*;

public class Network

{
	int input;

	LinkedList<Node> nodes;
	Node output_node;
	LinkedList<Integer> keys;
	public Network()
	{
		output_node=new Node(4);
		keys=new LinkedList<Integer>();
		this.genKeys();
		this.nodes=new LinkedList<Node>();
	}

	private void genKeys()
	{
		String string_key;
		for(int i=0; i<=2; i++)
			for (int j=0; j<=2; j++)
				for (int q=0; q<=2; q++)
				{
					int key=i*100+j*10+q;
					keys.push(key);
				}
	}


	public void buildNetwork()
	{
		int num_children=8;


		nodes.push(this.output_node);
		int depth=4;
		while (depth>1)
		{
			depth--;
			LinkedList<Node> tempList=new LinkedList<Node>();
			System.out.println("test");
			while(!this.nodes.isEmpty())
			{
				Node child_node=nodes.pop();
				for(int i=0; i<2; i++)
				{
				Node temp_node=new Node(depth);
				temp_node.addChild(child_node);
				tempList.push(temp_node);
				}

			}
			this.nodes=tempList;

		}

	}

	public LinkedList<Integer> parseDigits(int[][] d)
	{
		int key=0;
		int[] d_array=new int[3];
		LinkedList<Integer> digits=new LinkedList<Integer>();

		d_array[0]=d[0][0];
		d_array[1]=d[0][1];
		d_array[2]=d[0][2];
		key=d_array[0]*100+d_array[1]*10+d_array[2];
		digits.push(keys.indexOf(key));

		d_array=new int[3];
		d_array[0]=d[0][0];
		d_array[1]=d[1][0];
		d_array[2]=d[2][0];
		key=d_array[0]*100+d_array[1]*10+d_array[2];
		digits.push(keys.indexOf(key));

		d_array=new int[3];
		d_array[0]=d[0][0];
		d_array[1]=d[1][1];
		d_array[2]=d[2][2];
		key=d_array[0]*100+d_array[1]*10+d_array[2];
		digits.push(keys.indexOf(key));

		d_array=new int[3];
		d_array[0]=d[0][1];
		d_array[1]=d[1][1];
		d_array[2]=d[2][1];
		key=d_array[0]*100+d_array[1]*10+d_array[2];
		digits.push(keys.indexOf(key));

		d_array=new int[3];
		d_array[0]=d[0][2];
		d_array[1]=d[1][1];
		d_array[2]=d[2][0];
		key=d_array[0]*100+d_array[1]*10+d_array[2];
		digits.push(keys.indexOf(key));

		d_array=new int[3];
		d_array[0]=d[0][2];
		d_array[1]=d[1][2];
		d_array[2]=d[2][2];
		key=d_array[0]*100+d_array[1]*10+d_array[2];
		digits.push(keys.indexOf(key));

		d_array=new int[3];
		d_array[0]=d[1][0];
		d_array[1]=d[1][1];
		d_array[2]=d[1][2];
		key=d_array[0]*100+d_array[1]*10+d_array[2];
		digits.push(keys.indexOf(key));

		d_array=new int[3];
		d_array[0]=d[2][0];
		d_array[1]=d[2][1];
		d_array[2]=d[2][2];
		key=d_array[0]*100+d_array[1]*10+d_array[2];
		digits.push(keys.indexOf(key));
		return digits;

	}

	public void giveInput(int[][] in)
	{
		LinkedList<Integer> digits=this.parseDigits(in);
		digits.addAll(digits);
		int t=0;
		while (t<2)
		{
		for(int i=0; i<this.nodes.size(); i++){
			nodes.get(i).input(digits.pop());
		}
		t++;
	}
	}

	private int[] getOutput()
	{
		int[] out_move=new int[3];
		int k= keys.get(output_node.out);
		out_move[2]=k%10;
		k=k/10;
		out_move[1]=k%10;
		k=k/10;
		out_move[0]=k;
		return out_move;
	}

	public int[] getNextMove(LinkedList<int[]> moves, int[][] grid)
	{
		int[] move=new int[2];
		int evaluation=0;
		for (int i=0; i<moves.size(); i++)
		{
			int[] temp_move=new int[2];
			temp_move=moves.get(i);
			grid[temp_move[0]][temp_move[1]]=1;
			this.giveInput(grid);
			//System.out.println("output eval "+output_node.eval);
			if (output_node.eval>evaluation)
			{
				evaluation=output_node.eval;
				move=temp_move;
			}
			grid[temp_move[0]][temp_move[1]]=0;
		}
		if (evaluation==0)
		{
			int rand=(int)(Math.random()*moves.size());
			move=moves.get(rand);
		}
		//System.out.println("Eval: "+evaluation);
		return move;

	}

	public void giveFeedback(int f)
	{
		int t=0;
		for (int i=0; i<nodes.size(); i++)
			nodes.get(i).feedback(f);

	}

	public void printTree()
	{
		LinkedList<Node> tempNodes= new LinkedList<Node>();
		tempNodes.addAll(nodes);
		int i=0;
		int depth=1;
		while(depth<4)
		{
			i=i+1;
			Node n=tempNodes.pop();
			if (i%2==0)
			{
				tempNodes.add(n.child);
			}
			if (n.depth>depth)
			{
				depth=depth+1;
				System.out.println();
			}
			System.out.print(n.depth);
		}
	}



}