import React, { useState, useEffect } from 'react';
import { Typography, Button, Container, TextField, MenuItem, Card, CardContent, CardActions, IconButton, List, ListItem, ListItemText, Paper, Avatar, Grid } from '@mui/material';
import { Delete as DeleteIcon } from '@mui/icons-material';
import { useAuthUser } from 'react-auth-kit';
import API_URL from '../config/config';
import Swal from 'sweetalert2';
import { CartItem } from '../models/CartItem';
import { Product } from '../models/Product';
import { Helmet } from 'react-helmet';

const Cart: React.FC = () => {
  const [cart, setCart] = useState<CartItem[]>([]);
  const [productos, setProductos] = useState<Product[]>([]);
  const [metodoPago, setMetodoPago] = useState<string>('PAYPAL');
  const auth = useAuthUser();
  const customerId = auth()?.customerId;
  const token = localStorage.getItem("_auth");

  useEffect(() => {
    const storedCart = JSON.parse(localStorage.getItem('cart') || '[]');
    setCart(storedCart);
    fetchProducts(storedCart);
  }, []);

  const fetchProducts = async (cartItems: CartItem[]) => {
    const productPromises = cartItems.map((item: CartItem) =>
      fetch(`${API_URL}/v1/productos/producto/${item.idProducto}`)
        .then(response => response.json())
    );
    const productsData = await Promise.all(productPromises);
    setProductos(productsData);
  };

  const handleMetodoPagoChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setMetodoPago(event.target.value);
  };

  const removeFromCart = (idProducto: number) => {
    const updatedCart = cart.filter(item => item.idProducto !== idProducto);
    setCart(updatedCart);
    localStorage.setItem('cart', JSON.stringify(updatedCart));
  };

  const createOrder = async () => {
    if (!customerId || !token) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Debe estar logueado para realizar una compra.',
      });
      return;
    }

    const pedidoRequest = {
      idUsuario: customerId,
      metodoPago: metodoPago,
      productos: cart,
    };

    try {
      const response = await fetch(`${API_URL}/v1/pedidos/crear`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify(pedidoRequest),
      });

      if (response.ok) {
        Swal.fire({
          icon: 'success',
          title: 'Orden Registrada Exitosamente',
          text: 'La orden de compra se ha registrado correctamente.',
        });
        localStorage.removeItem('cart');
        setCart([]); 
        setProductos([]); 
      } else {
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Error al ejecutar la orden.',
        });
      }
    } catch (error) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Error al realizar la compra.',
      });
      console.error(error);
    }
  };

  return (
    <Container maxWidth="md" sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', height: 'auto', paddingTop: 4, paddingBottom: 2, mt:5 }} disableGutters>
        <Helmet>
          <title>Carrito de compras</title>
        </Helmet>
      <Card sx={{ padding: 20, boxShadow: 3, borderRadius: 2, width: '100%', maxWidth: 1000, textAlign: 'center', backgroundColor: 'background.paper', mb: 2,mt:2 , height:'300px' }}>

        <Typography variant="h4" color="primary" sx={{  fontWeight: 'bold' }}>
          Carrito de compras
        </Typography>
        <Typography variant="h6" color="textSecondary">
          Aquí puedes gestionar tus productos.
        </Typography>
      </Card>

      <Paper elevation={4} sx={{ borderRadius: 4, padding: 4, width: '100%', maxWidth: 1000 }}>
        {cart.length === 0 ? (
          <Typography variant="body1" align="center">El carrito está vacío.</Typography>
        ) : (
          <div>
            <List sx={{ maxHeight: 400, overflowY: 'auto' }}>
              {cart.map((item, index) => {
                const product = productos.find(p => p.idProducto === item.idProducto);
                return (
                  <ListItem key={item.idProducto}>
                    <Grid container spacing={2} alignItems="center">
                      <Grid item>
                        <Avatar
                          src={product?.imagenUrl || '/default-product-icon.png'}
                          alt={product?.nombre}
                          sx={{ width: 50, height: 50 }}
                        />
                      </Grid>
                      <Grid item xs>
                        <ListItemText
                          primary={product?.nombre || `Producto ID: ${item.idProducto}`}
                          secondary={`Cantidad: ${item.cantidad}`}
                        />
                      </Grid>
                      <Grid item>
                        <IconButton edge="end" onClick={() => removeFromCart(item.idProducto)}>
                          <DeleteIcon />
                        </IconButton>
                      </Grid>
                    </Grid>
                  </ListItem>
                );
              })}
            </List>

            <Card variant="outlined" sx={{ mt: 3 }}>
              <CardContent>
                <Typography variant="h6" gutterBottom>
                  Selecciona Método de Pago
                </Typography>
                <TextField
                  select
                  label="Método de Pago"
                  value={metodoPago}
                  onChange={handleMetodoPagoChange}
                  fullWidth
                  variant="outlined"
                  margin="normal"
                >
                  <MenuItem value="PAYPAL">PayPal</MenuItem>
                  <MenuItem value="CREDIT_CARD">Credit Card</MenuItem>
                  <MenuItem value="VISA">Visa</MenuItem>
                  <MenuItem value="MASTER_CARD">MasterCard</MenuItem>
                  <MenuItem value="BITCOIN">Bitcoin</MenuItem>
                </TextField>
              </CardContent>
              <CardActions sx={{ justifyContent: 'center' }}>
                <Button variant="contained" color="primary" onClick={createOrder} fullWidth>
                  Comprar
                </Button>
              </CardActions>
            </Card>
          </div>
        )}
      </Paper>
    </Container>
  );
};

export default Cart;
