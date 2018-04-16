package io.renren.modules.airdrop.entity;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

public class SheEntity implements Serializable {
    private String sheAddress;
    private String sheAmount;
    private String ethAddress;
    private String ethAmount;

    public String getSheAddress() {
        return sheAddress;
    }

    public void setSheAddress(String sheAddress) {
        this.sheAddress = sheAddress;
    }

    public String getSheAmount() {
        return sheAmount;
    }

    public void setSheAmount(String sheAmount) {
        this.sheAmount = sheAmount;
    }

    public String getEthAddress() {
        return ethAddress;
    }

    public void setEthAddress(String ethAddress) {
        this.ethAddress = ethAddress;
    }

    public String getEthAmount() {
        return ethAmount;
    }

    public void setEthAmount(String ethAmount) {
        this.ethAmount = ethAmount;
    }

    @Override
    public String toString() {
        return "SheEntity{" +
                "sheAddress='" + sheAddress + '\'' +
                ", sheAmount='" + sheAmount + '\'' +
                ", ethAddress='" + ethAddress + '\'' +
                ", ethAmount='" + ethAmount + '\'' +
                '}';
    }
}
