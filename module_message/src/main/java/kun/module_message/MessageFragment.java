package kun.module_message;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kun.module_message.R;
import com.kun.module_message.databinding.FragmentMessageBinding;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseFragment;
import kun.bdbd.coremodel.datamodel.http.entities.MessageData;

/**
 * Created by HOME_PC on 2018/1/31.
 */
@Route(path = ARouterPath.MessageFgt)
public class MessageFragment extends BaseFragment {
    FragmentMessageBinding binding;
    MessageAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false);
        adapter = new MessageAdapter(callback);
        binding.setRecycleAdapter(adapter);
        return binding.getRoot();
    }

    MessageItemClickCallback callback = new MessageItemClickCallback() {
        @Override
        public void onClick(MessageData.ResultsBean messageItem) {

        }
    };
}
