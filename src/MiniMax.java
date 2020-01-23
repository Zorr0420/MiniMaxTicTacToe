import java.util.ArrayList;

public class MiniMax {

    public int minimax(Game g, int depth, State s){
        if(depth == 0||g.isOver())
            return g.getEvaluation();
        else if(s == State.player1){
            int max = 2;
        }

    }
    public ArrayList<Game> getChildren(Game g){
        ArrayList<Game> children = new ArrayList<Game>();


    }

}
