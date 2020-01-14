package geekBrains.java1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final char DOT_EMPTY = '.';
    public static char DOT_X = 'X';
    public static char DOT_O = 'O';
    public static int SIZE = 5;
    public static int DOTS_TO_WIN = 4;
    public static char[][] MAP;
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();


    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (isWin(DOT_X)) {
                System.out.println("YOU WIN!!!");
                break;
            }
            aiTurn();
            printMap();
            if (isWin(DOT_O)) {
                System.out.println("YOU LOSE =(");
                break;
            }
            ;

            if (isEndGame()) {
                System.out.println("GAME OVER");
                break;
            }

        }


    }

    public static void initMap() {
        MAP = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = DOT_EMPTY;

            }

        }
    }

    public static void printMap() {
        for (int i = 0; i <= MAP.length; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i = 0; i < MAP.length; i++) {
            System.out.print(i + 1 + "  ");
            for (int j = 0; j < MAP[i].length; j++) {
                System.out.print(MAP[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Enter coordinates X Y:");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (isValidTurn(x, y));

        System.out.println();
        System.out.println("Your turn is: " + (x + 1) + " " + (y + 1));

        MAP[y][x] = DOT_X;
    }


    public static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (isValidTurn(x, y));
        System.out.println();
        System.out.println("Ai turn is: " + (x + 1) + " " + (y + 1));

        MAP[y][x] = DOT_O;
    }

    public static boolean isValidTurn(int x, int y) {
        if (x <= SIZE & y <= SIZE & x >= 0 & y >= 0) {
            return MAP[y][x] != DOT_EMPTY;
        }
        return true;

    }

    public static boolean isWin(char playerSym) {
        String victory = "";
        String tempString = "";
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            victory += playerSym;
        }

        //horizontal
        for (char[] arr : MAP) {
            for (char sym : arr) {
                tempString += sym;
            }

            if (tempString.contains(victory)) return true;
            tempString = "";

        }

        //vertical
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tempString += MAP[j][i];
            }
            if (tempString.contains(victory)) return true;
            tempString = "";
        }

        // diagonal from right to left
        for (int k = 0; k < SIZE * 2; k++) {
            for (int j = 0; j <= k; j++) {
                int i = k - j;
                if (i < SIZE && j < SIZE) {
                    tempString += MAP[i][j];

                }
            }

            if (tempString.contains(victory)) return true;
            tempString = "";

        }

        // rotate the matrix
        char[][] mat = new char[SIZE][SIZE];
        for (int i = 0; i < MAP.length; i++) {
            System.arraycopy(MAP[i], 0, mat[i], 0, MAP[0].length);
        }


        for (int x = 0; x < SIZE / 2; x++) {
            for (int y = x; y < SIZE - x - 1; y++) {
                int temp = mat[x][y];
                mat[x][y] = mat[y][SIZE - 1 - x];
                mat[y][SIZE - 1 - x] = mat[SIZE - 1 - x][SIZE - 1 - y];
                mat[SIZE - 1 - x][SIZE - 1 - y] = mat[SIZE - 1 - y][x];
                mat[SIZE - 1 - y][x] = (char) temp;
            }
        }

        //diagonal from left to right
        for (int k = 0; k < SIZE * 2; k++) {
            for (int j = 0; j <= k; j++) {
                int i = k - j;
                if (i < SIZE && j < SIZE) {
                    tempString += mat[i][j];

                }
            }

            if (tempString.contains(victory)) return true;
            tempString = "";

        }


        return false;
    }

    public static boolean isEndGame() {
        for (char[] chars : MAP) {
            for (char aChar : chars) {
                if (aChar == DOT_EMPTY) return false;
            }
        }
        return true;

    }

}
