<script lang="ts">
import {defineComponent} from 'vue'
import axios from "axios";

export default defineComponent({
  name: "LoginView"
})
</script>

<script lang="ts" setup>
import {reactive} from 'vue'

// do not use same name with ref
const form = reactive({
  account: 'admin',
  password: '123',
  // 这里的value前面加冒号，明确了这里的值是number后，type这里的0才可以不加双引号，
  // 如果加了双引号，就因为一个是字符串，一个是数字，而类型不一致，没法关联
  // el-radio :value="0"
  type: 0,
  rem: false,
})

// 执行登录的函数
const onSubmit = () => {
  console.log('submit!')
  // 发起登录请求
  axios.post('http://localhost:8080/admin/login',form)
      .then((res)=>{
        console.log(res)
      }) //请求成功的处理
      .catch((res)=>{
        console.log(res)
      })
}
</script>

<template>
  <el-row class="bg">
    <el-col :span="9" :offset="7">
      <el-card class="box-card">
        <template #header>
          <h1 class="title"> 欢迎使用使用宿舍管理系统</h1>
        </template>
        <el-form :model="form" label-width="auto" style="max-width: 600px">
          <el-form-item label="">
            <el-input v-model.trim="form.account" placeholder="账号"/>
          </el-form-item>
          <el-form-item label="">
            <el-input v-model.trim="form.password" placeholder="密码"/>
          </el-form-item>
          <el-form-item label="">
<!--            加上.number表示后面的值是数字类型-->
            <el-radio-group v-model.number="form.type">
<!--              不能写label，要写value-->
<!--              这里的0,1,2是根据数据库里面的约定设置的-->
              <el-radio :value="0">系统管理员</el-radio>
              <el-radio :value="1">宿舍管理员</el-radio>
              <el-radio :value="2">学生</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="">
            <el-checkbox label="记住密码" v-model="form.rem"/>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" style="width: 100%;padding: 20px" @click="onSubmit">登录</el-button>
          </el-form-item>
          <el-form-item style="display: flex;">
            <el-button style="flex: 1;">注册</el-button>
            <el-button style="flex: 1;">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-col>
  </el-row>

</template>

<style scoped>
.title {
  text-align: center;
}

/*样式穿透: vue2使用::v-deep 开头，vue3中使用:deep*/
:deep .el-form-item__content {
  justify-content: center;
}

.bg {
  background: url("../assets/蓝色背景图片.jpg") no-repeat;
  background-size: 100% 100%;
  height: 100%;
  padding-top: 150px;
}
</style>