package kun.bdbd.coremodel.datamodel.http.service;

import io.reactivex.Observable;
import kun.bdbd.coremodel.datamodel.http.entities.GirlsData;
import kun.bdbd.coremodel.datamodel.http.entities.NewsData;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dxx on 2017/11/8.
 */

public interface GankDataService {

    @GET("api/data/福利/{size}/{index}")
    Observable<GirlsData> getFuliData(@Path("size") String size, @Path("index") String index);

    @GET("api/data/Android/{size}/{index}")
    Observable<NewsData> getAndroidData(@Path("size") String size, @Path("index") String index);

}
