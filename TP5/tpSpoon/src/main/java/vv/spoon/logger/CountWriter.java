package vv.spoon.logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

// Thomas & Amona

public class CountWriter {

    private static PrintWriter fileWriter;
    private static HashMap<String, Integer> map = new HashMap<>();

    public static void writeLog() {
        // We write the content of our map
        for (Object key : map.keySet()) {
            fileWriter.write(key + ": " + map.get(key) + "\n");
        }
        fileWriter.close();
    }

    // Increment the number of calls of the specified method
    public static void count(String method) {
        Integer value = 1;
        if (map.containsKey(method)) {
            value = map.get(method) + 1;
        }

        map.put(method, value);

        try {
            getWriter();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static PrintWriter getWriter() throws FileNotFoundException {
        if(fileWriter == null) {
            ShutdownHookCount shutdownHook = new ShutdownHookCount();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            fileWriter = new PrintWriter("log");
        }
        return fileWriter;
    }
}
