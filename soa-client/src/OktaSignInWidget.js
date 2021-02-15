import React, { useEffect, useRef } from 'react';
import OktaSignIn from '@okta/okta-signin-widget';
import '@okta/okta-signin-widget/dist/css/okta-sign-in.min.css';

const OktaSignInWidget = ({ config, onSuccess, onError }) => {
  const widgetRef = useRef();
  useEffect(() => {
    if (!widgetRef.current)
      return false;
    console.log(config)
    const widget = new OktaSignIn(config);
    console.log(widget)
    widget.showSignInToGetTokens({
      el: widgetRef.current,
    }).then(onSuccess).catch(onError);

    return () => widget.remove();
  }, []);

  return (<div ref={widgetRef} />);
};

export default OktaSignInWidget;