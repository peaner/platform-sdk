package peaner.platform.sdk.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-2 下午10:24
 **/
@Slf4j
public class CustomCookieJar implements CookieJar {

    private static long timestamp = 0;
    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    public static long getTimestamp() {
        return timestamp;
    }

    public static void setTimestamp(long timestamp) {
        CustomCookieJar.timestamp = timestamp;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        log.info("-------save from response");

        for (Cookie cookie : cookies) {
            if ("session".equalsIgnoreCase(cookie.name())) {
                timestamp = new Date().getTime() + (1000 * 60 * 60 * 2);
            }
        }
        cookieStore.put(url.host(), cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        log.info("-------load for request");
        List<Cookie> cookies = cookieStore.get(url.host());
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }
}
