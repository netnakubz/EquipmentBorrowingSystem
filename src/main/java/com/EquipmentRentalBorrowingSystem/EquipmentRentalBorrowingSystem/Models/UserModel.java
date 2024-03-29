package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity()
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;

    private String surname;

    private String email;

    private String tel;

    private Date last_login;

    private Date create_date;

    private String localId;

    private String idNumber;

    @OneToOne
    @JoinColumn(name = "homeId")
    private HomeAddressModel homeAddressModel;

    public UserModel() {
        super();
    }

    public UserModel(int userId, String name, String surname, String email, String tel, Date last_login, Date create_date, String localId, HomeAddressModel homeAddressModel) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.tel = tel;
        this.last_login = last_login;
        this.create_date = create_date;
        this.localId = localId;
        this.homeAddressModel = homeAddressModel;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public HomeAddressModel getHomeAddressModel() {
        return homeAddressModel;
    }

    public void setHomeAddressModel(HomeAddressModel homeAddressModel) {
        this.homeAddressModel = homeAddressModel;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", last_login=" + last_login +
                ", create_date=" + create_date +
                ", localId='" + localId + '\'' +
                ", homeAddressModel=" + homeAddressModel +
                '}';
    }
}
