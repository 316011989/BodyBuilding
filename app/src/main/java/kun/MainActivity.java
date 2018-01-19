package kun;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import kun.bdbd.R;
import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseActivity;

/**
 * Created by zhangyl1 on 2018/01/19.
 */
@Route(path = ARouterPath.MainActivity)
public class MainActivity extends BaseActivity {
    ViewDataBinding viewDataBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        viewDataBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
    }
}
