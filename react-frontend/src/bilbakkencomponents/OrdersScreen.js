  
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import OrderCard from './OrderCard'

const OrdersScreen = (props) => {

    const [list, setList] = useState([]);

    const fetchListings = async() => {
        const result = await axios('http://localhost:25000/orders/user/' + props.token.id);
        setList(result.data)
    }

    useEffect(() => {
        fetchListings();
    }, []);

    
    
    return (
        <div>
            {list.map(item => (
                <OrderCard order={item} />
            ))}
        </div>
    )
}

export default OrdersScreen;