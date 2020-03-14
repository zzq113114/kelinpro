var data = [
    {
        path: '/home',
        name: ''
    },
    {
        name: '家政公司',
        child: [
            {
                name: '公司信息管理',
                path: '/company/manager'
            },
        ]
    },
    {
        name: '接单管理',
        child: [
            {
                path: '/order/manager',
                name: '接单管理'
            },
            {
                path: '/order/enter',
                name: '接单管理'
            }
        ]
    },
    {
        name: '派单管理',
        child: [
            {
                path: '/order/dispatch',
                name: '派单管理'
            }
        ]
    },
    {
        name: '员工管理',
        child: [
            {
                path: '/staff/manager',
                name: '员工管理'
                // permission: ['edit']
            },
            {
                path: '/staff/userManager',
                name: '账号管理'
            },
            {
                path: '/staff/roleManager',
                name: '角色管理'
            }
        ]
    },
    {
        name: '数据统计',
         child: [
          {
                path: '/statistics/a',
                name: '财务报表'
            }
       ]
    },
    {
        name: '财务统计',
        child: [
            {
                path: '/order/orderJudge',
                name: '订单审核'
            }
        ]
    },
    {
        name: '商品管理',
        child: [
            {
                path: '/good/goods',
                name: '商品'
            }
        ]
    },
    {
        path: '/i18n',
        name: '国际化'
    },
    {
        path: '/theme',
        name: '主题切换'
    }
]

export default [{
    path: '/user/navlist',
    data: data
}]
