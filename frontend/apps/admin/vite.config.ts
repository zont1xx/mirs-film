import react from '@vitejs/plugin-react'
import { defineConfig } from 'vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  // web uses the default 5173; admin runs beside it
  server: { port: 5174 },
})
