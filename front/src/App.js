import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';

import Login from './Login/Login';
import Converter from './Converter/Converter';

const App = () => (
  <BrowserRouter>
    <Switch>
      <Route exact path={'/login'} component={Login} />
    </Switch>
    <Switch>
      <Route exact path={'/converter'} component={Converter} />
    </Switch>
  </BrowserRouter>
);

export default App;
