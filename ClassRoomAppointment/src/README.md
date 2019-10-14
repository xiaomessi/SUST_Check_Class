###### build——String
###### buildlevel——int
###### buildnumber——String
###### day——int
###### time——int
###### week——int
###### classroom——int
###### code=0——success
###### code=1——failure

1、search classroom
GET请求
URL地址：http://localhost:8080/SSM/search?week=7&build=%E4%B8%80%E5%8F%B7%E6%95%99%E5%AD%A6%E6%A5%BC&buildlevel=1&time=12&day=1&buildnumber=F

前端传给后端json
```$xslt
    [
    	{"build":"一号教学楼"},
    	{"buildlevel":"1"},
    	{"buildnumber":"F"},
    	{"day":"1"},
    	{"time":"12"},
    	{"week":"7"},
    ]
```

后端传给前端json
输出样例：
```$xslt
{
code: -1,
msg: "",
data: {
    [
    	{"classroom": "105", "usage": "true"}，
    	{"classroom": "106", "usage": "true"}，
    	{"classroom": "103", "usage": "true"}，
    	{"classroom": "104", "usage": "true"}，
    	{"classroom": "102", "usage": "true"}，
    	{"classroom": "101", "usage": "true"}，
    	{"classroom": "108", "usage": "true"}
    ]
 }
}
```

2、appoint classroom：
POST请求，
URL地址：http://localhost:8080/SSM/apply

前端传给后端json
```$xslt
    [
    	{
            "build":"一号教学楼",
            "buildlevel":"1",
            "buildnumber":"F",
            "day":"1",
            "time":"12",
            "week":"7",
            "classroom":["5"]
        }
    ]
```

后端传给前端json
输出样例:
```$xslt
{
code: -1,
msg: "",
data: {
    [
    	{"state":"success"},
    	{"build":"一号教学楼"},
    	{"buildlevel":"1"},
    	{"buildnumber":"F"},
    	{"day":"1"},
    	{"time":"12"},
    	{"week":"7"},
    	{"classroom":"5"}
    ]
 }
}
```


3、apply classroom
POST请求，
URL地址：http://localhost:8080/SSM/apply

前端传后端json
```$xslt
    [
        {
            "build":"一号教学楼",
            "buildlevel":"1",
            "buildnumber":"F",
            "day":"1",
            "time":"12",
            "week":"7",
            "classroom":["5","6"]
        }
```

后端传前端json
输出样例：
```$xslt
{
code: -1,
msg: "",
data: {
    [
        {"state":"success","build":"一号教学楼","buildlevel":"1","buildnumber":"F","day":"1","time":"12","week":"7","classroom":"5"},
        {"state":"success","build":"一号教学楼","buildlevel":"1","buildnumber":"F","day":"1","time":"12","week":"7","classroom":"7"},
        {"state":"failure","build":"一号教学楼","buildlevel":"1","buildnumber":"F","day":"1","time":"12","week":"7","classroom":"8"},
    ]
 }
}
```

4.登录注册
POST请求
URL地址：http://localhost:8080/SSM/login
前端传后端json
注册
```$xslt
    [
        {
            "username":"Tom",
            "userpassword":"Li123456",
            "phonenumber":"15099997777",
            "name":"张三",
        }
    ]
```
登录
```$xslt
    [
        {
            "username":"Tom",
            "userpassword":"Li123456",
        }
    ]
```
后端传前端json

```
$xslt
{
code: 0,
msg: "",
data: {
    
 }
}
```
```$xslt
{
code: 0,
msg: "",
data: {
    
 }
}
```





