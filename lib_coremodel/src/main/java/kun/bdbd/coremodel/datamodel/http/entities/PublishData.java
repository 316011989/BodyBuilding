package kun.bdbd.coremodel.datamodel.http.entities;

/**
 * Created by zhangyl1 on 2018/01/23.
 */

public class PublishData {
    private String resultCode;
    private String errorInfo;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
