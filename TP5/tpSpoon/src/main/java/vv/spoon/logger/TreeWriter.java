package vv.spoon.logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

// Thomas & Amona

public class TreeWriter {

    private static PrintWriter fileWriter;
    private static int scope = -1;

    public static void writeLog() {
        fileWriter.close();
        scope = -1;
    }

    // We append the method call to the tree
    // The indent depends on the scope
    public static void append(String method) {

        String indent = "";
        for (int i = 0; i < scope; i++) {
            indent += "|  ";
        }

        try {
            getWriter().write(indent + method + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static PrintWriter getWriter() throws FileNotFoundException {
        if(fileWriter == null) {
            ShutdownHookTree shutdownHook = new ShutdownHookTree();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            fileWriter = new PrintWriter("log");
        }
        return fileWriter;
    }

    public static void incrScope() {
        scope++;
    }

    public static void decrScope() {
        scope--;
    }
}
