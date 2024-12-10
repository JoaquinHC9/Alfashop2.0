describe('Pruebas de Compras', function () {
  it('Debe realizar una compra con exito', () => {
        // Paso 1: Navegar a la aplicación
        cy.visit('http://localhost:5173/'); // Cambia la URL según tu configuración

        // Paso 2: Haz clic en el botón "Login" para abrir el modal
        cy.contains('button', 'Login').click();
    
        // Paso 3: Espera a que el modal esté visible (usando la clase que parece ser única para el modal)
        cy.get('div.MuiBox-root.css-1wnsr1i').should('be.visible');
    
        // Paso 4: Rellena el campo "Usuario"
        cy.get('input[type="text"]').type('joaquin@correo.com');
    
        // Paso 5: Rellena el campo "Contraseña"
        cy.get('input[type="password"]').type('j@123');
    
        // Paso 6: Haz clic en el botón "Iniciar sesión"
        cy.contains('button', 'Iniciar sesión').click();
    
       // Paso 8: Seleccionar un producto
       cy.contains('p', 'Teléfono inteligente con 128GB de almacenamiento')
       .closest('.product-card') // Asegurarse de seleccionar el contenedor correcto
       .click();
    
        // Paso 9: Esperar a que la página del producto cargue
        cy.url().should('include', '/products/1');
    
        // Paso 10: Ajustar la cantidad a 3
        cy.get('input[type="number"]')
          .clear()
          .type('3');
    
        // Paso 11: Hacer clic en el botón "Agregar al carrito"
        cy.contains('button', 'Agregar al Carrito')
          .click();
        // Paso 12: Abrir la barra lateral
        cy.get('[data-testid="MenuIcon"]').click();
        // Paso 13: Dar click en el carrito
        cy.contains('span', 'Carrito').click();
        // Paso 14: Presionar selector
        cy.contains('div','PayPal').click();
        // Paso 15: Seleccionar metodo de pago
        cy.contains('li','Visa').click();
        // Paso 16: Comprar:
        cy.contains('button', 'Comprar').click();
        // Paso 17 Exito
        cy.get('h2#swal2-title').should('have.text', 'Orden Registrada Exitosamente');
  });
  
});
