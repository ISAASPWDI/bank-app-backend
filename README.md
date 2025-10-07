# 🏦 Bank Application - Backend

Backend robusto y seguro para aplicación bancaria desarrollado con Spring Boot. Sistema completo de gestión bancaria con autenticación, transacciones, pagos y notificaciones por correo electrónico.

## 📋 Descripción

Este proyecto es el backend de una aplicación bancaria full-stack que proporciona una API RESTful completa para operaciones bancarias, incluyendo gestión de cuentas, transferencias, pagos, historial de transacciones y sistema de autenticación seguro.

## ✨ Características Principales

- 🔐 **Autenticación y Autorización**: Sistema seguro con Spring Security
- 💳 **Gestión de Cuentas**: Creación, consulta y administración de cuentas bancarias
- 💸 **Transferencias**: Sistema de transferencias entre cuentas con validaciones
- 💰 **Pagos**: Procesamiento y registro de pagos
- 📊 **Historial de Transacciones**: Registro completo de todas las operaciones
- 📧 **Notificaciones por Email**: Sistema de mensajería automática
- 🛡️ **Manejo de Excepciones**: Control global de errores
- 🔄 **Interceptores**: Middleware para logging y validación

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java** - Lenguaje de programación principal
- **Spring Boot** - Framework principal
- **Spring Security** - Autenticación y autorización
- **Spring Data JPA** - Persistencia de datos
- **MySQL/PostgreSQL** - Base de datos relacional
- **Maven** - Gestión de dependencias
- **JavaMail** - Envío de correos electrónicos

## 📁 Estructura del Proyecto

```
backend/
└── src/
    └── main/
        └── java/
            └── com/
                └── beko/
                    └── DemoBank_v1/
                        ├── config/                    # Configuraciones de la aplicación
                        │   ├── SecurityConfig.java
                        │   ├── DatabaseConfig.java
                        │   └── MailConfig.java
                        │
                        ├── controllers/               # Controladores REST
                        │   ├── AccountController.java      # Gestión de cuentas
                        │   ├── AppController.java          # Endpoints principales
                        │   ├── AuthController.java         # Autenticación
                        │   ├── IndexController.java        # Página inicial
                        │   ├── RegisterController.java     # Registro de usuarios
                        │   └── TransactController.java     # Transacciones
                        │
                        ├── controller_advisor/        # Manejo global de excepciones
                        │   └── GlobalExceptionHandler.java
                        │
                        ├── exception/                 # Excepciones personalizadas
                        │   ├── AccountNotFoundException.java
                        │   ├── InsufficientFundsException.java
                        │   └── InvalidTransactionException.java
                        │
                        ├── helpers/                   # Clases de ayuda y utilidades
                        │   ├── Token.java
                        │   ├── HTML.java
                        │   └── EmailHelper.java
                        │
                        ├── interceptors/              # Interceptores HTTP
                        │   └── AppInterceptor.java
                        │
                        ├── mailMessenger/             # Sistema de mensajería
                        │   └── MailMessenger.java
                        │
                        ├── models/                    # Modelos de datos
                        │   ├── Account.java               # Cuenta bancaria
                        │   ├── User.java                  # Usuario
                        │   ├── Transact.java              # Transacción
                        │   ├── TransactionHistory.java    # Historial de transacciones
                        │   ├── Payment.java               # Pago
                        │   ├── PaymentHistory.java        # Historial de pagos
                        │   ├── PaymentRequest.java        # Request de pago
                        │   └── TransferRequest.java       # Request de transferencia
                        │
                        ├── repository/                # Repositorios JPA
                        │   ├── UserRepository.java
                        │   ├── AccountRepository.java
                        │   ├── TransactRepository.java
                        │   ├── TransactHistoryRepository.java
                        │   ├── PaymentRepository.java
                        │   └── PaymentHistoryRepository.java
                        │
                        └── DemoBankV1Application.java  # Clase principal
```

## 🚀 Instalación y Configuración

### Prerrequisitos

- Java JDK 11 o superior
- Maven 3.6+
- MySQL 8.0+ o PostgreSQL 12+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/tu-usuario/bank-app.git
cd bank-app/backend
```

2. **Configurar Base de Datos**

Crear una base de datos:
```sql
CREATE DATABASE bank_app;
```

3. **Configurar application.properties**

Crear/editar `src/main/resources/application.properties`:
```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/bank_app
spring.datasource.username=root
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Configuration
jwt.secret=tu_secreto_jwt_super_seguro
jwt.expiration=86400000

# Mail Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=tu_email@gmail.com
spring.mail.password=tu_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Application Configuration
app.name=DemoBank
app.version=1.0.0
```

4. **Instalar Dependencias**
```bash
mvn clean install
```

5. **Ejecutar la Aplicación**
```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## 📚 API Endpoints

### Autenticación

```http
POST   /api/auth/register     # Registrar nuevo usuario
POST   /api/auth/login        # Iniciar sesión
POST   /api/auth/logout       # Cerrar sesión
GET    /api/auth/verify       # Verificar token
```

### Cuentas

```http
GET    /api/accounts          # Obtener todas las cuentas
GET    /api/accounts/{id}     # Obtener cuenta específica
POST   /api/accounts          # Crear nueva cuenta
PUT    /api/accounts/{id}     # Actualizar cuenta
DELETE /api/accounts/{id}     # Eliminar cuenta
GET    /api/accounts/balance/{id}  # Consultar saldo
```

### Transacciones

```http
GET    /api/transactions              # Listar transacciones
POST   /api/transactions/transfer     # Realizar transferencia
POST   /api/transactions/deposit      # Realizar depósito
POST   /api/transactions/withdraw     # Realizar retiro
GET    /api/transactions/history/{accountId}  # Historial de cuenta
```

### Pagos

```http
GET    /api/payments                  # Listar pagos
POST   /api/payments                  # Crear pago
GET    /api/payments/history          # Historial de pagos
PUT    /api/payments/{id}/status      # Actualizar estado de pago
```

### Usuario

```http
GET    /api/user/profile              # Obtener perfil
PUT    /api/user/profile              # Actualizar perfil
PUT    /api/user/password             # Cambiar contraseña
```

## 📦 Modelos de Datos

### User
```java
- id: Long
- firstName: String
- lastName: String
- email: String (único)
- password: String (encriptado)
- phoneNumber: String
- createdAt: LocalDateTime
- updatedAt: LocalDateTime
```

### Account
```java
- id: Long
- accountNumber: String (único)
- accountType: String
- balance: BigDecimal
- user: User
- status: String
- createdAt: LocalDateTime
```

### Transact
```java
- id: Long
- transactionId: String
- accountFrom: Account
- accountTo: Account
- amount: BigDecimal
- transactionType: String
- description: String
- createdAt: LocalDateTime
```

### Payment
```java
- id: Long
- paymentId: String
- account: Account
- beneficiary: String
- amount: BigDecimal
- paymentType: String
- status: String
- createdAt: LocalDateTime
```

## 🔒 Seguridad

- **Autenticación JWT**: Tokens seguros para sesiones de usuario
- **Encriptación BCrypt**: Passwords hasheados
- **Validación de Inputs**: Sanitización de datos de entrada
- **CORS Configurado**: Control de acceso de origen cruzado
- **SQL Injection Protection**: Uso de JPA y prepared statements
- **Rate Limiting**: Control de peticiones por usuario
- **Roles y Permisos**: Sistema de autorización basado en roles

## 🧪 Testing

```bash
# Ejecutar tests unitarios
mvn test

# Ejecutar tests de integración
mvn verify

# Generar reporte de cobertura
mvn jacoco:report
```

## 📝 Convenciones de Código

- Seguir las convenciones de Java (PascalCase para clases, camelCase para métodos)
- Documentación JavaDoc para clases y métodos públicos
- Nombres descriptivos y significativos
- Principios SOLID
- Clean Code

## 🚀 Despliegue

### Docker

```bash
# Construir imagen
docker build -t bank-app-backend .

# Ejecutar contenedor
docker run -p 8080:8080 bank-app-backend
```

### Docker Compose

```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - db
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/bank_app
  
  db:
    image: mysql:8.0
    environment:
      - MYSQL_ROOT_PASSWORD=rootpass
      - MYSQL_DATABASE=bank_app
    volumes:
      - db-data:/var/lib/mysql

volumes:
  db-data:
```

## 📊 Base de Datos

### Diagrama ER

```
User (1) ──── (N) Account
Account (1) ──── (N) Transact
Account (1) ──── (N) Payment
Transact (1) ──── (1) TransactionHistory
Payment (1) ──── (1) PaymentHistory
```

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama feature (`git checkout -b feature/NuevaCaracteristica`)
3. Commit tus cambios (`git commit -m 'Add: nueva característica'`)
4. Push a la rama (`git push origin feature/NuevaCaracteristica`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 👨‍💻 Autor

**Beko Development Team**

## 📧 Contacto

Para preguntas o sugerencias:
- Email: support@beko-bank.com
- Issues: [GitHub Issues](https://github.com/tu-usuario/bank-app/issues)

## 🙏 Agradecimientos

- Spring Boot Community
- Java Developer Community
- Todos los contribuidores del proyecto

## 📚 Recursos Adicionales

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Reference](https://spring.io/projects/spring-security)
- [JPA Documentation](https://spring.io/projects/spring-data-jpa)

---

💼 Desarrollado con ☕ y ❤️ para mejorar las operaciones bancarias

**Version:** 1.0.0  
**Last Updated:** Septiembre 2025
