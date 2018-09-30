package mainFrame;


import java.applet.Applet;
import java.applet.AudioClip;


/**
 * Manages and plays the sounds.
 */
public class SoundManager {
	private static final boolean SOUND_ON = true;
	private AudioClip explosionSound = Applet.newAudioClip(GUI.class.getResource(
			"/sounds/shipExplosion.wav"));
	private AudioClip nyanCat = Applet.newAudioClip(GUI.class.getResource(
			"/sounds/Nyan Cat.wav"));
	private AudioClip laserSound = Applet.newAudioClip(GUI.class.getResource(
			"/sounds/laser.wav"));
	private AudioClip redAlert = Applet.newAudioClip(GUI.class.getResource(
			"/sounds/Red Alert.wav"));
	private AudioClip tornadoSiren = Applet.newAudioClip(GUI.class.getResource(
			"/sounds/Tornado Siren.wav"));

	/**
	 * Plays sound for alarms.
	 */
	public void playLaser(){
		if(SOUND_ON){
			new Thread(new Runnable(){
				public void run() {
					laserSound.play();
				}
			}).start();
		}
	}

	/**
	 * Plays sound for alarms.
	 */
	public void playExplosionSound(){
		if(SOUND_ON){
			new Thread(new Runnable(){
				public void run() {
					explosionSound.play();
				}
			}).start();
		}
	}
	/**
	 * Plays red Alert sound for alarms.
	 */
	public void playRed(){
		if(SOUND_ON){
			new Thread(new Runnable(){
				public void run() {
					redAlert.play();
				}
			}).start();
		}
	} 
	/**
	 * Plays Tornado Siren sound for alarms.
	 */
	public void playTornado(){
		if(SOUND_ON){
			new Thread(new Runnable(){
				public void run() {
					tornadoSiren.play();
				}
			}).start();
		}
	}
	/**
	 * Plays Nyan Cat sound for alarms.
	 */
	public void playNyan(){
		if(SOUND_ON){
			new Thread(new Runnable(){
				public void run() {
					nyanCat.play();
				}
			}).start();
		}
	}
	/**
	 * Stops Nyan sound for alarms.
	 */
	public void stopNyan(){
		if(SOUND_ON){
			new Thread(new Runnable(){
				public void run() {
					nyanCat.stop();
				}
			}).start();
		}
	}
	/**
	 * Stop Red Alert sound for alarms.
	 */
	public void stopRedAlert(){
		if(SOUND_ON){
			new Thread(new Runnable(){
				public void run() {
					redAlert.stop();
				}
			}).start();
		}
	}

/**
 * Stop sound for alarms.
 */
	public void stopTornadoSiren(){
		if(SOUND_ON){
			new Thread(new Runnable(){
				public void run() {
					tornadoSiren.stop();
				}
			}).start();
		}
	}
/**
 * Stop sound for alarms.
 */
	public void stopLaser(){
		if(SOUND_ON){
			new Thread(new Runnable(){
				public void run() {
					laserSound.stop();
				}
			}).start();
		}
	}
/**
 * Stop sound for alarms.
 */
	public void stopExplosion(){
		if(SOUND_ON){
			new Thread(new Runnable(){
				public void run() {
					explosionSound.stop();
				}
			}).start();
		}
	}
}
