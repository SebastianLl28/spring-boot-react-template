import { useForm } from 'react-hook-form'
import { registerSchema, TRegisterSchema } from '../schemas/registerSchema'
import { zodResolver } from '@hookform/resolvers/zod'
import useRegisterMutation from '../hooks/useRegisterMutation'
import { Link } from 'react-router-dom'

const RegisterPage = () => {
  const { register, handleSubmit } = useForm<TRegisterSchema>({
    resolver: zodResolver(registerSchema),
  })

  const { mutate } = useRegisterMutation()

  const onSubmit = (data: TRegisterSchema) => {
    mutate(data)
  }

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <h2>Register Page</h2>
      <div>
        <label htmlFor='username'>Username</label>
        <input type='email' id='username' {...register('username')} />
      </div>
      <div>
        <label htmlFor='password'>Password</label>
        <input type='password' id='password' {...register('password')} />
      </div>
      <div>
        <label htmlFor='confirmPassword'>Confirm Password</label>
        <input type='password' id='confirmPassword' {...register('confirmPassword')} />
      </div>
      <button type='submit'>Submit</button>
      <Link to='/'>Already have an account? Login here</Link>
    </form>
  )
}

export default RegisterPage

