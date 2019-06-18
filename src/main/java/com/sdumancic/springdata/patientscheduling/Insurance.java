package com.sdumancic.springdata.patientscheduling;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class Insurance {

    private String providerName;
    private double copay;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public double getCopay() {
        return copay;
    }

    public void setCopay(double copay) {
        this.copay = copay;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "providerName='" + providerName + '\'' +
                ", copay=" + copay +
                '}';
    }
}
