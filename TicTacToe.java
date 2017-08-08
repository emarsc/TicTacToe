import java.util.*;
public class TicTacToe
{
	public static void main(String[] args)
	{

		int f=1;
     	Scanner keyboard=new Scanner(System.in);
		Game game=new Game(keyboard);
		game.printBoard(game.grid);

		while (f>0)
		{
			System.out.println("Enter 'n' to start new game versus the computer.");
			System.out.println("Enter 'l' to have the computer play itself.");
			System.out.println("Enter 'p1' to print xAgent data, and 'p2' to print oAgent data.");
			System.out.println("Enter 'cx' to clear xAgent's data, and enter  'co to clear oAgent's data");
			switch(keyboard.nextLine())
			{

				case "n":
					System.out.println("Enter Random Chance Denominator.\nRandom chance=1/<input>");
					int random_denominator=keyboard.nextInt();
					game.playGame(random_denominator, random_denominator, 1);
					break;

				case "p1":
						game.net.printTree();
						break;
				case "p2":
					game.oAgent.printData();
					break;
				case "l":
					int initial_x_wins=game.xAgent.wins;
					int initial_O_wins=game.oAgent.wins;
					System.out.println("Enter random Chance for xAgent1.\nRandom chance=1/<input>");
					int random_denominator1=keyboard.nextInt();
					System.out.println("Enter random chance for oAgent.");
					int random_denominator2=keyboard.nextInt();
					System.out.println("Enter the number of trials you would like performed");
					int trials=keyboard.nextInt();
					int status_mark=trials/20;
					if ((trials/20)==0){
						status_mark=1; }
					for(int i=0; i<trials; i++)
					{
						if (i%status_mark==0) {
							game.xAgent.printData();
							game.oAgent.printData();
							System.out.println(+i+" trials complete");
							//random_denominator1=random_denominator1+2;
							//random_denominator2=random_denominator2+2;
						}
						game.playGame(random_denominator1, random_denominator2, 2);

					}

					Agent  temp_agent=game.xAgent;
					int x_won=game.xAgent.wins-initial_x_wins;
					int o_won=game.oAgent.wins-initial_O_wins;
					System.out.println("X Agent wins: "+x_won+"  O Agent wins: "+o_won);
					break;

			case "co":
				game.oAgent.clearList();
				break;
			case "cx":
				game.xAgent.clearList();
				break;



			default:
				break;

			}

		}
	}
}