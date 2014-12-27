package IOAdapter;

import java.io.FileReader;

public class Factory {

    public static IOutput output(String name, String param) throws Exception {
        if (name.equals("stdout")) {
            return new PrintStream(System.out);
        }
        if (name.equals("file")) {
            return new File(param);
        }
        throw new Exception("Unknown adapter");
    }
    
    public static IOutput output(String name) throws Exception {
        return Factory.output(name, null);
    }
    
    public static IInput input(String name, String param) throws Exception {
        if(name.equals("stdin")) {
            return new InputStream(System.in);
        }
        if(name.equals("file")) {
            return new InputStream(new FileReader(param));
        }
        throw new Exception("Unknown adapter");
    }
    
    public static IInput input(String name) throws Exception {
        return Factory.input(name, null);
    }
    
    public static IInputAudio inputAudio(String name) {
        if(name.equals("microphone")) {
            return new Microphone();
        }
        return null;
    }
}
