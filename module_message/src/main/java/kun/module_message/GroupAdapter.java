package kun.module_message;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kun.module_message.R;
import com.kun.module_message.databinding.GroupItemBinding;

import java.util.List;

import kun.bdbd.coremodel.datamodel.http.entities.GroupData;

/**
 * Created by zhangyl1 on 2018/01/22.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {
    List<GroupData.ResultsBean> groupList;
    GroupItemClickCallback groupItemClickCallback;

    /**
     * 构造方法传入点击监听器
     *
     * @param itemClickCallback
     */
    public GroupAdapter(@Nullable GroupItemClickCallback itemClickCallback) {
        groupItemClickCallback = itemClickCallback;
    }

    public void setGroupList(final List<GroupData.ResultsBean> list) {
        if (groupList == null) {
            groupList = list;
            notifyItemRangeInserted(0, groupList.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return groupList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    GroupData.ResultsBean oldData = groupList.get(oldItemPosition);
                    GroupData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    GroupData.ResultsBean oldData = groupList.get(oldItemPosition);
                    GroupData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id()
                            && oldData.getCreatedAt() == newData.getCreatedAt()
                            && oldData.getPublishedAt() == newData.getPublishedAt()
                            && oldData.getSource() == newData.getSource();
                }
            });
            groupList = list;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GroupItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.group_item, parent, false);
        binding.setCallback(groupItemClickCallback);
        return new GroupViewHolder(binding);
    }


    @Override
    public int getItemCount() {
        return groupList == null ? 0 : groupList.size();
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        holder.binding.setGroupItem(groupList.get(position));
        holder.binding.executePendingBindings();
    }

    static class GroupViewHolder extends RecyclerView.ViewHolder {
        GroupItemBinding binding;

        public GroupViewHolder(GroupItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
