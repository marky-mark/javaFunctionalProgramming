package resources;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterARM implements AutoCloseable {
    private final FileWriter writer;

    public FileWriterARM(final String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }

    public void writeStuff(final String message) throws IOException {
        writer.write(message);
    }

    public void close() throws IOException {
        System.out.println("close called automatically...");
        writer.close();
    }

    public static void main(final String[] args) throws IOException {

        //J7 - ARM Automatic Resource Management - no need to use finally {writer.close()}
        //"implements AutoCloseable"to work
        try(final FileWriterARM writerARM = new FileWriterARM("build/peekaboo.txt")) {
            writerARM.writeStuff("peek-a-boo");
            System.out.println("done with the resource...");
        }
    }

}