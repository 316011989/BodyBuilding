package kun.module_mine;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseFragment;
import kun.module_mine.databinding.FragmentMineBinding;

/**
 * Created by HOME_PC on 2018/1/20.
 */
@Route(path = ARouterPath.MineFgt)
public class MineFragment extends BaseFragment {

    private FragmentMineBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);


        return binding.getRoot();
    }


}
