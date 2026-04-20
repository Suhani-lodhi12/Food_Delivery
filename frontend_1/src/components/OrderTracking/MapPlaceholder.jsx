import React from "react";

const MapPlaceholder = ({ orderStatus }) => {
  const riderText = orderStatus === "DELIVERED" ? "Delivered to your location" : "Courier moving toward you";
  const statusLabel = orderStatus === "DELIVERED" ? "Delivered" : "Rider moving";

  return (
    <div className="card border-0 shadow-sm ots-map-card">
      <div className="card-body p-4 ots-map-placeholder d-flex flex-column justify-content-between">
        <div className="d-flex justify-content-between align-items-start">
          <span className="badge rounded-pill ots-badge-soft px-3 py-2">{statusLabel}</span>
          <div className="ots-icon-dot">
            <i className="fa-solid fa-location-dot" />
          </div>
        </div>
        <div>
          <p className="mb-1 text-uppercase small ots-map-label">Courier location</p>
          <h5 className="mb-0">{riderText}</h5>
        </div>
      </div>
    </div>
  );
};

export default MapPlaceholder;
