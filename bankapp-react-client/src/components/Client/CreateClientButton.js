import React from "react";
import { Link } from "react-router-dom";

const CreateClientButton =  () => {
  return (
      <React.Fragment>
        <Link to="/addClient" className="btn btn-light">
            Create client
        </Link>
      </React.Fragment>
    );
};

export default CreateClientButton;
