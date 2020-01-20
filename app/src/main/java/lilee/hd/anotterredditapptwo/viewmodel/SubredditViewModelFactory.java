package lilee.hd.anotterredditapptwo.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

// https://github.com/android/architecture-components-samples/tree/master/BasicSample
@SuppressWarnings("unchecked")
public class SubredditViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private SubredditRepository repository;

    public SubredditViewModelFactory(SubredditRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new SubredditViewModel(repository);
    }
}
