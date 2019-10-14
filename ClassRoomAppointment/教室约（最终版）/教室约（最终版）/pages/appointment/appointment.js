Page({
  data: {
    week: "",
    build: "",
    buildlevel: "",
    time: "",
    day: "",
    buildnumber: "",
    roomData: []
  },
  reback: function (options) {
    wx.navigateTo({
      url: '../index/index',
    });
  },
  onLoad: function (options) {
    //options对象为上个页面的传参队形
    console.log(options);
    this.setData({
      week: options.week,
      build: options.build,
      buildlevel: options.buildlevel,
      time: options.time,
      day: options.day,
      buildnumber: options.buildnumber
    })
    //发送ajax请求
    //发送AJAX
    let _this = this;
    wx.request({
      url: 'http://47.112.23.227:8888/ClassroomAppointment/select/selectClassrooms', // 接口地址(需要提供)
      data: options,
      //build： 一号教学楼
      //buildlevel  2
      //week 7
      //day  3
      //time 34
      //buildnumber F
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data);
        _this.setData({
          roomData: res.data
        });
      }
    })
  },

})

