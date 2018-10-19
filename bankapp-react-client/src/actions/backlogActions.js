import axios from "axios";
import { GET_ERRORS, GET_BACKLOG } from "./types";

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

export const getBacklog = backlog_id => async dispatch => {
  try {
    const res = await axios.get(`/bank/backlog/${backlog_id}`);
    dispatch({
      type: GET_BACKLOG,
      payload: res.data
    });
  } catch (err) {}
};