import axios from "axios";

//const API_URL = 'http://api_service:9090/api/v1/apiMyAgenda/categorias'; //dedtnor del contendor si se comunican 
const API_URL = 'http://localhost:9090/api/v1/apiMyAgenda/categorias'; //fuera de docker el navegador no conocec el nombre den api_service lo toma como un dns real

export default{
    getAllCategorias() {
        return axios.get(API_URL)
        .then(response => response.data)
        .catch(error => {
            throw error;
        });
    }
};