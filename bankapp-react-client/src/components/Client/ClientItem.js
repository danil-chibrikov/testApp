import React, { Component } from "react"

class ClientItem extends Component {
  render() {
    return (
      <div className="container">
          <div className="card card-body bg-light mb-3">
              <div className="row">
                  <div className="col-3">
                      <span className="mx-auto">REACT</span>
                  </div>
                  <div className="col-lg-6 col-md-4 col-8">
                      <h3>Spring / React Project</h3>
                      <p>Project to create a Kanban Board with Spring Boot and React</p>
                  </div>
                  <div className="col-md-3 d-none d-lg-block">
                      <ul className="list-group">
                          <a href="#">
                              <li className="list-group-item board">
                                  <p className="fa fa-check pr-1"> Client Board </p>
                              </li>
                          </a>
                          <a href="#">
                              <li className="list-group-item update">
                                  <p className="fa fa-edit pr-1"> Update Client Info</p>
                              </li>
                          </a>
                          <a href="">
                              <li className="list-group-item delete">
                                  <p className="fa fa-ban pr-1"> Delete Client</p>
                              </li>
                          </a>
                      </ul>
                  </div>
              </div>
          </div>
      </div>
    );
  }
}

export default ClientItem;
