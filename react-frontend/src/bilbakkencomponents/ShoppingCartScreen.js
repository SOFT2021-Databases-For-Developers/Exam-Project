  
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

    const postOrder = async() => {
        let body = {
            "user": props.token,
            "listingIds": list
        }
        let res = await axios.post('http://localhost:25000/orders/', body)
        console.log(res)
        if(res.status == 201)
        {
            deleteShoppingCart()
            alert("Bought")
        }

        
    }
    
    return (
        <div>
            {list.map(item => (
                <p>{item}</p>
            ))}
            <button onClick={() => deleteShoppingCart()} >Clear shopping cart</button>
            <button onClick={() => postOrder()} >Buy</button>
        </div>
    )
}

export default ShoppingCartScreen;