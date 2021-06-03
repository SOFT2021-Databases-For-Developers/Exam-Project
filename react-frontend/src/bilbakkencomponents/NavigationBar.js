import React from 'react';
import { Link } from 'react-router-dom';


export default function NavigationBar() {
  return(
    <div>
      <Link to="/dashboard">Dash </Link>
      <Link to="/preferences">Pref </Link>
      <Link to="/listings">Listings </Link>
      <Link to="/createlisting" >CreateListing </Link>
      <Link to="/shoppingcart">ShoppingCart </Link>
    </div>
  );
}