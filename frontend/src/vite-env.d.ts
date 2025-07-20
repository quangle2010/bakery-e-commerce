/// <reference types="vite/client" />

declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  const component: DefineComponent<{}, {}, any>
  export default component
}
declare module 'bootstrap';


// Đảm bảo TypeScript nhận diện đối tượng window
interface Window {
  innerWidth: number;
  innerHeight: number;
  addEventListener: typeof addEventListener;
  removeEventListener: typeof removeEventListener;
}
