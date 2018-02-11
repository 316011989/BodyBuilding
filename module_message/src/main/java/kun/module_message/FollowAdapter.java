package kun.module_message;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kun.module_message.R;
import com.kun.module_message.databinding.FollowItemBinding;

import java.util.List;

import kun.bdbd.coremodel.datamodel.http.entities.FollowData;

/**
 * Created by zhangyl1 on 2018/01/22.
 */

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.FollowViewHolder> {
    List<FollowData.ResultsBean> followList;
    FollowItemClickCallback followItemClickCallback;

    /**
     * 构造方法传入点击监听器
     *
     * @param itemClickCallback
     */
    public FollowAdapter(@Nullable FollowItemClickCallback itemClickCallback) {
        followItemClickCallback = itemClickCallback;
    }

    public void setFollowList(final List<FollowData.ResultsBean> list) {
        if (followList == null) {
            followList = list;
            notifyItemRangeInserted(0, followList.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return followList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    FollowData.ResultsBean oldData = followList.get(oldItemPosition);
                    FollowData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    FollowData.ResultsBean oldData = followList.get(oldItemPosition);
                    FollowData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id()
                            && oldData.getCreatedAt() == newData.getCreatedAt()
                            && oldData.getPublishedAt() == newData.getPublishedAt()
                            && oldData.getSource() == newData.getSource();
                }
            });
            followList = list;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public FollowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FollowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.follow_item, parent, false);
        binding.setCallback(followItemClickCallback);
        return new FollowViewHolder(binding);
    }


    @Override
    public int getItemCount() {
        return followList == null ? 0 : followList.size();
    }

    @Override
    public void onBindViewHolder(FollowViewHolder holder, int position) {
        holder.binding.setFollowItem(followList.get(position));
        holder.binding.executePendingBindings();
    }

    static class FollowViewHolder extends RecyclerView.ViewHolder {
        FollowItemBinding binding;

        public FollowViewHolder(FollowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
