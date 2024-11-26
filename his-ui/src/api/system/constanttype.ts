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
      params:data
    })
  },
  add(data:any){
    return request({
      url:"/constanttype/add",
      method:"get",
      params:data
    })
  },
  delete(data:any){
    return request({
      url:"/constanttype/delete",
      method:"get",
      params:data
    })
  },
}

export default ConstanttypeAPI;
