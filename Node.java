import java.util.*;


public class Node
{
	Node child;
	int[] dial;
	int dialIndex;
	int value;
	int num;
	LinkedList<Integer> inputs;
	int depth;
	int out;
	int in1;
	int in2;
	int input_num;
	int feedback_count;
	int random;
	int eval;
	int[][] inOut;
	boolean children;
	public Node(int d)
	{
		feedback_count=0;
		input_num=0;
		children=false;
		inOut=new int[27][27];
		random=1000;
		depth=d;
		eval=0;

		inputs=new LinkedList<Integer>();
		out=0;
		in1=0;
		in2=0;
	}

	public void addChild(Node c)
	{
		this.child=c;
		children=true;

	}

	public void input(int k)
	{
		input_num++;
		inputs.push(k);
		if(input_num==1)
			in1=k;

		else if(input_num==2)
		{
			in2=k;
			input_num=0;
			this.output();
		}
	}

	private void output()
	{
		int max=-9999;
		//this.inOut[in1][in2]=10;
		this.eval=inOut[in1][in2];
		/*if((Math.random()*this.random)==0)
		{
			int r=(int) (10-Math.random()*20);
			//System.out.println("random"+r);
			eval=r;
		} */
		//System.out.println("out "+depth+" : "+this.out+" MAX: "+max);
		if (children==true)
		{
		//System.out.println(this.child.depth);
		//Node n=this.child;
		child.input(this.eval);
		}
	}

	public void feedback(int f)
	{
		feedback_count++;
		while(!this.inputs.isEmpty())
		{
		int t2=inputs.pop();
		int t1=inputs.pop();
		this.inOut[t1][t2]=this.inOut[t1][t2]+f;
		if (this.inOut[t1][t2]>26)
			this.inOut[t1][t2]=26;
		if (this.inOut[t1][t2]<0)
			this.inOut[t1][t2]=0;
		if (children==true)
		{
			feedback_count=0;
			this.child.feedback(f);
		}
		}
	}





}