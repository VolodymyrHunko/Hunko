package JUnit;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TestUnit1 {
    private String a = "Java";
    int i = 5;
    String b = null;

   @Test
    public void sample(){
        assertEquals("Java",a);
        assertFalse(i>6);
        assertNull(b);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void JDK_modification_List(){
        List<String> list = new ArrayList<>(Arrays.asList("one", "two", "three"));
        List<String> immut = Collections.unmodifiableList(list);
        List<String> immut2 = ImmutableList.copyOf(list);
//        immut.add("four");
        immut2.add("four");
    }
}

