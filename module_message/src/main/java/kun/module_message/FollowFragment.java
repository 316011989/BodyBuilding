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
import com.kun.module_message.databinding.FollowFragmentBinding;

import kun.bdbd.common.base.BaseFragment;
import kun.bdbd.coremodel.datamodel.http.entities.FollowData;
import kun.bdbd.coremodel.viewmodel.FollowViewModel;

/**
 * Created by HOME_PC on 2018/2/2.
 */
public class FollowFragment extends BaseFragment {
    FollowFragmentBinding binding;
    FollowAdapter adapter;
    FollowViewModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.follow_fragment, container, false);
        adapter = new FollowAdapter(callback);
        binding.setRecyclerAdapter(adapter);
        model = new FollowViewModel(getActivity().getApplication());
        subscribeToModel(model);
        binding.setFollowViewModel(model);
        return binding.getRoot();
    }

    /**
     * item点击回调
     */
    FollowItemClickCallback callback = new FollowItemClickCallback() {
        @Override
        public void onClick(FollowData.ResultsBean groupItem) {
            Toast.makeText(getContext(), groupItem.getDesc(), Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 订阅数据变化来刷新UI
     *
     * @param model
     */
    private void subscribeToModel(final FollowViewModel model) {
        //观察数据变化来刷新UI
        model.getLiveObservableData().observe(FollowFragment.this, new Observer<FollowData>() {
            @Override
            public void onChanged(@Nullable FollowData followData) {
                model.setUiObservableData(followData);
                adapter.setFollowList(followData.getResults());
            }
        });
    }
}
