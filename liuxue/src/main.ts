import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'


const  app = createApp(App)

app.use(router)
app.use(ElementPlus)
app.mount('#app')

window.addEventListener('error', e => {
    if (e.message.includes('ResizeObserver')) {
        e.stopImmediatePropagation() // 阻止错误继续传播
    }
})