package peixun.gitdroid.splash;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;
import peixun.gitdroid.R;

/**
 * Created by ~Wang~ on 2016/7/26.
 */
public class SplashPagerFragment extends Fragment {
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;
    @Bind(R.id.ivPhoneBlank)
    ImageView ivPhoneBlank;
    @Bind(R.id.ivPhoneFont)
    ImageView ivPhoneFont;
    @Bind(R.id.layoutPhone)
    FrameLayout layoutPhone;
    @Bind(R.id.content)
    FrameLayout content;
    //    private ViewPager viewPager;
    private SplashPagerAdapter splashPagerAdapter;
//    private CircleIndicator indicator;//指示器
//    private FrameLayout content;//当前页面Layout（主要住了更新其背景颜色）
    private int colorGreen;
    private int colorRed;
    private int colorYellow;

//    private FrameLayout layoutPhone;//屏幕中央的手机
//    private ImageView ivPhoneBlank;//屏幕中央的手机的整个北京
//    private ImageView ivPhoneFont;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_pager, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        colorGreen = getResources().getColor(R.color.colorGreen);
        colorRed = getResources().getColor(R.color.colorRed);
        colorYellow = getResources().getColor(R.color.colorYellow);

//        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
//        indicator =(CircleIndicator) view.findViewById(R.id.indicator);
//        frameLayout = (FrameLayout) view.findViewById(R.id.content);
//
//        layoutPhone = (FrameLayout) view.findViewById(R.id.layoutPhone);
//        ivPhoneBlank = (ImageView) view.findViewById(R.id.ivPhoneBlank);
//        ivPhoneFont = (ImageView) view.findViewById(R.id.ivPhoneFont);

        splashPagerAdapter = new SplashPagerAdapter(getContext());
        viewPager.setAdapter(splashPagerAdapter);
        indicator.setViewPager(viewPager);
        //添加viewpager监听（为了动画）
        viewPager.addOnPageChangeListener(pageChangeListener);
        viewPager.addOnPageChangeListener(phonepageChangeListener);

    }

    //主要为了负责做背景颜色的渐变处理
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        //IntEvaluator intEvaluator = new IntEvaluator();//颜色的DPI(颜色取值起)
        final ArgbEvaluator argbEvaluator = new ArgbEvaluator();

        //在onPageScrolled过程中进行触发的方法
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //int position, float positionOffset, int positionOffsetPixels.移动到哪里，移动是多少
            //intEvaluator.evaluate(0.5f,100,1000);//开始的变量值。结束的变量值，中间还有一个值。0,5f表示去中间的值，加入是0,1f就是去1
            //第一个页面到第二个页面之间都是0
            if (position == 0) {
                int color = (int) argbEvaluator.evaluate(positionOffset, colorGreen, colorRed);
                content.setBackgroundColor(color);
                return;
            }
            //第二个页面到第三个页面之间都是1
            if (position == 1) {
                int color = (int) argbEvaluator.evaluate(positionOffset, colorRed, colorYellow);
                content.setBackgroundColor(color);

            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    //主要为了做手机的动画处理效果（如平移、缩放、透明度的变化）
    private ViewPager.OnPageChangeListener phonepageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //int position, float positionOffset, int positionOffsetPixels.移动到哪里，移动是多少
            //在第二个页面和第三个页面之间的时候
            if (position == 1) {
                layoutPhone.setTranslationX(-positionOffsetPixels);
            }
            //在第一个页面和第二个页面之间的时候
            if (position == 0) {
                //手机的缩放处理
                float scale = 0.3f + positionOffset * 0.7f;
                layoutPhone.setScaleX(scale);
                layoutPhone.setScaleY(scale);
                //手机的平移处理
                int scale1 = (int) ((positionOffset - 1) * 200);//200是从中间开始向左边移动200
                layoutPhone.setTranslationX(scale1);
                //手机的字体的渐变
                ivPhoneFont.setAlpha(positionOffset);

            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
