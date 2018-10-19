import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createClient } from "../../actions/clientActions";
import classnames from "classnames";

class AddClient extends Component {
  constructor() {
    super()

    this.state = {
      fullname: "",
      address: "",
      cardNumber: "",
      errors: {}
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  //life cycle hooks
  componentWillReceiveProps(nextProps) {
    if(nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    const newClient = {
      "fullname": this.state.fullname,
      "address": this.state.address,
      "cardNumber": this.state.cardNumber
    };
    this.props.createClient(newClient, this.props.history);
  }

  render() {
    const {errors} = this.state

    return (
      <div>
        {
            //check name atribute input fields
            //create constructor
            //set state
            //set value on input fields
            //create onChange function
            //set onChange on each input field
            //bind on constructor
            //check state change in the react extension
        }
        <div className="client">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Create client form</h5>
                <hr />
                <form onSubmit={ this.onSubmit }>
                  <div className="form-group">
                    <input 
                      type="text"
                      className = {
                        classnames(
                          "form-control form-control-lg", {
                            "is-invalid": errors.fullname
                          }
                        )
                      }
                      placeholder="Fullname" 
                      name="fullname"
                      value={ this.state.fullname }
                      onChange={ this.onChange.bind(this) }
                    />
                    { errors.fullname && (
                      <div className="invalid-feedback">
                        {errors.fullname}
                      </div>
                    )}
                  </div>
                  <div className="form-group">
                    <input 
                      type="text"
                      className = {
                        classnames(
                          "form-control form-control-lg", {
                            "is-invalid": errors.address
                          }
                        )
                      }
                      placeholder="Address(City, street, number of building)"
                      name="address"
                      value={ this.state.address }
                      onChange={ this.onChange }
                    />
                    { errors.address && (
                      <div className="invalid-feedback">
                        {errors.address}
                      </div>
                    )}
                  </div>
                  <div className="form-group">
                    <input 
                      type="text"
                      className = {
                        classnames(
                          "form-control form-control-lg", {
                            "is-invalid": errors.cardNumber
                          }
                        )
                      }
                      placeholder="Credit card number"
                      name="cardNumber"
                      value={ this.state.cardNumber }
                      onChange={ this.onChange.bind(this) }
                    />
                    { errors.cardNumber && (
                      <div className="invalid-feedback">
                        {errors.cardNumber}
                      </div>
                    )}
                  </div>
                  <input type="submit" className="btn btn-primary btn-block mt-4" />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

AddClient.propTypes = {
  createClient: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors
});

export default connect(
  mapStateToProps,
  { createClient }
)(AddClient);
