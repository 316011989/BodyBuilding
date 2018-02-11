package kun.module_message;

import kun.bdbd.coremodel.datamodel.http.entities.FollowData;

/**
 * Created by zhangyl1 on 2018/01/22.
 */

public interface FollowItemClickCallback {
    void onClick(FollowData.ResultsBean followItem);
}
