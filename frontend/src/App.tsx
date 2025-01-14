import { BrowserRouter, Route, Routes } from 'react-router-dom'
import LoginPage from './features/auth/pages/LoginPage'
import { HOME_PATH, LOGIN_PATH, REGISTER_PATH } from './config/paths'
import HomePage from './features/home/page/HomePage'
import PrivateLayout from './components/layouts/PrivateLayout'
import RegisterPage from './features/auth/pages/RegisterPage'

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={LOGIN_PATH} index element={<LoginPage />} />
        <Route path={REGISTER_PATH} element={<RegisterPage />} />
        <Route element={<PrivateLayout />}>
          <Route path={HOME_PATH} element={<HomePage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App

