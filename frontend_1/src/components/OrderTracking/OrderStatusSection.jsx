import React from "react";

const OrderStatusSection = ({ orderId, orderStatus, totalPrice, orderItems }) => {
  const humanStatusMap = {
    ORDER_CONFIRMED: "Order Confirmed",
    RESTAURANT_PREPARING_FOOD: "Restaurant Preparing Food",
    OUT_FOR_DELIVERY: "Out for Delivery",
    DELIVERED: "Delivered",
  };

  return (
    <div className="card border-0 shadow-sm ots-card">
      <div className="card-body p-4">
        <div className="d-flex flex-wrap justify-content-between align-items-center gap-2 mb-3">
          <h4 className="mb-0">Order Status</h4>
          <span className="badge rounded-pill px-3 py-2 ots-badge-primary">{humanStatusMap[orderStatus] || "Processing"}</span>
        </div>
        <p className="text-secondary mb-1">Order ID: <span className="fw-semibold">{orderId}</span></p>
        <p className="text-secondary mb-3">Items: <span className="fw-semibold">{orderItems?.length || 0}</span></p>
        <h5 className="mb-0">Total: <span className="ots-primary">₹{Number(totalPrice || 0).toFixed(2)}</span></h5>
      </div>
    </div>
  );
};

export default OrderStatusSection;
