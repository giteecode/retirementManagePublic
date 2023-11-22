import * as ElIcons from '@element-plus/icons-vue'
import { App } from 'vue'

const useElementPlus = (app: App) => {
  // 注册图标
  for (const [key, component] of Object.entries(ElIcons)) {
    app.component(key, component)
  }
}

export default useElementPlus
