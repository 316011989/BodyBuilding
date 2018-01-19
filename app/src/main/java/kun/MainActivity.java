package kun;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import kun.bdbd.FragmentAdapter;
import kun.bdbd.R;
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
        mainBinding.toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        mainBinding.mainTitle.setText(getApplication().getPackageName());
        mainBinding.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        mPager = mainBinding.containerPager;
        mPager.setOffscreenPageLimit(3);

        BaseFragment fragmentNews = (BaseFragment) ARouter.getInstance().build(ARouterPath.AboutFgt).navigation();
        BaseFragment fragmentGirls = (BaseFragment) ARouter.getInstance().build(ARouterPath.AboutFgt).navigation();
        BaseFragment fragmentAbout = (BaseFragment) ARouter.getInstance().build(ARouterPath.AboutFgt).navigation();

        mFragments.add(fragmentNews);
        mFragments.add(fragmentGirls);
        mFragments.add(fragmentAbout);

        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mainBinding.setViewPaAdapter(mAdapter);


    }
}
