  
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import RecommendationList from './RecommendationList'

const Dashboard = (props) => {

    const [list, setList] = useState([]);

    function compare( a, b ) {
      console.log(a.count + " " + b.count)
      if ( a.count > b.count ){
        return -1;
      }
      if ( a.count < b.count ){
        return 1;
      }
      return 0;
    }

    const fetchListings = async() => {
        const result = await axios('http://localhost:25000/recommendation/' + props.token.email);
        result.data.sort(compare)
        setList(result.data)

    }

    useEffect(() => {
        fetchListings();
    }, []);

    
    
    return (
        <div>
            {list.map(item => (
                <div>
                  <h4>{item.make} {item.count}</h4>
                  <RecommendationList make={item.make} token={props.token}/>
                </div>
            ))}
        </div>
    )
}

export default Dashboard;