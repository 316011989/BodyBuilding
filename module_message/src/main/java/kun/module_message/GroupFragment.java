package kun.module_message;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kun.module_message.R;
import com.kun.module_message.databinding.FragmentGroupsBinding;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseFragment;

/**
 * Created by HOME_PC on 2018/2/2.
 */
@Route(path = ARouterPath.ContactsFgt)
public class GroupFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentGroupsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_groups, container, false);
        return binding.getRoot();
    }
}
