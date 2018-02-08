package kun.bdbd.coremodel.datamodel.http.service;

import io.reactivex.Observable;
import kun.bdbd.coremodel.datamodel.http.entities.DynamicData;
import kun.bdbd.coremodel.datamodel.http.entities.FriendData;
import kun.bdbd.coremodel.datamodel.http.entities.MessageData;
import kun.bdbd.coremodel.datamodel.http.entities.NewsData;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dxx on 2017/11/8.
 */

public interface GankDataService {


    @GET("api/data/Android/{size}/{index}")
    Observable<NewsData> getAndroidData(@Path("size") String size, @Path("index") String index);

    /**
     * 动态
     *
     * @param size
     * @param index
     * @return
     */
    @GET("api/data/福利/{size}/{index}")
    Observable<DynamicData> getDynamicData(@Path("size") int size, @Path("index") int index);


    /**
     * 消息
     *
     * @param size
     * @param index
     * @return
     */
    @GET("api/data/福利/{size}/{index}")
    Observable<MessageData> getMessageData(@Path("size") int size, @Path("index") int index);

    /**
     * 好友
     *
     * @param size
     * @param index
     * @return
     */
    @GET("api/data/福利/{size}/{index}")
    Observable<FriendData> getFriendData(@Path("size") int size, @Path("index") int index);


}
