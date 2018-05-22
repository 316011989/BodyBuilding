package kun.bdbd;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseActivity;
import kun.bdbd.common.base.BaseFragment;
import kun.bdbd.common.widget.NoScrollViewPager;
import kun.bdbd.databinding.ActivityMainBinding;

/**
 * Created by zhangyl on 2018/01/19.
 */
@Route(path = ARouterPath.MainActivity)
public class MainActivity extends BaseActivity {

    ActivityMainBinding mainBinding;
    private NoScrollViewPager mPager;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private FragmentAdapter mAdapter;

//    public BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            int i = item.getItemId();
//            if (i == R.id.navigation_dynamic) {
//                mPager.setCurrentItem(0);
//                return true;
//            } else if (i == R.id.navigation_publish) {
//                mPager.setCurrentItem(2);
//                return true;
//            } else if (i == R.id.navigation_mine) {
//                mPager.setCurrentItem(3);
//                return true;
//            } else if (i == R.id.navigation_message) {
//                mPager.setCurrentItem(1);
//                return true;
//            }
//            return false;
//        }
//
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mPager = mainBinding.containerPager;
        mPager.setOffscreenPageLimit(3);

        BaseFragment fragmentDynamic = (BaseFragment) ARouter.getInstance().build(ARouterPath.DynamicFgt).navigation();
        BaseFragment fragmentMessage = (BaseFragment) ARouter.getInstance().build(ARouterPath.MessageFgt).navigation();
        BaseFragment fragmentPublish = (BaseFragment) ARouter.getInstance().build(ARouterPath.PublishFgt).navigation();
        BaseFragment fragmentMine = (BaseFragment) ARouter.getInstance().build(ARouterPath.MineFgt).navigation();

        mFragments.add(fragmentDynamic);
        mFragments.add(fragmentMessage);
        mFragments.add(fragmentPublish);
        mFragments.add(fragmentMine);

        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mainBinding.setViewPaAdapter(mAdapter);

        //让虚拟按键一直不显示
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        window.setAttributes(params);
    }
}
