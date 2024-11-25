import request from "@/utils/request";

const ConstantItemAPI = {
  getGender(data:any){
    return request({
      url:"/constantitem/get_gender",
      method:"get",
      params:data
    })
  },

}

export default ConstantItemAPI;
