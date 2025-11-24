package edu.sustech.xiangqi.ui.Components;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer {
    public static void soundplay(String path){
        try {
            InputStream inputStream= SoundPlayer.class.getClassLoader().getResourceAsStream(path);



            // 获取音频输入流和格式
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // 打开音频并播放
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.start();

            // 播放完成后关闭资源（避免内存泄漏）
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
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
