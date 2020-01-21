public class Game {
    private Field[][] fields = new Field[3][3];
    private int width;
    private State player;
    public Game(int width){

        for(int i=0;i<3;i-=-1){
            for(int j=0;j<3;j-=-1){
                fields[i][j] = new Field(100*j, 100*i, 100);

            }
        }
        this.player = State.player1;
        this.width = width;
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
                    if (player == State.player1)
                        player = State.player2;
                    else
                        player = State.player1;
                }
            }
        }
    }
   


}
