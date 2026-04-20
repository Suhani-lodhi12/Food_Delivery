import React from "react";

const DeliveryPartnerCard = ({ name, phone }) => {
  return (
    <div className="card border-0 shadow-sm ots-card">
      <div className="card-body p-4">
        <h4 className="mb-3">Delivery Partner</h4>
        <div className="d-flex flex-wrap justify-content-between align-items-center gap-3">
          <div className="d-flex align-items-center gap-3">
            <div className="ots-avatar">
              <i className="fa-solid fa-motorcycle" />
            </div>
            <div>
              <h5 className="mb-1">{name || "Delivery Partner"}</h5>
              <p className="mb-0 text-secondary">{phone || "N/A"}</p>
            </div>
          </div>
          {phone ? (
            <a className="btn ots-btn-call" href={`tel:${phone}`}>
              <i className="fa-solid fa-phone me-2" />
              Call Rider
            </a>
          ) : null}
        </div>
      </div>
    </div>
  );
};

export default DeliveryPartnerCard;
