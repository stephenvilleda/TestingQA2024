import org.apache.commons.io.IOUtils;

import java.net.URL;
import java.net.URLEncoder;

public class TelegramUtility {

    private static final String TOKEN = "";
    private static final String CHAT_ID = "";

    public static void sendMessage(String message) {
        try {
            message = URLEncoder.encode(message, "UTF-8");
            String url = "https://api.telegram.org/bot" + TOKEN + "/sendMessage" + "?chat_id="
                    + CHAT_ID + "&text=" + message;
            URL urlObj = new URL(url);
            String status = IOUtils.toString(urlObj.openStream(), "UTF-8");
            System.out.println(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printChatID() {
        try {
            String url = "https://api.telegram.org/bot" + TOKEN + "/getUpdates";
            URL urlObj = new URL(url);
            String str = IOUtils.toString(urlObj.openStream(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

