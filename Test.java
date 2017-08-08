import java.util.*;

public class Test
{
	public static void main(String[] args)
	{
		Network n=new Network();
		n.buildNetwork();
		n.printTree();
		Scanner input=new Scanner(System.in);
		while(true)
		{
		switch(input.nextLine())
		{
			case "t":
				int num1=input.nextInt();
				System.out.println("+");
				int num2=input.nextInt();
				int sum=num1+num2;
				sum=sum%10;
				System.out.println("correct sum: "+sum);
				int[] netIn=new int[9];
				netIn[0]=num1%10;
				netIn[1]=num2%10;
				n.giveInput(netIn);
				int computed_sum=n.getOutput();
				System.out.println("Net sum: "+computed_sum);
				if(computed_sum != sum)
				{
					System.out.println("FAIL");
					n.giveFeedback(0);
				}
				else
				{
					System.out.println("PASS");
					n.giveFeedback(1);
				}
				n.printTree();
				break;
			case "l":
				System.out.println("enter number of trials");
				int feed=0;
				int trials=input.nextInt();

				for(int i=0; i<trials; i++)
				{
					int x=(int) (Math.random()*9);
					int y=(int) (Math.random()*(9-x));
					int[] tempIn=new int[9]
					//tempIn[5]=x;
					//tempIn[8]=y;
					int temp_sum=x+y;
					for(int j=0; j<temp_sum; j++)
						tempIn[j]=1;
					n.giveInput(tempIn);
					int net_sum=n.getOutput();
					int diff=temp_sum-net_sum;
					if(net_sum==temp_sum)
					{
						feed=feed+1;
						n.giveFeedback(12);
					}
					else
					{
						diff=0-diff;
						n.giveFeedback(diff);
					}
				}
				System.out.println("Total success: "+feed);
				n.printTree();
				break;

			default:
				break;
			}

		}
	}

}