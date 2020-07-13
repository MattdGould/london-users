package com.dwp.users.dwplondonusers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DwpUser {

    private int id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;
    private String email;

    @JsonProperty("ip_address")
    private String ipAddress;
    private Double latitude;
    private Double longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DwpUser)) return false;
        DwpUser dwpUser = (DwpUser) o;
        return getId() == dwpUser.getId() &&
                Objects.equals(getFirstName(), dwpUser.getFirstName()) &&
                Objects.equals(getLastName(), dwpUser.getLastName()) &&
                Objects.equals(getEmail(), dwpUser.getEmail()) &&
                Objects.equals(getIpAddress(), dwpUser.getIpAddress()) &&
                Objects.equals(getLatitude(), dwpUser.getLatitude()) &&
                Objects.equals(getLongitude(), dwpUser.getLongitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getIpAddress(), getLatitude(), getLongitude());
    }

    @Override
    public String toString() {
        return "DwpUserModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
