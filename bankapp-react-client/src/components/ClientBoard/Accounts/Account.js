import React, { Component } from "react";
import { Link } from "react-router-dom";

 class Account extends Component {
  render() {
    const { account } = this.props;
  
    return (
        <div className="card mb-1 bg-light">
        <div className="card-header text-dark">
          card number:  
          <i className="text-primary"> { account.accountSequence }</i>
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{ account.count }</h5>
          <p className="card-text font-weight-light ">
          { account.updated_At }
          </p>
          <Link to={ `/updateAccount/${account.cardNumber}/${
              account.accountSequence
            }` }
            className="btn btn-primary"
          >
            View
          </Link>

          <button className="btn btn-outline-danger ml-4">Delete</button>
        </div>
      </div>
    );
  }
}
export default Account;