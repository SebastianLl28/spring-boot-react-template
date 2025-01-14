# React & Spring Boot Template

This is a basic template for building applications using React for the frontend and Spring Boot for the backend. It includes configurations and tools to facilitate the development and integration of both layers.

## Technologies

### Frontend (React)
- **React**: Library for building user interfaces.
- **TypeScript**: For static type checking.
- **Vite**: Fast development server and build tool.
- **React Hook Form**: For managing form state and validation.
- **Zod**: Schema validation library.
- **React Query**: For server state management.
- **Zustand**: Simple state management library.
- **Cypress**: For end-to-end testing.
- **Testing Library**: For unit and integration testing.

### Backend (Spring Boot)
- **Spring Boot**: Backend framework for building REST APIs.
- **Spring Security**: For authentication and authorization.
- **JWT**: For secure, token-based access.
- **Flyway**: For database migrations.
- **JPA (Hibernate)**: For database interaction.
- **MySQL**: Database management system.

---

## Installation

### Clone the Repository
```bash
git clone https://github.com/SebastianLl28/spring-boot-react-template.git
cd spring-boot-react-template
```

---

### Frontend Setup
1. Navigate to the `frontend` directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   pnpm install
   ```

3. Start the development server:
   ```bash
   pnpm dev
   ```

4. Run tests:
   ```bash
   pnpm test
   ```

---

### Backend Setup
1. Navigate to the `backend` directory:
   ```bash
   cd backend
   ```

2. Create a `.env` file in the root with the following environment variables:
   ```env
   SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/your_database
   SPRING_DATASOURCE_USERNAME=your_username
   SPRING_DATASOURCE_PASSWORD=your_password
   JWT_SECRET=your_jwt_secret
   ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Run database migrations with Flyway:
   ```bash
   ./mvnw flyway:migrate
   ```

5. Run tests:
   ```bash
   ./mvnw test
   ```

---

## Scripts

### Frontend
- **`pnpm dev`**: Starts the development server.
- **`pnpm build`**: Builds the frontend for production.
- **`pnpm lint`**: Runs the linter.
- **`pnpm test`**: Runs unit and integration tests.
- **`pnpm cy:open`**: Opens Cypress for end-to-end testing.

### Backend
- **`./mvnw spring-boot:run`**: Starts the Spring Boot application.
- **`./mvnw flyway:migrate`**: Applies database migrations.
- **`./mvnw test`**: Runs unit tests.

---

## License

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.
