package com.soccerconnect.models.ground;

public class GroundModel {

    String groundId;
    String groundName;
    String address;
    String postalCode;
    String contact;
    String email;

    public GroundModel(){}

    public GroundModel(String groundId, String groundName, String address,
                       String postalCode, String contact, String email) {
        this.groundId = groundId;
        this.groundName = groundName;
        this.address = address;
        this.postalCode = postalCode;
        this.contact = contact;
        this.email = email;
    }

    public GroundModel(String groundId, String groundName) {
        this.groundId = groundId;
        this.groundName = groundName;
    }

    public String getGroundId() {
        return groundId;
    }

    public void setGroundId(String groundId) {
        this.groundId = groundId;
    }

    public String getGroundName() {
        return groundName;
    }

    public void setGroundName(String groundName) {
        this.groundName = groundName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
