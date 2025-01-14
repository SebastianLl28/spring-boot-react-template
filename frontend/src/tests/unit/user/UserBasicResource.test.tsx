import UserBasicResource from '@/features/home/page/UserBasicResource'
import { userResponse } from '@/tests/fixture/user.fixture'
import { render } from '@/tests/utils/render'
import { screen } from '@testing-library/react'
import { test, expect, beforeEach, describe } from 'vitest'

describe('UserBasicResource', () => {
  let container: HTMLElement
  const { id, username } = userResponse

  beforeEach(() => {
    const renderResult = render(<UserBasicResource name={username} id={id} />)
    container = renderResult.container
  })

  test('should render correctly', () => {
    expect(screen).toBeTruthy()
  })

  test('should render name', () => {
    const text = screen.getByText(`Hola ${username}`)
    expect(text).toBeTruthy()
  })

  test('should render id', () => {
    const text = screen.getByText(`id: ${id}`)
    expect(text).toBeTruthy()
  })

  test('should match snapshot', () => {
    expect(container).toMatchSnapshot()
  })
})

