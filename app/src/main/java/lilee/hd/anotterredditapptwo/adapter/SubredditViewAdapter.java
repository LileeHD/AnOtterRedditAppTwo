package lilee.hd.anotterredditapptwo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapptwo.R;
import lilee.hd.anotterredditapptwo.model.Subreddit;
import lilee.hd.anotterredditapptwo.model.SubredditNode;
import lilee.hd.anotterredditapptwo.viewmodel.SubredditViewModel;

public class SubredditViewAdapter extends RecyclerView.Adapter<SubredditViewAdapter.SubredditViewHolder> {
    private SubredditViewModel viewModel;
    private List<SubredditNode> subreddits;
    private Context context;
    private SubredditNode subreddit;

    public SubredditViewAdapter(List<SubredditNode> subreddits, Context context) {
        this.subreddits = subreddits;
        this.context = context;
    }

    @NonNull
    @Override
    public SubredditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_subreddit_item,
                parent, false);
        viewModel = new SubredditViewModel();
        return new SubredditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubredditViewHolder holder, int position) {
        subreddit = subreddits.get(position);
        holder.subredditName.setText(subreddit.getData().getName());
        holder.subredditDescription.setText(subreddit.getData().getDescription());
        if (subreddit.getData().getIconUrl() == null) {
            holder.subIcon.setVisibility(View.GONE);
        } else {
            imageLoader(holder);
        }
    }
    private void imageLoader(SubredditViewHolder holder) {
        RequestOptions defaultOptions = new RequestOptions()
                .error(null);
        Glide.with(context)
                .setDefaultRequestOptions(defaultOptions)
                .load(subreddits)
                .into(holder.subIcon);
    }

    @Override
    public int getItemCount() {
        if (subreddits != null && subreddits.size() > 0) {
            return subreddits.size();
        } else {
            return 0;
        }
    }

    public class SubredditViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.subreddit_name)
        TextView subredditName;
        @BindView(R.id.subreddit_description)
        TextView subredditDescription;
        @BindView(R.id.subreddit_icon)
        ImageButton subIcon;
        @BindView(R.id.subscribers_num)
        TextView subscribersNum;

        public SubredditViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
