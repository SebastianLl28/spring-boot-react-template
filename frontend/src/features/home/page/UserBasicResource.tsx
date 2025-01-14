interface Props {
  name: string
  id: number
}

const UserBasicResource = ({ name, id }: Props) => {
  return (
    <div>
      <h3>Hola {name}</h3>
      <p>id: {id}</p>
    </div>
  )
}

export default UserBasicResource

