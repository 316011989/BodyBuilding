package kun.bdbd.coremodel.datamodel.http.repository;

import kun.bdbd.coremodel.datamodel.http.ApiClient;
import kun.bdbd.coremodel.datamodel.http.entities.DynamicData;
import kun.bdbd.coremodel.datamodel.http.entities.NewsData;
import kun.bdbd.coremodel.datamodel.http.entities.VideoData;
import io.reactivex.Observable;

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


    public static Observable<DynamicData> getDynamicDataRepository(String size, String index) {

        Observable<DynamicData> observableForGetAndroidDataFromNetWork = ApiClient.getGankDataService().getDynamicData(size, index);

        //可以操作Observable来筛选网络或者是本地数据

        return observableForGetAndroidDataFromNetWork;
    }
}
