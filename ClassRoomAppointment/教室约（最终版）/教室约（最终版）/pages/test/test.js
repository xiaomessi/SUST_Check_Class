//logs.js
const util = require('../../utils/util.js')








Page({
  //
  data: {
    color: "#BBBBBB",
    //number:"",
    organization: "",
    name: "",
    telphone: "",
    description: "",
    logs: []
  },

  onLoad: function () {
    this.setData({
      logs: (wx.getStorageSync('logs') || []).map(log => {
        return util.formatTime(new Date(log))
      })
    })
  },



  organization(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      organization: e.detail.value
    })
  },

  name(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      name: e.detail.value
    })
  },

  telphone(e) {
    var that = e.detail.value
    var s
    s = that
    console.log("正常提交", s.length)
    console.log('picker发送选择改变，携带值为', e.detail.value),
      this.setData({
        telphone: e.detail.value,
      })

    if (s.length != 11) {
      console.log('picker发送选择改变，携带值为', e.detail.value)
      console.log("长度：", s.length)
      this.setData({
        color: "red",
        //number:"",
        telphone: "",
      })
    }



  },

  description(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      description: e.detail.value
    })
  },

  degnji() {

    if (this.data.organization == "" || this.data.name == "" || this.data.telphone == 0 || this.data.description == "") {
      
      wx.showModal({
        title: '提示',
        content: '请完整填写且正确的数据',
        success: function (res) {
          if (res.confirm) {
            console.log('用户点击确定')
          }
        }
      })
      return
    }
    var organization = this.data.organization
    var name = this.data.name
    var telphone = this.data.telphone
    var description = this.data.description
   console.log("草拟吗", description)
    wx.setStorageSync('organization',organization)
     wx.setStorageSync('name',name)
     wx.setStorageSync('telphone',telphone)
     wx.setStorageSync('description',description)



    wx.request({
      url: 'http://47.112.23.227:8888/ClassroomAppointment/user/setUserAndCookie',
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      data: {
        organization: this.data.organization,
        name: this.data.name,
        telphone: this.data.telphone,
        description: this.data.description,
      },
      success: function (res) {
        var that = res.data
        console.log('收到res', that.status)
        if (that.status == "success") {
          wx.navigateTo({
            url: '../search/search?source=apply'
          })
        }

        // name: "",
        //   telphone: "",
        //     description: "",
        //       logs: []

        else {
          wx.showModal({
            title: '提示',
            content: '登记失败',
            success: function (res) {
              if (res.confirm) {
                console.log('用户点击确定')
              }
            }
          })
        }
      },
      fail: function (res) {

      }
    })
  },


})




