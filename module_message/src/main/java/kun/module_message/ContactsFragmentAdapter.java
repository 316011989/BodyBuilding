package kun.module_message;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kun.bdbd.common.base.BaseFragment;

/**
 * Created by HOME_PC on 2018/2/8.
 */

public class ContactsFragmentAdapter extends FragmentPagerAdapter {
    List<BaseFragment> fragments = new ArrayList<>();
    String[] pageNames = new String[]{"好友", "关注", "粉丝", "群组"};

    public ContactsFragmentAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new FriendFragment());
        fragments.add(new FollowFragment());
        fragments.add(new FansFragment());
        fragments.add(new GroupFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position % fragments.size());
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //得到缓存的fragment
        Fragment fragment = (Fragment) super.instantiateItem(container,
                position);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageNames[position % pageNames.length];
    }
}
