package kun.module_login;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseActivity;
import kun.bdbd.coremodel.datamodel.http.entities.GirlsData;
import kun.bdbd.coremodel.viewmodel.GirlsViewModel;
import kun.module_login.databinding.ActivityWelcomeBinding;

@Route(path = ARouterPath.WelcomeActivity)
public class WelcomeActivity extends BaseActivity {
    GirlsAdapter girlsAdapter;
    ActivityWelcomeBinding activityWelcomeBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Module_WelcomeActivity");//不显示标题栏
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//
        activityWelcomeBinding = DataBindingUtil.setContentView(WelcomeActivity.this, R.layout.activity_welcome);
        GirlsViewModel girlsViewModel = new GirlsViewModel(WelcomeActivity.this.getApplication());
        girlsAdapter = new GirlsAdapter(girlItemClickCallback);
        activityWelcomeBinding.setRecyclerAdapter(girlsAdapter);
        subscribeToModel(girlsViewModel);

        activityWelcomeBinding.welcomeBtn.setOnClickListener(new View.OnClickListener() {
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

    GirlItemClickCallback girlItemClickCallback = new GirlItemClickCallback() {
        @Override
        public void onClick(GirlsData.ResultsBean fuliItem) {
            Toast.makeText(WelcomeActivity.this, fuliItem.getDesc(), Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 订阅数据变化来刷新UI
     *
     * @param model
     */
    private void subscribeToModel(final GirlsViewModel model) {
        //观察数据变化来刷新UI
        model.getLiveObservableData().observe(this, new Observer<GirlsData>() {
            @Override
            public void onChanged(@Nullable GirlsData girlsData) {
                Log.i("danxx", "subscribeToModel onChanged onChanged");
                model.setUiObservableData(girlsData);
                girlsAdapter.setGirlsList(girlsData.getResults());
            }
        });
    }

}
