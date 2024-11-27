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
  search(searchParams: { search_name: string }){
    return request({
      url:"/guahao/search",
      method:"get",
      params:searchParams
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
