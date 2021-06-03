import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Select from 'react-select'

const CreateListingScreen = (props) => {

    const [title, setTitle] = useState();
    const [des, setDes] = useState();

    const [price, setPrice] = useState();
    const [km, setKm] = useState();

    const [model, setModel] = useState()
    const [models, setModels] = useState();

    var test;

    const fetchModels = async() => {
        const result = await axios('http://localhost:25000/cars');

        setModels(result.data.content)
        let temp = []
        result.data.content.map(item => temp.push({value: item, label: item.make.name}))
        setModels(temp)
    }

    useEffect(() => {
        fetchModels();
    }, []);

    const click = async() => {
       
       let body = {
            "seller": props.token.id,
            "title": title,
            "description": des,
            "price": price,
            "km": km,
            "status": "ACTIVE",
            "car": model
        }
        console.log(body)
        let res = await axios.post('http://localhost:25000/listings/', body)
        if(res.data.id)
        {
            alert("Listing created")
        }
    }

    const carChange = (option) => {
        setModel(option.value)
    }
    
    const handleSubmit = async e => {
        e.preventDefault();
        click()
      }

      return(
        <div className="login-wrapper">
          <h1>Listing creator</h1>
          <form onSubmit={handleSubmit}>
            <label>
              <p>Title</p>
              <input type="text" onChange={e => setTitle(e.target.value)} />
            </label>
            <label>
              <p>Description</p>
              <input type="text" onChange={e => setDes(e.target.value)} />
            </label>
            <label>
              <p>Price</p>
              <input type="number" onChange={e => setPrice(e.target.value)} />
            </label>
            <label>
              <p>KM</p>
              <input type="number" onChange={e => setKm(e.target.value)} />
            </label>
            <label>
                <p>----Car---------</p>
                <Select options={models} onChange={carChange}/>
            </label>
            <div>
              <button type="text">Submit</button>
            </div>
          </form>
        </div>
      )
}

export default CreateListingScreen;