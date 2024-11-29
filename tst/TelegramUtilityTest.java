import org.junit.Test;

public class TelegramUtilityTest {

    @Test
    public void testSendMessage() {

        String message = "Hello Friend, " +
                "You have just won a lottery! Please come and get it!";
        TelegramUtility.sendMessage(message);

    }


    @Test
    public void testChatID() {
        TelegramUtility.printChatID();
    }

    @Test
    public void testExceptionMessage() {
        try {
            double x = 1/0;
        } catch (Exception e) {
            TelegramUtility.sendMessage("ArithmeticException, pls take care of this issue asap!!!");
        }
    }

    @Test(expected = ArithmeticException.class)
    public void testArithmeticException() {
        double x = 1/0;
    }
}

