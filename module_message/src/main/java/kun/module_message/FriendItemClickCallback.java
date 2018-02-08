package kun.module_message;

import kun.bdbd.coremodel.datamodel.http.entities.FriendData;

/**
 * Created by zhangyl1 on 2018/01/22.
 */

public interface FriendItemClickCallback {
    void onClick(FriendData.ResultsBean friendItem);
}
