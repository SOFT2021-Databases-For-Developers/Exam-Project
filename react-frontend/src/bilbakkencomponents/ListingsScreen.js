  
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ListingCard from './ListingCard'

const ListingsScreen = (props) => {

    const [list, setList] = useState([]);

    const fetchListings = async() => {
        const result = await axios('http://localhost:25000/listings');
        setList(result.data.content)
    }

    useEffect(() => {
        fetchListings();
    }, []);

    
    
    return (
        <div>
            {list.map(item => (
                <ListingCard title={item.title} token={props.token} listing={item}/>
            ))}
        </div>
    )
}

export default ListingsScreen;