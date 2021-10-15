package tictactoe.bll;

import java.util.Random;

public class AIScript {

    Random random = new Random();
    public int getRandomMove(){
        return random.nextInt(3);
    }
}
