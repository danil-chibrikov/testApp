import React, { Component } from "react";
import { connect } from "react-redux";
import classnames from "classnames";
import { getAccount, updateAccount } from "../../../actions/backlogActions";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

 class UpdateAccount extends Component {

  constructor() {
    super();
     this.state = {
      "id": "",
      "accountSequence": "",
      "count": "",
      "type": "",
      "description": "",
      "cardNumber": "",
      "created_At": "",
      "errors": {}
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }


  componentDidMount() {
    const { backlog_id, account_id } = this.props.match.params;
    this.props.getAccount(backlog_id, account_id, this.props.history);
  }

  componentWillReceiveProps(nextProps) {
    if(nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }

    const {
      id,
      accountSequence,
      count,
      type,
      description,
      cardNumber,
      created_At
    } = nextProps.account;
     this.setState({
      id,
      accountSequence,
      count,
      type,
      description,
      cardNumber,
      created_At
    });
  }
   onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }
   onSubmit(e) {
    e.preventDefault();
     const updateAccount = {
      id: this.state.id,
      accountSequence: this.state.accountSequence,
      count: this.state.count,
      type: this.state.type,
      description: this.state.description,
      cardNumber: this.state.cardNumber,
      created_At: this.state.created_At
    };
     this.props.updateAccount(this.state.cardNumber, 
      this.state.accountSequence, updateAccount, this.props.history);
  }

   render() {
    const { errors } = this.state;
    return (
        <div className="add-PBI">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
            <br></br>
              <Link to={`/clientBoard/${ this.state.cardNumber }`} className="btn btn-light">
                Back to Client Board
              </Link>
              <h4 className="display-4 text-center">Update Account</h4>
              <p className="lead text-center">
                Client card number: {this.state.cardNumber} | Account:{" "}
                {this.state.accountSequence}{" "}
              </p>
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.count
                    })}
                    name="count"
                    placeholder="Count"
                    value={this.state.count}
                    onChange={this.onChange}
                  />
                  {
                    errors.count && (
                      <div className="invalid-feedback">{ errors.count }</div>
                    )
                  }
                </div>

                <div className="form-group">
                  <select
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.type
                    })}
                    name="type"
                    value={this.state.type}
                    onChange={this.onChange}
                  >
                    <option value="">Select Status</option>
                    <option value="RUBLE">RUBLE</option>
                    <option value="EURO">EURO</option>
                    <option value="DOLLAR">DOLLAR</option>
                  </select>
                  {
                    errors.type && (
                      <div className="invalid-feedback">{ errors.type }</div>
                    )
                  }
                </div>
                
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    name="description"
                    placeholder="Description"
                    value={this.state.description}
                    onChange={this.onChange}
                  />
                </div>
                <h6>Created date</h6>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    name="created_At"
                    disabled
                    value={this.state.created_At}
                    onChange={this.onChange}
                  />
                </div>


                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

UpdateAccount.propTypes = {
  getAccount: PropTypes.func.isRequired,
  account: PropTypes.object.isRequired,
  updateAccount: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
  account: state.backlog.account,
  errors: state.errors
})

export default connect(
  mapStateToProps, 
  { getAccount, updateAccount }
  )(UpdateAccount);