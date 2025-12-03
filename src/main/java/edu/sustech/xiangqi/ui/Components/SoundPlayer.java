package edu.sustech.xiangqi.ui.Components;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer {


    private static volatile boolean isPlaying=false;

    private static Clip clip1;
    private static Clip clip2;

    public static void soundplay(String path) {

        if (isPlaying&&path.equals("垓下.wav")){
            return;
        }
            try {
                InputStream inputStream = SoundPlayer.class.getClassLoader().getResourceAsStream(path);


                AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);

                if (path.equals("垓下.wav")){
                    clip2 = (Clip) AudioSystem.getLine(info);
                    clip2.open(audioStream);

                    isPlaying=true;

                    clip2.start();
                    clip2.addLineListener(event -> {

                        if (event.getType() == LineEvent.Type.STOP) {
                            clip2.close();
                            try {
                                audioStream.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                                isPlaying=false;
                        }
                    });
                }else {

                clip1 = (Clip) AudioSystem.getLine(info);
                clip1.open(audioStream);


                clip1.start();

                clip1.addLineListener(event -> {

                    if (event.getType() == LineEvent.Type.STOP) {
                        clip1.close();
                        try {
                            audioStream.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });}
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }


public static void StopPlaying(){
        if (clip2 != null && clip2.isRunning()){
            clip2.close();
        }
}
}