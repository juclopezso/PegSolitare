package view;

import model.Field.Symbol;

import javax.swing.JButton;

public interface ViewInterface
{
    /**
     * Changes a field to a user symbol.
     * 
     * @param symbol    the symbol of the current player.
     * @param button    the button that was clicked.
     */
    public void updateBoard(Symbol owner, JButton button);
    
    /**
     * Informs the user who won.
     * 
     * @param symbol    the symbol of the current player (=> winner).
     */
    public void informWin(Symbol userSymbol);
    
    /**
     * Informs the user of the tie.
     */
    public void informTie();
}
