import SvgIcon from '@/components/SvgIcon/index.vue'
import { h, defineComponent } from 'vue'

export function useRenderIcon(iconName: string, attrs?: any) {
  return defineComponent({
    name: 'SvgIcon',
    render() {
      return h(SvgIcon, {
        icon: iconName,
        ...attrs
      })
    }
  })
}
