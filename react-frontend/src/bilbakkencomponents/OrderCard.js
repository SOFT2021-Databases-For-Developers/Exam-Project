import React from 'react';

const OrderCard = (props) => {

    
    
    return (
        <div>
            <h4>{props.order.id}</h4>
            {props.order.listings.map(item => <p>--{item.title}</p>)}
        </div>
    )
}

export default OrderCard;