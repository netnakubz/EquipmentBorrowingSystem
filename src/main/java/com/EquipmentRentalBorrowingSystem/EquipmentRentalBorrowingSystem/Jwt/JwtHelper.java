package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Jwt;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.RefreshToken;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtHelper {
    static final String issuer = "MyApp";

    private long accessTokenExpirationMs;
    private long refreshTokenExpirationMs;

    private Algorithm accessTokenAlgorithm;
    private Algorithm refreshTokenAlgorithm;
    private JWTVerifier accessTokenVerifier;
    private JWTVerifier refreshTokenVerifier;

    public JwtHelper(@Value("${accessTokenSecret}") String accessTokenSecret, @Value("${refreshTokenSecret}") String refreshTokenSecret, @Value("${refreshTokenExpirationDays}") int refreshTokenExpirationDays, @Value("${accessTokenExpirationMinutes}") int accessTokenExpirationMinutes) {
        accessTokenExpirationMs = (long) accessTokenExpirationMinutes * 60 * 1000;
        refreshTokenExpirationMs = (long) refreshTokenExpirationDays * 24 * 60 * 60 * 1000;
        accessTokenAlgorithm = Algorithm.HMAC512(accessTokenSecret);
        refreshTokenAlgorithm = Algorithm.HMAC512(refreshTokenSecret);
        accessTokenVerifier = JWT.require(accessTokenAlgorithm)
                .withIssuer(issuer)
                .build();
        refreshTokenVerifier = JWT.require(refreshTokenAlgorithm)
                .withIssuer(issuer)
                .build();
    }

    public String generateAccessToken(UserModel user) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject("118044518041531359821")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + accessTokenExpirationMs))
                .sign(accessTokenAlgorithm);
    }

    public String generateRefreshToken(UserModel user, RefreshToken refreshToken) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject("118044518041531359821")
                .withClaim("tokenId", refreshToken.getTokenId())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date((new Date()).getTime() + refreshTokenExpirationMs))
                .sign(refreshTokenAlgorithm);
    }

    private Optional<DecodedJWT> decodeAccessToken(String token) {
        try {
            System.out.println(accessTokenVerifier.verify(token));
            return Optional.of(accessTokenVerifier.verify(token));
        } catch (JWTVerificationException e) {
        }
        return Optional.empty();
    }

    private Optional<DecodedJWT> decodeRefreshToken(String token) {
        try {
            return Optional.of(refreshTokenVerifier.verify(token));
        } catch (JWTVerificationException e) {
        }
        return Optional.empty();
    }

    public boolean validateAccessToken(String token) {
        return decodeAccessToken(token).isPresent();
    }

    public boolean validateRefreshToken(String token) {
        return decodeRefreshToken(token).isPresent();
    }

    public String getUserIdFromAccessToken(String token) {
        return decodeAccessToken(token).get().getSubject();
    }

    public String getUserIdFromRefreshToken(String token) {
        return decodeRefreshToken(token).get().getSubject();
    }

    public String getTokenIdFromRefreshToken(String token) {
        return decodeRefreshToken(token).get().getClaim("tokenId").asString();
    }
}
