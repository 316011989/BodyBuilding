package kun.module_message;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kun.module_message.R;
import com.kun.module_message.databinding.ActivityContactsBinding;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseActivity;

/**
 * Created by HOME_PC on 2018/2/6.
 */
@Route(path = ARouterPath.ContactsAct)
public class ContactsActivity extends BaseActivity {
    ContactsFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContactsBinding binding = DataBindingUtil.setContentView(ContactsActivity.this, R.layout.activity_contacts);
        adapter = new ContactsFragmentAdapter(getSupportFragmentManager());
        binding.setFragmentAdapter(adapter);
        binding.contactsTab.addTab(binding.contactsTab.newTab().setText("a"));
        binding.contactsTab.addTab(binding.contactsTab.newTab().setText("b"));
        binding.contactsTab.addTab(binding.contactsTab.newTab().setText("c"));
        binding.contactsTab.addTab(binding.contactsTab.newTab().setText("d"));
        binding.contactsTab.setupWithViewPager(binding.contactsContent);
    }
}
