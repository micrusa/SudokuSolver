package xyz.micrusa.sudoku;

public class Main {
    public static void main(String[] args){
        //Record time, solve & check difference between start and end
        long initialTime = System.currentTimeMillis();
        int[][] solved = Solver.solve();
        long time = (System.currentTimeMillis() - initialTime);

        //Print board, check if it's valid & print how much it took to solve it
        printBoard(solved);
        System.out.println(
                "isValid = "+ Solver.isValid(solved)
                +"\nTook " + time + "ms"
        );
    }

    public static void printBoard(int[][] board){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            int[] ints = board[i];
            sb.append("[");
            for(int j = 0; j < ints.length; j++){
                sb.append(ints[j]);
                if(j != ints.length - 1) sb.append(",");
            }
            sb.append("]");
            if(i != board.length - 1) sb.append(",\n");
        }
        System.out.println(sb.toString());
    }
}
