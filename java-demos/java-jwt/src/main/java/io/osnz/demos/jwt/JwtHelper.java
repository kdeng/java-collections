package io.osnz.demos.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.osnz.demos.principal.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
public class JwtHelper {

  static final Logger LOG = LoggerFactory.getLogger(JwtHelper.class);

  static final SecretKey DEFAULT_SECRET_KEY = Keys.hmacShaKeyFor("mydefaultprivatekeymydefaultprivatekey".getBytes());

  public static class Encoder {

    public static String encodeJwt(UserPrincipal userPrincipal) {
      return Jwts.builder()
        .setSubject(userPrincipal.getSubject())
        .setAudience(userPrincipal.getName())
        .signWith(DEFAULT_SECRET_KEY, SignatureAlgorithm.HS256)
        .compact();
    }

    public static String encodeJwts(UserPrincipal userPrincipal, PrivateKey privateKey) {
      String jwts = Jwts.builder()
        .setSubject(userPrincipal.getSubject())
        .setAudience(userPrincipal.getName())
        .signWith(privateKey, SignatureAlgorithm.RS256)
        .compact();
      return jwts;
    }

  }

  public static class Decoder {

    public static Claims decodeJwt(String jwt) {
      try {
        return Jwts.parser().setSigningKey(DEFAULT_SECRET_KEY).parseClaimsJws(jwt).getBody();
      } catch (JwtException ex) {
        LOG.debug("Cannot parse JWT", ex);
      }
      return null;
    }

    public static Claims decodeJwts(String jwts, PublicKey publicKey) {
      return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(jwts).getBody();
    }

  }

}
