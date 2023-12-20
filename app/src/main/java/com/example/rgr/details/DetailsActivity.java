package com.example.rgr.details;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.rgr.App;
import com.example.rgr.R;
import com.example.rgr.model.Launch;


public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_LAUNCH_NUMBER = "LAUNCH_NUMBER";

    private TextView launchNumberTextView;
    private TextView missionNameTextView;
    private TextView launchYearTextView;
    private TextView rocketName;
    private TextView launchSite;
    private TextView rocketType;
    private DetailsViewModel viewModel;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        launchNumberTextView = findViewById(R.id.launchNumber);
        missionNameTextView = findViewById(R.id.missionName);
        missionNameTextView.setMovementMethod(new ScrollingMovementMethod());
        launchYearTextView = findViewById(R.id.launchYear);
        rocketName = findViewById(R.id.rocketName);
        rocketType = findViewById(R.id.rocketType);
        progressBar = findViewById(R.id.progress);
    launchSite=findViewById(R.id.launchSite);
        long launchNumber = getIntent().getLongExtra(EXTRA_LAUNCH_NUMBER, -1);
        if (launchNumber == -1) {
            throw new RuntimeException("There is no launch number");
        }

        App app = (App) getApplication();
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, app.getViewModelFactory());
        viewModel = viewModelProvider.get(DetailsViewModel.class);

        viewModel.loadLaunchByLaunchNumber(launchNumber);
        viewModel.getResults().observe(this, result -> {
            switch (result.getStatus()) {
                case SUCCESS:
                    Launch launch = result.getData();
                    launchYearTextView.setText(String.valueOf(launch.getLaunch_year()));
                    launchNumberTextView.setText(String.valueOf(launch.getFlightNumber()));
                    missionNameTextView.setText(launch.getMissionName());
                    rocketName.setText(launch.getRocket().getRocket_name());
                    rocketType.setText(launch.getRocket().getRocket_type());
                    launchSite.setText(launch.getLaunch_site().getSite_name_long());
                    progressBar.setVisibility(View.GONE);
                    break;
                case EMPTY:
                    launchYearTextView.setText("");
                    launchNumberTextView.setText("");
                    missionNameTextView.setText("");
                    rocketName.setText("");
                    rocketType.setText("");
                    progressBar.setVisibility(View.GONE);
                    break;
                case LOADING:
                    launchYearTextView.setText("");
                    launchNumberTextView.setText("");
                    missionNameTextView.setText("");
                    progressBar.setVisibility(View.VISIBLE);
                    break;
            }
        });
    }


}
