import HomePage from '@/features/home/page/HomePage'
import { render } from '@/tests/utils/render'
import { describe, expect, test } from 'vitest'

test('HomePage', () => {
  describe('should render correctly', () => {
    render(<HomePage />)
    expect(screen).toBeTruthy()
  })
})

