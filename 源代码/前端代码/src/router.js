import Vue from 'vue';
import local from './local.js'
import { Message } from 'element-ui';
export default [{
        path: '/login',
        name: 'login',
        component: function(resolve) {
            require(['./views/login.vue'], resolve)
        },
    },
    {
        path: '/',
        name: 'index',
        component: function(resolve) {
            require(['./views/index.vue'], resolve)
        },
        children: [{
                path: '/',
                name: 'homepage',
                meta: {
                    father: '首页',
                    name: '首页',
                },
                component: function(resolve) {
                    require(['./views/homepage.vue'], resolve)
                }
            },
            {
                path: '/user',
                name: 'user',
                meta: {
                    father: '系统管理',
                    name: '用户管理',
                },
                component: function(resolve) {
                    require(['./views/system/user.vue'], resolve) 
                }
            },
            {
                path: '/role',
                name: 'userRole',
                meta: {
                    father: '系统管理',
                    name: '角色管理',
                },
                component: function(resolve) {
                    var check=local.checkpermission("角色管理")
                    if(!check){
                          Message.error('没有权限');
                          history.go(-1)
                          return;
                    }
                    require(['./views/system/userRole.vue'], resolve)
                    
                }
            },
            {
                path: '/dataDictionary',
                name: 'dataDictionary',
                meta: {
                    father: '系统管理',
                    name: '数据字典',
                },
                component: function(resolve) {
                    require(['./views/system/dataDictionary.vue'], resolve)
                }
            },
            {
                path: '/dataCategory',
                name: 'dataCategory',
                meta: {
                    father: '系统管理',
                    name: '数据分类',
                },
                component: function(resolve) {
                    require(['./views/system/dataCategory.vue'], resolve)
                }
            },
            {
                path: '/sysGameMain',
                name: 'sysGameMain',
                meta: {
                    father: '系统管理',
                    name: '游戏管理',
                },
                component: function(resolve) {
                    require(['./views/system/gameMain.vue'], resolve)
                }
            },
            {
                path: '/sysGameMain/a',
                name: 'sysGameMainA',
                meta: {
                    father: '系统管理',
                    name: '新增游戏',
                },
                component: function(resolve) {
                    require(['./views/system/gameMainAU.vue'], resolve)
                }
            },
            {
                path: '/sysGameMain/u',
                name: 'sysGameMainU',
                meta: {
                    father: '系统管理',
                    name: '修改游戏',
                },
                component: function(resolve) {
                    require(['./views/system/gameMainAU.vue'], resolve)
                }
            },
            {
                path: '/itemMain',
                name: 'itemMain',
                meta: {
                    father: '系统管理',
                    name: '物品管理',
                },
                component: function(resolve) {
                    require(['./views/system/itemMain.vue'], resolve)
                }
            },
            {
                path: '/gameMain',
                name: 'gameMain',
                meta: {
                    father: '基本信息',
                    name: '游戏管理',
                },
                component: function(resolve) {
                    require(['./views/basic/userGameMain.vue'], resolve)
                }
            },
            {
                path: '/accountMain',
                name: 'accountMain',
                meta: {
                    father: '基本信息',
                    name: '账号管理',
                },
                component: function(resolve) {
                    require(['./views/basic/accountNumberMain.vue'], resolve)
                }
            },
						
						{
								path: '/opinionMain',
								name: 'opinionMain',
								meta: {
										father: '意见反馈',
										name: '意见反馈',
								},
								component: function(resolve) {
										require(['./views/opinion/opinionMain.vue'], resolve)
								}
						},
						

        ]
    }
]