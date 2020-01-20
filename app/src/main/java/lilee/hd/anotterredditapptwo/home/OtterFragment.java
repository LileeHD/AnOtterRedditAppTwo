package lilee.hd.anotterredditapptwo.home;

import androidx.fragment.app.Fragment;

public class OtterFragment extends Fragment {
//    private static final String TAG = "OtterFragment";
//    @BindView(R.id.logo_otter)
//    ImageView logo;
//    @BindView(R.id.list_view_otter)
//    RecyclerView postView;
//    @BindView(R.id.swipe_layout_otter)
//    SwipeRefreshLayout mSwipeRefreshLayout;
//    @BindView(R.id.connection_info_otter)
//    TextView connectionInfo;
//    @BindView(R.id.progressbar_otter)
//    ProgressBar progressBar;
//
//    private Snackbar snackbar;
//    private PostViewAdapter adapter;
//    private Post currentpost;
//    private ArrayList<Children> postsList = new ArrayList<>();
//    private SubredditViewModel viewModel;
//    private boolean mIsRefreshing = false;
//    private String sort = "new";
//
//    public OtterFragment() {
//    }
//    public static OtterFragment newInstance() {
//        return new OtterFragment();
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_otter_feed, container, false);
//        ButterKnife.bind(this, view);
//
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        viewModel = ViewModelProviders.of(getActivity()).get(SubredditViewModel.class);
//        initViewModel();
//        initPostView();
//    }
//
//    private void initViewModel() {
//            viewModel.initCryAgain();
//            viewModel.needHelp().observe(this, feed -> {
//                List<Children> childrenList = feed.getData().getChildren();
//                postsList.addAll(childrenList);
//                adapter.notifyDataSetChanged();
//                Log.d(TAG, "initViewModel: " + postsList.size());
//            });
//    }
//
//    private void initPostView() {
//        if (adapter == null) {
//            DividerItemDecoration dividerItemDecoration =
//                    new DividerItemDecoration(Objects.requireNonNull(getContext()),
//                    DividerItemDecoration.VERTICAL);
//            adapter = new PostViewAdapterOtter(getContext(), postsList, this);
//            postView.addItemDecoration(dividerItemDecoration);
//            postView.setItemAnimator(new DefaultItemAnimator());
//            postView.setAdapter(adapter);
//            postView.setLayoutManager(new LinearLayoutManager(getContext()));
//            postView.setHasFixedSize(true);
//            mSwipeRefreshLayout.setOnRefreshListener(() -> {
//                adapter.notifyDataSetChanged();
//            });
//            Log.d(TAG, "initPostView: ");
//        }
//    }
//
//    // Click
//    @Override
//    public void onPostClick(PostViewModel model, int position) {
//        Children post = postsList.get(position);
//        model.sendData(post.getData());
//        swapFragment();
//        Log.d(TAG, "onPostClick: " + position);
//    }
//
//    private void swapFragment() {
//        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
//                .setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
//                .replace(R.id.fragment_container, DetailFragment.newInstance())
//                .addToBackStack(null)
//                .commit();
//    }

}
