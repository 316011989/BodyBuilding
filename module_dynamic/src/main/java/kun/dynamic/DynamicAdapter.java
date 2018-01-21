package kun.dynamic;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import kun.bdbd.coremodel.datamodel.http.entities.DynamicData;
import kun.dynamic.databinding.DynamicItemBinding;

/**
 * Created by zhangyl1 on 2018/01/22.
 */

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.DynamicViewHolder> {
    List<DynamicData.ResultsBean> dynamicList;
    DynamicItemClickCallback dynamicItemClickCallback;

    /**
     * 构造方法传入点击监听器
     *
     * @param itemClickCallback
     */
    public DynamicAdapter(@Nullable DynamicItemClickCallback itemClickCallback) {
        dynamicItemClickCallback = itemClickCallback;
    }

    public void setDynamicList(final List<DynamicData.ResultsBean> list) {
        if (dynamicList == null) {
            dynamicList = list;
            notifyItemRangeInserted(0, dynamicList.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return dynamicList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    DynamicData.ResultsBean oldData = dynamicList.get(oldItemPosition);
                    DynamicData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    DynamicData.ResultsBean oldData = dynamicList.get(oldItemPosition);
                    DynamicData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id()
                            && oldData.getCreatedAt() == newData.getCreatedAt()
                            && oldData.getPublishedAt() == newData.getPublishedAt()
                            && oldData.getSource() == newData.getSource();
                }
            });
            dynamicList = list;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public DynamicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DynamicItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.dynamic_item, parent, false);
        binding.setCallback(dynamicItemClickCallback);
        return new DynamicViewHolder(binding);
    }


    @Override
    public int getItemCount() {
        return dynamicList == null ? 0 : dynamicList.size();
    }

    @Override
    public void onBindViewHolder(DynamicViewHolder holder, int position) {
        holder.binding.setDynamicItem(dynamicList.get(position));
        holder.binding.executePendingBindings();
    }

    static class DynamicViewHolder extends RecyclerView.ViewHolder {
        DynamicItemBinding binding;

        public DynamicViewHolder(DynamicItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
