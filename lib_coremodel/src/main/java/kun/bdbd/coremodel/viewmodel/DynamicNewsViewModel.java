package kun.bdbd.coremodel.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import kun.bdbd.coremodel.datamodel.http.entities.VideoData;

/**
 * Created by dxx on 2017/11/20.
 * 动态的url
 */

public class DynamicNewsViewModel extends BaseViewModel<VideoData> {

    public DynamicNewsViewModel(@NonNull Application application, String fullUrl) {
        super(application, fullUrl);
    }
}
