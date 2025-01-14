import z from 'zod'

export const registerSchema = z
  .object({
    username: z.string().email(),
    password: z.string().min(6),
    confirmPassword: z.string().min(6),
  })
  .refine((data) => data.password === data.confirmPassword, {
    message: 'Password and Confirm Password must be the same',
  })

export type TRegisterSchema = z.infer<typeof registerSchema>

