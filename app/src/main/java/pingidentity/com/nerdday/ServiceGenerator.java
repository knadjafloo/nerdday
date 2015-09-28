package pingidentity.com.nerdday;


import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ServiceGenerator {


    // No need to instantiate this class.
    private ServiceGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit.create(serviceClass);
    }

}
