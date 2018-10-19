import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { deleteClient } from "../../actions/clientActions";

class ClientItem extends Component {
  
  onDeleteClick = id => {
    this.props.deleteClient(id);
  };

  render() {
    const {client} = this.props;
    return (
      <div className="container">
          <div className="card card-body bg-light mb-3">
              <div className="row">
                  <div className="col-3">
                      <span className="mx-auto">{ client.cardNumber }</span>
                  </div>
                  <div className="col-lg-6 col-md-4 col-8">
                      <h3>{ client.fullname }</h3>
                      <p>{ client.address }</p>
                  </div>
                  <div className="col-md-3 d-none d-lg-block">
                      <ul className="list-group">
                          <Link to={ `/clientBoard/${client.cardNumber}` }>
                              <li className="list-group-item board">
                                  <p className="fa fa-check pr-1"> Client Board </p>
                              </li>
                          </Link>
                          <Link to={ `/updateClient/${ client.cardNumber }` }>
                              <li className="list-group-item update">
                                  <p className="fa fa-edit pr-1"> Update Client Info</p>
                              </li>
                          </Link>   
                          <a href="#">                     
                              <li className="list-group-item delete" 
                                  onClick={ this.onDeleteClick.bind(
                                      this, 
                                      client.cardNumber) }>
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

ClientItem.propTypes = {
    deleteClient: PropTypes.func.isRequired
};

export default connect(
    null,
    {deleteClient}
)(ClientItem);
