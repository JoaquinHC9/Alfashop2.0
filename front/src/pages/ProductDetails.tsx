import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import API_URL from '../config/config';
import { Typography, Button, Box, Grid, TextField, Rating, Container,InputAdornment } from '@mui/material';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';
import { Product } from '../models/Product';
import { CartItem } from '../models/CartItem';
import { useAuthUser } from 'react-auth-kit';
import Swal from 'sweetalert2';
import { Review } from '../models/Review';
import { Helmet } from 'react-helmet';
import { SimilarProducts } from './SimilarProducts';


const ProductDetails: React.FC = () => {
  const { idProducto } = useParams();
  const auth = useAuthUser();
  const [product, setProduct] = useState<Product | null>(null);
  const [cantidad, setCantidad] = useState<string>('');
  const [loading, setLoading] = useState(true);
  const [reviews, setReviews] = useState<Review[]>([]);
  const [newReview, setNewReview] = useState({
    comentario: '',
    puntuacion: 0,
  });
  const customerId = auth()?.customerId;
  const token = localStorage.getItem("_auth");

  useEffect(() => {
    const fetchProductDetails = async () => {
      try {
        const response = await fetch(`${API_URL}/v1/productos/producto/${idProducto}`);
        if (response.ok) {
          const data = await response.json();
          setProduct(data);
        } else {
          console.error("Error al obtener detalles del producto:", response.statusText);
        }
      } catch (error) {
        console.error("Error al obtener detalles del producto:", error);
      } finally {
        setLoading(false);
      }
    };

    const fetchReviews = async () => {
      try {
        const response = await fetch(`${API_URL}/v1/resenas/producto/${idProducto}`);
        if (response.ok) {
          const data = await response.json();
          setReviews(data);
        } else {
          console.error("Error al obtener reseñas:", response.statusText);
        }
      } catch (error) {
        console.error("Error al obtener reseñas:", error);
      }
    };

    fetchProductDetails();
    fetchReviews();
  }, [idProducto]);
  

  const handleCantidadChange = (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
    const newValue = e.target.value;
    if (newValue === '' || (!isNaN(Number(newValue)) && Number(newValue) >= 1)) {
      setCantidad(newValue);
    }
  };

  const handleReviewChange = (field: string, value: string | number) => {
    setNewReview(prev => ({ ...prev, [field]: value }));
  };

  const addReview = async () => {
    if (!customerId || !token) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Debe de estar logueado para registrar una Reseña.',
      });
      return;
    }

    const resenaRequest = {
      idUsuario: Number(customerId),
      idProducto: Number(idProducto),
      comentario: newReview.comentario,
      puntuacion: newReview.puntuacion,
    };

    try {
      const response = await fetch(`${API_URL}/v1/resenas/crear`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(resenaRequest),
      });

      if (response.ok) {
        toast.success('Reseña agregada exitosamente');
        setNewReview({ comentario: '', puntuacion: 0 });
        const data: Review = await response.json();
        setReviews((prev) => [...prev, data]);
      } else {
        toast.error('Error al agregar la reseña');
      }
    } catch (error) {
      toast.error('Error al agregar la reseña');
    }
  };

  const addToCart = () => {
    if (cantidad === '' || isNaN(Number(cantidad)) || Number(cantidad) <= 0) {
      toast.error('Por favor ingresa una cantidad válida');
      return;
    }

    const cantidadInt = Number(cantidad);
    if (cantidadInt > product!.stock) {
      toast.error(`Número de unidades seleccionadas supera el stock`);
      return;
    }

    const cartItem: CartItem = {
      idProducto: product!.idProducto,
      nombreProducto: product!.nombre,
      cantidad: cantidadInt,
    };

    let cart: CartItem[] = JSON.parse(localStorage.getItem('cart') || '[]');
    const existingItemIndex = cart.findIndex(item => item.idProducto === cartItem.idProducto);
    if (existingItemIndex !== -1) {
      cart[existingItemIndex].cantidad += cartItem.cantidad;
    } else {
      cart.push(cartItem);
    }
    localStorage.setItem('cart', JSON.stringify(cart));
    toast.success(`Se han agregado ${cantidadInt} unidad(es) al carrito`);
  };

  if (loading) {
    return <Typography variant="body1">Cargando detalles del producto...</Typography>;
  }

  if (!product) {
    return <Typography variant="body1">Producto no encontrado.</Typography>;
  }

  return (
    <Container sx={{ m: 5, ml: 5 }}>
      <Helmet>
        <title>{product.nombre}</title>
      </Helmet>
      <Typography variant="h2" color="primary" gutterBottom>
        Detalles del Producto
      </Typography>

      <Grid container spacing={4}>
        <Grid item xs={12} md={6}>
          <Box display="flex" justifyContent="center" mb={3}>
            <img
              src={product.imagenUrl}
              alt={product.nombre}
              style={{
                maxWidth: '100%',
                maxHeight: '350px',
                height: 'auto',
                borderRadius: '8px',
                objectFit: 'contain',
              }}
            />
          </Box>
        </Grid>

        <Grid item xs={12} md={6}>
          <Box sx={{ padding: 3, borderRadius: 2, boxShadow: 3, backgroundColor: '#f9f9f9' }}>
            <Typography variant="h6" color="primary">{product.nombre}</Typography>
            <Typography variant="body1" sx={{ marginBottom: 2 }}>{product.descripcion}</Typography>
            <Typography variant="h6" color="primary">Tipo: {product.nombreCategoria}</Typography>
            <Typography variant="body1" color="secondary">Precio: ${product.precio.toFixed(2)}</Typography>
            <Typography variant="body2">Disponibles: {product.stock}</Typography>

            <TextField
              type="number"
              label="Cantidad"
              value={cantidad}
              onChange={handleCantidadChange}
              InputProps={{
                inputProps: { min: 1 },
                startAdornment: <InputAdornment position="start">Unidades</InputAdornment>,
              }}
              variant="outlined"
              margin="normal"
              fullWidth
            />

            <Button variant="contained" color="primary" onClick={addToCart} fullWidth>
              Agregar al Carrito
            </Button>
          </Box>
        </Grid>
      </Grid>

      <Box sx={{ marginTop: 4 }}>
      <SimilarProducts product={product} />

        
      <Typography variant="h6" color="primary">Reseñas del Producto</Typography>
      {reviews.length > 0 ? (
        <TableContainer component={Paper} sx={{ marginTop: 2 }}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell><Typography variant="h6" color="primary">Usuario</Typography></TableCell>
                <TableCell><Typography variant="h6" color="primary">Comentario</Typography></TableCell>
                <TableCell><Typography variant="h6" color="primary">Puntuación</Typography></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {reviews.map((review) => (
                <TableRow key={review.idUsuario}>
                  <TableCell>{`Usuario ${review.idUsuario}`}</TableCell>
                  <TableCell>{review.comentario}</TableCell>
                  <TableCell>
                    <Rating value={review.puntuacion} readOnly />
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      ) : (
        <Typography variant="body1" color="textSecondary">No hay reseñas disponibles.</Typography>
      )}
    </Box>

      <Box sx={{ marginTop: 4 }}>
        <Typography variant="h6" color="primary">Añadir una Reseña</Typography>
        <TextField
          label="Comentario"
          value={newReview.comentario}
          onChange={(e) => handleReviewChange('comentario', e.target.value)}
          fullWidth
          multiline
          margin="normal"
        />
          <Rating
            name="rating"
            value={newReview.puntuacion}
            onChange={(event, newValue) => {
              if (newValue !== null) {
                handleReviewChange('puntuacion', newValue);
              }
            }}
            sx={{ marginBottom: 2 }}
          />
        <Button variant="contained" color="primary" onClick={addReview} fullWidth>
          Añadir Reseña
        </Button>
      </Box>
    </Container>
  );
};

export default ProductDetails;
