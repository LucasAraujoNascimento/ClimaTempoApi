package com.nascimento.api_clima.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponseModel {

    @JsonProperty("location")
    private Location location;

    @JsonProperty("current")
    private Current current;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public static class Location {
        @JsonProperty("name")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Current {
        @JsonProperty("temp_c")
        private double tempC;

        @JsonProperty("condition")
        private Condition condition;

        @JsonProperty("last_updated")
        private String lastUpdated;

        public double getTempC() {
            return tempC;
        }

        public void setTempC(double tempC) {
            this.tempC = tempC;
        }

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }

        public String getLastUpdated() {
            return lastUpdated;
        }

        public void setLastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
        }

        public static class Condition {
            @JsonProperty("text")
            private String text;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
