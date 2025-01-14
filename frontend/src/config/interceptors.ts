import { apiClient, LOGIN_ENDPOINT } from './endpoints'

export const bearerInterceptor = () =>
  apiClient.interceptors.request.use(
    (config) => {
      const whiteList = [LOGIN_ENDPOINT]
      const url = config?.url ?? ''
      if (url && !whiteList.includes(url)) {
        const token = localStorage.getItem('token')
        if (token) {
          config.headers.Authorization = `Bearer ${token}`
        }
      }
      return config
    },
    (error) => Promise.reject(error)
  )

