package kun.module_message;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kun.module_message.R;
import com.kun.module_message.databinding.MessageFragmentBinding;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseFragment;
import kun.bdbd.common.util.ToastUtils;
import kun.bdbd.coremodel.datamodel.http.entities.MessageData;
import kun.bdbd.coremodel.viewmodel.MessageClickCallback;
import kun.bdbd.coremodel.viewmodel.MessageViewModel;

/**
 * Created by HOME_PC on 2018/1/31.
 */
@Route(path = ARouterPath.MessageFgt)
public class MessageFragment extends BaseFragment {
    MessageFragmentBinding binding;
    MessageAdapter adapter;
    MessageViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.message_fragment, container, false);
        adapter = new MessageAdapter(callback);
        binding.setRecycleAdapter(adapter);
        viewModel = new MessageViewModel(getActivity().getApplication());
        subscribeToModel(viewModel);
        binding.setClickCallback(onclick);
        return binding.getRoot();
    }

    //点击去通讯录
    MessageClickCallback onclick = new MessageClickCallback() {
        @Override
        public void onClick(View view) {
            ARouter.getInstance()
                    .build(ARouterPath.ContactsAct)
                    /**可以针对性跳转跳转动画*/
                    .withTransition(R.anim.activity_up_in, R.anim.activity_up_out)
                    .navigation(getActivity());
        }
    };

    //点击消息item
    MessageItemClickCallback callback = new MessageItemClickCallback() {
        @Override
        public void onClick(MessageData.ResultsBean messageItem) {

        }
    };

    /**
     * 订阅数据变化来刷新UI
     *
     * @param model
     */
    private void subscribeToModel(final MessageViewModel model) {
        //观察数据变化来刷新UI
        model.getLiveObservableData().observe(MessageFragment.this, new Observer<MessageData>() {
            @Override
            public void onChanged(@Nullable MessageData messageData) {
                model.setUiObservableData(messageData);
                adapter.seMessageList(messageData.getResults());
            }
        });
    }
}
