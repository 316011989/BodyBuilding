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
import com.kun.module_message.databinding.GroupFragmentBinding;

import kun.bdbd.common.base.BaseFragment;
import kun.bdbd.coremodel.datamodel.http.entities.GroupData;
import kun.bdbd.coremodel.viewmodel.GroupViewModel;

/**
 * Created by HOME_PC on 2018/2/2.
 */
public class GroupFragment extends BaseFragment {
    GroupFragmentBinding binding;
    GroupAdapter adapter;
    GroupViewModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.group_fragment, container, false);
        adapter = new GroupAdapter(callback);
        binding.setRecyclerAdapter(adapter);
        model = new GroupViewModel(getActivity().getApplication());
        subscribeToModel(model);
        binding.setGroupViewModel(model);
        return binding.getRoot();
    }

    /**
     * item点击回调
     */
    GroupItemClickCallback callback = new GroupItemClickCallback() {
        @Override
        public void onClick(GroupData.ResultsBean groupItem) {
            Toast.makeText(getContext(), groupItem.getDesc(), Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 订阅数据变化来刷新UI
     *
     * @param model
     */
    private void subscribeToModel(final GroupViewModel model) {
        //观察数据变化来刷新UI
        model.getLiveObservableData().observe(GroupFragment.this, new Observer<GroupData>() {
            @Override
            public void onChanged(@Nullable GroupData friendData) {
                model.setUiObservableData(friendData);
                adapter.setGroupList(friendData.getResults());
            }
        });
    }
}
