package com.zou.corejava.ooad.Builder;

public class PersonalComputer {
    private String brand;
    private String os;
    private String cpu;
    private String screen;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "PersonalComputer{" +
                "brand='" + brand + '\'' +
                ", os='" + os + '\'' +
                ", cpu='" + cpu + '\'' +
                ", screen='" + screen + '\'' +
                '}';
    }
}
