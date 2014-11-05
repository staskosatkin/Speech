package common;

import IOAdapter.Factory;
import IOAdapter.IInput;
import IOAdapter.IOutput;
import Threads.Threads;

public class Console {
    
    private static String threadName;

    public static void writeLine(String message) throws Exception {
        IOutput adapter = Factory.output("stdout");
        adapter.writeLine(message);
    }
    
    public static void write(String str) throws Exception {
        Factory.output("stdout").write(str);
    }
    
    public static String readLine() throws Exception {
        Console.write("> ");
        IInput adapter = Factory.input("stdin");
        return adapter.readLine();
    }
    
    public static void run() {
        Console.threadName = Threads.add(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    String command;
                    try {
                        command = Console.readLine();
                        if (command.length() != 0) {
                            Application.commandControl(command);
                            Threads.varSet("command", command);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Threads.start(Console.threadName);
        while(Threads.isAlive(Console.threadName)) {}
    }
    
    public static void stop() {
        Threads.interrupt(Console.threadName);
    }
    
}
