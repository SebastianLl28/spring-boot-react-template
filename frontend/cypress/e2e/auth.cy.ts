describe('auth', () => {
  beforeEach(() => {
    cy.clearLocalStorage('token')
  })

  describe('register', () => {
    it('registers', () => {
      cy.visit('/register')
      cy.get('input[name="username"]').type('betsaly@gmail.com')
      cy.get('input[name="password"]').type('123456')
      cy.get('input[name="confirmPassword"]').type('123456')
      cy.get('button[type="submit"]').click()
      cy.contains('logout').should('exist')
    })
  
    it('registers with wrong credentials', () => {
      cy.visit('/register')
      cy.get('input[name="username"]').type('invalid-email@gmail.com')
      cy.get('input[name="password"]').type('123456')
      cy.get('input[name="confirmPassword"]').type('123456')
      cy.get('button[type="submit"]').click()
      cy.contains('Register Page').should('exist')
    })
  })

  describe('login', () => {
    it('logs in', () => {
      cy.get('input[name="username"]').type('admin@admin.com')
      cy.get('input[name="password"]').type('123456')
      cy.get('button[type="submit"]').click()
      cy.contains('logout').should('exist')
    })
  
    it('logs in with wrong credentials', () => {
      cy.get('input[name="username"]').type('test@gmail.com')
      cy.get('input[name="password"]').type('123456')
      cy.get('button[type="submit"]').click()
      cy.contains("Don't have an account? Register here").should('exist')
    })
  })
})