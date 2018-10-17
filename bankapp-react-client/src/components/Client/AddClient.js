import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createClient } from "../../actions/clientActions";

class AddClient extends Component {
  constructor() {
    super()

    this.state={
      "fullname": "",
      "address": "",
      "phoneNumber": ""
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    const newClient = {
      "fullname": this.state.fullname,
      "address": this.state.address,
      "phoneNumber": this.state.phoneNumber
    };
    this.props.createClient(newClient, this.props.history);
  }

  render() {
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
                                    className="form-control form-control-lg"
                                    placeholder="Client Fullname" 
                                    name="fullname"
                                    value={ this.state.fullname }
                                    onChange={ this.onChange.bind(this) }
                                    />
                            </div>
                            <div className="form-group">
                                <input 
                                    type="text"
                                    className="form-control form-control-lg"
                                    placeholder="Client address"
                                    name="address"
                                    value={ this.state.address }
                                    onChange={ this.onChange }
                                    />
                            </div>
                            <div className="form-group">
                                <input 
                                    type="text"
                                    className="form-control form-control-lg"
                                    placeholder="Client phoneNumber"
                                    name="phoneNumber"
                                    value={ this.state.phoneNumber }
                                    onChange={ this.onChange.bind(this) }
                                    />
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
  createClient: PropTypes.func.isRequired
};

export default connect(
  null,
  { createClient }
)(AddClient);
