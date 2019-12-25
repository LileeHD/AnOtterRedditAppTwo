package lilee.hd.anotterredditapptwo.ui;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapptwo.R;
import lilee.hd.anotterredditapptwo.adapter.PostViewAdapter;
import lilee.hd.anotterredditapptwo.model.Children;
import lilee.hd.anotterredditapptwo.model.Post;
import lilee.hd.anotterredditapptwo.viewmodel.PostViewModel;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class OtterFragment extends Fragment implements PostViewAdapter.PostClickListener {
    private static final String TAG = "OtterFragment";
    @BindView(R.id.logo_otter)
    ImageView logo;
    @BindView(R.id.list_view_otter)
    RecyclerView postView;
    @BindView(R.id.swipe_layout_otter)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.connection_info_otter)
    TextView connectionInfo;
    @BindView(R.id.progressbar_otter)
    ProgressBar progressBar;

    private Snackbar snackbar;
    private PostViewAdapter adapter;
    private Children post;
    private Post currentpost;
    private ArrayList<Children> postsList = new ArrayList<>();
    private PostViewModel mPostViewModel;
    private boolean mIsRefreshing = false;
    private String sort = "new";

    public OtterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otter_feed, container, false);
        ButterKnife.bind(this, view);
        Log.d(TAG, "onCreateView: OTTER");
        checkConnection();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPostViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
    }

    private void initViewModel() {
        mPostViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        mPostViewModel.initOtter();
        mPostViewModel.getFeedRepository().observe(this, feed -> {
            List<Children> childrenList = feed.getData().getChildren();
            postsList.addAll(childrenList);
            adapter.notifyDataSetChanged();
            Log.d(TAG, "initViewModel: ");
        });
    }

    private void initPostView() {
        if (adapter == null) {
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(Objects.requireNonNull(getContext()),
                    DividerItemDecoration.VERTICAL);
            adapter = new PostViewAdapter(getContext(), postsList, this);
            postView.addItemDecoration(dividerItemDecoration);
            postView.setItemAnimator(new DefaultItemAnimator());
            postView.setAdapter(adapter);
            postView.setLayoutManager(new LinearLayoutManager(getContext()));
            postView.setHasFixedSize(true);
            mSwipeRefreshLayout.setOnRefreshListener(() -> {
                adapter.notifyDataSetChanged();
            });
            Log.d(TAG, "initPostView: ");
        }
    }

    // Click
    @Override
    public void onPostClick(PostViewModel model, int position) {
        post = postsList.get(position);
        mPostViewModel.sendData(post.getData());
        swapFragment();
        Log.d(TAG, "onPostClick: ");
    }

    private void swapFragment() {
        DetailFragment detailFragment = new DetailFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detailFragment);
        transaction.addToBackStack("tag");
        transaction.commit();
    }

    //  Navigation
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_refresh) {
            mSwipeRefreshLayout.setRefreshing(true);
            initViewModel();
            initPostView();
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkConnection() {
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager != null ? manager.getActiveNetworkInfo() : null;
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            initViewModel();
            initPostView();
        } else {
            connectionInfo.setVisibility(View.VISIBLE);
            connectionInfo.setText(getText(R.string.no_connected));
            mSwipeRefreshLayout.setVisibility(View.GONE);
        }
    }
}
