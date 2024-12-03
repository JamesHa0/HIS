import request from "@/utils/request";

const MenzhenAPI = {
  get_by_belong(data:any){
    return request({
      url:"/register/getByBelong",
      method:"get",
      params:data
    })
  },
  add_record(data:any){
    return request({
      url:"/record/add_one",
      method:"get",
      params:data
    })
  },
  get_one_by_id(data:any){
    return request({
      url:"/record/get_one_by_id",
      method:"get",
      params:data
    })
  },
}

export default MenzhenAPI;
