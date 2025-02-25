## 安装Qdrant向量数据库

> 这里使用了dockerpull的镜像加速，如果无法访问，请自行更换镜像源
> 原始镜像为：`qdrant/qdrant`

```yml
version: '3'
services:
  qdrant:
    image: dockerpull.pw/qdrant/qdrant
    ports:
      - "6333:6333"
      - "6334:6334"
    restart: always

```

端口6333：用于HTTP API
端口6334：用于gRPC API

管理后台的访问地址为：http://192.168.56.106:6333/dashboard

我本机ip为：192.168.56.106，所以访问地址为：http://192.168.56.106:6333



And	同时满足多个条件	Filter.and(condition1, condition2)
Or	满足其中任意一个条件	Filter.or(condition1, condition2)
Not	不满足条件	Filter.not(condition)
IsEqualTo	等于	new IsEqualTo("field", "value")
IsGreaterThan	大于	new IsGreaterThan("field", value)
IsLessThan	小于	new IsLessThan("field", value)
IsIn	在列表内	new IsIn("field", listOfValues)
