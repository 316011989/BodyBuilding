package kun.bdbd.common.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.apkfuns.logutils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import kun.bdbd.common.R;
import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseFragment;
import kun.bdbd.common.databinding.FragmentAboutBinding;

/**
 * 关于页面
 */
@Route(path = ARouterPath.AboutFgt)
public class FragmentAbout extends BaseFragment {

    private FragmentAboutBinding fragmentAboutBinding;

    private UserData userData1, userData2;
    private UserDataOF userDataOF;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentAboutBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);

        List<UserData> userList = new ArrayList<>();

        userData1 = new UserData();
        userData1.setUserName("zhangyilong");
        userData1.setUserId("1212");

        userData2 = new UserData();
        userData2.setUserName("zhangyilong2");
        userData2.setUserId("2331");

        userList.add(userData1);
        userList.add(userData2);

        fragmentAboutBinding.setUserList(userList);

        userDataOF = new UserDataOF();
        userDataOF.userName.set("zhangyilong5");
        userDataOF.userId.set("zyl");

        fragmentAboutBinding.setUserDataOF(userDataOF);

        /**执行executePendingBindings方法开始数据绑定*/
        fragmentAboutBinding.executePendingBindings();

        LogUtils.d("fragmentAboutBinding--->");

        fragmentAboutBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeName();
            }
        });

        return fragmentAboutBinding.getRoot();
    }

    public void changeName() {
        userData1.setUserName("张一龙");
        userData2.setUserName("张一龙2");

        userDataOF.userName.set("张一龙5");

    }

}
