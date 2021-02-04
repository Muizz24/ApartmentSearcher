/* jshint esversion: 6 */
import React, { Component } from 'react';
import Search from './MainPage';
import Main from './MainPage';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

class App extends Component {
  render() {
    return (
      <Router>
        <div>
          <Switch>
            <Route exact path='/' component={ Search } />
          </Switch>
        </div>
      </Router>
    );
  }
}

export default App;
