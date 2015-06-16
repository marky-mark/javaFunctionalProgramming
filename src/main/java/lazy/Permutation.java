package lazy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by markkelly on 16/06/15.
 */
public class Permutation {

    public  static void perm1(String s) { perm1("", s); }
    private static void perm1(String prefix, String s) {
        int N = s.length();
        if (N == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < N; i++)
                perm1(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, N));
        }

    }

    public  static List<String> perm2(String s) { return perm2("", s); }
    private static List<String> perm2(String prefix, String s) {

        List<String> perms = new ArrayList<>();

        int N = s.length();
        if (N == 0) return Collections.singletonList(prefix);
        else {
            IntStream.range(0, N).forEach(i ->
                    perms.addAll(
                            perm2(prefix + s.charAt(i),
                                    s.substring(0, i) + s.substring(i + 1, N))));
        }

        return perms;
    }
}
