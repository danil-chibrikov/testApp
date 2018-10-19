import React, { Component } from "react";

 class Account extends Component {
  render() {
    return (
        <div className="card mb-1 bg-light">
        <div className="card-header text-primary">
          card number: accountSequence
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">account.count</h5>
          <p className="card-text text-truncate ">
            account.created_At
          </p>
          <a href="" className="btn btn-primary">
            View / Update
          </a>

          <button className="btn btn-danger ml-4">Delete</button>
        </div>
      </div>
    );
  }
}
export default Account;