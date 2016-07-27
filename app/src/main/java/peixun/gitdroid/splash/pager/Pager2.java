package peixun.gitdroid.splash.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import peixun.gitdroid.R;

/**
 * Created by ~Wang~ on 2016/7/26.
 */
public class Pager2 extends FrameLayout{
    public Pager2(Context context) {
        this(context, null);
    }

    public Pager2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Pager2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        itit();
    }

    private void itit() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_2, this, true);
    }


}
