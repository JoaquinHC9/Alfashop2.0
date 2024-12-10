describe('Pruebas de Compras', function () {
  it('Debe realizar una compra con éxito y verificar aparezca en el historial de compras', () => {
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

    // Paso 7: Seleccionar un producto
    cy.contains('p', 'Teléfono inteligente con 128GB de almacenamiento')
      .closest('.product-card') // Asegurarse de seleccionar el contenedor correcto
      .click();

    // Paso 8: Esperar a que la página del producto cargue
    cy.url().should('include', '/products/1');

    // Paso 9: Ajustar la cantidad a 3
    cy.get('input[type="number"]')
      .clear()
      .type('3');

    // Paso 10: Hacer clic en el botón "Agregar al Carrito"
    cy.contains('button', 'Agregar al Carrito')
      .click();

    // Paso 11: Abrir la barra lateral
    cy.get('[data-testid="MenuIcon"]').click();

    // Paso 12: Dar click en el carrito
    cy.contains('span', 'Carrito').click();

    // Paso 13: Verificar el total de la compra
    cy.wait(500)
    cy.get('[title="total-price"]') 
      .should('be.visible')
      .invoke('text')
      .then((totalText) => {
        const expectedTotal = parseFloat(totalText.replace('$', '').trim()); // Limpiar el texto y convertir a número
        // Aquí puedes agregar una aserción para verificar el valor esperado
        expect(expectedTotal).to.be.greaterThan(0); // Verificar que el total es un valor positivo
      
        // Paso 14: Presionar selector para seleccionar el método de pago
        cy.contains('div', 'PayPal').click();

        // Paso 15: Seleccionar metodo de pago
        cy.contains('li', 'Visa').click();

        // Paso 16: Comprar
        cy.contains('button', 'Comprar').click();

        // Paso 17: Hacer clic en el botón "OK" para confirmar la acción
        cy.get('button.swal2-confirm').click(); // Clic en el botón "OK"

        // Paso 18: Verificar que la orden fue registrada correctamente
        cy.get('h2#swal2-title').should('have.text', 'Orden Registrada Exitosamente');

        // Paso 19: Verificar el historial de pago
        cy.contains('span', 'Pagos').click();
        cy.wait(500);
       // Paso 20: Verificar que el total de la compra esté en el historial de pagos
        cy.get('[aria-label="Monto-Total"]')
        .first() // Selecciona el primer elemento
        .invoke('text')
        .then((paymentTotalText) => {
          // Elimina el texto "Total: " y luego limpia el valor para obtener solo el número
          const paymentTotal = parseFloat(paymentTotalText.replace('Total: ', '').replace('$', '').trim());
          console.log(paymentTotal);

          // Aquí puedes agregar una aserción para verificar que el total de la compra coincida
          expect(paymentTotal).to.equal(expectedTotal); // Suponiendo que ya has guardado expectedTotal
        });

      });
  });
});
