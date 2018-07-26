package peaner.platform.sdk.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import peaner.platform.sdk.exception.ConnectionException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lilongzhou
 * @Description: 请求处理工具类
 * @Date: Created in 18-7-2 下午3:30
 **/
@Slf4j
public class RetrofitUtils {

    private static Retrofit instance = null;

    public static <T> T createService(Retrofit retrofit, Class<T> clazz) {
        return retrofit.create(clazz);
    }

    public static Retrofit.Builder getInstance() {
        return build();
    }


    public static String stringInfo(Call<ResponseBody> call) throws IOException {
        Response<ResponseBody> response = call.execute();

        if (response.isSuccessful()) {
            String responseContent = response.body().string();
            log.info("---------------- success: " + responseContent);
            return responseContent;
        } else {
            log.info("---------------- http status code:\t" + response.code());
            log.info("---------------- http response message:\t" + response.message());
            log.info("---------------- http error body:\t" + response.errorBody().string());
            throw new RuntimeException("---------------- request error");
        }
    }


    public static <T> T info(Call<T> call) throws ConnectionException {
        Response<T> response = null;
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                T responseBody = response.body();
                log.info("---------------- success: " + GsonUtils.toJson(responseBody));
                return responseBody;
            } else {
                log.info("---------------- http status code:\t" + response.code());
                log.info("---------------- http response message:\t" + response.message());
                log.info("---------------- http error body:\t" + response.errorBody().string());
                throw new ConnectionException("---------------- request error");
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ConnectionException("---------------- request error");
        }
    }

    private static Retrofit.Builder build() {
        // 声明日志类//设定日志级别
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> log.info("********* {}", message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.SECONDS)
                .connectTimeout(2, TimeUnit.SECONDS)
                .writeTimeout(2, TimeUnit.SECONDS)
                // 记录请求cookie
                //.cookieJar(new )
                // 添加拦截器
                .addInterceptor(httpLoggingInterceptor)
                .build();
        return new Retrofit.Builder().client(okHttpClient);
    }

}
