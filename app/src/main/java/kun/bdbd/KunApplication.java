package kun.bdbd;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;

import kun.bdbd.common.base.BaseApplication;
import kun.bdbd.common.util.Utils;

/**
 * Created by zhangyl1 on 2018/01/17.
 */

public class KunApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        if (Utils.isAppDebug()) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }
}
