import React, { Component } from "react";
import { Link } from "react-router-dom";
import Backlog from "./Backlog";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getBacklog } from "../../actions/backlogActions";

class ClientBoard extends Component {

  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getBacklog(id);
  }

  render() {
    const { id } = this.props.match.params;
    return (
      <div className="container">
      <br></br>
        <Link to={`/addAccount/${id}`} className="btn btn-primary mb-3">
          <p> Create Account</p>
        </Link>
        <br />
        <hr />
        <Backlog />
      </div>
    );
  }
}

ClientBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired
};

 const mapStateToProps = state => ({
  backlog: state.backlog
});

 export default connect(
  mapStateToProps,
  { getBacklog }
)(ClientBoard);