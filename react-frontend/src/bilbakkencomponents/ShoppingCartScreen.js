  
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ListingCard from './ListingCard'

const ShoppingCartScreen = (props) => {

    const [list, setList] = useState([]);

    const fetchListings = async() => {
        const result = await axios('http://localhost:25000/session/' + props.token.email);
        if(result.data.shoppingCart)
        {
            setList(result.data.shoppingCart)
        }
       
        
    }

    const deleteShoppingCart = async() => {
        const result = await axios.delete('http://localhost:25000/session/' + props.token.email);
        if(result.status == 200)
        {
            setList([])
        }
    }

    useEffect(() => {
        fetchListings();
    }, []);

    
    
    return (
        <div>
            {list.map(item => (
                <p>{item}</p>
            ))}
            <button onClick={() => deleteShoppingCart()} >Clear shopping cart</button>
        </div>
    )
}

export default ShoppingCartScreen;