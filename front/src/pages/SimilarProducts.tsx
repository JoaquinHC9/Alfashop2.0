import React, { useEffect, useState } from "react";
import { Box, Typography, Button, IconButton, Slide, Paper } from "@mui/material";
import { Product } from "../models/Product";
import API_URL from "../config/config";
import { ArrowBackIos, ArrowForwardIos } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";

interface SimilarProductsProps {
  product: Product;
}

export const SimilarProducts: React.FC<SimilarProductsProps> = ({ product }) => {
  const [similarProducts, setSimilarProducts] = useState<Product[]>([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const itemsPerPage = 4; // Número de productos visibles al mismo tiempo
  const navigate = useNavigate();

  useEffect(() => {
    const fetchSimilarProducts = async () => {
      try {
        const response = await fetch(`${API_URL}/v1/productos/categoria/${product.idCategoria}`);
        if (!response.ok) {
          throw new Error(`Error al obtener productos similares: ${response.statusText}`);
        }
        const data: Product[] = await response.json();
        const filteredProducts = data.filter((p) => p.idProducto !== product.idProducto);
        setSimilarProducts(filteredProducts.slice(0, 10));
      } catch (error: any) {
        setError(error.message);
        console.error(error);
      } finally {
        setLoading(false);
      }
    };

    fetchSimilarProducts();
  }, [product]);

  const handleNext = () => {
    setCurrentIndex((prevIndex) =>
      (prevIndex + itemsPerPage) % similarProducts.length
    );
  };

  const handlePrev = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex === 0
        ? similarProducts.length - itemsPerPage
        : prevIndex - itemsPerPage
    );
  };

  if (loading) {
    return <Typography variant="body1">Cargando productos similares...</Typography>;
  }

  if (error) {
    return (
      <Typography variant="body1" color="error">
        {error}
      </Typography>
    );
  }

  if (similarProducts.length === 0) {
    return <Typography variant="body1">No hay productos similares.</Typography>;
  }

  const visibleProducts = similarProducts.slice(
    currentIndex,
    currentIndex + itemsPerPage
  );

  const goToProductDetails = (productId: number) => {
    // Retraso de 2 segundos antes de navegar
    setTimeout(() => {
      navigate(`/products/${productId}`);
      window.location.reload(); // Recargar la página después de navegar
    }, 500); 
  };

  return (
    <Paper
      elevation={4}
      sx={{
        m: 4, // Márgenes alrededor del Paper
        borderRadius: 3, // Bordes redondeados
        p: 3, // Padding interno
        width: "95%", // Ajusta el ancho del Paper
        maxWidth: "1200px", // Máximo ancho
        mx: "auto", // Centrar horizontalmente
        position: "relative",
      }}
    >
      <Box mt={5}>
        <Typography variant="h5" color="primary" gutterBottom>
          Productos Similares
        </Typography>
        <Box
          sx={{
            position: "relative",
            overflow: "hidden",
            width: "100%",
            display: "flex",
            alignItems: "center",
          }}
        >
          <IconButton
            sx={{ position: "absolute", left: 0, zIndex: 10 }}
            onClick={handlePrev}
          >
            <ArrowBackIos />
          </IconButton>

          <Box
            sx={{
              display: "flex",
              justifyContent: "center",
              gap: 2, // Espaciado entre productos
              width: "100%",
            }}
          >
            {similarProducts.length > 0 &&
              visibleProducts.map((p) => (
                <Slide
                  key={p.idProducto}
                  direction="right"
                  in={true}
                  timeout={600} // Transición más lenta
                  mountOnEnter
                  unmountOnExit
                >
                  <Box
                    sx={{
                      minWidth: "200px",
                      maxWidth: "300px",
                      textAlign: "center",
                    }}
                  >
                    <img
                      src={p.imagenUrl}
                      alt={p.nombre}
                      style={{
                        maxWidth: "100%",
                        height: "200px",
                        objectFit: "cover",
                        borderRadius: "8px",
                      }}
                    />
                    <Typography variant="subtitle1" sx={{ mt: 2 }}>
                      {p.nombre}
                    </Typography>
                    <Typography variant="body2" color="textSecondary">
                      ${p.precio.toFixed(2)}
                    </Typography>
                    <Button
                      variant="contained"
                      color="primary"
                      sx={{ mt: 1 }}
                      onClick={() => goToProductDetails(p.idProducto)}
                    >
                      Ver Producto
                    </Button>
                  </Box>
                </Slide>
              ))}
          </Box>

          <IconButton
            sx={{ position: "absolute", right: 0, zIndex: 10 }}
            onClick={handleNext}
          >
            <ArrowForwardIos />
          </IconButton>
        </Box>
      </Box>
    </Paper>
  );
};

export default SimilarProducts;
