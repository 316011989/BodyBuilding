package kun.bdbd.coremodel.datamodel.http.repository;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import kun.bdbd.coremodel.datamodel.http.ApiClient;
import kun.bdbd.coremodel.datamodel.http.entities.DynamicData;
import kun.bdbd.coremodel.datamodel.http.entities.NewsData;
import kun.bdbd.coremodel.datamodel.http.entities.PublishData;
import kun.bdbd.coremodel.datamodel.http.entities.VideoData;

/**
 * Created by dxx on 2017/11/8.
 */

public class GankDataRepository {

    public static Observable<VideoData> getFuliDataRepository(String size, String index) {

        Observable<VideoData> observableForGetFuliDataFromNetWork = ApiClient.getGankDataService().getFuliData(size, index);

        //可以操作Observable来筛选网络或者是本地数据

        return observableForGetFuliDataFromNetWork;
    }

    public static Observable<NewsData> getNewsDataRepository(String size, String index) {

        Observable<NewsData> observableForGetAndroidDataFromNetWork = ApiClient.getGankDataService().getAndroidData(size, index);

        //可以操作Observable来筛选网络或者是本地数据

        return observableForGetAndroidDataFromNetWork;
    }


    public static Observable<DynamicData> getDynamicDataRepository(int size, int index) {

        Observable<DynamicData> observableForGetAndroidDataFromNetWork = ApiClient.getGankDataService().getDynamicData(size, index);

        //可以操作Observable来筛选网络或者是本地数据

        return observableForGetAndroidDataFromNetWork;
    }

    public static Observable<PublishData> getPublishDataRepository(String size, String index) {
        Map<String, String> map = new HashMap<>();
        map.put("certNo", "");
        map.put("givenName", "");
        map.put("familyName", "");
        map.put("phoneNo", "");
        map.put("request_locale", "");
        map.put("channel", "");

        Observable<PublishData> observableForGetAndroidDataFromNetWork = ApiClient.getPublishService().getPublishData(map);

        //可以操作Observable来筛选网络或者是本地数据

        return observableForGetAndroidDataFromNetWork;
    }
}
