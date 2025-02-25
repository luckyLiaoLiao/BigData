import axios from 'axios';

// 创建axios自定义实例
const instance = axios.create({
    baseURL:'http://localhost:8080',
    timeout:1000*5,
    // headers:{'X-Custom-Header':'foobar'}
})

// 添加请求拦截器
instance.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    console.log("请求拦截器1")
    // 获取token信息
    let token = sessionStorage.getItem("token");
    if (token) {
        // 添加token信息
        config.headers.token = token;
    }
    return config;
}, function (error) {
    // 对请求错误做些什么
    console.log("请求拦截器1")
    return Promise.reject(error);
});

// 添加响应拦截器
instance.interceptors.response.use(function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    console.log("响应拦截器1")
    return response;
}, function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    console.log("响应拦截器2")
    return Promise.reject(error);
});

// 导出axios对象
export default instance;