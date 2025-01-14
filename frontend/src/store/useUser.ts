import { type User } from '@/models/User'
import { create } from 'zustand'

interface UserStore {
  user: User
  setUser: (user: User) => void
  isLogged: boolean
  setIsLogged: (isLogged: boolean) => void
  logout: () => void
  login: (user: User) => void
}

export const useUser = create<UserStore>((set) => ({
  user: { id: 0, username: '' },
  setUser: (user: User) => {
    return set({ user })
  },
  isLogged: false,
  setIsLogged: (isLogged: boolean) => {
    return set({ isLogged })
  },
  logout: () => {
    set({ user: { id: 0, username: '' }, isLogged: false })
  },
  login: (user: User) => {
    set({ user, isLogged: true })
  },
}))

