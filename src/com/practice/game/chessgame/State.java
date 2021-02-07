package com.practice.game.chessgame;

public class State {
    PieceColour color;
    Piece piece;
    boolean isOccupied;
    State(PieceColour color, Piece piece) {
        this.color = color;
        this.isOccupied = false;
        this.piece = piece;
    }
}
