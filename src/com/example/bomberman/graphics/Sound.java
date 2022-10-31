package com.example.bomberman.graphics;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    private Clip clip;
    public static Sound menu = new Sound("res/sound/menu.wav");
    public static Sound menuSelect = new Sound("res/sound/MenuSelect.wav");
    public static Sound playGame = new Sound("res/sound/playgame.mid");
    public static Sound attack = new Sound("res/sound/attackwarning.wav");
    public static Sound bomberdie = new Sound("res/sound/bomber_die.wav");
    public static Sound enemydie = new Sound("res/sound/monster_die.wav");
    public static Sound newBomb = new Sound("res/sound/newbomb.wav");
    public static Sound bombExplode = new Sound("res/sound/bomb_bang.wav");
    public static Sound collectItem = new Sound("res/sound/item.wav");
    public static Sound nextLv = new Sound("res/sound/startstage.wav");
    public static Sound win = new Sound("res/sound/win.wav");
    public static Sound lose = new Sound("res/sound/lose.mid");
    public Sound(String fileSound) {
        try {
            File file = new File(fileSound);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileSound);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }
    }
    public void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}

