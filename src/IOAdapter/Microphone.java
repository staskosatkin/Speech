package IOAdapter;

import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;

import common.Console;
import Threads.Threads;

class Microphone implements IInputAudio {

    private String threadName;
    private AudioInputStream audioInputStream;
    
    @Override
    public void start(String filename) {
        try {
            Console.writeLine(" --- Record have started ---");
            
            AudioFormat format = new AudioFormat(44100.0f, 16, 1, true, true);
            TargetDataLine microphone = AudioSystem.getTargetDataLine(format);
            
            microphone.open(format);
            microphone.start();
            
            this.audioInputStream = new AudioInputStream(microphone);
            final java.io.File fileOut = new java.io.File(filename);
            
            this.threadName = Threads.add(() -> {
                try {
                    Console.writeLine("Thread started");
                    if(AudioSystem.isFileTypeSupported(AudioFileFormat.Type.WAVE, audioInputStream)) {
                        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, fileOut);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            Threads.start(this.threadName);
        } catch(Exception e) {}
    }

    @Override
    public void stop() {
        if(this.audioInputStream != null) {
            try {
                this.audioInputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(this.threadName != null && this.threadName.length() != 0) {
            Threads.interrupt(this.threadName);
        }
    }
    
}
