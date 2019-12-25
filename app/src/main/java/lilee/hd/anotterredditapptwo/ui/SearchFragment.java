package lilee.hd.anotterredditapptwo.ui;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapptwo.R;
import lilee.hd.anotterredditapptwo.adapter.SubredditViewAdapter;
import lilee.hd.anotterredditapptwo.model.SubredditNode;
import lilee.hd.anotterredditapptwo.viewmodel.RedditRepository;
import lilee.hd.anotterredditapptwo.viewmodel.SubredditViewModel;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static lilee.hd.anotterredditapptwo.R.string.no_connected;

public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    @BindView(R.id.search_edit_text)
    AppCompatEditText searchEditText;
    @BindView(R.id.create_feed)
    Button createFeed;
    @BindView(R.id.sub_list_view)
    RecyclerView postView;
    @BindView(R.id.sub_swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.sub_connection_info)
    TextView connectionInfo;
    @BindView(R.id.save)
    Button saveBtn;

    private SubredditViewAdapter adapter;
    private ArrayList<SubredditNode> subreddits = new ArrayList<>();
    private SubredditViewModel viewModel = new SubredditViewModel();
    private String mSearchResult;
    private RedditRepository repository;

    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        checkConnection();
        searchEditText.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                closeKeyboard();
                return true;
            }
            return false;
        });
        Log.d(TAG, "onCreateView: MEI");
        saveInput();
        return view;
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(SubredditViewModel.class);
        viewModel.initFetchSubs(mSearchResult);
        viewModel.getSubredditName(mSearchResult).observe(this, subreddits -> {
            adapter.notifyDataSetChanged();
        });
        initListView();
    }

    //                if (!subreddit.isNsfw() && !feedName.isEmpty()){
////                repository.insertSubreddit(subreddit);
//        Log.d(TAG, "initViewModel: "+ feedName);
//    }else{
//        Toast.makeText(getContext(), error_search, Toast.LENGTH_LONG).show();
//    }
    private void saveInput() {
        String feedName = searchEditText.getText().toString();
        saveBtn.setOnClickListener(v -> {
            initListView();
            if (feedName.equals("")) {
                mSearchResult = feedName;
                initViewModel();
                Log.d(TAG, "saveInput: " + mSearchResult);
            }else {
                Toast.makeText(getContext(), "Empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //    private void saveInput(final String input){
//
//        saveBtn.setOnClickListener(v -> {
//            if (input.isEmpty()){
//                Toast.makeText(getContext(), input_empty, Toast.LENGTH_SHORT).show();
//            }else{
//                Log.d(TAG, "saveInput: " + input);
//                Toast.makeText(getContext(), db_insert_msg , Toast.LENGTH_SHORT).show();
////                TODO add to database
//            }
//        });
//    }
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
    private void initListView() {
        if (adapter == null) {
            DividerItemDecoration dividerItemDecoration =
                    new DividerItemDecoration(Objects.requireNonNull(getContext()),
                            DividerItemDecoration.VERTICAL);
            adapter = new SubredditViewAdapter(subreddits, getContext());
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

    private void checkConnection() {
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager != null ? manager.getActiveNetworkInfo() : null;
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            initListView();
        } else {
            connectionInfo.setVisibility(View.VISIBLE);
            connectionInfo.setText(getText(no_connected));
            mSwipeRefreshLayout.setVisibility(View.GONE);
        }
    }

    private void closeKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) searchEditText
                        .getContext()
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
        }
    }


}
