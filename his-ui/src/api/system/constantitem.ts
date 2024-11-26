import request from "@/utils/request";

const ConstantItemAPI = {
  getGender(data:any){
    return request({
      url:"/constantitem/get_gender",
      method:"get",
      params:data
    })
  },
  getAll(){
    return request({
      url:"/constantitem/getAll",
      method:"get",
    })
  },

}

export default ConstantItemAPI;
