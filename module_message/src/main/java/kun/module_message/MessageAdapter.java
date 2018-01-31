package kun.module_message;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kun.module_message.R;
import com.kun.module_message.databinding.MessageItemBinding;

import java.util.List;

import kun.bdbd.coremodel.datamodel.http.entities.MessageData;

/**
 * Created by HOME_PC on 2018/1/31.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    MessageItemClickCallback callback;
    List<MessageData.ResultsBean> messagelist;

    public MessageAdapter(MessageItemClickCallback callback) {
        this.callback = callback;
    }

    public void seMessageList(final List<MessageData.ResultsBean> list) {
        if (messagelist == null) {
            messagelist = list;
            notifyItemRangeInserted(0, messagelist.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return messagelist.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    MessageData.ResultsBean oldData = messagelist.get(oldItemPosition);
                    MessageData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    MessageData.ResultsBean oldData = messagelist.get(oldItemPosition);
                    MessageData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.get_id() == newData.get_id()
                            && oldData.getCreatedAt() == newData.getCreatedAt()
                            && oldData.getPublishedAt() == newData.getPublishedAt()
                            && oldData.getSource() == newData.getSource();
                }
            });
            messagelist = list;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MessageItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.message_item, parent, false);
        return new MessageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.binding.setMessageItem(messagelist.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return messagelist == null ? 0 : messagelist.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        MessageItemBinding binding;

        public MessageViewHolder(MessageItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
