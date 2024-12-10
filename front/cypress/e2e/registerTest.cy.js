describe('Pruebas de Registro', function () {
  it('Debe completar el formulario de registro y mostrar un mensaje de éxito', () => {
    // Paso 1: Abre la aplicación en la ruta del componente Register
    cy.visit('http://localhost:5173/register'); // Cambia la URL según tu configuración

    // Paso 2: Completa los campos del formulario
    cy.get('input[name="nombre"]').type('John');
    cy.get('input[name="apellido"]').type('Doe');
    cy.get('input[name="email"]').type('johndoe69696@example.com');
    cy.get('input[name="contrasena"]').type('password123');
    cy.get('input[name="telefono"]').type('1234567890');

    // Paso 3: Abre el calendario de Material-UI
    cy.get('button[aria-label="Choose new calendar date"]').click();

    // Paso 4: Espera a que se cargue el menú del calendario y selecciona el día específico
    cy.get('.MuiPickersDay-root').should('be.visible');
    cy.get('button[data-timestamp="1733720400000"]').click(); // Selecciona el día 22

    // Paso 5: Haz clic en el botón de registro
    cy.contains('button', 'Registrarse').click();

    // Paso 6: Espera y valida el mensaje de éxito en el elemento <h2>
    cy.get('h2#swal2-title').should('have.text', 'Registro exitoso');
  });
});
