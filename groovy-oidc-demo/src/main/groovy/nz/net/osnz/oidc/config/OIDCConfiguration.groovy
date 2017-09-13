package nz.net.osnz.oidc.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Configuration
class OIDCConfiguration {

	@Value('${oidc.issuer}')
	String issuer

	@Value('${oidc.client_id}')
	String client_id

	@Value('${oidc.client_secret}')
	String client_secret

	/**
	 * OAuth Authorize endpoint
	 */
	public static final String AUTHORIZATION_ENDPOINT = 'authorization_endpoint'

	/**
	 * JWK endpoint
	 */
	public static final String TOKEN_ENDPOINT = 'token_endpoint'

	/**
	 * userinfo endpoint
	 */
	public static final String USERINFO_ENDPOINT = 'userinfo_endpoint'

	/**
	 * JWK endpoint
	 */
	public static final String JWK_ENDPOINT = 'jwks_uri'

	/**
	 * Configuration endpoint
	 */
	public static final String CONFIGURATION_ENDPOINT = '/well-known/openid-configuration'


}
