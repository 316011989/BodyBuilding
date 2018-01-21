package kun.dynamic;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseFragment;
import kun.bdbd.coremodel.datamodel.http.entities.DynamicData;
import kun.bdbd.coremodel.viewmodel.DynamicViewModel;
import kun.dynamic.databinding.FragmentDynamicBinding;

/**
 * Created by zhangyl1 on 2018/01/22.
 */
@Route(path = ARouterPath.DynamicFgt)
public class DynamicFragment extends BaseFragment {
    FragmentDynamicBinding binding;
    DynamicAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dynamic, container, false);
        adapter = new DynamicAdapter(callback);
        binding.setRecyclerAdapter(adapter);
        final DynamicViewModel dynamicViewModel = new DynamicViewModel(getActivity().getApplication());
        subscribeToModel(dynamicViewModel);
        return binding.getRoot();
    }

    /**
     * item点击回调
     */
    DynamicItemClickCallback callback = new DynamicItemClickCallback() {
        @Override
        public void onClick(DynamicData.ResultsBean dynamicItem) {
            Toast.makeText(getContext(), dynamicItem.getDesc(), Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 订阅数据变化来刷新UI
     *
     * @param model
     */
    private void subscribeToModel(final DynamicViewModel model) {
        //观察数据变化来刷新UI
        model.getLiveObservableData().observe(DynamicFragment.this, new Observer<DynamicData>() {
            @Override
            public void onChanged(@Nullable DynamicData dynamicData) {
                model.setUiObservableData(dynamicData);
                adapter.setDynamicList(dynamicData.getResults());
            }
        });
    }

}
