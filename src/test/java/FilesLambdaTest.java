import org.junit.Test;

import java.io.File;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FilesLambdaTest {

    @Test
    public void testFiles() throws Exception {
        Files.list(Paths.get("."))
                .forEach(System.out::println);
    }

    @Test
    public void testADirectory() throws Exception {
        Files.list(Paths.get("."))
                .filter(Files::isDirectory)
                .forEach(System.out::println);
    }

    @Test
    public void testSelectJavaFiles() throws Exception {
        /*final String[] files =
                new File("fpij").list(new java.io.FilenameFilter() {
                    public boolean accept(final File dir, final String name) {
                        return name.endsWith(".java");
                    }
                });*/

        final String[] files =
                new File("fpij").list((dir, name) -> name.endsWith(".java"));

        System.out.println(Arrays.toString(files));


        new File(".").listFiles(File::isHidden);
    }

    public static void listTheHardWay() {
        List<File> files = new ArrayList<>();
        File[] filesInCurerentDir = new File(".").listFiles();
        for (File file : filesInCurerentDir) {
            File[] filesInSubDir = file.listFiles();
            if (filesInSubDir != null) {
                files.addAll(Arrays.asList(filesInSubDir));
            } else {
                files.add(file);
            }
        }
        System.out.println("Count: " + files.size());
    }

    //Flatmap - The method then flattens the multiple
    //streams, obtained by mapping each element, into one flat stream.
    public static void betterWay() {
        List<File> files =
                Stream.of(new File(".").listFiles())
                        .flatMap(file -> file.listFiles() == null ?
                                Stream.of(file) : Stream.of(file.listFiles()))
                        .collect(toList());
        System.out.println("Count: " + files.size());
    }

    @Test
    public void testWatchFileChange() throws Exception {
        final Path path = Paths.get(".");
        final WatchService watchService = path.getFileSystem().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
        System.out.println("Report any file changed within next 1 minutes...");

        final WatchKey watchKey = watchService.poll(1, TimeUnit.MINUTES);
        if(watchKey != null) {
            watchKey.pollEvents().stream().forEach(event ->
                    System.out.println(event.context()));
        }
    }
}
