package com.practice.game.chessgame;

public class Game {
    State[][] states = new State[8][8];
    Player player1;
    Player player2;

    Game() {
        initialiseGame(PieceColour.BLACK, 0,1);
        initialiseGame(PieceColour.WHITE, 7,6);
    }
    int turn = 0;
    private void initialiseGame(PieceColour colour, int startRow, int endRow) {
        for(int y = 0;y<8;y++) {
            State state = new State(colour, Piece.PAWN);
            state.isOccupied = true;
            states[endRow][y] = state;
        }
    }

    public void startingPlayer(Player player) {
        if (player.pieceColour == PieceColour.WHITE) {
            turn ++;
        }
    }

    public GameStatus move(int x, int y, Piece piece) {
        PieceColour pieceColour = turn % 2 == 0 ? PieceColour.BLACK : PieceColour.WHITE;

        if(!isValidMove(x, y, piece, pieceColour)) {
            return GameStatus.INVALID_MOVE;
        }

        turn++;
        return GameStatus.CARRY_ON;
    }

    private boolean isValidMove(int x, int y, Piece piece, PieceColour pieceColour) {
        if(x < 0 || y < 0 || x > 7 || y > 7 || states[x][y].isOccupied) {
            return false;
        }
        return true;
    }
}
