package lilee.hd.anotterredditapptwo.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapptwo.R;
import lilee.hd.anotterredditapptwo.adapter.PostViewAdapter;
import lilee.hd.anotterredditapptwo.model.Children;
import lilee.hd.anotterredditapptwo.model.Post;
import lilee.hd.anotterredditapptwo.viewmodel.PostViewModel;

public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";
    @BindView(R.id.post_subreddit_detail)
    TextView subNameView;
    @BindView(R.id.post_author_detail)
    TextView authorView;
    @BindView(R.id.post_thumbnail_detail)
    ImageView postImg;
    @BindView(R.id.post_title_view_detail)
    TextView titleView;
    @BindView(R.id.post_text_detail)
    TextView postBodyView;
    @BindView(R.id.commentsNum_detail)
    TextView commentNumView;
    @BindView(R.id.share_img_detail)
    ImageView share;
    @BindView(R.id.comment_list_rv)
    RecyclerView commentRecyclerView;
    private PostViewModel mPostViewModel;
    private PostViewAdapter adapter;
    private Post post;

    public DetailFragment() {
    }

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPostViewModel = ViewModelProviders.of(getActivity()).get(PostViewModel.class);
        mPostViewModel.getCurrentPost().observe(getActivity(),
                children -> {
                    bindViews();
                });
    }

    private void bindViews(){
        Post post = mPostViewModel.getCurrentPost().getValue();
        if (post != null) {
            subNameView.setText(post.getSubredditR());
            authorView.setText(post.getAuthor());
            titleView.setText(post.getTitle());
            postBodyView.setText(post.getBody());

            if (post.getImageUrl() == null) {
                postImg.setVisibility(View.GONE);
            } else {
                RequestOptions defaultOptions = new RequestOptions()
                        .error(null);
                Glide.with(getContext())
                        .setDefaultRequestOptions(defaultOptions)
                        .load(post.getImageUrl())
                        .centerInside()
                        .into(postImg);
            }
            if (post.getBody().isEmpty()) {
                postBodyView.setVisibility(View.GONE);
            } else {
                postBodyView.setText(post.getBody());
            }
        }
    }

}
