package kun.module_message;

import kun.bdbd.coremodel.datamodel.http.entities.GroupData;

/**
 * Created by zhangyl1 on 2018/01/22.
 */

public interface GroupItemClickCallback {
    void onClick(GroupData.ResultsBean groupItem);
}
