export interface IRoute {
  id: number
  pid: number
  name: string
  path: string
  redirect?: string
  component: string
  meta: {
    title: string
    icon?: string
  }
}

const routes: IRoute[] = [
  {
    id: 1,
    pid: 0,
    path: '/home',
    name: 'Home',
    component: 'home/index.vue',
    meta: {
      title: '首页',
      icon: 'home'
    }
  },
  {
    id: 2,
    pid: 0,
    path: '/sale',
    name: 'Sale',
    redirect: '/sale/counsel',
    component: 'sale/index.vue',
    meta: {
      title: '营销管理',
      icon: 'sale'
    }
  },
  {
    id: 3,
    pid: 2,
    path: 'counsel',
    name: 'CounselSale',
    component: 'sale/counsel/index.vue',
    meta: {
      title: '咨询管理',
      icon: ''
    }
  },
  // {
  //   id: 4,
  //   pid: 2,
  //   path: 'intention',
  //   name: 'IntentionSale',
  //   component: 'sale/intention/index.vue',
  //   meta: {
  //     title: '意向客户',
  //     icon: ''
  //   }
  // },
  {
    id: 5,
    pid: 2,
    path: 'book',
    name: 'BookSale',
    component: 'sale/book/index.vue',
    meta: {
      title: '预定管理',
      icon: ''
    }
  },
  {
    id: 6,
    pid: 0,
    path: '/live',
    name: 'Live',
    redirect: '/live/bed',
    component: 'live/index.vue',
    meta: {
      title: '入住管理',
      icon: 'live'
    }
  },
  {
    id: 7,
    pid: 6,
    path: 'bed',
    name: 'BedLive',
    component: 'live/bed/index.vue',
    meta: {
      title: '床位全景',
      icon: ''
    }
  },
  {
    id: 8,
    pid: 6,
    path: 'enter',
    name: 'EnterLive',
    component: 'live/enter/index.vue',
    meta: {
      title: '入住签约',
      icon: ''
    }
  },
  {
    id: 9,
    pid: 6,
    path: 'leave',
    name: 'LeaveLive',
    component: 'live/leave/index.vue',
    meta: {
      title: '外出登记',
      icon: ''
    }
  },
  {
    id: 10,
    pid: 6,
    path: 'visitor',
    name: 'VisitorLive',
    component: 'live/visitor/index.vue',
    meta: {
      title: '来访登记',
      icon: ''
    }
  },
  {
    id: 11,
    pid: 6,
    path: 'accident',
    name: 'AccidentLive',
    component: 'live/accident/index.vue',
    meta: {
      title: '事故登记',
      icon: ''
    }
  },
  {
    id: 12,
    pid: 6,
    path: 'aplly',
    name: 'CheckOutLive',
    component: 'live/apply/index.vue',
    meta: {
      title: '退住申请',
      icon: ''
    }
  },
  {
    id: 13,
    pid: 0,
    path: '/people',
    name: 'People',
    redirect: '/people/old',
    component: 'people/index.vue',
    meta: {
      title: '人员管理',
      icon: 'people'
    }
  },
  {
    id: 14,
    pid: 13,
    path: 'old',
    name: 'OldPeople',
    component: 'people/old/index.vue',
    meta: {
      title: '长者档案',
      icon: ''
    }
  },
  {
    id: 15,
    pid: 13,
    path: 'staff',
    name: 'StaffPeople',
    component: 'people/staff/index.vue',
    meta: {
      title: '员工管理',
      icon: ''
    }
  },
  // {
  //   id: 16,
  //   pid: 13,
  //   path: 'activity',
  //   name: 'ActivityPeople',
  //   component: 'people/activity/activity.vue',
  //   meta: {
  //     title: '活动管理',
  //     icon: ''
  //   }
  // },
  {
    id: 17,
    pid: 0,
    path: '/serve',
    name: 'Serve',
    redirect: '/serve/project',
    component: 'serve/index.vue',
    meta: {
      title: '服务管理',
      icon: 'serve'
    }
  },
  {
    id: 18,
    pid: 17,
    path: 'project',
    name: 'ProjectServe',
    component: 'serve/project/index.vue',
    meta: {
      title: '服务项目',
      icon: ''
    }
  },
  {
    id: 19,
    pid: 17,
    path: 'level',
    name: 'LevelServe',
    component: 'serve/level/index.vue',
    meta: {
      title: '护理等级',
      icon: ''
    }
  },
  {
    id: 20,
    pid: 17,
    path: 'book',
    name: 'BookServe',
    component: 'serve/book/index.vue',
    meta: {
      title: '服务预定',
      icon: ''
    }
  },
  // {
  //   id: 21,
  //   pid: 0,
  //   path: '/resource',
  //   name: 'Resource',
  //   redirect: '/resource/info',
  //   component: 'resource/index.vue',
  //   meta: {
  //     title: '物资管理',
  //     icon: 'resource'
  //   }
  // },
  // {
  //   id: 22,
  //   pid: 21,
  //   path: 'info',
  //   name: 'InfoResource',
  //   component: 'resource/info/index.vue',
  //   meta: {
  //     title: '物资信息',
  //     icon: ''
  //   }
  // },
  // {
  //   id: 23,
  //   pid: 21,
  //   path: 'Storage',
  //   name: 'StorageResource',
  //   component: 'resource/storage/index.vue',
  //   meta: {
  //     title: '仓库设置',
  //     icon: ''
  //   }
  // },
  // {
  //   id: 24,
  //   pid: 21,
  //   path: 'enter',
  //   name: 'EnterResource',
  //   component: 'resource/enter/index.vue',
  //   meta: {
  //     title: '入库管理',
  //     icon: ''
  //   }
  // },
  // {
  //   id: 25,
  //   pid: 21,
  //   path: 'leave',
  //   name: 'LeaveResource',
  //   component: 'resource/leave/index.vue',
  //   meta: {
  //     title: '出库管理',
  //     icon: ''
  //   }
  // },
  // {
  //   id: 26,
  //   pid: 21,
  //   path: 'search',
  //   name: 'SearchResource',
  //   component: 'resource/search/index.vue',
  //   meta: {
  //     title: '库存查询',
  //     icon: ''
  //   }
  // },
  {
    id: 27,
    pid: 0,
    path: '/diet',
    name: 'Diet',
    redirect: '/diet/dish',
    component: 'diet/index.vue',
    meta: {
      title: '餐饮管理',
      icon: 'diet'
    }
  },
  {
    id: 28,
    pid: 27,
    path: 'dish',
    name: 'DishDiet',
    component: 'diet/dish/index.vue',
    meta: {
      title: '菜品管理',
      icon: ''
    }
  },
  {
    id: 29,
    pid: 27,
    path: 'package',
    name: 'PackageDiet',
    component: 'diet/package/index.vue',
    meta: {
      title: '餐饮套餐',
      icon: ''
    }
  },
  {
    id: 30,
    pid: 27,
    path: 'order',
    name: 'OrderDiet',
    component: 'diet/order/index.vue',
    meta: {
      title: '点餐',
      icon: ''
    }
  },
  {
    id: 31,
    pid: 0,
    path: '/charge',
    name: 'Charge',
    redirect: '/charge/prestore',
    component: 'charge/index.vue',
    meta: {
      title: '费用管理',
      icon: 'charge'
    }
  },
  {
    id: 32,
    pid: 31,
    path: 'prestore',
    name: 'PrestoreCharge',
    component: 'charge/prestore/index.vue',
    meta: {
      title: '预存充值',
      icon: ''
    }
  },
  {
    id: 33,
    pid: 31,
    path: 'record',
    name: 'RecordCharge',
    component: 'charge/record/index.vue',
    meta: {
      title: '消费记录',
      icon: ''
    }
  },
  {
    id: 34,
    pid: 31,
    path: 'audit',
    name: 'AuditCharge',
    component: 'charge/audit/index.vue',
    meta: {
      title: '退住审核',
      icon: ''
    }
  },
  {
    id: 35,
    pid: 0,
    path: '/base',
    name: 'Base',
    redirect: '/base/sale/origin',
    component: 'base/index.vue',
    meta: {
      title: '基础配置',
      icon: 'base'
    }
  },
  {
    id: 36,
    pid: 35,
    path: 'sale',
    name: 'SaleBase',
    redirect: '/base/sale/origin',
    component: 'base/sale/index.vue',
    meta: {
      title: '营销',
      icon: ''
    }
  },
  {
    id: 37,
    pid: 36,
    path: 'origin',
    name: 'OriginSaleBase',
    component: 'base/sale/origin/index.vue',
    meta: {
      title: '来源渠道',
      icon: ''
    }
  },
  // {
  //   id: 38,
  //   pid: 36,
  //   path: 'tag',
  //   name: 'TagSaleBase',
  //   component: 'base/sale/tag/index.vue',
  //   meta: {
  //     title: '客户标签',
  //     icon: ''
  //   }
  // },
  {
    id: 39,
    pid: 35,
    path: 'live',
    name: 'LiveBase',
    redirect: '/base/live/room',
    component: 'base/live/index.vue',
    meta: {
      title: '入住',
      icon: ''
    }
  },
  {
    id: 40,
    pid: 39,
    path: 'room',
    name: 'RoomLiveBase',
    component: 'base/live/room/index.vue',
    meta: {
      title: '房间类型',
      icon: ''
    }
  },
  {
    id: 41,
    pid: 39,
    path: 'flat',
    name: 'FlatLiveBase',
    component: 'base/live/flat/index.vue',
    meta: {
      title: '楼栋管理',
      icon: ''
    }
  },
  // {
  //   id: 42,
  //   pid: 35,
  //   path: 'activity',
  //   name: 'ActivityBase',
  //   component: 'base/activity/index.vue',
  //   meta: { title: '活动', icon: '' }
  // }
]

export default routes
