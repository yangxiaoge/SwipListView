package swipelistview.yjn.com.swiplistview.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.yjn.swipelistview.swipelistviewlibrary.widget.SwipeMenu;
import com.yjn.swipelistview.swipelistviewlibrary.widget.SwipeMenuCreator;
import com.yjn.swipelistview.swipelistviewlibrary.widget.SwipeMenuItem;
import com.yjn.swipelistview.swipelistviewlibrary.widget.SwipeMenuListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import swipelistview.yjn.com.swiplistview.R;
import swipelistview.yjn.com.swiplistview.adapter.TransferAdapter;
import swipelistview.yjn.com.swiplistview.base.BaseActivity;
import swipelistview.yjn.com.swiplistview.widget.ToolbarWidget;

/**
 * Description:
 * Author: 0027008122 [yang.jianan@zte.com.cn]
 * Time: 2016/3/31 14:18
 * Version: 1.0
 */
public class MainActivity extends BaseActivity {

    @InjectView(R.id.transfer_title)
    ToolbarWidget transferTitle;
    @InjectView(R.id.rechage_amount_tv)
    TextView rechageAmountTv;
    @InjectView(R.id.recharge_count_tv)
    TextView rechargeCountTv;
    @InjectView(R.id.list_view)
    SwipeMenuListView listView;

    //listview 数据
    List<HashMap<String, Object>> listViewData;
    //listview 假数据
    List<HashMap<String, Object>> localTestData;

    List<HashMap<String, Object>> mAppList;

    private TransferAdapter transferAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer);
        ButterKnife.inject(this);
        setBackListener(this, transferTitle);

        initData();

    }

    private void initData() {

        localTestData = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> testData = new HashMap<>();
            testData.put("happy", "happty" + i);
            if (i % 2 == 0) {
                testData.put("quantity", "15" + i);
            }
            testData.put("amount", "$" + "610" + i);
            localTestData.add(testData);
        }

        transferAdapter = new TransferAdapter(MainActivity.this, localTestData);
        listView.setAdapter(transferAdapter);

        final SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.parseColor("#fe3c3a")));
                deleteItem.setWidth(dp2px(89));
                deleteItem.setTitle("Delete");
                deleteItem.setTitleColor(Color.WHITE);
                deleteItem.setTitleSize(20);
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        listView.setMenuCreator(creator);

        // listener item click event
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                if (index == 0) {
                    // delete
                    localTestData.remove(position);
                    transferAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                this.getResources().getDisplayMetrics());
    }

    @OnClick({R.id.rechage_amount_tv, R.id.recharge_count_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rechage_amount_tv:
                break;
            case R.id.recharge_count_tv:
                break;
        }
    }
}
