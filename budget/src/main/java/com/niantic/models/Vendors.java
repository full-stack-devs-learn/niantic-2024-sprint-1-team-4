package com.niantic.models;

public class Vendors {
    private int vendorId;
    private String vendorName;
    private String website;

    public Vendors(int vendorId, String vendorName, String website) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.website = website;
    }

    public Vendors() {
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}