import axios from "axios";

export const getOrderTrackingById = async (baseUrl, orderId) => {
  const response = await axios.get(`${baseUrl}/api/orders/${orderId}`);
  return response.data;
};
