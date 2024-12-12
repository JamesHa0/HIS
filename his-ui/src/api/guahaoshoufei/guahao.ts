import request from "@/utils/request";

const GuahaoAPI = {
  getall(data:any){
    return request({
      url:"/register/getAll",
      method:"get",
      params:data
    })
  },
  add_register(data:any){
    return request({
      url:"/register/add",
      method:"get",
      params:data
    })
  },
  search(data:any){
    return request({
      url:"/register/search",
      method:"get",
      params:data
    })
  },
  update(data:any){
    return request({
      url:"/register/update",
      method:"get",
      params:data
    })
  },
  change_state(data:any){
    return request({
      url:"/register/changeState",
      method:"get",
      params:data
    })
  },
}

export default GuahaoAPI;
