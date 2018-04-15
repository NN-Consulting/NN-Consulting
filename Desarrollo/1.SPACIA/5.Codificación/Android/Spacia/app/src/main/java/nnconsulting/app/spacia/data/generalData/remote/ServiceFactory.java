package nnconsulting.app.spacia.data.generalData.remote;

import nnconsulting.app.spacia.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    private static final String API_BASE_URL = BuildConfig.BASE;

    // set your desired log level
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addNetworkInterceptor(new AddHeadersInterceptor());
        client.addInterceptor(logging);

        Retrofit retrofit = builder.client(httpClient.build()).client(client.build()).build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createAuthService(Class<S> serviceClass) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        Retrofit retrofit = builder.client(httpClient.build()).client(client).build();
        return retrofit.create(serviceClass);
    }
}
