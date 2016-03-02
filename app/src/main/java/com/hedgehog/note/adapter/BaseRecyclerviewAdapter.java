package com.hedgehog.note.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

/**
 * Created by hedge_hog on 16/3/2.
 */
public class BaseRecyclerviewAdapter<E> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context mContext;
    private List<E> list;
    private Map<Integer, onInternalClickListener<E>> canClickItem;

    public BaseRecyclerviewAdapter(List<E> list) {
        this.list = list;
    }

    public BaseRecyclerviewAdapter(Context mContext, List<E> list) {
        this.mContext = mContext;
        this.list = list;
    }

    /**
     *
     * @param e
     */
    public void add(E e) {
        this.list.add(0, e);
        notifyItemInserted(0);
    }





    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface onInternalClickListener<T> {
        void OnClickListener(View parentV, View v, Integer position,
                             T values);
        void OnLongClickListener(View parentV, View v, Integer position,
                                 T values);
    }
}
