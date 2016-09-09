package com.yushilei.slidingview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yushilei.slidingview.widget.SlidingView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by  yushilei.
 * @time 2016/9/8 -16:47.
 * @Desc
 */
public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.VH> implements View.OnClickListener {
    Context mContext;
    List<Bean> data = new ArrayList<>();


    public RecyAdapter(Context context) {
        mContext = context;
        for (int i = 0; i < 40; i++) {
            data.add(new Bean("item+" + i));
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        VH tag = new VH(view);
        view.setTag(tag);
        return tag;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Bean bean = data.get(position);
        ((SlidingView) holder.itemView).setBean(data.get(position));
        if (bean.isOpen()) {
            ((SlidingView) holder.itemView).setIsOpen(true);
        } else {
            ((SlidingView) holder.itemView).setIsOpen(false);
        }
        holder.tv.setText(bean.getItem());
        holder.dragV.setTag(bean);
        holder.moveV.setTag(bean);
        holder.dragV.setOnClickListener(this);
        holder.moveV.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_slid_move_view:
                Object tag = v.getTag();
                Toast.makeText(mContext, "点击了第" + ((Bean) tag).getItem(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_slid_drag_view:
                Object obj = v.getTag();
                Toast.makeText(mContext, "点击删除", Toast.LENGTH_SHORT).show();
                int indexOf = data.indexOf(obj);
                data.remove(obj);
                notifyItemRemoved(indexOf);
                break;
        }

    }

    public static class VH extends RecyclerView.ViewHolder {
        private TextView tv;
        private View dragV;
        private View moveV;

        public VH(View itemView) {
            super(itemView);
            tv = ((TextView) itemView.findViewById(R.id.item_tv));
            dragV = itemView.findViewById(R.id.id_slid_drag_view);
            moveV = itemView.findViewById(R.id.id_slid_move_view);
        }
    }
}
