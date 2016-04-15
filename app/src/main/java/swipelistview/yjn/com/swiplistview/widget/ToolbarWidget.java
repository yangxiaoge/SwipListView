package swipelistview.yjn.com.swiplistview.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import swipelistview.yjn.com.swiplistview.R;

/**
 * Description: 自定义 toolbar
 * Author 0027004702.
 * Time:16/1/26 16:53
 * Version:1.0
 * Task:
 */
public class ToolbarWidget extends Toolbar {

    private Context context;

    private ToolbarBtnOnclickListener listener;

    private ToolbarMenuOnclickListener menuOnclickListener;

    // TODO 从属性配置文件中获取
    private int mTitleMarginTop;

    private int mTitleMarginBottom;

    private int mTitleMarginStart;

    private int mTitleMarginEnd;

    // TODO navigation icon 这里其实不应该仅仅是显示或不显示后退键
    private static final Class superView = ToolbarWidget.class.getSuperclass();

    @InjectView(R.id.widget_toolbar_title)
    TextView mTitleTextView;

    @InjectView(R.id.widget_toolbar_subtitle)
    TextView mSubtitleTextView;

    @InjectView(R.id.toolbar_l_nav)
    ImageButton leftNavBtn;

    @InjectView((R.id.toolbar_r_nav))
    ImageButton rightNavBtn;

    public ToolbarWidget(Context context) {
        this(context, null);
    }

    public ToolbarWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToolbarWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;

        View view = LayoutInflater.from(context).inflate(R.layout.widget_toolbar, this);

        ButterKnife.inject(this, view);

        initView(attrs, defStyleAttr);

    }

    private void initView(AttributeSet attrs, int defStyleAttr) {

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.toolbarWidget);

        Drawable lNavIcon = ta.getDrawable(R.styleable.toolbarWidget_leftnav);
        if (lNavIcon != null) {
//            leftNavBtn.setBackgroundDrawable(lNavIcon);

            leftNavBtn.setImageDrawable(lNavIcon);
            leftNavBtn.setVisibility(VISIBLE);
        }

        Drawable rNavIcon = ta.getDrawable(R.styleable.toolbarWidget_rightnav);
        if (rNavIcon != null) {
            rightNavBtn.setBackgroundDrawable(rNavIcon);
            rightNavBtn.setVisibility(VISIBLE);
        }

        final CharSequence title = ta.getText(R.styleable.toolbarWidget_title);
        if (!TextUtils.isEmpty(title)) {
            mTitleTextView.setText(title);
        }

        if (ta.hasValue(R.styleable.toolbarWidget_titlecolor)) {
            mTitleTextView.setTextColor(ta.getColor(R.styleable.toolbarWidget_titlecolor, 0xffffffff));
        }


        // TODO subtitle

    }

    @OnClick(R.id.toolbar_l_nav)
    void leftNavBtnOnclick() {
        this.listener.onClick(leftNavBtn);
    }

    @OnClick(R.id.toolbar_r_nav)
    void rightNavBtnOnClick() {
        this.menuOnclickListener.menuOnclick(rightNavBtn);
    }

    public void setNavBtnClickListener(ToolbarBtnOnclickListener listener) {
        this.listener = listener;
    }

    public void setMenuOnclickListener(ToolbarMenuOnclickListener listener) {
        this.menuOnclickListener = listener;
    }

    @Override
    public void setSubtitle(CharSequence subtitle) {
        mSubtitleTextView.setVisibility(View.VISIBLE);
        mSubtitleTextView.setText(subtitle);
        super.setSubtitle(subtitle);
    }

    public interface ToolbarBtnOnclickListener {
        void onClick(View v);
    }

    public interface ToolbarMenuOnclickListener {
        void menuOnclick(View v);
    }


}
