import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {

    @Test
    public void testInventory() {
        Inventory inventory = new Inventory();
        Assert.assertEquals(0, inventory.getSize());
        Game stardew = new Game("413150");
        inventory.add(stardew);
        Assert.assertEquals(1, inventory.getSize());
        inventory.add(stardew);
        Assert.assertEquals(1, inventory.getSize());
        inventory.remove(stardew);
        Assert.assertEquals(0, inventory.getSize());
    }
}
