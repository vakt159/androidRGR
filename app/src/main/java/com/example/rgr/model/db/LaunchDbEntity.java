package com.example.rgr.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.rgr.model.LaunchSite;
import com.example.rgr.model.Rocket;
import com.example.rgr.model.network.LaunchNetworkEntity;


@Entity(tableName = "launches")
public class LaunchDbEntity {

    @PrimaryKey
    @ColumnInfo(name = "flight_number")
    private long flightNumber;
    @ColumnInfo(name = "mission_name")
    private String mission_name;
    @ColumnInfo(name = "launch_year")
    private String launch_year;
    @Embedded
    private Rocket rocket;
    @Embedded
    private LaunchSite launch_site;
    public LaunchDbEntity() {
    }

    public LaunchDbEntity(LaunchNetworkEntity launchNetworkEntity) {
        this.flightNumber = launchNetworkEntity.getFlight_number();
        this.mission_name = launchNetworkEntity.getMission_name();
        this.launch_year = launchNetworkEntity.getLaunch_year();
        this.rocket=launchNetworkEntity.getRocket();
        this.launch_site = launchNetworkEntity.getLaunch_site();
    }

    public long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getMission_name() {
        return mission_name;
    }

    public void setMission_name(String mission_name) {
        this.mission_name = mission_name;
    }

    public String getLaunch_year() {
        return launch_year;
    }

    public void setLaunch_year(String launch_year) {
        this.launch_year = launch_year;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public LaunchSite getLaunch_site() {
        return launch_site;
    }

    public void setLaunch_site(LaunchSite launch_site) {
        this.launch_site = launch_site;
    }

}
