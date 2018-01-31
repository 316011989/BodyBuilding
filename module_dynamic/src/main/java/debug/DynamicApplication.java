package debug;

import com.facebook.drawee.backends.pipeline.Fresco;

import kun.bdbd.common.base.BaseApplication;

/**
 * Created by HOME_PC on 2018/2/2.
 */

public class DynamicApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
