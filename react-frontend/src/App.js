import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ReactPaginate from 'react-paginate';
import useFetchGames from "./components/useFetchHooks";
import SearchForm from './components/searchForm';
import { Container, Spinner } from "react-bootstrap";
import Game from "./components/Game";
import Listing from './components/Listing'
import Pagination from "./components/Pagination";
 
function App() {
  const [listingList, setListingList] = useState([]);
  const [categoryList, setCategoryList] = useState([]);



  const [modelList, setModelList] = useState([{id: 0, name: "Loading...", year: 0, make: {make_id: 0, name: "Loading..."}}]);
  const [model, setModel] = useState(modelList[0]);
  
  const [makeList, setMakeList] = useState([{id: 0, name: "Loading..."}]);
  const [make, setMake] = useState(makeList[0]);

  const [makeLoading, setMakeLoading] = useState(true);
  const [modelLoading, setModelLoading] = useState(true);

  const [filterQuery, setFilterQuery] = useState();




  async function fetchListings() {
    const result = await axios('http://localhost:25001/listings?page=1&size=10');
    setListingList(result.data.content)
  }

  async function fetchCategories() {
    const result = await axios('http://localhost:25001/categories');
    setCategoryList(result.data)
  }

  async function fetchMakes() {
    let unmounted = false;
    const response = await axios('http://localhost:25001/makes');
      if(!unmounted) {
        setMakeList(response.data)
        setMake(response.data[0])
        setMakeLoading(false);
    }
  }
  
  async function fetchModels() {
    let unmounted = false;
    const response = await axios('http://localhost:25001/models');
      if(!unmounted) {
        setModelList(response.data)
        setModel(response.data[0])
        setModelLoading(false);
    }
    //console.log(response.data)
  }

  const updateMake = (value) => {
    let item = makeList.filter(item => item.id == value)[0]
    setMake(item)
  }

  const updateModel = (value) => {
    let item = modelList.filter(item => item.id == value)[0]
    setModel(item)
    setFilterQuery({a: "aaaaaaaaaaaaaaaaaaa", make: make.id, model: model.id})    
  }






  const [params, setParams] = useState({});
  const [page, setPage] = useState(0);
  const { games, loading, error, hasNextPage } = useFetchGames(params, page);

  console.log(games);



  const handleParamChange = (e) => {
    const param = e.target.name;
    const value = e.target.value;
    setPage(0);
    setParams((prevParams) => {
      return { ...prevParams, [param]: value };
    });
  };
  




  useEffect(() => {
    fetchListings();
    fetchMakes();
    fetchModels();
  }, []);

  return (
    <div className="container-fluid">
      <h2>Hello world!</h2>
      <SearchForm params={params} onParamChange={handleParamChange} />
      <Pagination page={page} setPage={setPage} hasNextPage={hasNextPage} />
      {loading && <Spinner animation="border" variant="primary" />}
      {listingList.map((listing) => {
        return <Listing key={listing.id} listing={listing} />;
      })}
      <Pagination page={page} setPage={setPage} hasNextPage={hasNextPage} />


      <div id="selector-box">
        <div className="row">
          <div className="col-3">
            <select className="form-select"
              disabled={makeLoading}
              value={make.id}
              onChange={(e) => updateMake(e.currentTarget.value)}
            >
              {makeList.map((item, index) => (
                <option key={item.id} value={item.id}>
                  {item.name}
                </option>
              ))}
            </select>
            <p>{JSON.stringify(make)}</p>
          </div>
          <div className="col-3">
            <select className="form-select"
              disabled={makeLoading}
              value={model.id}
              onChange={(e) => updateModel(e.currentTarget.value)}
            >
              {modelList.filter(item => item.make.id === make.id).map(item => (
                <option key={item.id} value={item.id}>
                  {item.name}           
                </option>
              ))}
            </select>
            <p>{JSON.stringify(model)}</p>
          </div>
        </div>
      </div>
      <div>
    </div>
    <div>
    </div>
      <p>{`Found ${listingList.length} listings`}</p>
      <p>{`Found ${makeList.length} makes`}</p>
      <p>{`Found ${modelList.length} models`}</p>
      <ul className="list-group">
        {listingList.map(item => (
          <li className="list-group-item" key={item.id}>
            <p>{`${item.title}`}</p>
            <p>{`${item.description}`}</p>
            <p>{`Price: $${item.price}`}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}
 
export default App;