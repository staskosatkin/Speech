package common;

import java.util.Date;
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
            try {
                Console.writeLine(" --- Record completed ---");
                Audio.microphoneStop();
                
                Console.writeLine(" --- Convert ---");
                Convertor.wav2flac(Application.tempDir + Application.filename + ".wav", Application.tempDir + Application.filename + ".flac");
                
                Console.writeLine(" --- Translate ---");
                SpeechDecoder.request(Application.tempDir + Application.filename + ".flac");
                
                Console.writeLine(" --- Completed ---");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        if(command.equals("exit")) {
            Console.stop();
        }
        if(command.equals("convert")) {
            Convertor.wav2flac(Application.tempDir + Application.filename + ".wav", Application.tempDir + Application.filename + ".flac");
        }
        if(command.equals("translate")) {
            SpeechDecoder.request(Application.tempDir + Application.filename + ".flac");
        }
    }
}
