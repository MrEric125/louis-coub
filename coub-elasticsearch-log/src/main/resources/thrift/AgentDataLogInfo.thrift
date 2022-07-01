namespace java com.louis.coub.thrift

struct AgentDataLogInfo{
    1:i32 id
    2:string name
    3:i32 age=0
}

service AgentDataLogInfoService{
    AgentDataLogInfo getById(1:i32 id)

}