package lilee.hd.anotterredditapptwo.search;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import lilee.hd.anotterredditapptwo.R;
import lilee.hd.anotterredditapptwo.model.Subreddit;
import lilee.hd.anotterredditapptwo.model.SubredditNode;
import lilee.hd.anotterredditapptwo.viewmodel.SubredditRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubredditCallback implements Callback<SubredditNode> {
    private SubredditRepository mRepository;
    private EditText mEditText;
    private static final String TAG = "SubredditCallback";

    public SubredditCallback(SubredditRepository mRepository, EditText mEditText) {
        this.mRepository = mRepository;
        this.mEditText = mEditText;
    }

    @Override
    public void onResponse(Call<SubredditNode> call, Response<SubredditNode> response) {
        SubredditNode responseBody = response.body();
        Subreddit subreddit = responseBody != null ? responseBody.getData() : null;
        if (subreddit != null && subreddit.getName() != null) {
            if (!subreddit.isNsfw()) {
                mRepository.insertSubreddit(subreddit);
                mEditText.setText("");
                Log.d("DAO", "onResponse: \n" +
                        "name: " + subreddit.getName() + "\n" +
                        "iconurl: " + subreddit.getIconUrl() + "\n" +
                        "Nsfw: " + subreddit.isNsfw() + "\n" +
                        "-------------------------------------------------------------------------\n\n");
                Log.d(TAG, "DAO: " + subreddit.getName());
                closeKeyboard();
            } else {
                // TODO replace this.
                setEditTextError(R.string.nsfw_subreddit);
            }
        } else {
            // TODO replace this.
            setEditTextError(R.string.subreddit_doesnt_exist);
        }
    }

    @Override
    public void onFailure(Call<SubredditNode> call, Throwable t) {
        closeKeyboard();
        initConnectionError();
    }
// TODO replace this.
    private void setEditTextError(int errorTextId) {
        Context context = mEditText.getContext();
        String errorString = context.getString(errorTextId);
        mEditText.setError(errorString);
    }

    private void closeKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) mEditText
                        .getContext()
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        }
    }

    private void initConnectionError() {
        Toast.makeText(mEditText.getContext(), R.string.no_internet_connection, Toast.LENGTH_LONG).show();
    }

}
