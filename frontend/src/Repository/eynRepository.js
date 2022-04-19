import axios from '../custom-axios/axios'

const eynService = {
    fetchCategories: () => {
        return axios.get("/categories");
    }
}

export default eynService;