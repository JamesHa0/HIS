import request from "@/utils/request";

const MenzhenAPI = {
  get_by_belong(data:any){
    return request({
      url:"/register/getByBelong",
      method:"get",
      params:data
    })
  },
}

export default MenzhenAPI;
