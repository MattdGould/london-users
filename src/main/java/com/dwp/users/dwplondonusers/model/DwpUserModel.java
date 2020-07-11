package com.dwp.users.dwplondonusers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DwpUserModel {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String ipAddress;
    private String latitude;
    private String longitude;

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DwpUserModel)) return false;
        DwpUserModel dwpUserModel = (DwpUserModel) o;
        return getId() == dwpUserModel.getId() &&
                Objects.equals(getFirstName(), dwpUserModel.getFirstName()) &&
                Objects.equals(getLastName(), dwpUserModel.getLastName()) &&
                Objects.equals(getEmail(), dwpUserModel.getEmail()) &&
                Objects.equals(getIpAddress(), dwpUserModel.getIpAddress()) &&
                Objects.equals(getLatitude(), dwpUserModel.getLatitude()) &&
                Objects.equals(getLongitude(), dwpUserModel.getLongitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getIpAddress(), getLatitude(), getLongitude());
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
