package com.hedgehog.note.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
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

    public BaseRecyclerviewAdapter(Context mContext,List<E> list) {
        this.list = list;
        this.mContext = mContext;
    }

    /**
     * @param e
     */
    public void add(E e) {
        this.list.add(0, e);
        notifyItemInserted(0);
    }

    /**
     * 更新视图
     *
     * @param e
     * @param fromPosition
     * @param toPosition
     */
    public void update(E e, int fromPosition, int toPosition) {
        this.list.remove(fromPosition);
        this.list.add(toPosition, e);
        if (fromPosition == toPosition) {
            notifyItemChanged(fromPosition);
        } else {
            notifyItemRemoved(fromPosition);
            notifyItemInserted(toPosition);
        }

    }

    public void update(E e, int fromPosition){
        update(e, fromPosition, 0);
    }

    public void update(E e){
        int fromPosition = this.list.indexOf(e);
        update(e, fromPosition);
    }

    public void remove(E e) {
        int position = list.indexOf(e);
        remove(position);
    }

    public void remove(int position) {
        this.list.remove(position);
        notifyItemRemoved(position);
    }

    public void setList(List<E> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    public List<E> getList() {
        return list;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            addInternalClickListener(holder.itemView, position, list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * @param itemView
     * @param position
     * @param valuesMap
     */
    private void addInternalClickListener(final View itemView, final Integer position, final E valuesMap) {
        if (canClickItem != null) {
            for (Integer key : canClickItem.keySet()) {
                View inView = itemView.findViewById(key);

                final onInternalClickListener<E> listener = canClickItem.get(key);

                if (inView != null && listener != null) {
                    inView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.OnClickListener(itemView, v, position, valuesMap);
                        }
                    });

                    inView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            listener.OnLongClickListener(itemView, v, position, valuesMap);
                            return true;
                        }
                    });
                }
            }
        }
    }



    public void setOnInViewClickListener(Integer key, onInternalClickListener<E> onClickListener) {
        if (canClickItem == null)
            canClickItem = new HashMap<>();
        canClickItem.put(key, onClickListener);
    }


    /**
     * 点击长点击的接口
     *
     * @param <T>
     */
    public interface onInternalClickListener<T> {
        void OnClickListener(View parentV, View v, Integer position,
                             T values);

        void OnLongClickListener(View parentV, View v, Integer position,
                                 T values);
    }

    /**
     *
     *
     * @param <T>
     */
    public static class onInternalClickListenerImpl<T> implements onInternalClickListener<T> {

        @Override
        public void OnClickListener(View parentV, View v, Integer position, T values) {

        }

        @Override
        public void OnLongClickListener(View parentV, View v, Integer position, T values) {

        }
    }
}
