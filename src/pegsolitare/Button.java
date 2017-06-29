package pegsolitare;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Button extends JButton implements ActionListener{
	public static ImageIcon fill, noFill, none;
	protected int value = 0;
	protected boolean accesible = true;
	
	//0:none, 1:X, 2:O
	public Button(){
		fill = new ImageIcon(this.getClass().getResource("fill.png"));
		noFill = new ImageIcon(this.getClass().getResource("noFill.png"));
		none = new ImageIcon(this.getClass().getResource("none.png"));
		setIcon(none);
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		value++;
		value%=2;
		PegSolitare.isGameFinished();
		fillButton(value, accesible);
	}
	
	public void fillButton(int value, boolean accesible){
		if(accesible==true){
			
			switch(value){
				case 0:
					setIcon(noFill);
					break;
				case 1:
					setIcon(fill);
					break;
			}
		}
	}
	
	
}
