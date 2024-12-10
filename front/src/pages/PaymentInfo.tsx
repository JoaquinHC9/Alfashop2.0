import { useEffect, useState } from 'react';
import { Box, Typography, List, ListItem, ListItemText, ListItemIcon, Divider } from '@mui/material';
import { CreditCard, Payment, CheckCircle, Error } from '@mui/icons-material';
import API_URL from '../config/config';
import { useAuthUser } from 'react-auth-kit';
import { PaymentHistoryData } from '../models/PaymentHistory';
import CurrencyBitcoinIcon from '@mui/icons-material/CurrencyBitcoin';

const PaymentHistory: React.FC = () => {
  const [payments, setPayments] = useState<PaymentHistoryData[]>([]);
  const [loading, setLoading] = useState(true);
  const auth = useAuthUser();
  const customerId = auth()?.customerId;
  const token = localStorage.getItem('_auth'); // Obtener el token del almacenamiento local

  useEffect(() => {
    const fetchPaymentHistory = async () => {
      if (!customerId || !token) return; // Asegurar que el usuario esté autenticado

      try {
        const response = await fetch(`${API_URL}/v1/pedidos/usuario/${customerId}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`, // Añadir token a los headers
          },
        });

        if (response.ok) {
          const data: PaymentHistoryData[] = await response.json();

          // Ordenar los pagos por idPedido de manera descendente
          const sortedData = data.sort((a, b) => b.idPedido - a.idPedido);

          setPayments(sortedData);
        } else {
          console.error('Error al obtener el historial de pagos:', response.statusText);
        }
      } catch (error) {
        console.error('Error al obtener el historial de pagos:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchPaymentHistory();
  }, [customerId, token]);

  const getPaymentIcon = (metodoPago: string) => {
    switch (metodoPago) {
      case 'PAYPAL':
        return <Payment color="primary" />;
      case 'CREDIT_CARD':
      case 'VISA':
      case 'MASTER_CARD':
        return <CreditCard color="action" />;
      case 'BITCOIN':
        return <CurrencyBitcoinIcon color='action'/>; // Agregar un ícono de Bitcoin, necesitarás un ícono adecuado
      default:
        return <Payment color="disabled" />;
    }
  };

  const getStatusIcon = (estado: string) => {
    switch (estado) {
      case 'APROBADO':
        return <CheckCircle color="success" />;
      case 'PENDIENTE':
        return <Error color="warning" />;
      case 'FALLIDO':
        return <Error color="error" />;
      default:
        return <Error color="disabled" />;
    }
  };

  const getEstadoColor = (estado: string) => {
    switch (estado) {
      case 'APROBADO':
        return 'success.main'; // Verde para aprobado
      case 'PENDIENTE':
        return 'warning.main'; // Amarillo para pendiente
      case 'FALLIDO':
        return 'error.main'; // Rojo para fallido
      default:
        return 'text.secondary'; // Color secundario si el estado no coincide
    }
  };

  let paymentContent;
  if (loading) {
    paymentContent = <Typography>Cargando historial de pagos...</Typography>;
  } else if (payments.length > 0) {
    paymentContent = (
      <List sx={{ width: '100%', maxWidth: 1200, margin: '0 auto' }} aria-label="Lista-Pagos">
        {payments.map((payment) => (
          <div key={payment.idPedido}>
            <ListItem
              sx={{
                padding: 3, borderRadius: 1, backgroundColor: 'background.paper',
                boxShadow: 1, marginBottom: 3,  display: 'flex',
                justifyContent: 'space-between', alignItems: 'center',
              }}
            >
              <ListItemIcon sx={{ minWidth: 'auto', marginRight: 3 }}>
                {getPaymentIcon(payment.metodoPago)}
              </ListItemIcon>
              <ListItemText
                primary={<Typography variant="h6" sx={{ fontSize: '1.2rem' }}>ID del Pedido: {payment.idPedido}</Typography>} 
                secondary={
                  <>
                    <Typography variant="body1" sx={{ fontSize: '1.1rem' }}>Método de Pago: {payment.metodoPago}</Typography> 
                    <Typography variant="body1"
                      aria-label={`Monto-Total`}
                      sx={{ color: 'text.secondary', fontSize: '1.1rem' }}>
                      Total: ${payment.totalMonto.toFixed(2)}
                    </Typography>
                    <Typography variant="body1" sx={{
                      color: getEstadoColor(payment.estado),
                      fontSize: '1.1rem', // Aumentar tamaño de la fuente
                    }}>
                      Estado: {payment.estado}
                    </Typography>
                  </>
                }
              />
              <ListItemIcon sx={{ minWidth: 'auto' }}>
                {getStatusIcon(payment.estado)}
              </ListItemIcon>
            </ListItem>
            <Divider />
          </div>
        ))}
      </List>
    );
  } else {
    paymentContent = <Typography>No se encontró historial de pagos.</Typography>;
  }

  return (
    <Box sx={{ padding: 3 }}>
      <Typography variant="h4" sx={{ marginBottom: 3, fontSize: '2rem', width:'600px',mt:8 }}> {/* Aumentar tamaño del título */}
        Historial de Pagos
      </Typography>
      {paymentContent}
    </Box>
  );
};

export default PaymentHistory;
