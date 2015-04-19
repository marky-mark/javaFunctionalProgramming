package desingingWithLambdas;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HandleException {
    public static void main(String[] args) throws IOException {
        List<String> paths = Arrays.asList("/usr", "/tmp");

        /* Causes exception!
        paths.stream()
                .map(path -> new File(path).getCanonicalPath())
                .forEach(System.out::println);*/

        /*paths.stream()
                .map(new Function<String, UseInstance>() {

                         @Override
                         public UseInstance apply(String path) {

                             return (UseInstance) new UseInstance() {
                                 @Override
                                 public void accept(Object instance) throws Throwable {
                                     new File(path).getCanonicalPath();
                                 }
                             };
                         }
                     }
                )
                .forEach(System.out::println);*/

        paths.stream()
                .map(path -> (UseInstance)instance -> new File(path).getCanonicalPath())
                .forEach(System.out::println);

    }
}

