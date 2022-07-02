import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame; 
import javax.swing.JTextArea;

public class Main {
	private static JFrame frame = new JFrame("Bad Apple");
	private static JTextArea text = new JTextArea();

	public static void main(String[] args) {
		var asciiVideo = new ASCIIVideo(text);
		var font = new Font("Monospace 12", Font.PLAIN, 2);
		
		if (font.getSize() == 1) frame.setSize(480, 750);
		else frame.setSize(780, 960);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		text.setFont(font);
		text.setRows(360);
		text.setRows(480);
		text.setWrapStyleWord(false);
		text.setHighlighter(null);
		
		frame.add(text);

		asciiVideo.ReadImage();
	}

}