import org.apache.commons.lang3.*;
import org.junit.Assert;
import org.junit.Test;

public class CommonsLangTest {

    @Test
    public void testStringUtils() {
        String str = "i like pizza, pizza is great, long live pizza";
        int num = StringUtils.countMatches(str, "pizza");
        Assert.assertEquals(3, num);
        String str2 = "bananas";
        String rotated = StringUtils.rotate(str2, 3);
        System.out.println(rotated);
    }

    @Test
    public void testArrayUtils() {
        int [] nums = { 5, 4, 3, 2, 1};
        int [] updatedNums = ArrayUtils.add(nums, 0);
        System.out.println(ArrayUtils.toString(updatedNums) );
        Assert.assertArrayEquals(updatedNums, new int[] {5,4,3,2,1,0});
    }

    @Test
    public void testRandomUtils() {
        double d = RandomUtils.secure().nextDouble();
        System.out.println(d);
        String str = RandomStringUtils.randomAlphanumeric(10);
        System.out.println(str);
    }

    private void getAge(int age) {
        Validate.isTrue(age >= 0 && age <= 150);
        // stuff happens here
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidate() {
        getAge(160);
        getAge(-1);
    }


}
