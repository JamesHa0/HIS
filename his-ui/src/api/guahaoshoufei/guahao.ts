import request from "@/utils/request";

const GuahaoAPI = {
  getall(){
    return request({
      url:"/register/getAll",
      method:"get",
    })
  },
  add_register(data:any){
    return request({
      url:"/register/add",
      method:"get",
      params:data
    })
  },
  search(searchParams: { search_name: string }){
    return request({
      url:"/register/search",
      method:"get",
      params:searchParams
    })
  },
  update(data:any){
    return request({
      url:"/register/update",
      method:"get",
      params:data
    })
  }
}

export default GuahaoAPI;
