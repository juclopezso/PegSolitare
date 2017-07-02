package pegsolitare;

import controller.Controller;

public class PegSolitare {
    public static void main(String[] args) {        
        Controller controller = new Controller();

        while (!controller.isGameOver()) {
            try {
            	
                Thread.sleep(200);
                for(int y=0; y<7 ;y++){
        			for(int x=0; x<7 ; x++) {
        				controller.getView().updateBoard(controller.getGame().getBoard().getFieldOwner(x, y),controller.getView().getButton(x, y));
        			}
        		}	
                
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        
        controller.informOutcome();
    }    
}