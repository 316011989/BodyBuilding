package kun.dynamic;

import kun.bdbd.coremodel.datamodel.http.entities.DynamicData;

/**
 * Created by zhangyl1 on 2018/01/22.
 */

public interface DynamicItemClickCallback {
    void onClick(DynamicData.ResultsBean dynamicItem);
}
