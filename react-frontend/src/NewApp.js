import React, { useState } from 'react';
import './App.css';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Dashboard from './bilbakkencomponents/Dashboard';
import Preferences from './components/Preferences';
import Login from './components/Login'
import useToken from './components/useToken';
import NavigationBar from './bilbakkencomponents/NavigationBar'
import ListingsScreen from './bilbakkencomponents/ListingsScreen'
import CreateListingScreen from './bilbakkencomponents/CreateListingScreen'
import ShoppingCartScreen from './bilbakkencomponents/ShoppingCartScreen'
import OrdersScreen from './bilbakkencomponents/OrdersScreen'


function App() {

    const [ token, setToken ] = useState();

    const saveToken = (tmp) => {
      setToken(tmp)
    }
  
    if(!token) {
      return <Login setToken={saveToken} />
    }
  
    return (
      <div className="wrapper">
        <h1>Application</h1>
        <BrowserRouter>
          <NavigationBar />
          <Switch>
            <Route path="/dashboard">
              <Dashboard token={token}/>
            </Route>
            <Route path="/preferences">
              <Preferences />
            </Route>
            <Route path="/listings">
              <ListingsScreen token={token}/>
            </Route>
            <Route path="/createlisting">
              <CreateListingScreen token={token}/>
            </Route>
            <Route path="/shoppingcart">
              <ShoppingCartScreen token={token}/>
            </Route>
            <Route path="/orders">
              <OrdersScreen token={token}/>
            </Route>
          </Switch>
        </BrowserRouter>
      </div>
    );
  }
  
  export default App;