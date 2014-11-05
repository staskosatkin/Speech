package common;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javaFlacEncoder.FLAC_FileEncoder;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

import Threads.Threads;

public class Application {

    private static String tempDir = "./temp/";
    private static String filename;
    
    public Application() {}

    public void run() throws Exception {
        Console.writeLine(" --- Speech app ---");
        Console.run();
        Threads.clearAll();
    }
    
    protected static void commandControl(String command) {
        if(command.equals("start")) {
            Application.filename = Long.toString((new Date()).getTime());
            Audio.microphoneStart(Application.tempDir + Application.filename + ".wav");
        }
        if(command.equals("break")) {
            Audio.microphoneStop();
        }
        if(command.equals("exit")) {
            Console.stop();
        }
        if(command.equals("convert")) {
            Convertor.wav2flac(Application.tempDir + Application.filename + ".wav", Application.tempDir + Application.filename + ".flac");
        }
    }
}
