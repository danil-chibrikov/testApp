import axios from "axios";
import { GET_ERRORS, GET_CLIENTS, GET_CLIENT, DELETE_CLIENT } from "./types";

export const createClient = (client, history) => async dispatch => {
    try {
        const res = await axios.post("/bank/client", client);
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
    const res = await axios.get("/bank/client/all");
    dispatch ({
        type: GET_CLIENTS,
        payload: res.data
    });
};

export const getClient = (id, history) => async dispatch => {
    try {
        await axios.get(`/bank/client/${ id }`);
        dispatch ({
            type: GET_CLIENT,
            payload: id
        });
    } catch (error) {
        history.push("/dashboard");
        
    }
};

export const deleteClient = id => async dispatch => {
    if (
        window.confirm(
            "Are you sure? Thiw will delete the client and all the data related to it"
        )
    ) {
        await axios.delete(`/bank/client/${ id }`);
        dispatch ({
            type: DELETE_CLIENT,
            payload: id
        }); 
    }
};