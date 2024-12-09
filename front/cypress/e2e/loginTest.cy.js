describe('Pruebas de Login Modal', function () {
  it('Debe realizar login exitosamente y mostrar mensaje de éxito', () => {
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

    // Paso 7: Espera y verifica que el mensaje de éxito (Toast) aparezca
    cy.get('div.Toastify__toast--success').should('be.visible')
      .and('contain', 'Login Exitoso');
  });
});
