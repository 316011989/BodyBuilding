package kun.module_login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseActivity;
import kun.bdbd.common.ui.UserData;
import kun.bdbd.common.util.ToastUtils;
import kun.module_login.databinding.ActivityLoginBinding;

/**
 * Created by zhangyl on 2018/01/17.
 */
@Route(path = ARouterPath.LoginActivity)
public class LoginActivity extends BaseActivity {

    ActivityLoginBinding activityLoginBinding;
    private UserData userData1, userData2;
    List<UserData> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        activityLoginBinding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

        userData1 = new UserData();
        userData1.setUserName("张一龙");
        userData1.setUserId("1234");

        userData2 = new UserData();
        userData2.setUserName("张一龙2");
        userData2.setUserId("12345");

        userList.add(userData1);
        userList.add(userData2);
        activityLoginBinding.setUserList(userList);

        /**执行executePendingBindings方法开始数据绑定*/
        activityLoginBinding.executePendingBindings();

        activityLoginBinding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouterPath.MainActivity)
                        /**可以针对性跳转跳转动画*/
                        .withTransition(R.anim.activity_up_in, R.anim.activity_up_out)
                        .navigation(LoginActivity.this);
            }
        });
    }
}
