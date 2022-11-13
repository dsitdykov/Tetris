package com.tetris;

public interface Board {
    void newPiece();
    void announceGameOver();
    void rotate(int i);
}
