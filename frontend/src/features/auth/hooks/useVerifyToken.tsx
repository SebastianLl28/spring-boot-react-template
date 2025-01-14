import { apiClient, VERIFY_TOKEN_ENDPOINT } from '@/config/endpoints'
import { VERIFY_TOKEN_QUERY } from '@/config/keys'
import { useQuery } from '@tanstack/react-query'

const verifyToken = async () =>
  await apiClient.get(VERIFY_TOKEN_ENDPOINT).then((res) => res.data)

export const useVerifyToken = () =>
  useQuery({
    queryKey: VERIFY_TOKEN_QUERY,
    queryFn: verifyToken,
    retry: 0,
  })

