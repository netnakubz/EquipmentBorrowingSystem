package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;



import javax.persistence.*;

@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String localId;
    private String accessToken;
    private String refreshToken;

    public RefreshToken() {
    }

    public RefreshToken(String localId, String accessToken, String refreshToken) {
        this.localId = localId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "RefreshToken{" +
                "tokenId=" + tokenId +
                ", localId='" + localId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
