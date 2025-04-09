package file;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class SoundPlayer {
  public static void main(String[] args) {
    Timer timer = new Timer();
    timer.schedule(new PlaySoundTask(), 0, 7 * 60 * 1000); // Schedule task every 7 minutes
  }

  public static class PlaySoundTask extends TimerTask {
    @Override
    public void run() {
      playSound("/Users/i312177/githubWdf/cmd-shortcut/alarm_beep.wav"); // Replace with the path to your sound file
      System.out.println(getLineCount("output3.txt"));
    }

    private static long getLineCount(String fileName) {
      long lineCount = 0;
      try {
        ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", "wc -l < " + fileName);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        lineCount = Long.parseLong(reader.readLine().trim());
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return lineCount;
    }

    public static void playSound(String filePath) {
      try {
        File soundFile = new File(filePath);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        Thread.sleep(clip.getMicrosecondLength() / 1000);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
