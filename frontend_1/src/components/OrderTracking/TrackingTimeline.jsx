import React from "react";

const statuses = [
  { key: "ORDER_CONFIRMED", title: "Order Confirmed", desc: "Payment successful" },
  { key: "RESTAURANT_PREPARING_FOOD", title: "Restaurant Preparing Food", desc: "Your meal is being cooked" },
  { key: "OUT_FOR_DELIVERY", title: "Out for Delivery", desc: "Rider is nearby your location" },
  { key: "DELIVERED", title: "Delivered", desc: "Enjoy your meal" },
];

const TrackingTimeline = ({ currentStatus }) => {
  const currentIndex = statuses.findIndex((s) => s.key === currentStatus);

  return (
    <div className="card border-0 shadow-sm ots-card">
      <div className="card-body p-4">
        <div className="d-flex flex-wrap justify-content-between align-items-center gap-2 mb-3">
          <div>
            <h4 className="mb-1">Order Tracking Timeline</h4>
            <span className="ots-live-mini"><span className="ots-live-dot" /> Live tracking</span>
          </div>
          <span className="small text-secondary">Updated in real time</span>
        </div>

        <div className="ots-timeline">
          {statuses.map((status, index) => {
            const completed = currentIndex >= index;
            const current = currentIndex === index;

            return (
              <div key={status.key} className="ots-timeline-item d-flex gap-3">
                <div className="ots-timeline-marker-wrap d-flex flex-column align-items-center">
                  <span className={`ots-timeline-marker ${completed ? "is-complete" : ""} ${current ? "is-current" : ""}`}>
                    {completed ? <i className="fa-solid fa-check" /> : index + 1}
                  </span>
                  {index !== statuses.length - 1 ? <span className="ots-timeline-line" /> : null}
                </div>
                <div>
                  <h5 className={`mb-1 ${current ? "ots-primary" : ""}`}>{status.title}</h5>
                  <p className="text-secondary mb-0">{status.desc}</p>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
};

export default TrackingTimeline;
