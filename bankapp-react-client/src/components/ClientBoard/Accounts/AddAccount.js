import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import classnames from "classnames";
import { addAccount } from "../../../actions/backlogActions";
import PropTypes from "prop-types";

class AddAccount extends Component {

    constructor(props) {
        super(props);
        const { id_client } = this.props.match.params;
         this.state = {
          count: "",
          type: "",
          created_At: "",
          cardNumber: id_client,
          errors: {}
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        
      }

      componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
          this.setState({ errors: nextProps.errors });
        }
      }
      
      onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
      }

      onSubmit(e) {
        e.preventDefault();
         const newAccount = {
            count: this.state.count,
            type: this.state.type,
            created_At: this.state.created_At,
            cardNumber: this.state.cardNumber
        };
        this.props.addAccount(
          this.state.cardNumber,
          newAccount,
          this.props.history
        );
      }
      

  render() {
    const { id_client } = this.props.match.params;
    const { errors } = this.state;
    return (
      <div className="add-PBI">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
            <br></br>
              <Link to={ `/clientBoard/${id_client}` } className="btn btn-light">
                Back to Client Board
              </Link>
              <h4 className="display-4 text-center">Add Account</h4>
              <p className="lead text-center">Account count + type</p>
              <form onSubmit={ this.onSubmit }>
                <div className="form-group">
                  <input
                    type="text"
                    className={ classnames("form-control form-control-lg", {
                      "is-invalid": errors.count
                    })}
                    name="count"
                    placeholder="Count"
                    value={ this.state.count }
                    onChange={ this.onChange }
                  />
                  { errors.count && (
                    <div className="invalid-feedback">{ errors.count }</div>
                  )}
                </div>

                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="type"
                    value={ this.state.type }
                    onChange={ this.onChange }
                  >
                    <option value="">Select Status</option>
                    <option value="RUBLE">RUBLE</option>
                    <option value="EURO">EURO</option>
                    <option value="DOLLAR">DOLLAR</option>
                  </select>
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

AddAccount.propTypes = {
    addAccount: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
  };

  const mapStateToProps = state => ({
    errors: state.errors
  });

   export default connect(
    mapStateToProps,
    { addAccount }
  )(AddAccount);