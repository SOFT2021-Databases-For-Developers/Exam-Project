import React from 'react';
import { Link } from 'react-router-dom';


export default function NavigationBar() {
  return(
    <div>
      <Link to="/dashboard">Recommendations </Link>
      <Link to="/listings">Listings </Link>
      <Link to="/createlisting" >CreateListing </Link>
      <Link to="/shoppingcart">ShoppingCart </Link>
      <Link to="/orders">Orders </Link>
    </div>
  );
}