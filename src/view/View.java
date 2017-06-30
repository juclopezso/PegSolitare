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
    private final JButton[] buttons;   
	private ImageIcon none;
	
    public View() {
        super("Peg Solitare");
        grid = new GridLayout(8, 7);
        buttons = new JButton[49];
        
		none = new ImageIcon(this.getClass().getResource("none.png"));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(getContentPane());
        pack();
        setVisible(true);

    }

    public void addComponentsToPane(final Container pane) {
        final JPanel panel = new JPanel();
        panel.setLayout(grid);        
        panel.setPreferredSize(new Dimension(450, 450));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].getPreferredSize();
            panel.add(buttons[i]);
            buttons[i].setIcon(none);
            if(i<2 || (i>4&&i<9) || (i>11&&i<14) || (i>34&&i<37) || (i>39&&i<44) || (i>46&&i<49))
				buttons[i].setEnabled(false);
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
        for (JButton button : buttons) {
            button.setEnabled(false);
        }

        JOptionPane.showMessageDialog(null,"You won!");
    }

    @Override
    public void informTie() {
        JOptionPane.showMessageDialog(null, "Restart");
    }

    public JButton getButton(int index) {
        return buttons[index];
    }

    public int getNumberOfButtons() {
        return buttons.length;
    }
}