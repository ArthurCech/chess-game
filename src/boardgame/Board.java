package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}

		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns]; // inicializar tabuleiro com tudo null
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	// retornar uma peca do tabuleiro da posicao especificada
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}

		return pieces[row][column];
	}

	// retornar uma peca do tabuleiro da posicao especificada
	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}

		return pieces[position.getRow()][position.getColumn()];
	}

	// posicionar uma peca no tabuleiro
	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}

		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	// remover uma peca do tabuleiro
	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}

		if (piece(position) == null) {
			return null;
		}

		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;

		return aux;
	}

	// verificar se a posicao informada existe no tabuleiro
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	// verificar se a posicao informada existe no tabuleiro
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	// verificar se existe uma peca no tabuleiro na posicao informada
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}

		return piece(position) != null;
	}

}
