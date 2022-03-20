package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class SecurityService {
    public String getBearerToken(HttpServletRequest request) {
        String bearerToken = null;
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasText(authorization) &&
                authorization.startsWith("Bearer ")) {
            bearerToken = authorization.substring(7, authorization.length());
        }
        return bearerToken;
    }
}
