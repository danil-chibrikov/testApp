import axios from "axios";
import { GET_ERRORS } from "./types";

 export const addAccount = (
  backlog_id,
  account,
  history
) => async dispatch => {
  try {
    await axios.post(`/bank/backlog/${backlog_id}`, account);
    history.push(`/clientBoard/${backlog_id}`);
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};