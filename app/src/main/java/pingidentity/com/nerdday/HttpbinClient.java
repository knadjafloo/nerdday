package pingidentity.com.nerdday;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by kamyar on 9/27/2015.
 */
public interface HttpbinClient {
    @GET("/ip")
    Call<IpAddress> getIpAddress();

}
