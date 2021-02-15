const oktaAuthConfig = {
  issuer: 'https://dev-70871902.okta.com/oauth2/default',
  clientId: '0oa5ro6b3gJcBuV5U5d6',
  redirectUri: 'http://localhost:8080/login/oauth2/code/okta',
};

const oktaSignInConfig = {
  baseUrl: 'https://dev-70871902.okta.com',
  clientId: '0oa5ro6b3gJcBuV5U5d6',
  redirectUri: 'http://localhost:8080/login/oauth2/code/okta',
  authParams: {
    issuer: 'https://dev-70871902.okta.com/oauth2/default',
    responseType: ['id_token', 'token'],
    scopes: ['openid', 'email', 'profile']
  }
};

export { oktaAuthConfig, oktaSignInConfig };