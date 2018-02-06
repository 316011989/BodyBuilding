package kun.module_message;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kun.module_message.R;
import com.kun.module_message.databinding.ActivityContactsBinding;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseActivity;

/**
 * Created by HOME_PC on 2018/2/6.
 */
@Route(path = ARouterPath.ContactsAct)
public class ContactsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContactsBinding binding = DataBindingUtil.setContentView(ContactsActivity.this, R.layout.activity_contacts);
        //inject需要注入后才可以读取到携带过来的参数
        ARouter.getInstance().inject(this);
        binding.contactsTab.addTab(binding.contactsTab.newTab().setText("a"));
        binding.contactsTab.addTab(binding.contactsTab.newTab().setText("b"));
        binding.contactsTab.addTab(binding.contactsTab.newTab().setText("c"));
        binding.contactsTab.addTab(binding.contactsTab.newTab().setText("d"));
    }
}
