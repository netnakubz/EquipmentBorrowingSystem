package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;

@Entity
public class HomeAddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer homeId;
    private String province;
    private String district;
    private String postCode;
    private String road;


    public HomeAddressModel() {
    }

    public HomeAddressModel(Integer homeId, String province, String district, String postCode, String road) {
        this.homeId = homeId;
        this.province = province;
        this.district = district;
        this.postCode = postCode;
        this.road = road;
    }

    public Integer getHomeId() {
        return homeId;
    }

    public void setHomeId(Integer homeId) {
        this.homeId = homeId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    @Override
    public String toString() {
        return "HomeAddressModel{" + "homeId=" + homeId + ", province='" + province + '\'' + ", district='" + district + '\'' + ", postCode='" + postCode + '\'' + ", road='" + road + '\'' + '}';
    }
}
