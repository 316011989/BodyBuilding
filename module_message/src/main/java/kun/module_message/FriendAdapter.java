package kun.module_message;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kun.module_message.R;
import com.kun.module_message.databinding.FriendItemBinding;

import java.util.List;

import kun.bdbd.coremodel.datamodel.http.entities.FriendData;

/**
 * Created by zhangyl1 on 2018/01/22.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {
    List<FriendData.ResultsBean> friendList;
    FriendItemClickCallback friendItemClickCallback;

    /**
     * 构造方法传入点击监听器
     *
     * @param itemClickCallback
     */
    public FriendAdapter(@Nullable FriendItemClickCallback itemClickCallback) {
        friendItemClickCallback = itemClickCallback;
    }

    public void setFriendList(final List<FriendData.ResultsBean> list) {
        if (friendList == null) {
            friendList = list;
            notifyItemRangeInserted(0, friendList.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return friendList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    FriendData.ResultsBean oldData = friendList.get(oldItemPosition);
                    FriendData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    FriendData.ResultsBean oldData = friendList.get(oldItemPosition);
                    FriendData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id()
                            && oldData.getCreatedAt() == newData.getCreatedAt()
                            && oldData.getPublishedAt() == newData.getPublishedAt()
                            && oldData.getSource() == newData.getSource();
                }
            });
            friendList = list;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FriendItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.friend_item, parent, false);
        binding.setCallback(friendItemClickCallback);
        return new FriendViewHolder(binding);
    }


    @Override
    public int getItemCount() {
        return friendList == null ? 0 : friendList.size();
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        holder.binding.setFriendItem(friendList.get(position));
        holder.binding.executePendingBindings();
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder {
        FriendItemBinding binding;

        public FriendViewHolder(FriendItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
