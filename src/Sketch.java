import processing.core.PApplet;

public class Sketch extends PApplet {
    public static Sketch s;
    private  Game g = new Game(300);
    public void settings(){
        size(300,300);
        System.out.println(MiniMax.minimax(g,9,State.player1));

        MiniMax.bestMove(g);


        s = this;
    }
    public void draw(){
        g.draw(s);
    }
    public static void main(String[] args) {
        PApplet.main("Sketch");

    }
    public void mouseClicked(){
        g.decision(this.mouseX, this.mouseY);
    }
}
