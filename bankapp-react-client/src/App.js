import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddClient from "./components/Client/AddClient";
import { Provider } from "react-redux";
import store from "./store";
import UpdateClient from "./components/Client/UpdateClient";
import ClientBoard from "./components/ClientBoard/ClientBoard";
import AddAccount from "./components/ClientBoard/Accounts/AddAccount";

class App extends Component {
  render() {
    return (
      <Provider store={ store }>
        <Router>
          <div className="App">
            <Header />
            <Route exact path="/dashboard" component={ Dashboard } />
            <Route exact path="/addClient" component={ AddClient } />
            <Route exact path="/updateClient/:id" component={ UpdateClient } />
            <Route exact path="/clientBoard/:id" component={ ClientBoard } />
            <Route
              exact
              path="/addAccount/:id"
              component={ AddAccount }
            />
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
