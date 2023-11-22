import SvgIcon from '@/components/SvgIcon/index.vue'
import { App } from 'vue'

// 获取上下文 require.context（检索的目录，是否检索子文件夹，正则表达式）
// 返回值是一个函数（传入路径可以导入文件）
// 通过静态方法keys可以检索所有文件路径
// 通过.prototype可以查看所有静态方法

const svgRequired = require.context('./svg', false, /\.svg$/)

svgRequired.keys().forEach(item => svgRequired(item))

export default (app: App) => {
  app.component('svg-icon', SvgIcon)
}
