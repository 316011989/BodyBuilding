package kun.module_message;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kun.module_message.R;
import com.kun.module_message.databinding.FanItemBinding;

import java.util.List;

import kun.bdbd.coremodel.datamodel.http.entities.FansData;

/**
 * Created by zhangyl1 on 2018/01/22.
 */

public class FansAdapter extends RecyclerView.Adapter<FansAdapter.FansViewHolder> {
    List<FansData.ResultsBean> fansList;
    FanItemClickCallback fanItemClickCallback;

    /**
     * 构造方法传入点击监听器
     *
     * @param itemClickCallback
     */
    public FansAdapter(@Nullable FanItemClickCallback itemClickCallback) {
        fanItemClickCallback = itemClickCallback;
    }

    public void setFansList(final List<FansData.ResultsBean> list) {
        if (fansList == null) {
            fansList = list;
            notifyItemRangeInserted(0, fansList.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return fansList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    FansData.ResultsBean oldData = fansList.get(oldItemPosition);
                    FansData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    FansData.ResultsBean oldData = fansList.get(oldItemPosition);
                    FansData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id()
                            && oldData.getCreatedAt() == newData.getCreatedAt()
                            && oldData.getPublishedAt() == newData.getPublishedAt()
                            && oldData.getSource() == newData.getSource();
                }
            });
            fansList = list;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public FansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FanItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fan_item, parent, false);
        binding.setCallback(fanItemClickCallback);
        return new FansViewHolder(binding);
    }


    @Override
    public int getItemCount() {
        return fansList == null ? 0 : fansList.size();
    }

    @Override
    public void onBindViewHolder(FansViewHolder holder, int position) {
        holder.binding.setFanItem(fansList.get(position));
        holder.binding.executePendingBindings();
    }

    static class FansViewHolder extends RecyclerView.ViewHolder {
        FanItemBinding binding;

        public FansViewHolder(FanItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
