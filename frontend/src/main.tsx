import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { QueryClientProvider } from '@tanstack/react-query'
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'
import { queryClient } from './config/queryClient.ts'
import App from './App.tsx'
import './index.css'
import { bearerInterceptor } from './config/interceptors.ts'

bearerInterceptor()

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <QueryClientProvider client={queryClient}>
      <ReactQueryDevtools />
      <App />
    </QueryClientProvider>
  </StrictMode>
)

