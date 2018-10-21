import React, { Component } from "react";
import { Link } from "react-router-dom";
import Backlog from "./Backlog";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getBacklog } from "../../actions/backlogActions";

class ClientBoard extends Component {

  constructor() {
    super();
    this.state = {
      errors: {}
    };
  }

  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getBacklog(id);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  render() {
    const { id } = this.props.match.params;
    const { accounts } = this.props.backlog;
    const { errors } = this.state;
    let BoardContent;
    const boardAlgorithm = (errors, accounts) => {
      if (accounts.length < 1) {
        if (errors.accountNotFound) {
          return (
            <div className="alert alert-danger text-center" role="alert">
              {errors.accountNotFound}
            </div>
          );
        } else {
          return (
            <div className="alert alert-info text-center" role="alert">
              No account on this board
            </div>
          );
        }
      } else {
        return <Backlog accounts_prop={accounts} />;
      }
    };
    
    BoardContent = boardAlgorithm(errors, accounts);
    
    return (
      <div className="container">
      <br></br>
        <Link to={`/addAccount/${id}`} className="btn btn-light">
          <p>Create account</p>
        </Link>
        <br />
        <hr />
        { BoardContent }
      </div>
    );
  }
}

ClientBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

 const mapStateToProps = state => ({
  backlog: state.backlog,
  errors: state.errors
});

 export default connect(
  mapStateToProps,
  { getBacklog }
)(ClientBoard);