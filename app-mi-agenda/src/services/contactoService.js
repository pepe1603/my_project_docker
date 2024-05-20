import axios from "axios";

const API_URL = 'http://localhost:9090/api/v1/apiMyAgenda/contactos';

export default {
    getAllContactos() {
        return axios.get(API_URL)
                .then(response => response.data)
                .catch(error => {
                    throw error;
                });
    },

    saveContacto(contacto) {
        return axios.post(API_URL, contacto)
                    .then(response => response.data)
                    .catch(error => {
                        throw error;
                    });
    },

    getContactoById(idContacto) {
        return axios.get(`${API_URL}/${idContacto}`)
                    .then(response => response.data)
                    .catch(error => {
                        throw error;
                    });
    },

    deleteContacto(idContacto) {
        return axios.delete(`${API_URL}/${idContacto}`)
                    .then(error => {
                        throw error;
                    });
    },

    getAllContactosByCategoria(nombre) {
        return axios.get(`${API_URL}/categoria/${nombre}`)
                    .then(response => response.data)
                    .catch(error => {
                        throw error;
                    });
    }

};


/* ------------ puede ser otra manera

export const getAllContactos = () => {
    return axios.get(API_URL);
}

export const saveContacto = (contacto) => {
    return axios.post(API_URL, contacto);
}*/