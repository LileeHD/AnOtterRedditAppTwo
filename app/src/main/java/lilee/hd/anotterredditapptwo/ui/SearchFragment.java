package lilee.hd.anotterredditapptwo.ui;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
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
import lilee.hd.anotterredditapptwo.adapter.SubredditViewAdapter;
import lilee.hd.anotterredditapptwo.model.Children;
import lilee.hd.anotterredditapptwo.model.Subreddit;
import lilee.hd.anotterredditapptwo.viewmodel.PostViewModel;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class SearchFragment extends Fragment implements PostViewAdapter.PostClickListener {
    private static final String TAG = "SearchFragment";
    @BindView(R.id.search_edit_text)
    AppCompatEditText searchEditText;
    @BindView(R.id.create_feed)
    Button createFeed;
    @BindView(R.id.home_list_view)
    RecyclerView postView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.connection_info)
    TextView connectionInfo;

    private Snackbar snackbar;
    private SubredditViewAdapter adapter;
    private ArrayList<Subreddit> subreddits = new ArrayList<>();
    private PostViewModel mPostViewModel;
    private String mSearchResult;
    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
//        checkConnection();
//        searchBtn.setOnClickListener(v -> {
//            initPostView();
//            String feedName = searchEditText.getText().toString();
//            if (!feedName.equals("")){
//                mSearchResult = feedName;
//                mPostViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
//                mPostViewModel.initHome();
//                mPostViewModel.getSearchResults(mSearchResult).observe(this, feed -> {
//                    List<Children> childrenList = feed.getData().getChildren();
////                    postsList.addAll(childrenList);
//                    adapter.notifyDataSetChanged();
//                    Log.d(TAG, "initViewModel: ");
//                });
//            }else {
//                Log.d(TAG, "onClick: SET A SNACK BAR ");
//            }
//        });
//        searchEditText.setOnKeyListener((v, keyCode, event) -> {
//            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
////                onSearchClick(v);
//                closeKeyboard();
//                return true;
//            }
//            return false;
//        });
        Log.d(TAG, "onCreateView: PHARAH");

        return view;
    }
    private void initViewModel() {
        mPostViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        mPostViewModel.initHome();
        mPostViewModel.getFeedRepository().observe(this, feed -> {
            List<Children> childrenList = feed.getData().getChildren();
//            postsList.addAll(childrenList);
            adapter.notifyDataSetChanged();
            Log.d(TAG, "initViewModel: ");
        });

    }
    private void initPostView() {
        if (adapter == null) {
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(Objects.requireNonNull(getContext()),
                    DividerItemDecoration.VERTICAL);
            adapter = new SubredditViewAdapter();
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
    private void checkConnection(){
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager != null ? manager.getActiveNetworkInfo() : null;
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()){

        }else{
            connectionInfo.setVisibility(View.VISIBLE);
            connectionInfo.setText(getText(R.string.no_connected));
            mSwipeRefreshLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPostClick(PostViewModel model, int position) {
        createFeed.setOnClickListener(v -> {
//            send stored subreddits
        });
        swapFragment();
        Log.d(TAG, "onPostClick: ");
    }
    private void swapFragment(){
        OtterFragment otterFragment = new OtterFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, otterFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
