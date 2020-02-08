import java.util.ArrayList;
public class Game {
    private Field[][] fields = new Field[3][3];
    private int width;
    private int value;
    private State player;
    private ArrayList<Field> freeFields = new ArrayList<Field>();
    private boolean over = false;
    public Game(int width){

        for(int i=0;i<3;i-=-1){
            for(int j=0;j<3;j-=-1){
                fields[i][j] = new Field(100*j, 100*i, 100);
                freeFields.add(fields[i][j]);
            }
        }
        this.player = State.player1;
        this.width = width;
    }

    public void evaluate(){
        if(player == State.player1 && checkForWin()) {
            value = 1;
            player = State.player2;
        }
        else if(player == State.player2 && checkForWin()) {
             value = -1;
            player = State.player1;
        }
        else if(checkForTie()){
            value = 0;
        }
    }

    public Field[][] getFields(){
        return fields;
    }

    public int getEvaluation(){
        evaluate();
        return value;
    }
    public boolean isOver(){
        over = checkForTie()||checkForWin();
        return checkForTie()||checkForWin();
    }
    public void draw(Sketch s){
        for(Field[] ff:fields){
            for(Field f:ff){
                f.draw(s);
            }
        }

        s.line(100, 0, 100, width);
        s.line(200,0,200,width);
        s.line(0,100,width,100);
        s.line(0,200,width,200);
    }
    public void setPlayer(State s){
        this.player = s;
    }
    public void setField(int i, int j, State s){
        fields[i][j].setState(s);
        over = checkForTie()||checkForWin();
    }
    public void decision(int x, int y) {
        player = State.player1;
        if (!isOver()) {
            player=State.player2;
            for (Field[] ff : fields) {
                for (Field f : ff) {
                    if (f.inRange(x, y)) {
                        f.setState(player);
                        if(checkForWin())
                            System.out.println(player+" wins");
                        else if(checkForTie())
                            System.out.println("Tie");
                        player = State.player1;
                    }
                }
            }
            MiniMax.bestMove(this);




        }
    }
    public void setOver(boolean over){
        this.over = over;
    }
    public boolean checkForWin(){

        over = checkDiag()||checkVerti()||checkHori();
        return checkDiag()||checkVerti()||checkHori();
    }
    public boolean checkForTie(){
        int count = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(fields[i][j].getState() != State.empty)
                    count++;
            }
        }
        over = count == 9;
        return count == 9;
    }
    private boolean checkHori(){
        int horizontal = 0;
        for(int i=0;i<3;i++){
            if(fields[i][1].getState() == fields[i][0].getState() && fields[i][2].getState() == fields[i][0].getState()&&(fields[i][0].getState() != State.empty)){
                player =fields[i][0].getState();
                return true;
            }
        }
        return false;
    }
    private boolean checkVerti(){
        int vertical = 0;
        for(int i=0;i<3;i++){
            if(fields[1][i].getState() == fields[0][i].getState() && fields[2][i].getState() == fields[0][i].getState()&&(fields[0][i].getState() != State.empty)){
                player =fields[0][i].getState();
                return true;
            }
        }
        return false;
    }
    private boolean checkDiag(){
        if(fields[0][0].getState() == fields[1][1].getState() && fields[2][2].getState() == fields[1][1].getState()||(fields[0][2].getState() == fields[1][1].getState() && fields[2][0].getState() == fields[1][1].getState())&&(fields[1][1].getState()!=State.empty)){
            player = fields[1][1].getState();
            return true;
        }
        return false;
    }
}
