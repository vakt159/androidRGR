package com.example.rgr.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rgr.BaseViewModel;
import com.example.rgr.model.Callback;
import com.example.rgr.model.Cancellable;
import com.example.rgr.model.Launch;
import com.example.rgr.model.LaunchService;
import com.example.rgr.model.Result;

public class DetailsViewModel extends BaseViewModel {

    private MutableLiveData<Result<Launch>> launchLiveData = new MutableLiveData<>();

    {
        launchLiveData.setValue(Result.empty());
    }

    private Cancellable cancellable;

    @Override
    protected void onCleared() {
        super.onCleared();
        if (cancellable != null) cancellable.cancel();
    }

    public DetailsViewModel(LaunchService launchService) {
        super(launchService);
    }

    public void loadLaunchByLaunchNumber(long launchNumber) {
        launchLiveData.setValue(Result.loading());
        cancellable = getLaunchService().getLaunchByLaunchNumber(launchNumber, new Callback<Launch>() {
            @Override
            public void onError(Throwable error) {
                launchLiveData.postValue(Result.error(error));
            }

            @Override
            public void onResults(Launch data) {
                launchLiveData.postValue(Result.success(data));
            }
        });
    }

    public LiveData<Result<Launch>> getResults() {
        return launchLiveData;
    }
}
