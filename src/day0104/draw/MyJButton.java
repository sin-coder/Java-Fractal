package day0104.draw;

import javax.swing.JButton;

public class MyJButton extends JButton {
	
	public MyJButton(String text){
		this.setText(text);
		super.paint(getGraphics());
	}
}
