import request from "@/utils/request";

const InspectAPI = {
  getallinspect(){
    return request({
      url:"/inspect/get_all",
      method:"get"
    })
  },
}

export default InspectAPI;
