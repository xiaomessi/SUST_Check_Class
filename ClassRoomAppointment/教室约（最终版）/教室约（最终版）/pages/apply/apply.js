Page({
  data: {
    week: "",
    build: "",
    buildlevel: "",
    time: "",
    day: "",
    buildnumber: "",
    organization: "",
    name: "",
    telphone: "",
    description: "",
    roomData: [],
    //预约的教室
    classroom: []
  },


  onLoad: function (options) {
    //options对象为上个页面的传参队形
    console.log(options);

  var organization = wx.getStorageSync('organization')
    //console.log("这是本地存储信息", organization);
    var name = wx.getStorageSync('name')
    var telphone = wx.getStorageSync('telphone')
    var description = wx.getStorageSync('description')

    this.setData({
      organization: organization,
      name: name,
      telphone: telphone,
      description: description
    })

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
    var _this = this;
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
  chb(e) {
    //  console.log(this.data);
    this.setData({
      classroom: e.detail.value
    });
    //  console.log(this.data);
  },

  //预约成功
  apply() {
    this.data.classroom.forEach(function(item,index,array) {
        item = String(item);
        item = item.split("");
        item.shift();
        item = item.join("");
        array[index] = item;
      }
    )
    //console.log(this.data);
    var that = this;
    wx.request({
      url: 'http://47.112.23.227:8888/ClassroomAppointment/apply/applyClassrooms', // 接口地址(需要提供)
      data: {
        organization: this.data.organization,
        name: this.data.name,
        telphone: this.data.telphone,
        description: this.data.description,
        week: this.data.week,
        build: this.data.build,
        buildlevel: this.data.buildlevel,
        time: this.data.time,
        day: this.data.day,
        buildnumber: this.data.buildnumber,
        classroom: this.data.classroom
      },
      method:"GET",
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data);
        wx.navigateTo({
          url: `../success/success?week=${that.data.week}&build=${that.data.build}&buildlevel=${that.data.buildlevel}&time=${that.data.time}&day=${that.data.day}&buildnumber=${that.data.buildnumber}&classroom=${that.data.classroom}`
        });
      }
    })
  }
})