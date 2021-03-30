package xyz.micrusa.sudoku;

public class Solver {
    private static int[][] board = new int[][]{
            new int[]{7,0,0,0,0,0,0,6,0},
            new int[]{1,2,0,0,0,0,0,0,0},
            new int[]{0,0,0,2,8,0,3,1,0},
            new int[]{0,4,0,6,0,0,8,0,0},
            new int[]{9,0,5,0,0,4,2,0,0},
            new int[]{0,0,0,0,0,0,0,0,0},
            new int[]{0,5,0,0,4,0,0,0,0},
            new int[]{0,0,0,0,7,5,0,0,0},
            new int[]{0,7,0,3,0,0,4,0,8}
    };

    public static int[][] solve(){
        loop(0, 0);
        return board;
    }

    private static boolean loop(int i, int j){
        if(j == board[0].length){
            i++;
            j = 0;
        }
        if(i != board.length){
            if(board[i][j] == 0){
                for(int k = 1; k <= 9; k++){
                    board[i][j] = k;
                    if(canBeValid(board, i, j) && (loop(i, j + 1))){
                        return true;
                    } else {
                        board[i][j] = 0;
                        if(k == 9)
                            return false;
                    }
                }
            } else return loop(i, j+1);
        }
        return true;
    }

    public static boolean isValid(int[][] board){
        //Check rows
        for(int i = 0; i < board.length; i++){
            boolean[] used = new boolean[9];
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == 0 || used[board[i][j]-1]) {
                    return false;
                }
                used[board[i][j]-1] = true;
            }
        }

        //Check columns
        for(int j = 0; j < board[0].length; j++){
            boolean[] used = new boolean[9];
            for(int i = 0; i < board.length; i++){
                if(board[i][j] == 0 || used[board[i][j]-1]) {
                    return false;
                }
                used[board[i][j]-1] = true;
            }
        }

        //Check 3x3 box
        for(int i = 0; i < board.length; i+=3){
            for(int j = 0; j < board[0].length; j+=3){
                boolean[] used = new boolean[9];
                for(int k = i; k < i+3; k++){
                    for(int l = j; l < j+3; l++){
                        if(board[k][l] == 0 || used[board[k][l]-1]) {
                            return false;
                        }
                        used[board[k][l]-1] = true;
                    }
                }
            }
        }
        return true;
    }

    public static boolean canBeValid(int[][] board, int i, int j){
        //Check rows
        boolean[] used = new boolean[9];
        for(int k = 0; k < board[i].length; k++){
            if(board[i][k] != 0 && used[board[i][k]-1]) return false;
            if(board[i][k] != 0) used[board[i][k]-1] = true;
        }

        //Check columns
        used = new boolean[9];
        for(int k = 0; k < board.length; k++){
            if(board[k][j] != 0 && used[board[k][j]-1]) return false;
            if(board[k][j] != 0) used[board[k][j]-1] = true;
        }

        //Check 3x3 box
        used = new boolean[9];
        for(int k = 0; k < board.length; k+=3){
            for(int l = 0; l < board[0].length; l+=3){
                if(k <= i && i < k+3 && l <= j && j < l+3){
                    for(int m = k; m < k+3; m++){
                        for(int n = l; n < l+3; n++){
                            if(board[m][n] != 0 && used[board[m][n]-1]) return false;
                            if(board[m][n] != 0) used[board[m][n]-1] = true;
                        }
                    }
                    break;
                }
            }
        }
        return true;
    }
}
