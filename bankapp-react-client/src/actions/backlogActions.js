import axios from "axios";
import { GET_ERRORS, GET_BACKLOG, GET_ACCOUNT, DELETE_ACCOUNT } from "./types";

 export const addAccount = (
  backlog_id,
  account,
  history
) => async dispatch => {
  try {
    await axios.post(`/bank/backlog/${ backlog_id }`, account);
    history.push(`/clientBoard/${ backlog_id }`);
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
    const res = await axios.get(`/bank/backlog/${ backlog_id }`);
    dispatch({
      type: GET_BACKLOG,
      payload: res.data
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const getAccount = (backlog_id, account_id, history) => async dispatch => {
  try {
    const res = await axios.get(`/bank/backlog/${ backlog_id }/${ account_id }`)
    dispatch({
      type: GET_ACCOUNT,
      payload: res.data
    });
  } catch (error) {
    history.push("/dashboard");
    
  }
};

export const updateAccount = (backlog_id, account_id, account, history) => async dispatch => {
  try {
    await axios.patch(`/bank/backlog/${ backlog_id }/${ account_id }`, account)
    history.push(`/clientBoard/${ backlog_id }`)
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const deleteAccount = (backlog_id, account_id) => async dispatch => {
  if (window.confirm(
        `Are you sure? Thiw will delete the account ${ account_id }, this action cannot be undone`
    ))
  {
    await axios.delete(`/bank/backlog/${ backlog_id }/${ account_id }`);
    dispatch ({
        type: DELETE_ACCOUNT,
        payload: account_id
    }); 
  }
};