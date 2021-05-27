import React, { useState } from "react";
import { Card, Button, Collapse } from "react-bootstrap";

export default function Listing({ listing }) {
  const [open, setOpen] = useState(false);

  return (
    <Card className="mb-3">
      <Card.Body>
        <div className="d-flex justify-content-between">
          <div>
            <Card.Title>
              {listing.title} -{" "}
              <span className="font-weight-light">${listing.price}</span>
            </Card.Title>
            <Card.Subtitle className="text-muted mb-2">
              Model Year:{" "}
              {listing.created_on}
            </Card.Subtitle>
          </div>
        </div>
      </Card.Body>
    </Card>
  );
}