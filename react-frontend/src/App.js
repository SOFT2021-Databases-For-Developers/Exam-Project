import React, { useState, useEffect } from 'react';
import axios from 'axios';
 
function App() {
  const [data, setData] = useState([]);



  useEffect(() => {
    async function fetchData() {
      // You can await here
      const result = await axios('http://localhost:25001/listings');
      console.log(result.data)
      setData(result.data)
      // ...
    }
    fetchData();
    console.log(data)
  }, []);
 
  return (
    <div>
      <h2>Hello world!</h2>
      <ul>
        {data.map(item => (
          <li key={item.id}>
            <p>{item.id}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}
 
export default App;