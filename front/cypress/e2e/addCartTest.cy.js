// cypress/e2e/agregar-al-carrito.spec.js

describe('Test: agregar item al carrito de compras', () => {

  it('Debe permitir agregar un producto al carrito de compras', () => {
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
    cy.contains('button', 'Agregar al Carrito')
      .click();
    
  });
});
