# Alfashop E-Commerce

## 📖 Descripción

Alfashop es una plataforma en línea que permite a los usuarios explorar un catálogo de productos con descripciones, imágenes y precios, agregar artículos a un carrito de compras, y realizar pagos de manera segura mediante diversas opciones, como tarjetas de crédito. Además, ofrece funcionalidades como registro de usuarios y historial de compras.

## 🎯 Propósito

El propósito de Alfashop es facilitar la compra y venta de productos o servicios de manera rápida, accesible y segura, brindando a los usuarios una experiencia eficiente que les permita adquirir lo que necesitan desde cualquier lugar, mientras las empresas amplían su alcance y optimizan sus procesos de ventas.

## 📋 Características Principales

- **Catálogo de Productos**: Listado organizado con imágenes, descripciones detalladas, precios.
- **Carrito de Compras**: Herramienta para seleccionar, gestionar y calcular el costo total de los productos antes de la compra.
- **Pasarela de Pago**: Integración de métodos de pago seguros.
- **Gestión de Usuarios**: Opciones de registro, inicio de sesión y acceso al historial de compras.
- **Seguridad**: Protección de datos personales.

## 🛠️ Tecnologías utilizadas

- Spring Security con JWT
- MySQL
- JPA como ORM
- Pruebas Unitarias Mockito
  - Reporte de Pruebas Unitarias con InteliJ
- Mocha y Cypress
  - Reporte de Pruebas con Mochawesome
- SonarQube
- Jenkins pipeline

## 📜 Estructura del proyecto

```
Alfashop/
├───.scannerwork
│   ├───.sonar_lock
│   └───report-task.txt
├───back
│   ├───.mvn
│   │   └───wrapper
│   │       └───maven-wrapper.properties
│   ├───src
│   │   ├──main
│   │   │  ├──java
│   │   │  │     └──com
│   │   │  │        └──pe
│   │   │  │    	└──unmsm
│   │   │  │    	    └──fisi
│   │   │  │    		└──alfashop
│   │   │  │    		    ├──controller
│   │   │  │    		    │   ├──PagoController.java
│   │   │  │    		    │   ├──PedidoController.java
│   │   │  │    		    │   ├──ProductoController.java
│   │   │  │    		    │   ├──ResenaController.java
│   │   │  │    		    │   └──UsuarioController.java
│   │   │  │    		    ├──infrastructure
│   │   │  │    		    │   ├──DTO
│   │   │  │    		    │   │   ├──PagoRequest.java
│   │   │  │    		    │   │   ├──PagoResponse.java
│   │   │  │    		    │   │   ├──PedidoRequest.java
│   │   │  │    		    │   │   ├──PedidoResponse.java
│   │   │  │    		    │   │   ├──ProductCatResponse.java
│   │   │  │    		    │   │   ├──ProductoCompraRequest.java
│   │   │  │    		    │   │   ├──ProductReponse.java
│   │   │  │    		    │   │   ├──ResenaRequest.java
│   │   │  │    		    │   │   └──ResenaResponse.java
│   │   │  │    		    │   ├──mapper
│   │   │  │    		    │   │   ├──PagoMapper.java
│   │   │  │    		    │   │   ├──PedidoMapper.java
│   │   │  │    		    │   │   ├──ProductoMapper.java
│   │   │  │    		    │   │   └──ResenaMapper.java
│   │   │  │    		    │   └──repository
│   │   │  │    		    │       ├──CategoriaRepository.java
│   │   │  │    		    │       ├──PagoRepository.java
│   │   │  │    		    │       ├──PedidoProductoRepository.java
│   │   │  │    		    │       ├──PedidoRepository.java
│   │   │  │    		    │       ├──ProductoRepository.java
│   │   │  │    		    │       ├──ResenaRepository.java
│   │   │  │    		    │       └──UsuarioRepository.java
│   │   │  │    		    ├──model
│   │   │  │    		    │   ├──Categoria.java
│   │   │  │    		    │   ├──MetodoPago.java
│   │   │  │    		    │   ├──Pago.java
│   │   │  │    		    │   ├──Pedido.java
│   │   │  │    		    │   ├──PedidoProducto.java
│   │   │  │    		    │   ├──Producto.java
│   │   │  │    		    │   ├──Resena.java
│   │   │  │    		    │   ├──Rol.java
│   │   │  │    		    │   └──Usuario.java
│   │   │  │    		    ├──security
│   │   │  │    		    │   ├──config
│   │   │  │    		    │   │   └──SecurityConfig.java
│   │   │  │    		    │   ├──controller
│   │   │  │    		    │   │   └──AuthController.java
│   │   │  │    		    │   ├──DTO
│   │   │  │    		    │   │   ├──LoginRequest.java
│   │   │  │    		    │   │   ├──RegistroRequest.java
│   │   │  │    		    │   │   └──TokenResponse.java
│   │   │  │    		    │   ├──exception
│   │   │  │    		    │   │   └──UsuarioRegistradoException.java
│   │   │  │    		    │   ├──handler
│   │   │  │    		    │   │   ├──ErrorResponse.java
│   │   │  │    		    │   │   └──GlobalExceptionHandler.java
│   │   │  │    		    │   ├──jwt
│   │   │  │    		    │   │   ├──JwtFilter.java
│   │   │  │    		    │   │   └──JwtProvider.java
│   │   │  │    		    │   ├──repository
│   │   │  │    		    │   │   └──RolRepository.java
│   │   │  │    		    │   ├──service
│   │   │  │    		    │   │   └──UserDetailServicelmpl.java
│   │   │  │    		    │   └──RolEnum.java
│   │   │  │    		    ├──service
│   │   │  │    		    │   ├──PagoService.java
│   │   │  │    		    │   ├──PedidoService.java
│   │   │  │    		    │   ├──ProductoService.java
│   │   │  │    		    │   ├──ResenaService.java
│   │   │  │    		    │   └──UsuarioService.java
│   │   │  │    		    └──AlfashopApplication.java
│   │   │  │
│   │   │  └──resources
│   │   │        ├──alfashop v1.sql
│   │   │        ├──application.properties
│   │   │        └──backup.sql
│   │   └──test
│   │      └──java
│   │            └──com
│   │               └──pe
│   │           	└──unmsm
│   │           	    └──fisi
│   │           		└──alfashop
│   │   			    ├──service
│   │   			    │   ├──PagoServiceTest.java
│   │   			    │   ├──PedidoServiceTest.java
│   │   			    │   ├──ProductosServiceTest.java
│   │   			    │   ├──ResenaServiceTest.java
│   │   			    │   └──UsuarioServiceTest.java
│   │   			    └──AlfashopApplicationTest.java
│   ├──.gitignore
│   ├──docker-compose.yml
│   ├──Dockerfile
│   ├──mvnw
│   ├──mvnw.cmd
│   └──pom.xml
│
├───front
│   ├───cypress
│   │    ├──e2e
│   │    │    ├──addCartTest.cy.js
│   │    │    ├──buyTest.cy.js
│   │    │    ├──checkPaymentInfo.cy.js
│   │    │    ├──loginTest.cy.js
│   │    │    ├──manageCart.cy.js
│   │    │    └──registerTest.cy.js
│   │    ├──fictures
│   │    │    └──example.json
│   │    └──support
│   │         ├──commands.js
│   │         ├──commands.ts
│   │         ├──components.ts
│   │         ├──component-index.html
│   │         └──e2e.js
│   ├───public
│   │    ├──_redirects
│   │    └──vite.svg
│   ├───src
│   │    ├──assets
│   │    │    ├──images
│   │    │    │   └──resct.svg
│   │    │    └──styles
│   │    │        ├──App.css
│   │    │        ├──AppBar.css
│   │    │        ├──CustomerInfo.css
│   │    │        ├──Dashboard.css
│   │    │        ├──index.css
│   │    │        ├──PaymentInfo.css
│   │    │        └──Sidebar.css
│   │    ├──components
│   │    │    ├──Header.tsx
│   │    │    ├──Sidebar.tsx
│   │    │    └──SiderData.tsx
│   │    ├──config
│   │    │    ├──base.js
│   │    │    └──config.tsx
│   │    ├──models
│   │    │    ├──Cart.tsx
│   │    │    ├──Cartitem.tsx
│   │    │    ├──Order.tsx
│   │    │    ├──OrderLine.tsx
│   │    │    ├──OrderRequest.tsx
│   │    │    ├──PaymentHistory.tsx
│   │    │    ├──Product.tsx
│   │    │    ├──Review.tsx
│   │    │    └──User.tsx
│   │    ├──pages
│   │    │    ├──CartPage.tsx
│   │    │    ├──Categories.tsx
│   │    │    ├──CustomersInfo.tsx
│   │    │    ├──Dashboards.tsx
│   │    │    ├──Login.tsx
│   │    │    ├──LoginLogic.tsx
│   │    │    ├──LoginModal.tsx
│   │    │    ├──NotFound.tsx
│   │    │    ├──PaymentInfo.tsx
│   │    │    ├──ProdductDetails.tsx
│   │    │    ├──Products.tsx
│   │    │    ├──Pregister.tsx
│   │    │    ├──ReviewProduct.tsx
│   │    │    └──SimilarProducts.tsx
│   │    ├──App.tsx
│   │    ├──main.tsx
│   │    └──vite-env.d.ts
│   ├───.eslintrc.cjs
│   ├───.gitignore
│   ├───cypress.config.js
│   ├───index.html
│   ├───multi-reporter-config.json
│   ├───package.json
│   ├───package-lock.json
│   ├───README.md
│   ├───tsconfig.app.json
│   ├───tsconfig.json
│   ├───tsconfig.node.json
│   └───vite.config.ts
│
└───README.md
```

## 📊 Responsables

Proyecto desarrollado en Java Spring boot y React Vite (Typescript)

- Código de proyecto a ser analizado en SonarQube
- Grupo 7
- Alumnos:
  - Hidalgo Cock Joaquin
  - Rique Garcia Marko
  - Huaman Ortiz Emerson Raul
  - Landeo Cuentas Sebastian
  - Ore Paredes Gianfranco
  - Palomino Julian Alex Marcelo
- Curso: Verificación y Validación
- Profesor: Edgar Sarmiento Calisaya

# Jenkins Pipeline

Este pipeline está diseñado para automatizar el ciclo de vida de desarrollo de la aplicación **Alfashop**. Incluye pasos para la construcción, pruebas, empaquetado y despliegue.

## Configuración de Plugins

- **Git**
- **JDK**
- **Maven**.
- **SonarQube Scanner**
- **NodeJs**

## Etapas del Pipeline

Imagen de stage del pipeline

### 1. **Git Checkout**

```bash
stage("Git Checkout") {
    steps {
        git branch: 'master', url: 'https://github.com/JoaquinHC9/Alfashop2.0.git'
    }
}
```
