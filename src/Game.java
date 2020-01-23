public class Game {
    private Field[][] fields = new Field[3][3];
    private int width;
    private State player;
    private int value;
    private int occupiedFields=0;
    private boolean over = false;
    public Game(int width){

        for(int i=0;i<3;i-=-1){
            for(int j=0;j<3;j-=-1){
                fields[i][j] = new Field(100*j, 100*i, 100);

            }
        }
        this.player = State.player1;
        this.width = width;
    }
    public Game doMove(){

    }
    public void evaluate(){
        if(player == State.player1 && checkForWin()) {
            value = 1;
            over=true;
        }
        else if(player == State.player2 && checkForWin()) {
            value = -1;
            over=true;
        }
        else if(occupiedFields==9) {
            value = 0;
            over=true;
        }
    }
    public int getEvaluation(){
        return value;
    }
    public boolean isOver(){
        return over;
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
    public void decision(int x, int y){

        for(Field[] ff:fields){
            for(Field f:ff) {
                if (f.inRange(x, y)) {
                    f.setState(player);
                    occupiedFields++;
                    if(checkForWin())
                        System.out.println(player+" wins");
                    if (player == State.player1)
                        player = State.player2;
                    else
                        player = State.player1;
                }
            }
        }


    }
    private boolean checkForWin(){
        return checkDiag()||checkVerti()||checkHori();
    }
    private boolean checkHori(){
        int horizontal = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(fields[i][j].getState() == player){
                    horizontal++;
                }
            }
            if(horizontal == 3)
                return true;
            horizontal =0;
        }
        return false;
    }
    private boolean checkVerti(){
        int vertical = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(fields[j][i].getState() == player){
                    vertical++;
                }
            }
            if(vertical == 3)
                return true;
            vertical =0;
        }
        return false;
    }
    private boolean checkDiag(){
        if(fields[0][0].getState() == player&&fields[1][1].getState() == player&&fields[2][2].getState() == player)
            return true;
        else if(fields[0][2].getState() == player&&fields[1][1].getState() == player&&fields[2][0].getState() == player)
            return true;
        return false;
    }
}
