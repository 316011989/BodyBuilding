package kun.module_publish;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.apkfuns.logutils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseFragment;
import kun.bdbd.common.ui.UserData;
import kun.bdbd.common.ui.UserDataOF;
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
        return binding.getRoot();
    }


}
