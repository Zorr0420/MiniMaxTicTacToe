public class Field {
    private int x;
    private int y;
    private int width;
    private boolean occupied;
    private State state;
    public Field(int x, int y, int width){
        this.x = x;
        this.y = y;
        this.width = width;
        this.state = State.empty;
    }
    public void draw(Sketch s){
        s.fill(255);
        s.rect(x,y,width,width);

        if(state == State.player1){
            s.fill(0);
            s.line(x+10, y+10, x+width-10, y+width-10);
            s.line(x+width-10, y+10, x+10, y+width-10);
        }else if(state == State.player2){
            s.noFill();
            s.ellipse(x+(this.width/2), y+(this.width/2), width-10, width-10);
        }

    }
    public boolean inRange(int x, int y){
        if((x>=this.x&&y>=this.y)&&(x<=this.x+width&&y<=this.y+width)&&state==State.empty)
            return true;
        return false;
    }
    public State getState(){
        return state;
    }
    public void setState(State s){
        this.state=s;
        this.occupied = true;
    }





}

