import z from 'zod'

export const loginSchema = z.object({
  username: z
    .string()
    .min(1, 'Username is required - this is a custom error message')
    .email('Invalid email (this is a custom error message)'),
  password: z.string().min(6),
})

export type TLoginSchema = z.infer<typeof loginSchema>

