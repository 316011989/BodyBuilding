package kun.module_message;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kun.module_message.R;
import com.kun.module_message.databinding.FragmentFansBinding;
import com.kun.module_message.databinding.FragmentFriendsBinding;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseFragment;
import kun.bdbd.coremodel.datamodel.http.entities.DynamicData;
import kun.bdbd.coremodel.datamodel.http.entities.FriendData;
import kun.bdbd.coremodel.viewmodel.DynamicViewModel;
import kun.bdbd.coremodel.viewmodel.FriendViewModel;

/**
 * Created by HOME_PC on 2018/2/2.
 */
@Route(path = ARouterPath.ContactsFgt)
public class FriendFragment extends BaseFragment {
    FragmentFriendsBinding binding;
    FriendAdapter adapter;
    FriendViewModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends, container, false);
        adapter = new FriendAdapter(callback);
        binding.setRecyclerAdapter(adapter);
        model = new FriendViewModel(getActivity().getApplication());
        subscribeToModel(model);
        binding.setFriendViewModel(model);
        return binding.getRoot();
    }

    /**
     * item点击回调
     */
    FriendItemClickCallback callback = new FriendItemClickCallback() {
        @Override
        public void onClick(FriendData.ResultsBean friendItem) {
            Toast.makeText(getContext(), friendItem.getDesc(), Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 订阅数据变化来刷新UI
     *
     * @param model
     */
    private void subscribeToModel(final FriendViewModel model) {
        //观察数据变化来刷新UI
        model.getLiveObservableData().observe(FriendFragment.this, new Observer<FriendData>() {
            @Override
            public void onChanged(@Nullable FriendData friendData) {
                model.setUiObservableData(friendData);
                adapter.setFriendList(friendData.getResults());
            }
        });
    }

}
