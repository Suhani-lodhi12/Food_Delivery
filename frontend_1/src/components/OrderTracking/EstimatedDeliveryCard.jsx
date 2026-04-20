import React from "react";

const EstimatedDeliveryCard = ({ estimatedDeliveryTime, orderStatus }) => {
  const progressMap = {
    ORDER_CONFIRMED: 20,
    RESTAURANT_PREPARING_FOOD: 45,
    OUT_FOR_DELIVERY: 80,
    DELIVERED: 100,
  };

  const statusLabelMap = {
    ORDER_CONFIRMED: "Order confirmed",
    RESTAURANT_PREPARING_FOOD: "Restaurant is preparing your order",
    OUT_FOR_DELIVERY: "Rider is on the way",
    DELIVERED: "Order delivered",
  };

  const progress = progressMap[orderStatus] ?? 10;
  const statusLabel = statusLabelMap[orderStatus] ?? "Processing";

  return (
    <div className="card shadow-sm border-0 ots-card">
      <div className="card-body p-4">
        <div className="d-flex align-items-center justify-content-between mb-2">
          <p className="text-uppercase small fw-semibold ots-label mb-0">Estimated arrival</p>
          <span className="ots-live-mini"><span className="ots-live-dot" /> Live</span>
        </div>
        <div className="d-flex align-items-end gap-2 mb-3">
          <h2 className="mb-0 fw-bold ots-time">{estimatedDeliveryTime || "--"}</h2>
        </div>
        <div className="progress mb-3 ots-progress-wrap" role="progressbar" aria-label="Delivery progress" aria-valuenow={progress} aria-valuemin="0" aria-valuemax="100">
          <div className="progress-bar ots-progress" style={{ width: `${progress}%` }} />
        </div>
        <p className="mb-0 text-secondary">{statusLabel}</p>
      </div>
    </div>
  );
};

export default EstimatedDeliveryCard;
