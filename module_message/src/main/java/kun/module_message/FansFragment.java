package kun.module_message;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kun.module_message.R;
import com.kun.module_message.databinding.FanFragmentBinding;

import kun.bdbd.common.base.BaseFragment;
import kun.bdbd.coremodel.datamodel.http.entities.FansData;
import kun.bdbd.coremodel.viewmodel.FansViewModel;

/**
 * Created by HOME_PC on 2018/2/2.
 */
public class FansFragment extends BaseFragment {
    FanFragmentBinding binding;
    FansAdapter adapter;
    FansViewModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fan_fragment, container, false);
        adapter = new FansAdapter(callback);
        binding.setRecyclerAdapter(adapter);
        model = new FansViewModel(getActivity().getApplication());
        subscribeToModel(model);
        binding.setFansViewModel(model);
        return binding.getRoot();
    }

    /**
     * item点击回调
     */
    FanItemClickCallback callback = new FanItemClickCallback() {
        @Override
        public void onClick(FansData.ResultsBean groupItem) {
            Toast.makeText(getContext(), groupItem.getDesc(), Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 订阅数据变化来刷新UI
     *
     * @param model
     */
    private void subscribeToModel(final FansViewModel model) {
        //观察数据变化来刷新UI
        model.getLiveObservableData().observe(FansFragment.this, new Observer<FansData>() {
            @Override
            public void onChanged(@Nullable FansData friendData) {
                model.setUiObservableData(friendData);
                adapter.setFansList(friendData.getResults());
            }
        });
    }
}
