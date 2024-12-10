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
  get_one_by_regist(data:any){
    return request({
      url:"/record/get_one_by_regist",
      method:"get",
      params:data
    })
  },
  update_by_casenumber(data:any){
    return request({
      url:"/record/update_by_casenumber",
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
  searchbyname(data:any){
    return request({
      url:"/register/searchByBelong",
      method:"get",
      params:data
    })
  },
}

export default MenzhenAPI;
