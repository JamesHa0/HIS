import request from "@/utils/request";

const GuahaoAPI = {
  getall(){
    return request({
      url:"/guahao/getAll",
      method:"get",
    })
  },
  add_register(data:any){
    return request({
      url:"/guahao/add",
      method:"get",
      params:data
    })
  },
  update(data:any){
    return request({
      url:"/guahao/update",
      method:"get",
      params:data
    })
  }
}

export default GuahaoAPI;
