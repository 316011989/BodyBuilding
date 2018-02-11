package kun.module_message;

import kun.bdbd.coremodel.datamodel.http.entities.FansData;

/**
 * Created by zhangyl1 on 2018/01/22.
 */

public interface FanItemClickCallback {
    void onClick(FansData.ResultsBean fanItem);
}
