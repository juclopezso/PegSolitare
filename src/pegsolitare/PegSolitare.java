package pegsolitare;

import controller.Controller;

public class PegSolitare {
    public static void main(String[] args) {        
        Controller controller = new Controller();

        while (!controller.isGameOver()) {
            try {
                Thread.sleep(200);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        
        controller.informOutcome();
    }    
}