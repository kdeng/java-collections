package io.osnz.demos.jwt;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.osnz.demos.principal.UserPrincipal;
import org.junit.Test;

import java.net.URL;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

import static io.jsonwebtoken.Jwts.parser;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
public class JwtHelperTest {

  @Test
  public void shouldEncodeAndDecodeTokenProperly() {
    UserPrincipal principal = UserPrincipal.builder()
      .subject("1234")
      .name("bob")
      .email("bob@gmail.com")
      .build();

    String jwt = JwtHelper.Encoder.encodeJwt(principal);

    assertThat(jwt).isNotEmpty();

    Claims claims = JwtHelper.Decoder.decodeJwt(jwt);

    assertThat(claims).isNotEmpty();

    assertThat(claims.getSubject()).isEqualTo("1234");
    assertThat(claims.getAudience()).isEqualTo("bob");
  }

  @Test
  public void shouldEncodeAndDecodeProperlyWithSignature() {
    UserPrincipal principal = UserPrincipal.builder()
      .subject("1234")
      .name("bob")
      .email("bob@gmail.com")
      .build();

    KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

    String jwts = JwtHelper.Encoder.encodeJwts(principal, keyPair.getPrivate());

    assertThat(jwts).isNotEmpty();

    Claims claims = JwtHelper.Decoder.decodeJwts(jwts, keyPair.getPublic());

    assertThat(claims).isNotEmpty();

    assertThat(claims.getSubject()).isEqualTo("1234");
    assertThat(claims.getAudience()).isEqualTo("bob");

  }

  @Test
  public void shouldSignTokenProperly() {
    String key = "asdasdasdasjdksjfdsfkljdsflkdssdfjj432k423434324";

    String jwts = Jwts.builder()
      .signWith(Keys.hmacShaKeyFor(key.getBytes()), SignatureAlgorithm.HS256)
      .setSubject("111")
      .compact();

    Claims claims = parser().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).parseClaimsJws(jwts).getBody();

    assertThat(claims).isNotNull();
    assertThat(claims.getSubject()).isEqualTo("111");

  }

  @Test
  public void shouldVerifyGoogleIdTokenProperly() throws Exception {
    String idToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjA3YTA4MjgzOWYyZTcxYTliZjZjNTk2OTk2Yjk0NzM5Nzg1YWZkYzMiLCJ0eXAiOiJKV1QifQ." +
      "eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIzMDkwMjQzOTc5MTUtN2Nib2ptcHRxNXVqazYzdGFiN2RvZnZtaDNqNXQ0dDYuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIzMDkwMjQzOTc5MTUtN2Nib2ptcHRxNXVqazYzdGFiN2RvZnZtaDNqNXQ0dDYuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTIyNjM5NDU1NzY3MjkxNTgxODQiLCJoZCI6IjUxYW55LmNvbSIsImVtYWlsIjoiZGVuZ0A1MWFueS5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6IkpnZTY1T3VJMVFtUm0xQ2hTS21aNmciLCJuYW1lIjoiS2VmZW5nIERlbmciLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDYuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1wTHZCalRrMHFEWS9BQUFBQUFBQUFBSS9BQUFBQUFBQUNnOC9yYTd1RVhwOTRsQS9zOTYtYy9waG90by5qcGciLCJnaXZlbl9uYW1lIjoiS2VmZW5nIiwiZmFtaWx5X25hbWUiOiJEZW5nIiwibG9jYWxlIjoiZW4tR0IiLCJpYXQiOjE1NTkwMTYyNjcsImV4cCI6MTU1OTAxOTg2N30." +
      "Ys7zPWK82hWPlPlk5Z08COY9MDauMRSiIZMjFYPF0CA0f5bbaNWGm_gBRPVQrrjOBEtt1lThlOSxHY5zy0GdV1IQUlZTsban1uP694ep1o7jwgid8dH460H-O8RLRQP2DLHDx1L6kkLjfN6UcNzDB0I56PSXx4YUgYm2bfvtEgbapBncwjW4fwDBdJi9IX-8-r8CMqgJAKJz_HMPkmE7l6CMbVhJdttcRDZOdoQW6liSfZ8IIWZvUtOHRwzM_6BWa9FWlRYHM6LlGLbkm9Riq_Pq7LYrrlJR0UAVDya5j0ZgBGn751jAdeGJb2K6Mc04l0kqaKmEfdjjmYbQPBDZ1w";

    DecodedJWT decodedJWT = JWT.decode(idToken);
    URL url = new URL("https://www.googleapis.com/oauth2/v3/certs");
    JwkProvider provider = new UrlJwkProvider(url);
    Jwk jwk = provider.get(decodedJWT.getKeyId());

    Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
    algorithm.verify(decodedJWT);

    assertThat(true).isTrue();

  }

}
