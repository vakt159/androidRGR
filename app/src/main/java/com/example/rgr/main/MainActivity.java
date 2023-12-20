package com.example.rgr.main;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rgr.App;
import com.example.rgr.R;
import com.example.rgr.details.DetailsActivity;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private Button searchButton;
    private RecyclerView repositoriesList;
    private ProgressBar progress;
    private TextView emptyTextView;
    private TextView errorTextView;
    private TextView nameTitle;
    private MainViewModel viewModel;
    private ImageView uk, en;

    private LaunchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uk = findViewById(R.id.ukraineLang);
        uk.setOnClickListener(v -> {
            setLocal(MainActivity.this, "uk");
            finish();
            startActivity(getIntent());
        });
        en = findViewById(R.id.engLang);
        en.setOnClickListener(v->{
            setLocal(this, "en");
            finish();
            startActivity(getIntent());
        });
        searchButton = findViewById(R.id.searchButton);
        repositoriesList = findViewById(R.id.repositoriesList);
        progress = findViewById(R.id.progress);
        emptyTextView = findViewById(R.id.emptyTextView);
        errorTextView = findViewById(R.id.errorTextView);

        nameTitle = findViewById(R.id.missionNameText);
        App app = (App) getApplication();
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, app.getViewModelFactory());
        viewModel = viewModelProvider.get(MainViewModel.class);

        viewModel.getViewState().observe(this, state -> {
            searchButton.setEnabled(state.isEnableSearchButton());
            repositoriesList.setVisibility(toVisibility(state.isShowList()));
            progress.setVisibility(toVisibility(state.isShowProgress()));
            emptyTextView.setVisibility(toVisibility(state.isShowEmptyHint()));
            errorTextView.setVisibility(toVisibility(state.isShowError()));
            nameTitle.setVisibility(toVisibility(state.isShowTitles()));
            adapter.setRepositories(state.getLaunches());
        });

        searchButton.setOnClickListener(v -> {
            viewModel.getLaunches();
        });

        initRepositoriesList();

    }
    private void initRepositoriesList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        repositoriesList.setLayoutManager(layoutManager);
        adapter = new LaunchAdapter(launch -> {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.EXTRA_LAUNCH_NUMBER, launch.getFlightNumber());
            startActivity(intent);
        });
        repositoriesList.setAdapter(adapter);
    }

    static int toVisibility(boolean show) {
        return show ? View.VISIBLE : View.GONE;
    }

    public void setLocal(Activity activity, String langCode) {
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}