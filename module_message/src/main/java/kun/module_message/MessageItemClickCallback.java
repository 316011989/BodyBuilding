package kun.module_message;

import kun.bdbd.coremodel.datamodel.http.entities.MessageData;

/**
 * Created by HOME_PC on 2018/1/31.
 */

public interface MessageItemClickCallback {
    void onClick(MessageData.ResultsBean messageItem);
}
