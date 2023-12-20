package com.example.rgr.model;

import com.example.rgr.model.db.LaunchDbEntity;

public class Launch {

    private final long flightNumber;
    private final String missionName;
    private final String launch_year;
    private final Rocket rocket;
    private LaunchSite launch_site;


    public Launch(long flightNumber, String missionName, String launch_year,
                   Rocket rocket, LaunchSite launch_site) {
        this.flightNumber = flightNumber;
        this.missionName = missionName;
        this.launch_year = launch_year;
        this.rocket = rocket;
        this.launch_site = launch_site;
    }

    public Launch(LaunchDbEntity entity) {
        this(
                entity.getFlightNumber(),
                entity.getMission_name(),
                entity.getLaunch_year(),
                entity.getRocket(),
                entity.getLaunch_site());
    }

    public long getFlightNumber() {
        return flightNumber;
    }

    public String getMissionName() {
        return missionName;
    }

    public String getLaunch_year() {
        return launch_year;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public LaunchSite getLaunch_site() {
        return launch_site;
    }

    public void setLaunch_site(LaunchSite launch_site) {
        this.launch_site = launch_site;
    }
}
