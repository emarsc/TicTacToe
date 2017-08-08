import game
from game import Game
from agent import Human
from agent import AI
from random import randint

game=Game()
human=Human()
ai=AI()
ai.buildNetwork()
print(ai.edges)
while True:	
	game_type=int(input("input game type"))
	player_list=[]
	if game_type==1:
		player_list=[human, human]
	elif game_type==2:
		player_list=[human, ai]
	elif game_type==3:
		player_list=[ai, ai]
		
	num_games=input("input number of games")
	i=0	
	while i<int(num_games):
		game.newBoard()
		player_turn=randint(0, 1)
		while game.isOver==0:
			if game_type!=3:
				game.printBoard()
			legal_moves=game.getLegalMoves()
			move=player_list[player_turn%2].getNextMove(game.board, game.next_move, legal_moves)
			game.nextMove(move[0], move[1])
			player_turn=player_turn+1
		ai.recordResult(game.board, game.winner)

		i=i+1
	print(ai.edges)
	print(str(len(ai.explored_edges))+" explored edges out of "+str(len(list(ai.edges.keys())))+ "possible edges")
	
		
		
