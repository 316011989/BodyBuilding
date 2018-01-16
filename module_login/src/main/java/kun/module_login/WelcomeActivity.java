package kun.module_login;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import kun.bdbd.common.base.BaseActivity;
import kun.bdbd.coremodel.datamodel.http.entities.GirlsData;
import kun.bdbd.coremodel.viewmodel.GirlsViewModel;
import kun.module_login.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends BaseActivity {
    GirlsAdapter girlsAdapter;
    ActivityWelcomeBinding activityGirlsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTitle("Module_ActivityGirls");//不显示标题栏
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//
        activityGirlsBinding = DataBindingUtil.setContentView(WelcomeActivity.this, R.layout.activity_welcome);
        GirlsViewModel girlsViewModel = new GirlsViewModel(WelcomeActivity.this.getApplication());
        girlsAdapter = new GirlsAdapter(girlItemClickCallback);
        activityGirlsBinding.setRecyclerAdapter(girlsAdapter);
        subscribeToModel(girlsViewModel);
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
