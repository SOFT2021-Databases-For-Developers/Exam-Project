import React from 'react';
import axios from 'axios';

const ListingsCard = (props) => {

    const click = async() => {
        let body = {
            name: props.token.email,
            make: props.listing.car.make.name,
            listingId: props.listing.id
        }
        axios.post('http://localhost:25000/recommendation/', body)
    }
    
    const addToCart = async() => {
        var res = await axios.get('http://localhost:25000/session/' + props.token.email)
        let body = {
            "username": props.token.email,
            "shoppingCart": []
        }
        if(res.data.shoppingCart)
        {
            if(!res.data.shoppingCart.includes(props.listing.id.toString()))
            {
                res.data.shoppingCart.push(props.listing.id)
            }
            
            body.shoppingCart = res.data.shoppingCart;
        }
        else{
            body.shoppingCart.push(props.listing.id)
        }
        axios.post('http://localhost:25000/session/', body)
    }
    const checkIfSold = () => {
        if(props.listing.status != "SOLD")
        {
            return (<div onClick={() => click()}>
            <p>{props.title} id:{props.listing.id} <button onClick={() => addToCart()}>Add to cart</button></p>
            
        </div>)
        }
    }
    
    return (
        <div>
            {checkIfSold()}
        </div>
    )
}

export default ListingsCard;