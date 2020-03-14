import Cookies from 'js-cookie'
import axios from '@/util/ajax'
// import axios from 'axios'
import Auth from '@/util/auth'
import global_ from '@/global';

const state = {
    token: '',
    navList: []
}

const mutations = {
    setNavList: (state, data) => {        
        state.navList = data
        // console.log(state.navList)
    },
    setToken: (state, data) => {
        if(data){
            Auth.setToken(data)
            Auth.setLoginStatus()
        } else {
            Auth.removeToken()
            Auth.removeLoginStatus()
        }
        state.token = data
    }
}

const actions = {
    // 邮箱登录
    loginByEmail({ commit }, userInfo) {
        return new Promise((resolve) => {
            axios({
                // url: '/login',
                url: global_.REQUEST_HOST+'/login/byAccount',
                method: 'post',
                data: {
                    ...userInfo
                }
            }).then(res => {
                if(res.code == 2000){
                    localStorage.setItem('loginUser',JSON.stringify(res.data));
                    commit('setToken', res.data.token)
                    commit('user/setName', res.data.userName, { root: true })
                } else {
                    commit('setToken', '')
                }
                resolve(res)
            })
        });
    },

    // 登出
    logout({commit}) {
        return new Promise((resolve) => {
            var storage = window.localStorage;
            storage.clear()
            commit('setToken', '')
            commit('user/setName', '', { root: true })
            commit('tagNav/removeTagNav', '', {root: true})
            resolve()
        })
    },

    // 重新登录
    relogin({dispatch, commit, state}){
        return new Promise((resolve) => {
            // 根据Token进行重新登录
            let token = Cookies.get('token')
            if(!token){
                // 这里需要根据实际情况确认token刷新协议
                // 若直接使用时因state.token不存在，将无法获得新的token
                dispatch("getNewToken").then(() => {
                    commit('setToken', state.token)
                })
            } else {
                commit('setToken', token)
            }
            commit('user/setName', decodeURIComponent(Cookies.get('userName')), { root: true })
            resolve()
        })
    },

    // 获取新Token
    getNewToken({commit, state}){
        return new Promise((resolve) => {
            axios({
                url: '/getToken',
                method: 'get',
                param: {
                    token: state.token
                }
            }).then((res) =>{
                commit("setToken", res.token)
                resolve()
            })
        })
    },

    // 获取该用户的菜单列表
    getNavList({commit}){
        return new Promise((resolve) =>{
            let loginUser = JSON.parse(localStorage.getItem('loginUser'))
            axios({
                url:  global_.REQUEST_HOST + 'role/menuAction/'+loginUser.roleId, //'/user/navlist',
                methods: 'get'
                // data: {}
            }).then((res) => {
                commit("setNavList", res.data)
                resolve(res)
            })
        })
    },

    // 将菜单列表扁平化形成权限列表
    getPermissionList({state}){
        return new Promise((resolve) =>{
            let permissionList = []
            // 将菜单数据扁平化为一级
            function flatNavList(arr){
                for(let v of arr){
                    if(v.child && v.child.length){
                        flatNavList(v.child)
                    } else{
                        permissionList.push(v)
                    }
                }
            }
            flatNavList(state.navList)
            resolve(permissionList)
        })
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}