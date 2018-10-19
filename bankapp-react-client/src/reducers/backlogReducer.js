import {
    GET_BACKLOG,
    GET_ACCOUNT,
    DELETE_ACCOUNT
  } from "../actions/types";
  
  const initialState = {
    accounts: [],
    account: {}
  };
  console.log(accounts);
  
  export default function(state = initialState, action) {
    switch (action.type) {
      case GET_BACKLOG:
        return {
          ...state,
          accounts: action.payload          
        };
  
      case GET_ACCOUNT:
        return {
          ...state,
          account: action.payload
        };
  
      case DELETE_ACCOUNT:
        return {
          ...state
  
        };
  
      default:
        return state;
    }
  }