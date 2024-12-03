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
  }
}

export default InspectAPI;
