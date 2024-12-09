describe('Test: agregar y quitar productos del carrito de compras', () => {
  it('Debe permitir agregar y quitar un producto al carrito de compras', () => {
    // Paso 1: Navegar a la página principal
    cy.visit('http://localhost:5173/');

    // Paso 2: Seleccionar un producto
    cy.contains('p', 'Teléfono inteligente con 128GB de almacenamiento')
      .closest('.product-card') // Asegurarse de seleccionar el contenedor correcto
      .click();

    // Paso 3: Esperar a que la página del producto cargue
    cy.url().should('include', '/products/1');

    // Paso 4: Ajustar la cantidad a 3
    cy.get('input[type="number"]')
      .clear()
      .type('3');

    // Paso 5: Hacer clic en el botón "Agregar al carrito"
    cy.contains('button', 'Agregar al Carrito').click();

    // Paso 6: Verificar que el producto se ha agregado al localStorage
    cy.window().then((window) => {
      const cart = JSON.parse(window.localStorage.getItem('cart'));
      expect(cart).to.have.lengthOf(1); // Verifica que hay un solo producto en el carrito
      expect(cart[0].idProducto).to.equal(1); // Verifica que el ID del producto sea correcto
      expect(cart[0].cantidad).to.equal(3); // Verifica que la cantidad sea 3
    });

    // Paso 7: Abrir la barra lateral
    cy.get('[data-testid="MenuIcon"]').click();

    // Paso 8: Dar clic en el carrito
    cy.contains('span', 'Carrito').click();

    // Paso 9: Quitar un item del carrito
    cy.get('[data-testid="DeleteIcon"]').click();

    // Paso 10: Verificar el toast
    cy.get('.Toastify__toast-body') // Selecciona el contenedor del mensaje de Toastify
    .should('be.visible') // Verifica que sea visible
    .and('contain.text', 'Se ha eliminado el producto del carrito'); // Verifica el texto dentro

    });
});
