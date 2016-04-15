package swipelistview.yjn.com.swiplistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import swipelistview.yjn.com.swiplistview.R;
import swipelistview.yjn.com.swiplistview.util.StringUtil;

/**
 * Description:
 * Author: 0027008122 [yang.jianan@zte.com.cn]
 * Time: 2016/3/31 16:29
 * Version: 1.0
 */
public class TransferAdapter extends BaseAdapter {
    private Context context;
    private List<HashMap<String, Object>> data;

    public TransferAdapter(Context context, List<HashMap<String, Object>> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.transfer_li_item, parent, false);
            mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.happyNum.setText((String) data.get(position).get("happy"));

        if (StringUtil.isEmpty((String) data.get(position).get("quantity"))) {
            //如果 quantity 没有值, 就 隐藏quantity这行所属的 LinearLayout
            mHolder.transferQuantityLinear.setVisibility(View.GONE);
        } else {
            mHolder.transferQuantity.setText((String) data.get(position).get("quantity"));
        }

        mHolder.transferTotal.setText((String) data.get(position).get("amount"));

        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.happy_Num)
        TextView happyNum;
        @InjectView(R.id.transfer_quantity)
        TextView transferQuantity;
        @InjectView(R.id.transfer_quantity_linear)
        LinearLayout transferQuantityLinear;
        @InjectView(R.id.transfer_total)
        TextView transferTotal;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
