import React, { Component } from "react";
import ClientItem from "./Client/ClientItem";
import CreateClientButton from "./Client/CreateClientButton";

class Dashboard extends Component {
  render() {
    return(
      <div className="clients">
          <div className="container">
              <div className="row">
                  <div className="col-md-12">
                      <h1 className="display-4 text-center">Clients</h1>
                      <br />
                      <CreateClientButton />
                      <br />
                      <hr />
                      <ClientItem />
                  </div>
              </div>
          </div>
      </div>
      );
  }
}

export default Dashboard;
