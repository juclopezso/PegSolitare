package view;

import model.Field.Symbol;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View extends JFrame implements ViewInterface {
    private final GridLayout grid;     
    private final JButton[][] buttons;   
	private ImageIcon none;
	
    public View() {
        super("Peg Solitare");
        grid = new GridLayout(7, 7);
        buttons = new JButton[7][7];
        
		none = new ImageIcon(this.getClass().getResource("icons/none.png"));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(getContentPane());
        pack();
        setVisible(true);

    }

    public void addComponentsToPane(final Container pane) {
        final JPanel panel = new JPanel();
        panel.setLayout(grid);        
        panel.setPreferredSize(new Dimension(450, 450));

        for(int y=0; y<7 ;y++){
			for(int x=0; x<7 ; x++){

	            buttons[x][y] = new JButton();
	            buttons[x][y].getPreferredSize();
	            panel.add(buttons[x][y]);
	            buttons[x][y].setIcon(none);
	            if(x<2){
					if(y<2||y>4){
						buttons[x][y].setEnabled(false);
					} 	
				}
				if(x>4){
					if(y<2||y>4){
						buttons[x][y].setEnabled(false);
					} 	
				}	
				
			}	
        }

        pane.add(panel);
    }

    @Override
    public void updateBoard(Symbol userSymbol, JButton button) {    
    		ImageIcon fill;
    		ImageIcon noFill; 
    		fill = new ImageIcon(this.getClass().getResource("icons/X.png"));
    		noFill = new ImageIcon(this.getClass().getResource("icons/O.png"));
    		none = new ImageIcon(this.getClass().getResource("icons/none.png"));
    	
    		if(userSymbol.equals(Symbol.X)) button.setIcon(fill);
    		if(userSymbol.equals(Symbol.O)) button.setIcon(noFill);
    		if(userSymbol.equals(Symbol.NONE)) button.setIcon(none);
    	
    }  
    
    @Override
    public void informWin(Symbol userSymbol) {
        for(int x=0; x<7 ;x++){
    	for (JButton button : buttons[x]) {
            button.setEnabled(false);
        }
    	}

        JOptionPane.showMessageDialog(null,"You won!");
    }

    @Override
    public void informTie() {
        JOptionPane.showMessageDialog(null, "Restart");
    }

    public JButton getButton(int x,int y) {
        return buttons[x][y];
    }

    public int getNumberOfButtons() {
        return buttons.length;
    }
}