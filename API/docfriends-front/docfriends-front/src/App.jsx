import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
// import logo from './logo.svg';
import './App.css';

import 'react-app-polyfill/ie9';
import 'react-app-polyfill/stable';

function App() {
  return (
    <Router>
      <Switch>
        <Route path="question">
          <div>asdfasfd</div>
        </Route>
        <Route path="*">
        <div>aqerwersdfasfd</div>
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
