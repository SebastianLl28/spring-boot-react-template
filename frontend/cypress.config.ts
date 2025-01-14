import { defineConfig } from 'cypress'

export default defineConfig({
  e2e: {
    baseUrl: "http://localhost:5173",
    env: {
      VITE_CLEAN_DATABASE_ENDPOINT: 'http://localhost:8080/public/api/v1/auth/actions/clean-database'
    }
  }
})