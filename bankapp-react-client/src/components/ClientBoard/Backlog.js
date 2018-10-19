import React, { Component } from "react";
import Account from "./Accounts/Account";

 class Backlog extends Component {
     
  render() {
    const { accounts_prop } = this.props;
    const account_for = accounts_prop.map(account => (
      <Account key={account.id} account={account} />
    ));

    let ruble = [];
    let euro = [];
    let dollar = [];
     for (let i = 0; i < account_for.length; i++) {
      console.log(account_for[i]);
       if (account_for[i].props.account.type === "RUBLE") {
        ruble.push(account_for[i]);
      }
       if (account_for[i].props.account.type === "EURO") {
        euro.push(account_for[i]);
      }
       if (account_for[i].props.account.type === "DOLLAR") {
        dollar.push(account_for[i]);
      }
    }

    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>Ruble</h3>
              </div>
            </div>
            { ruble }
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>Euro</h3>
              </div>
            </div>
            { euro }
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>Dollar</h3>
              </div>
            </div>
            { dollar }
          </div>
        </div>
      </div>
    );
  }
}
 export default Backlog;