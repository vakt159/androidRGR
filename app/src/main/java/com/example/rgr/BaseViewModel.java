package com.example.rgr;

import androidx.lifecycle.ViewModel;

import com.example.rgr.model.LaunchService;

public class BaseViewModel extends ViewModel {
    private LaunchService launchService;

    public BaseViewModel(LaunchService launchService){
        this.launchService = launchService;
    }

    protected final LaunchService getLaunchService(){
        return launchService;
    }
}
