# Imagen base de Node.js
FROM node:20-alpine
# Configuración del directorio de trabajo
WORKDIR /app
# Copiar los archivos necesarios para instalar dependencias
COPY package*.json .
RUN npm install
# Copiar todos los archivos del proyecto al contenedor
COPY . .

# Copiar específicamente el archivo de configuración de Cypress
COPY cypress.config.js ./cypress.config.js

EXPOSE 5173

CMD ["npm", "run", "dev"]
