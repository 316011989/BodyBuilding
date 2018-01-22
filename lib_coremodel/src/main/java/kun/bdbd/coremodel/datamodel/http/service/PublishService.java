package kun.bdbd.coremodel.datamodel.http.service;


import java.util.Map;

import io.reactivex.Observable;
import kun.bdbd.coremodel.datamodel.http.entities.PublishData;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zhangyl1 on 2018/01/23.
 */

public interface PublishService {
    /**
     * 发布
     *
     * @return
     */
    @GET("/mobile/checkin/check-in!checkQuick.shtml")
    Observable<PublishData> getPublishData(@QueryMap Map<String, String> par);
}
