//index.js
//获取应用实例

Page({
  data: {

    imgUrls: [
      'https://images.unsplash.com/photo-1551334787-21e6bd3ab135?w=640',
      'https://images.unsplash.com/photo-1551214012-84f95e060dee?w=640',
      'https://images.unsplash.com/photo-1551446591-142875a901a1?w=640'
    ],
    autoplay: false,
    aFlag: false,
    bFlag: false,
    userInfo: {
      avatarUrl: "",//用户头像
      nickName: "",//用户昵称
    },
    organization_:"",
    name_:"",
    telphone_:"",
    description_:"",

    classroom:"",

    array1:[],
  },

  changeAutoplay(e) {
    this.setData({
      autoplay: !this.data.autoplay
    })
  },
  showA: function() {
    this.setData({
      aFlag: true,
      bFlag: false,
      cFlag: false,
    });
  },
  showB: function () {
    this.setData({
      aFlag: false,
      bFlag: true,
      cFlag: false,
    });
  },
  showC: function () {
    var organization = wx.getStorageSync('organization')
    console.log("这是本地存储信息", organization)
    var name = wx.getStorageSync('name')
    var telphone = wx.getStorageSync('telphone')
    var description = wx.getStorageSync('description')

    this.setData({
      organization_: organization,
      name_: name,
      telphone_: telphone,
      description_: description
    })

    this.setData({
      aFlag: false,
      bFlag: false,
      cFlag: true,
    });


    let _this = this;
    wx.request({
      url: 'http://47.112.23.227:8888/ClassroomAppointment/user/getUserMessage', // 接口地址
      data: {
        organization: this.data.organization_,
        name: this.data.name_,
        telphone: this.data.telphone_,
        description: this.data.description_
      },
      method: "GET",
      header: {
        'content-type': 'application/json' // 默认值
      },
      
      success: function (res) {
        
        console.log('res.data',res.data);

        var that = res.data
        var classroom = ""
        if (that.status == "success"){
          //for (let i = 0; i < that.length; ++i) {
            
            _this.setData({
              array1:that.data
          })
          
          var arr = _this.data.array1
          for (var i = 0, len = arr.length; i < len; i++) {
            
            console.log('len', len);
            console.log('array',arr[i]);
            var hunchong = arr[i]
            console.log('hunchong', hunchong);
            
            for (var idx in hunchong) {
              var subject = hunchong[idx]
              console.log('subject', subject)
              classroom = " " + subject + classroom
              console.log('classroom', classroom);////////////////////
            }
            //classroom = "\n" + classroom
            // for (var j = 0, cd = hunchong.length; j < cd; j++) {
            //   console.log('长度', cd);
            //   classroom === classroom + hunchong[j],
            //     _this.setData({
            //    })
            // }
          
            _this.setData({
              classroom:classroom
            })
          }

          console.log('classroom', _this.data.classroom)
          console.log('res.data.data',that.data)
          console.log('array1', _this.data.array1)
         // }
          
        }
      }
    });
  },
  jumpBtn1:function(options){
    wx.navigateTo({
      url: '../search/search?source=query',
    });
  },
  jumpBtn2(){
    wx.navigateTo({
      url: '../test/test'
    })
    // wx.request({
    //   url: '../test/test', // 接口地址
    //   data: that.data,//附带参数
    //   method: "GET",
    //   header: {
    //     'content-type': 'application/json' // 默认值
    //   },
    //   success: function (res) {
    //     console.log(res.data);
    //     if(res.data=1){
    //       wx.navigateTo({
    //         url: `../logs/login`
    //       });
    //     }
    //     else{
    //       wx.navigateTo({
    //         url: `../search/search`
    //       });
    //     }
    //   }
    // })
  },
  add_address_fun: function () {
    wx.navigateTo({
      url: 'add_address/add_address',
    })
  },
  onLoad: function (options) {
    var that = this;

    

    
    /**
     * 获取用户信息
     */
    wx.getUserInfo({
      success: function (res) {
        console.log(res);
        var avatarUrl = 'userInfo.avatarUrl';
        var nickName = 'userInfo.nickName';
        that.setData({
          [avatarUrl]: res.userInfo.avatarUrl,
          [nickName]: res.userInfo.nickName,
        })
      }
    })
  },

})