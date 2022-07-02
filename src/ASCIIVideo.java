import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;


public class ASCIIVideo {

	private JTextArea text;
	private MusicThread musicThread = new MusicThread();
	
	private final int FRAME_COUNT = 6572;
	private int FRAME_DELAY = 19;
	
	private final char[] density = {'Q','_','8','&','o',':','*','.',' '};
	
	public ASCIIVideo(JTextArea text) { // Constructor
		musicThread.start();
		this.text = text;
	}

	public void ReadImage() { // Reads Files
		BufferedImage image = null;
		for (int i = 27; i <= FRAME_COUNT; ++i) {
			Instant start = Instant.now();
			try {
				if (i <= 9) image = ImageIO.read(getClass().getResource("/res/Bad-Apple_00" + i + ".png"));
				else if (i <= 99) image = ImageIO.read(getClass().getResource("/res/Bad-Apple_0" + i + ".png"));
				else if (i > 99) image = ImageIO.read(getClass().getResource("/res/Bad-Apple_" + i + ".png"));
			} catch (IOException e) {
				
			}
			text.setText(convert(image));
			try {
				Thread.sleep(FRAME_DELAY);
			} catch (InterruptedException e) {

			}
			Instant end = Instant.now();
			Duration timeElapsed = Duration.between(start, end);
			FRAME_DELAY = Clamp(41 - (int) (FRAME_DELAY + (timeElapsed.toMillis() / 25)), 0, 100, false, true);
		}
	}

	// Private Methods

	private String convert(BufferedImage image) { // Creates ASCII Art from an Image
		var sb = new StringBuilder((image.getWidth() + 1) * image.getHeight());
		for (int y = 0; y < image.getHeight(); ++y) {
			if (sb.length() != 0) sb.append("\n");
			for (int x = 0; x < image.getWidth(); ++x) {
				Color pixelColor = new Color(image.getRGB(x, y));
				double gValue = (double) pixelColor.getRed() * 0.2989 
						+ (double) pixelColor.getBlue() * 0.5870
						+ (double) pixelColor.getGreen() * 0.1140;
				final char s = returnStr(gValue);
				sb.append(s);
			}
		}
		return sb.toString();
	}
	
	private char returnStr(double g) { // Returns ASCII character based on pixel colour
		if (g <= 200.0) {
			return density[0];
		} else if (g >= 200.0) {
			return density[1];
		} else if (g >= 180.0) {
			return density[2];
		} else if (g >= 160.0) {
			return density[3];
		} else if (g >= 130.0) {
			return density[4];
		} else if (g >= 100.0) {
			return density[5];
		} else if (g >= 70.0) {
			return density[6];
		} else if (g >= 50.0) {
			return density[7];
		} else {
			return density[8];
		}
	}
	
	private int Clamp(int var, int min, int max, boolean noMin, boolean noMax) {
		if (var < min && !noMin) return min;
		else if (var > max && !noMax) return max;
		else return var;
	}

}