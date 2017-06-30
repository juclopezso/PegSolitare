package view;

import model.Field.Symbol;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View extends JFrame implements ViewInterface {
    private final GridLayout grid;     // default grid-size for tic-tac-toe
    private final JButton[] buttons;   // an array containing the 9 buttons
	private ImageIcon none;

    public View() {
        super("tic-tac-toe");
        grid = new GridLayout(7, 7);
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
    	for(int i=0; i<49; i++){
    		
    	try {
            Image icon = ImageIO.read(View.class.getResource("icons/" + userSymbol.toString() + ".png"));
            button.setIcon(new ImageIcon(icon));
        } catch (IOException ex) {
            System.out.println("icons/" + userSymbol.toString() + ".png not found.");
        }
    	}
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