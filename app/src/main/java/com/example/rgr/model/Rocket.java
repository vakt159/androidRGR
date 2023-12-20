package com.example.rgr.model;

public class Rocket {
    private String rocket_name;
    private String rocket_type;

    public Rocket(String rocket_name, String rocket_type) {
        this.rocket_name = rocket_name;
        this.rocket_type = rocket_type;
    }

    public String getRocket_name() {
        return rocket_name;
    }

    public void setRocket_name(String rocket_name) {
        this.rocket_name = rocket_name;
    }

    public String getRocket_type() {
        return rocket_type;
    }

    public void setRocket_type(String rocket_type) {
        this.rocket_type = rocket_type;
    }


}
