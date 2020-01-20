package lilee.hd.anotterredditapptwo.home;

import android.os.Bundle;
import android.text.TextUtils;
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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilee.hd.anotterredditapptwo.R;
import lilee.hd.anotterredditapptwo.adapter.SubredditViewAdapter;
import lilee.hd.anotterredditapptwo.model.Subreddit;
import lilee.hd.anotterredditapptwo.reddit.RedditAPI;
import lilee.hd.anotterredditapptwo.util.SendSubredditData;
import lilee.hd.anotterredditapptwo.viewmodel.SubredditRepository;
import lilee.hd.anotterredditapptwo.viewmodel.SubredditViewModel;
import lilee.hd.anotterredditapptwo.viewmodel.SubredditViewModelFactory;

public class SearchFragment extends Fragment implements SubredditViewAdapter.SubClickListener {
    private static final String TAG = "DAO";
    @BindView(R.id.search_edit_text)
    AppCompatEditText mEditText;
    @BindView(R.id.create_feed)
    Button createFeed;
    @BindView(R.id.save)
    Button saveBtn;
    //    --------------------------------------------- //
    @BindView(R.id.sub_list_view)
    RecyclerView recyclerView;
    @BindView(R.id.sub_swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.sub_connection_info)
    TextView connectionInfo;
    private SubredditViewAdapter adapter;
    private SubredditViewModel viewModel;
    private Subreddit currentSubreddit;
    private ArrayList<Subreddit> subList = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private RedditAPI redditAPI;
    SendSubredditData callback;

    public SearchFragment() {
    }
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        initViewModel();
        initRecyclerView();
        initOtherViews();
        return view;
    }

    private void initOtherViews() {
        mEditText.setOnClickListener(v ->{
            initSearch();
        });
        saveBtn.setOnClickListener(v -> initSearch());
        createFeed.setOnClickListener(v -> {
            viewModel.needHelp();
            Log.d(TAG, "initOtherViews: " + viewModel.needHelp());
        });
    }
    private void updateNames(List<String>names){
        mNames= (ArrayList<String>) names;
        for (int i = 0; i < mNames.size(); i++) {
            String query = TextUtils.join("+", mNames);
            viewModel.initCryAgain(query);
            Log.d(TAG, "updateNames: " + query);
        }
    }

    private void initViewModel() {
        SubredditRepository repository = SubredditRepository.setInstance(getContext());
        SubredditViewModelFactory factory = new SubredditViewModelFactory(repository);
        viewModel = ViewModelProviders.of(this, factory).get(SubredditViewModel.class);
        viewModel.getSubreddits().observe(this, this::updateList);
        viewModel.getnames().observe(this, this::updateNames);
        Log.d(TAG, "initViewModel: ");
    }

    private void initRecyclerView() {
        if (adapter == null) {
            DividerItemDecoration dividerItemDecoration =
                    new DividerItemDecoration(Objects.requireNonNull(getContext()),
                            DividerItemDecoration.VERTICAL);
            adapter = new SubredditViewAdapter(getContext(), viewModel, this);
            recyclerView.addItemDecoration(dividerItemDecoration);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            Log.d(TAG, "initPostView: ");
        }
    }

    private void updateList(List<Subreddit> subreddits) {
        subList = (ArrayList<Subreddit>) subreddits;
        adapter.updateList((ArrayList<Subreddit>) subreddits);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            initViewModel();
            initRecyclerView();
            mSwipeRefreshLayout.setRefreshing(false);
        });
        Log.d(TAG, "updateList: " +subList.size());
    }

    private void initSearch() {
        String userInput = mEditText.getText().toString();
        if (!userInput.equals("")) {
            viewModel.saveSubreddit(mEditText);
        }
        Log.d(TAG, "initSearch: ");
    }

    @Override
    public void onSubClick(int position) {
        if (subList.size() != 0) {
            currentSubreddit = subList.get(position);
            viewModel.sendSubredditforNewFeed(currentSubreddit);
        }
        Log.d(TAG, "onSubClick: " + "\n" + "item position: " +
                position + "\n" + "List size: " + subList.size() +
                "\n" + "-----------");
        Log.d(TAG, "onSubClick: " + currentSubreddit.getName());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SubredditRepository repository = SubredditRepository.setInstance(getContext());
        SubredditViewModelFactory factory = new SubredditViewModelFactory(repository);
        viewModel = ViewModelProviders.of(getActivity(), factory).get(SubredditViewModel.class);
    }

}
