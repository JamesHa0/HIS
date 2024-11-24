import request from "@/utils/request";

const ConstanttypeAPI = {
  getAll(){
    return request({
      url:"/constanttype/getAll",
      method:"get"
    })
  },
  update(data:any){
    return request({
      url:"/constanttype/update",
      method:"get",
      data:data
    })
  }
}

export default ConstanttypeAPI;
