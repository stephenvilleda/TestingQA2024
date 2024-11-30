import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class HelloMockito {


    @Test
    public void testMock() {
        List<String> names = Mockito.mock(List.class);
        Mockito.when(names.get(0)).thenReturn("Susan");
        Mockito.when(names.size()).thenReturn(1000000);
        System.out.println(names.get(0));
        System.out.println(names.size());
    }
}
