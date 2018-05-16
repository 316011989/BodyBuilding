package kun.module_login;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.lang.reflect.Field;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseActivity;
import kun.module_login.databinding.ActivityWelcomeBinding;

@Route(path = ARouterPath.WelcomeActivity)
public class WelcomeActivity extends BaseActivity {
    ActivityWelcomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Module_WelcomeActivity");//不显示标题栏
        binding = DataBindingUtil.setContentView(WelcomeActivity.this, R.layout.activity_welcome);
        binding.setClickCallback(new OnClickCallback() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance()
                        .build(ARouterPath.GuideActivity)
                        /**可以针对性跳转跳转动画*/
                        .withTransition(R.anim.activity_up_in, R.anim.activity_up_out)
                        .navigation(WelcomeActivity.this);
            }
        });
    }


}
