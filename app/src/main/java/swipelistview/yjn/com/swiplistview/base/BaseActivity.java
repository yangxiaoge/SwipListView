package swipelistview.yjn.com.swiplistview.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import swipelistview.yjn.com.swiplistview.widget.ToolbarWidget;

/**
 * Created by 0027006362 [liu.tong33@zte.com.cn]
 * Date: 2016/3/8
 * Time: 11:04
 */
public class BaseActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setBackListener(Activity context,ToolbarWidget toolbar) {
        final Activity mContext = context;
        if(toolbar != null){
            toolbar.setNavBtnClickListener(new ToolbarWidget.ToolbarBtnOnclickListener() {
                @Override
                public void onClick(View v) {
                    mContext.onBackPressed();
                }
            });
        }
    }
}
