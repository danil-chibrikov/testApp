import React, { Component } from 'react';
import { getClient, createClient } from "../../actions/clientActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import classnames from "classnames";

class UpdateClient extends Component {
    //set state
    constructor() {
        super()

        this.state = {
            id_client: "",
            fullname: "",
            address: "",
            creditCardNumber: "",
            created_At: "",
            updated_At: "",
            errors: {}
        };
        
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    componentWillReceiveProps(nextProps) {
        if(nextProps.errors) {
            this.setState({ errors: nextProps.errors });
        }

        const {
            id_client,
            fullname,
            address,
            creditCardNumber,
            created_At,
            updated_At
        } = nextProps.client;

        this.setState({
            id_client,
            fullname,
            address,
            creditCardNumber,
            created_At,
            updated_At
        });
    }

    componentDidMount() {
        const { id_client } = this.props.match.params;
        this.props.getClient(id_client, this.props.history);
    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    onSubmit(e) {
        e.preventDefault()

        const UpdateClient = {
            id_client: this.state.id_client,
            fullname: this.state.fullname,
            address: this.state.address,
            creditCardNumber: this.state.creditCardNumber,
            created_At: this.state.created_At,
            updated_At: this.state.updated_At
        };

        this.props.createClient(UpdateClient, this.props.history);
    }

  render() {
      const { errors } = this.state;
    return (
    <div className="client">
        <div className="container">
            <div className="row">
                <div className="col-md-8 m-auto">
                    <h5 className="display-4 text-center">Update Client form</h5>
                    <hr />
                    <form onSubmit={ this.onSubmit }>
                        <div className="form-group">
                            <input 
                                type="text" 
                                className={classnames("form-control form-control-lg", {
                                    "is-invalid": errors.fullname
                                })} 
                                placeholder="Fullname"
                                name="fullname"
                                value={ this.state.fullname } 
                                onChange={ this.onChange }
                            />
                            { errors.fullname && (
                                <div className="invalid-feedback">{ errors.fullname }</div>
                            )}
                        </div>
                        <div className="form-group">
                            <input 
                                type="text" 
                                className={classnames("form-control form-control-lg", {
                                    "is-invalid": errors.address
                                })} 
                                placeholder="Address(City, street, number of building)"
                                name="address"
                                value={ this.state.address }
                                onChange={ this.onChange }
                             />
                             { errors.address && (
                                <div className="invalid-feedback">{ errors.address }</div>
                            )}
                        </div>
                        <div className="form-group">
                            <input 
                                type="text"
                                className="form-control form-control-lg"
                                placeholder="Credit card number"
                                name="creditCardNumber"
                                value={ this.state.creditCardNumber }
                                disabled
                            />
                        </div>
                        <h6>Registration date</h6>
                        <div className="form-group">
                            <input 
                                type="datetime" 
                                className="form-control form-control-lg" 
                                name="created_At"
                                value={ this.state.created_At }
                                disabled
                            />
                        </div>
                        <h6>Last update date</h6>
                        <div className="form-group">
                            <input 
                                type="datetime" 
                                className="form-control form-control-lg" 
                                name="updated_At"
                                value={ this.state.updated_At }
                                disabled
                            />
                        </div>

                        <input type="submit" className="btn btn-primary btn-block mt-4" />
                    </form>
                </div>
            </div>
        </div>
    </div>
    );
  }
}

UpdateClient.PropTypes = {
    getClient: PropTypes.func.isRequired,
    createClient: PropTypes.func.isRequired,
    client: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    client: state.client.client,
    errors: state.errors
})

export default connect(
    mapStateToProps,
    { getClient, createClient }
)(UpdateClient);