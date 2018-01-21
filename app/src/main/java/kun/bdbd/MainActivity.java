package kun.bdbd;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
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

    public BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.navigation_home) {
                mPager.setCurrentItem(0);
                return true;
            } else if (i == R.id.navigation_dashboard) {
                mPager.setCurrentItem(1);
                return true;
            } else if (i == R.id.navigation_notifications) {
                mPager.setCurrentItem(2);
                return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mainBinding.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        mPager = mainBinding.containerPager;
        mPager.setOffscreenPageLimit(3);

        BaseFragment fragmentNews = (BaseFragment) ARouter.getInstance().build(ARouterPath.AboutFgt).navigation();
        BaseFragment fragmentGirls = (BaseFragment) ARouter.getInstance().build(ARouterPath.PublishFgt).navigation();
        BaseFragment fragmentAbout = (BaseFragment) ARouter.getInstance().build(ARouterPath.MineFgt).navigation();

        mFragments.add(fragmentNews);
        mFragments.add(fragmentGirls);
        mFragments.add(fragmentAbout);

        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mainBinding.setViewPaAdapter(mAdapter);


    }
}
