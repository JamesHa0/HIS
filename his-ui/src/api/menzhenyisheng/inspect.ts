import request from "@/utils/request";

const InspectAPI = {
  get_all_inspect(){
    return request({
      url:"/inspect/get_all",
      method:"get"
    })
  },
  add_many_apply(data:any){
    return request({
      url:"/inspect/add_many_apply",
      method:"get",
      params:data
    })
  },
  get_by_registid(data:any){
    return request({
      url:"/inspect/get_by_registid",
      method:"get",
      params:data
    })
  },
  delete_many_apply(data:any){
    return request({
      url:"/inspect/delete_many_apply",
      method:"get",
      params:data
    })
  },
  update_result(data:any){
    return request({
      url:"/inspect/update_result",
      method:"get",
      params:data
    })
  },
  pay(data:any){
    return request({
      url:"/inspect/pay",
      method:"get",
      params:data
    })
  },
}

export default InspectAPI;
