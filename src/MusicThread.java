import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class MusicThread extends Thread {
	
	public void run() { // Runs Code In Parallel Using A Thread
		playSound("/res/Bad Apple.wav");
	}

	// Private Methods
	
	private synchronized void playSound(final String url) { 
		  new Thread(new Runnable() { 
		    public void run() { 
		     try { 
		        Clip clip = AudioSystem.getClip(); 
		        var inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(url)); 
		        clip.open(inputStream);
		        clip.start(); 
		     } catch (Exception e) { 
		        e.printStackTrace();
		     } 
		    } 
		   }).start(); 
	}
}