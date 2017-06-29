package pegsolitare;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PegSolitare extends JFrame{

	JPanel p = new JPanel();
	static Button buttons[] = new Button[49];
	public static boolean isFinished = false;
	
	public static void main(String[] args) {
		new PegSolitare();
	}
	
	public PegSolitare(){
		super("Peg Solitare");
		setSize(400, 400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setLayout(new GridLayout(7,7));
		initializeBoard();
		cruz();

		while(isFinished==false){
			add(p);
			setVisible(true);		
		}
		win();		
		setVisible(false);		
	}
	
	private void initializeBoard(){
		
		for(int i=0; i<49 ; i++){
			buttons[i]=new Button();
			if(i<2 || (i>4&&i<9) || (i>11&&i<14) || (i>34&&i<37) || (i>39&&i<44) || (i>46&&i<49))
				buttons[i].accesible=false;	
			
			buttons[i].fillButton(buttons[i].value, buttons[i].accesible);
			p.add(buttons[i]);

		} 
	}
	
	private void cruz(){
		buttons[10].value=1;
		buttons[16].value=1;
		buttons[17].value=1;
		buttons[18].value=1;
		buttons[24].value=1;
		buttons[31].value=1;
		for(int i=0; i<49 ; i++){
			buttons[i].fillButton(buttons[i].value, buttons[i].accesible);
		}
	}
	
	public static void isGameFinished(){
		int pegs=0;
		for(int i=0; i<49 ; i++){
			if(buttons[i].value==1 && buttons[i].accesible==true)
				pegs++;
		}
		if(pegs==1)
			isFinished=true;
	}
	
	private void win(){
		JOptionPane.showMessageDialog(this, "You win");
	}
	
}
