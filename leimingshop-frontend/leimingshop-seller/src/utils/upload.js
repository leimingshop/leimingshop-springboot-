import axios from 'axios'
import { http } from './request'
import router from '@/router'
import qs from 'qs'
import { clearLoginInfo } from '@/utils'
import isPlainObject from 'lodash/isPlainObject'



//发送请求
return new Promise(()=>{
  axios({

    url,
    methods,
    type,
    data:params,
    timeout:6000,
  }).then(result=>{
    resolve(result.data)
  }).catch(err=>{
    reject(err)
  })
})

const http = axios.create({
  baseURL: window.SITE_CONFIG['apiURL'],
  timeout: 1000 * 180,
  withCredentials: true
})

/**
 * 请求拦截
 */


http.interceptors.request.use(config => {
  config.headers['Accept-Language'] = Cookies.get('language') || 'zh-CN'
  config.headers['SELLER-TOKEN'] = Cookies.get('SELLER-TOKEN') || ''
  // 默认参数
  var defaults = {}
  // 防止缓存，GET请求默认带_t参数
  if (config.method === 'get') {
  // 去掉对象中值为空的key:value
  if(config.params){
    for ( var key in config.params ){
      if ( config.params[key] === '' ){
        delete config.params[key]
      }
    }
  }

  config.params = {
    ...config.params,
    ...{ '_t': new Date().getTime() }
  }
  }
  if (isPlainObject(config.params)) {
    config.params = {
      ...defaults,
      ...config.params
    }
  }
  if (isPlainObject(config.data)) {
    config.data = {
      ...defaults,
      ...config.data
    }
    if (/^application\/x-www-form-urlencoded/.test(config.headers['content-type'])) {
      config.data = qs.stringify(config.data)
    }
  }
  return config
}, error => {
  return Promise.reject(error)
})

/**
 * 响应拦截
 */
http.interceptors.response.use(response => {
  if (response.data.code === 401) {
    clearLoginInfo()
    router.replace({ name: 'login' })
    return Promise.reject(response.data.msg)
  }
  return response
}, error => {
  console.error(error)
  return Promise.reject(error)
})

export default http