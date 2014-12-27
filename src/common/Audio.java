package common;

import IOAdapter.Factory;
import IOAdapter.IInputAudio;

public class Audio {
    
    private static IInputAudio microphone = Factory.inputAudio("microphone");
    
    public static void microphoneStart(String filename) {
        microphone.start(filename);
    }
    
    public static void microphoneStop() {
        microphone.stop();
    }
}
