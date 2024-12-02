import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class PersonTest {

    private CacheManager cache;
    private DiskManager disk;

    @Before
    public void setUp() {
        cache = Mockito.mock(CacheManager.class);
        disk = Mockito.mock(DiskManager.class);
    }


    @Test
    public void testPersonDoesNotExist() {
        Person elvis = new Person(cache, disk);
        elvis.setPerson(12345, "Elvis", "Presley");
        Mockito.when(cache.getPerson(12345)).thenReturn(null);
        Mockito.when(disk.getPerson(12345)).thenReturn(null);
        String fullName = elvis.getFullName();
        Assert.assertEquals("", fullName);

        Mockito.verify(cache).getPerson(12345);
        Mockito.verify(disk).getPerson(12345);
        Mockito.verifyNoMoreInteractions(cache, disk);

        InOrder inOrder = Mockito.inOrder(cache, disk);
        inOrder.verify(cache).getPerson(12345);
        inOrder.verify(disk).getPerson(12345);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testPersonExistsInCache() {
        Person taylor = new Person(cache, disk);
        taylor.setPerson(24680, "Taylor", "Swift");
        Mockito.when(cache.getPerson(24680)).thenReturn(taylor);
        Mockito.when(disk.getPerson(24680)).thenReturn(taylor);
        Assert.assertEquals("Taylor Swift", taylor.getFullName());

        Mockito.verify(cache).getPerson(24680);
        Mockito.verifyNoMoreInteractions(cache, disk);
    }

    @Test
    public void testPersonExistsInDisk() {
        Person post = new Person(cache, disk);
        post.setPerson(11235, "Post", "Malone");
        Mockito.when(cache.getPerson(11235)).thenReturn(null);
        Mockito.when(disk.getPerson(11235)).thenReturn(post);

        Assert.assertEquals("Post Malone", post.getFullName());

        Mockito.verify(cache).getPerson(11235);
        Mockito.verify(disk).getPerson(11235);
        Mockito.verifyNoMoreInteractions(cache, disk);

        InOrder inOrder = Mockito.inOrder(cache, disk);
        inOrder.verify(cache).getPerson(11235);
        inOrder.verify(disk).getPerson(11235);
        inOrder.verifyNoMoreInteractions();
    }
}
