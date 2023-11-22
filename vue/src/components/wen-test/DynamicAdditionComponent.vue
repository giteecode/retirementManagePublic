<!--在 Element Plus 中动态添加组件可以使用 el-component 组件，该组件可以将任何一个 Vue 组件动态添加到页面中。下面是一个示例代码：-->
<!--在上面的示例中，我们使用了 el-component 组件来动态添加组件，通过 :is 属性指定要添加的组件类型，通过 :props 属性传递组件所需的 props 数据。-->
<!--在 addNew 方法中，我们向 componentList 数组中添加一个组件对象，其中 type 属性指定要添加的组件类型，props 属性指定要传递给组件的数据。-->
<!--最后，在模板中使用 v-for 指令遍历 componentList 数组，并通过 :is 和 :props 属性将组件动态添加到页面中。-->
<!--需要注意的是，在使用 el-component 组件动态添加组件时，需要将要添加的组件在 components 中先进行注册。-->
<template>
  <div>
    <el-button @click="addNew">添加组件</el-button>
    <div v-for="(item, index) in componentList" :key="index">
      <el-component :is="item.type" :props="item.props"></el-component>
    </div>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  data() {
    return {
      componentList: []
    }
  },
  methods: {
    addNew() {
      this.componentList.push({
        type: HelloWorld,
        props: {
          msg: 'Hello World!'
        }
      })
    }
  },
  components: {
    HelloWorld
  }
}
</script>
