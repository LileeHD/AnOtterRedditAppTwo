package lilee.hd.anotterredditapptwo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapptwo.R;
import lilee.hd.anotterredditapptwo.model.Subreddit;
import lilee.hd.anotterredditapptwo.viewmodel.SubredditViewModel;

public class SubredditViewAdapter extends RecyclerView.Adapter<SubredditViewAdapter.SubredditViewHolder> {

    private ArrayList<Subreddit> mSubreddits = new ArrayList<>();
    private Context context;
    private Subreddit subreddit;
    private SubredditViewModel subViewModel;
    private SubClickListener mListener;

    public SubredditViewAdapter(Context context, SubredditViewModel viewModel, SubClickListener listener) {
        this.context = context;
        this.subViewModel = viewModel;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public SubredditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_subreddit_item,
                parent, false);
        return new SubredditViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SubredditViewHolder holder, int position) {
        if (mSubreddits != null) {
            subreddit = mSubreddits.get(position);
            holder.subredditName.setText(subreddit.getName());

            if (subreddit.getIconUrl() == null) {
                holder.subIcon.setVisibility(View.GONE);
            } else {
                imageLoader(holder);
            }
            holder.initRemoveBtn(mSubreddits.get(position));
        }
    }

    private void imageLoader(SubredditViewHolder holder) {
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_otter);
        Glide.with(context)
                .setDefaultRequestOptions(defaultOptions)
                .load(subreddit.getIconUrl())
                .placeholder(R.drawable.ic_otter)
                .into(holder.subIcon);
    }

    public void updateList(ArrayList<Subreddit> subreddits) {
        mSubreddits = subreddits;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mSubreddits != null && mSubreddits.size() > 0) {
            return mSubreddits.size();
        } else {
            return 0;
        }
    }
    public interface SubClickListener {
        void onSubClick(int position);
    }
    class SubredditViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.subreddit_name)
        TextView subredditName;
        @BindView(R.id.subreddit_icon)
        ImageView subIcon;
        @BindView(R.id.close_icon)
        ImageButton removeBtn;
        SubClickListener mListener;

        SubredditViewHolder(@NonNull View itemView, SubClickListener listener){
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mListener = listener;
            itemView.setOnClickListener(this);
        }

        private void initRemoveBtn(Subreddit subreddit){
            removeBtn.setOnClickListener(v -> subViewModel.removeSubreddit(subreddit));
        }


        @Override
        public void onClick(View v) {
            mListener.onSubClick(getAdapterPosition());
        }
    }

}
