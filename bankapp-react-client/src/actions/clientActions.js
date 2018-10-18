import axios from "axios";
import { GET_ERRORS, GET_CLIENTS, GET_CLIENT } from "./types";

export const createClient = (client, history) => async dispatch => {
    try {
        const res = await axios.post("http://localhost:8080/bank/client", client);
        history.push("/dashboard");
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

export const getClients = () => async dispatch => {
    const res = await axios.get("http://localhost:8080/bank/client/all");
    dispatch ({
        type: GET_CLIENTS,
        payload: res.data
    });
};

export const getClient = (id_client, history) => async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8080/bank/client/${ id_client }`);
        dispatch ({
            type: GET_CLIENT,
            payload: res.data
        });
    } catch (error) {
        history.push("/dashboard");
        
    }
};