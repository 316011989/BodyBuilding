package kun.module_login;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class GuideViewpagerAdapter extends PagerAdapter {
    private List<SimpleDraweeView> Views;

    public GuideViewpagerAdapter(List<SimpleDraweeView> views) {
        this.Views = views;
    }

    @Override
    public int getCount() {
        return Views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        // TODO Auto-generated method stub
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(Views.get(position));
        return Views.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
