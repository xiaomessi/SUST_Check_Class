// pages/success/success.js
Page({
 

  /**
   * 组件的初始数据
   */
  data: {
    imgUrls: [
      'https://images.unsplash.com/photo-1551334787-21e6bd3ab135?w=640',
      'https://images.unsplash.com/photo-1551214012-84f95e060dee?w=640',
      'https://images.unsplash.com/photo-1551446591-142875a901a1?w=640'
    ],
    week: "",
    build: "",
    buildlevel: "",
    time: "",
    day: "",
    buildnumber: "",
    classroom: []
  },
  reback: function (options) {
    wx.navigateTo({
      url: '../index/index',
    });
  },
  /**
   * 组件的方法列表
   */
  onLoad: function (option) {
    //options对象为上个页面的传参队形
    // console.log(111111);
    console.log(option);
    this.setData({
      week: option.week,
      build: option.build,
      buildlevel: option.buildlevel,
      time: option.time,
      day: option.day,
      buildnumber: option.buildnumber,
      classroom:option.classroom,
    })

  },
})
