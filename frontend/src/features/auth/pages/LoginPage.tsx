import { useForm } from 'react-hook-form'
import { loginSchema, TLoginSchema } from '../schemas/loginSchema'
import { zodResolver } from '@hookform/resolvers/zod'
import useLoginMutation from '../hooks/useLoginMutation'
import { Link } from 'react-router-dom'

const LoginPage = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<TLoginSchema>({
    resolver: zodResolver(loginSchema),
  })

  const { mutate, isPending, isError } = useLoginMutation()

  const onSubmit = (data: TLoginSchema) => {
    mutate(data)
  }

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <h2>Login Page</h2>
      <div>
        <label htmlFor='username'>Username</label>
        <input type='text' id='username' {...register('username')} />
        <small>{errors.username && errors.username.message}</small>
      </div>
      <div>
        <label htmlFor='password'>Password</label>
        <input type='password' id='password' {...register('password')} />
        <small>{errors.password && errors.password.message}</small>
      </div>
      <button type='submit'>Submit</button>
      {isPending && <p>Loading...</p>}
      {isError && <p>Something went wrong</p>}
      <Link to='/register'>Don't have an account? Register here</Link>
    </form>
  )
}

export default LoginPage

