class Game:

	board=[]
	board.append([' ', ' ', ' '])
	board.append([' ', ' ', ' '])
	board.append([' ', ' ', ' '])
	next_move='x'
	moves=0
	isOver=0
	winner=' '
	
	def newBoard(self):
		self.board[0]=[' ', ' ', ' ']
		self.board[1]=[' ', ' ', ' ']
		self.board[2]=[' ', ' ', ' ']
		self.winner=' '
		self.isOver=0
		self.moves=0
		"""if self.next_move=='x':
			self.next_move='o'
		else:
			self.next_move='x'"""
	
	def printBoard(self):
		for row in self.board:
			print(row[0]+" | "+row[1]+" | "+row[2])
		
	def insertValue(self, row, col, value):
		self.board[row][col]=value
		
	def checkWinner(self):
		if self.board[0][0]==self.board[0][1]==self.board[0][2]:
			return self.board[0][0]
		if self.board[0][0]==self.board[1][0]==self.board[2][0]:
			return self.board[0][0]
		if self.board[0][0]==self.board[1][1]==self.board[2][2]:
			return self.board[0][0]
		if self.board[0][1]==self.board[1][1]==self.board[2][1]:
			return self.board[0][1]
		if self.board[0][2]==self.board[1][2]==self.board[2][2]:
			return self.board[0][2]
		if self.board[0][2]==self.board[1][1]==self.board[2][0]:
			return self.board[0][2]
		if self.board[1][0]==self.board[1][1]==self.board[1][2]:
			return self.board[1][0]
		if self.board[2][0]==self.board[2][1]==self.board[2][2]:
			return self.board[2][0]

		return ' '
		
	def getLegalMoves(self):
		legal_moves=[]
		for i in range(0, 3):
			for j in range(0, 3):
				if self.board[i][j]==' ':
					legal_moves.append((i, j))
		return legal_moves
		
		
	def nextMove(self, row, col):
		if self.board[row][col]!=' ':
			print('ivalid move')
			return
		self.moves+=1
		self.insertValue(row, col, self.next_move)
		if self.next_move=='x':
			self.next_move='o'
		else:
			self.next_move='x'
		self.winner=self.checkWinner()
		if (self.winner!=' ') or self.moves==9:
			self.isOver=1
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	