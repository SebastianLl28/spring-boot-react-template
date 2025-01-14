import { apiClient, REGISTER_ENDPOINT } from '@/config/endpoints'
import { REGISTER_MUTATION } from '@/config/keys'
import { useMutation } from '@tanstack/react-query'
import { useNavigate } from 'react-router-dom'

interface IRegisterRequest {
  username: string
  password: string
}

interface IRegisterResponse {
  jwt: string
}

export const postRegister = async ({
  username,
  password,
}: IRegisterRequest): Promise<IRegisterResponse> =>
  apiClient
    .post(REGISTER_ENDPOINT, {
      username,
      password,
    })
    .then((res) => res.data)

const useRegisterMutation = () => {
  const navigate = useNavigate()

  return useMutation({
    mutationKey: REGISTER_MUTATION,
    mutationFn: postRegister,
    onSuccess: (data) => {
      localStorage.setItem('token', data.jwt)
      navigate('/home')
    },
    onError: (error) => {
      console.log(error)
    },
  })
}

export default useRegisterMutation

