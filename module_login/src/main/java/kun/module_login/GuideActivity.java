package kun.module_login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import kun.bdbd.common.base.ARouterPath;
import kun.bdbd.common.base.BaseActivity;
import kun.module_login.databinding.ActivityGuideBinding;

@Route(path = ARouterPath.GuideActivity)
public class GuideActivity extends BaseActivity {
    ActivityGuideBinding binding;
    GuideViewpagerAdapter adapter;
    List<SimpleDraweeView> guideViews;
    int[] pics = new int[]{R.drawable.guide_page1, R.drawable.guide_page2, R.drawable.guide_page3, R.drawable.guide_page4, R.drawable.guide_page5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(GuideActivity.this, R.layout.activity_guide);
        guideViews = new ArrayList<>();
        for (int i = 0; i < pics.length; i++) {
            SimpleDraweeView itemView = new SimpleDraweeView(GuideActivity.this);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            itemView.setScaleType(ImageView.ScaleType.FIT_XY);
            itemView.setImageResource(pics[i]);
            guideViews.add(itemView);
            if (i == pics.length - 1)
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ARouter.getInstance()
                                .build(ARouterPath.LoginActivity)
                                /**可以针对性跳转跳转动画*/
                                .withTransition(R.anim.activity_up_in, R.anim.activity_up_out)
                                .navigation(GuideActivity.this);
                    }
                });
        }
        adapter = new GuideViewpagerAdapter(guideViews);
        binding.guideViewpager.setAdapter(adapter);
    }
}
