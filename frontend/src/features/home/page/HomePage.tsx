import { useVerifyToken } from '@/features/auth/hooks/useVerifyToken'
import { Navigate, useNavigate } from 'react-router-dom'
import User from './UserBasicResource'
import { useEffect } from 'react'
import { useUser } from '@/store/useUser'

const HomePage = () => {
  const { isLoading, isError, isSuccess, data } = useVerifyToken()

  const navigate = useNavigate()

  const login = useUser((state) => state.login)
  const logout = useUser((state) => state.logout)
  const user = useUser((state) => state.user)

  useEffect(() => {
    if (isSuccess) {
      login(data)
    }
  }, [data, isSuccess, login])

  const onLogout = () => {
    localStorage.removeItem('token')
    navigate('/', { replace: true })
    logout()
  }

  if (isLoading) return <p>loading...</p>
  else if (!isLoading && isError) return <Navigate to='/' replace />
  else
    return (
      <div>
        <p>this is protected route</p>

        <User name={user.username} id={user.id} />

        <button onClick={onLogout}>logout</button>
      </div>
    )
}

export default HomePage

