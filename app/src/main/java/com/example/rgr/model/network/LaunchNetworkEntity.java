package com.example.rgr.model.network;


import com.example.rgr.model.LaunchSite;
import com.example.rgr.model.Rocket;

public class LaunchNetworkEntity {
    private int flight_number;
    private String mission_name;
    private String launch_year;
    private Rocket rocket;
    private LaunchSite launch_site;

    public LaunchNetworkEntity() {
    }

    public void setFlight_number(int flight_number) {
        this.flight_number = flight_number;
    }

    public void setMission_name(String mission_name) {
        this.mission_name = mission_name;
    }

    public void setLaunch_year(String launch_year) {
        this.launch_year = launch_year;
    }

    public LaunchSite getLaunch_site() {
        return launch_site;
    }

    public void setLaunch_site(LaunchSite launch_site) {
        this.launch_site = launch_site;
    }


    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public int getFlight_number() {
        return flight_number;
    }

    public String getMission_name() {
        return mission_name;
    }

    public String getLaunch_year() {
        return launch_year;
    }


}
