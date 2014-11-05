package Threads;

import java.util.ArrayList;
import java.util.HashMap;

public class Threads {
    
    
    private volatile static HashMap<String, String> vars = new HashMap<String, String>();
    private volatile static ArrayList<Thread> array = new ArrayList<Thread>();
    
    public static String add(Runnable target) {
        return Threads.add(target, "");
    }
    
    public static String add(Runnable target, String name) {
        Thread thread = new Thread(target);
        
        if(name.length() != 0) {
            thread.setName(name);
        }
        Threads.array.add(thread);
        return thread.getName();
    }

    public static boolean start(String name) {
        Thread thread = Threads.find(name);
        if(name != null) {
            thread.start();
            return true;
        }
        return false;
    }
    
    public static boolean interrupt(String name) {
        Thread thread = Threads.find(name);
        if(name != null) {
            thread.interrupt();
            Threads.remove(thread);
            return true;
        }
        
        return false;
    }
    
    public static void varSet(String key, String value) {
        Threads.vars.put(key, value);
    }
    
    public static String varGet(String key) {
        return Threads.vars.get(key);
    }
    
    public static boolean isAlive(String name) {
        Thread thread = Threads.find(name);
        if(thread != null) {
            return thread.isAlive();
        }
        return false;
    }
    
    public static void clearAll() {
        for(Thread thread : Threads.array) {
            thread.interrupt();
        }
        Threads.array.clear();
    }

    private static Thread find(String name) {
        for(Thread thread : Threads.array) {
            if(thread.getName().equals(name)) {
                return thread;
            }
        }
        return null;
    }
    
    private static boolean remove(Thread thread) {
        return Threads.array.remove(thread);
    }
    
}
