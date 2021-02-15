import React from 'react';
import { BrowserRouter as Router, Route, useHistory} from 'react-router-dom';
import { Security, SecureRoute, LoginCallback } from '@okta/okta-react';
import { OktaAuth } from '@okta/okta-auth-js';
import Home from './Home';
import Login from './Login';
import Protected from './Protected';
import { oktaAuthConfig, oktaSignInConfig } from './config';

const oktaAuth = new OktaAuth(oktaAuthConfig);

const AppWithRouterAccess = () => {
  const history = useHistory();

  const customAuthHandler = () => {
    history.push('/login');
  };

  return (
    <Security
      oktaAuth={oktaAuth}
      onAuthRequired={customAuthHandler}
    >
      <Router>
        <Route path='/' exact={true} component={Home} />
        <SecureRoute path='/protected' component={Protected} />
        <Route path='/login' render={() => <Login config={oktaSignInConfig} />} />
        <Route path='http://localhost:8080/login/oauth2/code/okta' component={LoginCallback} />
      </Router>
    </Security>
  );
};
export default AppWithRouterAccess;