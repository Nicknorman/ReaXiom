import org.junit.Test;
import org.testng.junit.JUnitTestClass;

import java.util.Arrays;

/**
 * Created by Nick on 27-05-2017.
 */
public class TestGiberish {

    @Test
    public void testStreams() {
        Arrays.stream(new int[] {1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);  // 5.0
    }
}
