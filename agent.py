class Human:

	def getNextMove(self, board, player, legal_moves):
		row=input('Enter Row: ')
		col=input('Enter Column: ')
		return (int(row), int(col))
		
class AI:
	games=[]
	game_moves=[]
	explored_edges=[]
	edges={}
	game_edges=[]
	def buildNetwork(self):
		one_edges=['01', '02', '10', '20', '11', '22']
		mid_edges=['22', '02', '20', '10', '12', '01', '21']
		right_corner=['21', '20', '12', '02']
		left_corner=['10', '02', '21']
		mid_top=['21', '02']
		mid_right=['10', '02']
		inputs=[' ', 'x', 'o']
		for input in inputs:
			for input2 in inputs:
				for edge in one_edges:
					string='00'+str(edge)+input+input2
					self.edges[string]=0
				for edge in mid_edges:
					string='11'+str(edge)+input+input2
					self.edges[string]=0
				for edge in right_corner:
					string='02'+str(edge)+input+input2
					self.edges[string]=0
				for edge in left_corner:
					string='20'+str(edge)+input+input2
					self.edges[string]=0
				for edge in mid_top:
					string='01'+str(edge)+input+input2
					self.edges[string]=0
				for edge in mid_right:
					string='12'+str(edge)+input+input2
					self.edges[string]=0
									
		
					
	def parseBoard(self, board):
		board_array=[]
		for row in board:
			for col in row:
				board_array.append(col)
		return board_array
		
	def recordResult(self, board, result):
		weight=0
		possible=list(self.edges.keys())
		
		if result=='x':
			weight=1
		elif result=='o':
			weight=-1
		#self.games.append(self.game_moves, weight)
		del self.game_moves[:]
		#print(self.game_edges)
		for edge in self.game_edges:
			self.edges[edge]=self.edges[edge]+weight
		del self.game_edges[:]

		"""for i in range(0, 3):
			for j in range(0, 3):
				for k in range(0, 3):
					for l in range(0, 3):
						string=str(i)+str(j)+str(k)+str(l)+board[i][j]+board[k][l]
						if string in possible:
							self.edges[string]=self.edges[string]+weight"""
		
	def getNextMove(self, board, player, legal_moves):
		from random import randint
		if randint(0, 20)==15:
			random_index=randint(0, len(legal_moves)-1)
			self.game_moves.append(str(legal_moves[random_index][0])+str(legal_moves[random_index][1])+player)
			return legal_moves[random_index]
		max_move=legal_moves[0]
		max_util=0
		max_edges=[]
		sign=1
		string=''
		if player=='o':
			sign=-1
		for move in legal_moves:
			temp_edges=[]
			temp_util=0
			for i in range(0, 3):
				for j in range(0, 3):
					string=str(move[0])+str(move[1])+str(i)+str(j)+player+board[i][j]
					if string in list(self.edges.keys()):
						temp_edges.append(string)						
						if string not in self.explored_edges:
								temp_util=temp_util+20
								self.explored_edges.append(string)
						else:
							temp_util=temp_util+(self.edges[string])*sign
			if temp_util>max_util:
				max_edges=temp_edges
				max_util=temp_util
				max_move=move
		self.game_edges.extend(max_edges)	
				
		return max_move
			
			
			
		
		
		
			
