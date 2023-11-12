//package com.tetris;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Point;
//import java.util.ArrayList;
//import java.util.Collections;
//
//import javax.swing.*;
//
//public class Tetris extends JPanel implements Board {
//    private final Color[] tetrisColors = {
//            Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.magenta, Color.red
//    };
//    private Point pieceOrigin;
//    private int currentPiece;
//    private int rotation;
//    private ArrayList<Integer> nextPieces = new ArrayList<Integer>();
//    private Color[][] board;
//
//    public Tetris() {
//        board = new Color[12][24];
//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 23; j++) {
//                if (i == 0 || i == 11 || j == 22) {
//                    board[i][j] = Color.DARK_GRAY;
//                } else {
//                    board[i][j] = Color.BLACK;
//                }
//            }
//        }
//        newPiece();
//    }
//
//    @Override
//    public void announceGameOver() {
//        Main.gameover = true;
//        JOptionPane.showMessageDialog(null, "Game Over! \n Your score is: " + Main.score, "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    @Override
//    public void rotate(int i) {
//        int newRotation = (rotation + i) % 4;
//        if (newRotation < 0) {
//            newRotation = 3;
//        }
//        if (!willCollide(pieceOrigin.x, pieceOrigin.y, newRotation)) {
//            rotation = newRotation;
//        }
//        repaint();
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        g.setColor(Color.DARK_GRAY);
//        g.fillRect(0, 0, 26 * 12, 26 * 23);
//        g.setColor(Color.BLACK);
//        g.fillRect(26, 0, 26 * 10, 26 * 22);
//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 23; j++) {
//                g.setColor(board[i][j]);
//                g.fillRect(26 * i, 26 * j, 25, 25);
//            }
//        }
//
//        g.setColor(Color.WHITE);
//        g.drawString("" + Main.score, 21 * 12, 25);
//
//        drawPiece(g);
//    }
//
//    @Override
//    public void newPiece() {
//        if (Main.gameover) return;
//        pieceOrigin = new Point(5, 0);
//        rotation = 0;
//        if (willCollide(pieceOrigin.x, pieceOrigin.y, rotation)) {
//            announceGameOver();
//            return;
//        }
//        if (nextPieces.isEmpty()) {
//            Collections.addAll(nextPieces, 0, 1, 2, 3, 4, 5, 6);
//            Collections.shuffle(nextPieces);
//        }
//        currentPiece = nextPieces.get(0);
//        nextPieces.remove(0);
//    }
//
//    private boolean willCollide(int x, int y, int rotation) {
//        for (Point p : Shape.Tetraminos[currentPiece][rotation]) {
//            if (board[p.x + x][p.y + y] != Color.BLACK) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void move(int i) {
//        if (!willCollide(pieceOrigin.x + i, pieceOrigin.y, rotation)) {
//            pieceOrigin.x += i;
//        }
//        repaint();
//    }
//
//    public void dropDown() {
//        if (!willCollide(pieceOrigin.x, pieceOrigin.y + 1, rotation)) {
//            pieceOrigin.y += 1;
//        } else {
//            fixToBoard();
//        }
//        repaint();
//    }
//
//    public void fixToBoard() {
//        for (Point p : Shape.Tetraminos[currentPiece][rotation]) {
//            board[pieceOrigin.x + p.x][pieceOrigin.y + p.y] = tetrisColors[currentPiece];
//        }
//        scanRows();
//        newPiece();
//    }
//
//    public void deleteRow(int row) {
//        for (int j = row - 1; j > 0; j--) {
//            for (int i = 1; i < 11; i++) {
//                board[i][j + 1] = board[i][j];
//            }
//        }
//    }
//
//    public void scanRows() {
//        boolean gap;
//        int rowCount = 0;
//
//        for (int j = 21; j > 0; j--) {
//            gap = false;
//            for (int i = 1; i < 11; i++) {
//                if (board[i][j] == Color.BLACK) {
//                    gap = true;
//                    break;
//                }
//            }
//            if (!gap) {
//                deleteRow(j);
//                j += 1;
//                rowCount += 1;
//            }
//        }
//
//        switch (rowCount) {
//            case 1:
//                Main.score += 100;
//                break;
//            case 2:
//                Main.score += 300;
//                break;
//            case 3:
//                Main.score += 500;
//                break;
//            case 4:
//                Main.score += 800;
//                break;
//        }
//    }
//
//    private void drawPiece(Graphics g) {
//        g.setColor(tetrisColors[currentPiece]);
//        for (Point p : Shape.Tetraminos[currentPiece][rotation]) {
//            g.fillRect((p.x + pieceOrigin.x) * 26,
//                    (p.y + pieceOrigin.y) * 26,
//                    25, 25);
//        }
//    }
//}