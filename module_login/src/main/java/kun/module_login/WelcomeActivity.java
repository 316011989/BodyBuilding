package kun.module_login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseActivity;
import kun.module_login.databinding.ActivityWelcomeBinding;

@Route(path = ARouterPath.WelcomeActivity)
public class WelcomeActivity extends BaseActivity {
    ActivityWelcomeBinding activityWelcomeBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Module_WelcomeActivity");//不显示标题栏
        activityWelcomeBinding = DataBindingUtil.setContentView(WelcomeActivity.this, R.layout.activity_welcome);

        activityWelcomeBinding.welcomeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance()
                        .build(ARouterPath.LoginActivity)
                        /**可以针对性跳转跳转动画*/
                        .withTransition(R.anim.activity_up_in, R.anim.activity_up_out)
                        .navigation(WelcomeActivity.this);
            }
        });

    }

}
