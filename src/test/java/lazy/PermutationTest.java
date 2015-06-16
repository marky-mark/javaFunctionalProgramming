package lazy;

import org.junit.Test;

/**
 * Created by markkelly on 16/06/15.
 */
public class PermutationTest {

    @Test
    public void testPerm1() throws Exception {
        Permutation.perm1("1234");
    }

    @Test
    public void testPerm2() throws Exception {
        Permutation.perm2("1234").forEach(System.out::println);
    }
}