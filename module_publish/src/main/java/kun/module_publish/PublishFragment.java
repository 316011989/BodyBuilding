package kun.module_publish;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.apkfuns.logutils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseFragment;
import kun.bdbd.common.ui.UserData;
import kun.bdbd.common.ui.UserDataOF;
import kun.bdbd.coremodel.datamodel.http.entities.DynamicData;
import kun.bdbd.coremodel.datamodel.http.entities.PublishData;
import kun.bdbd.coremodel.viewmodel.DynamicViewModel;
import kun.bdbd.coremodel.viewmodel.PublishViewModel;
import kun.module_publish.databinding.FragmentPublishBinding;

/**
 * Created by HOME_PC on 2018/1/20.
 */
@Route(path = ARouterPath.PublishFgt)
public class PublishFragment extends BaseFragment {

    private FragmentPublishBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_publish, container, false);

        PublishViewModel viewModel = new PublishViewModel(getActivity().getApplication());
        subscribeToModel(viewModel);

        return binding.getRoot();
    }

    /**
     * 订阅数据变化来刷新UI
     *
     * @param model
     */
    private void subscribeToModel(final PublishViewModel model) {
        //观察数据变化来刷新UI
        model.getLiveObservableData().observe(PublishFragment.this, new Observer<PublishData>() {
            @Override
            public void onChanged(@Nullable final PublishData publishData) {

            }
        });

    }

}
