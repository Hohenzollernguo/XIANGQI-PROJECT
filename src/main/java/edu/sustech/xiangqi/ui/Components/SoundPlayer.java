package edu.sustech.xiangqi.ui.Components;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer {


    private static volatile boolean isPlaying=false;

    public static void soundplay(String path) {

        if (isPlaying&&path.equals("垓下.wav")){
            return;
        }
            try {
                InputStream inputStream = SoundPlayer.class.getClassLoader().getResourceAsStream(path);


                AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);


                Clip clip = (Clip) AudioSystem.getLine(info);
                clip.open(audioStream);

               if (path.equals("垓下.wav")){
                   isPlaying=true;
               }
                clip.start();

                clip.addLineListener(event -> {

                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
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
                       if (path.equals("垓下.wav")){
                           isPlaying=false;
                       }
                    }
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }
}