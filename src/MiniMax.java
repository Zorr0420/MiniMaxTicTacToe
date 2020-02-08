import java.util.ArrayList;

public class MiniMax {

    private static int minimax(Game g, int depth, State player){
        if(g.isOver()||depth == 0)
            return g.getEvaluation();
        else if(player == State.player1){
            int max = -10000;
            for(int i=0;i<3;i++) {
                for(int j=0;j<3;j++) {
                    Field[][] board = g.getFields();
                    if(board[i][j].getState() == State.empty) {
                        g.setField(i,j,player);
                        int eval = minimax(g, depth -1, State.player2);
                        g.setField(i,j,State.empty);
                        max = Math.max(max, eval);
                    }
                }
            }

            return max;
        }
        else{
            int min = 10000;
            for(int i=0;i<3;i++) {
                for(int j=0;j<3;j++) {
                    Field[][] board = g.getFields();
                    if(board[i][j].getState() == State.empty) {
                        g.setField(i,j,player);
                        int eval = minimax(g, depth -1, State.player1);
                        g.setField(i,j,State.empty);
                        min = Math.min(min, eval);
                    }
                }
            }
            return min;
        }

    }
    public static void bestMove(Game game){
        int bestScore = -2;
        int finalI =0;
        int finalJ=0;
        Field [][] ff = game.getFields();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){

                if(ff[i][j].getState() == State.empty){
                    game.setField(i,j,State.player1);
                    int score = minimax(game, 100, State.player2);
                    game.setField(i,j,State.empty);
                    if(score > bestScore){
                        bestScore = score;
                        finalI = i;
                        finalJ = j;
                    }

                }
            }
        }
        ff[finalI][finalJ].setState(State.player1);
        if(game.checkForWin()) {
            game.setOver(true);
            System.out.println("A.I. wins");
        }
        else if(game.checkForTie()){
            System.out.println("Tie");
        }
        game.setPlayer(State.player2);


    }

}
