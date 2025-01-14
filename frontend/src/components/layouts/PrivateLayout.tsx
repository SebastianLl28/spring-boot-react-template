import { Outlet } from 'react-router-dom'

const PrivateLayout = () => {
  


  return (
    <div>
      <p>private layout</p>
      <Outlet />
    </div>
  )
}

export default PrivateLayout

