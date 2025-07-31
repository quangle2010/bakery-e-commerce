import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      '/api/viettel': {
        target: 'https://partner.viettelpost.vn',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api\/viettel/, ''),
      }
    }
  }
})
