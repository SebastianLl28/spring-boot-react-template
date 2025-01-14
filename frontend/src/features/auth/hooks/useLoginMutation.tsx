import { apiClient, LOGIN_ENDPOINT } from '@/config/endpoints'
import { LOGIN_MUTATION } from '@/config/keys'
import { useMutation } from '@tanstack/react-query'
import { useNavigate } from 'react-router-dom'

interface ILoginRequest {
  username: string
  password: string
}

interface ILoginResponse {
  jwt: string
}

const postLogin = async ({
  username,
  password,
}: ILoginRequest): Promise<ILoginResponse> => {
  console.log(LOGIN_ENDPOINT)
  return await apiClient
    .post<ILoginResponse>(LOGIN_ENDPOINT, { username, password })
    .then((res) => res.data)
}

const useLoginMutation = () => {
  const navigate = useNavigate()

  return useMutation({
    mutationKey: LOGIN_MUTATION,
    mutationFn: async (data: ILoginRequest) => postLogin(data),
    onSuccess: (data) => {
      localStorage.setItem('token', data.jwt)
      navigate('/home', { replace: true })
    },
    onError: (error) => {
      console.log(error)
    },
  })
}

export default useLoginMutation

