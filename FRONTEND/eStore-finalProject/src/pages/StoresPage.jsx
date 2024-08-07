import React, { useState, useEffect } from 'react'; // Import useState and useEffect
import NavComponent from '../components/NavComponent';
import StoreCard from '../components/StoreCard';
import './StoresPage.css'; // Import the CSS file
import { Link } from 'react-router-dom';

function StoresPage() {
  const [stores, setStores] = useState([]);

  const getStores = async () => {
    const response = await fetch('http://localhost:8080/api/stores'); 
    const data = await response.json();
    console.log(data);
    setStores(data);
  };

  useEffect(() => {
    getStores();
  }, []);

  const storeList = stores.map((store) => {
    return (
      <li key={store.id}>
        <Link to={`/stores/${store.id}`}>
          <StoreCard store={store} />
        </Link>
      </li>
    );
  });

  return (
    <div className="stores-page">
      <div className="navbar">
        <NavComponent />
      </div>
      <div className="header">
        <h1>Stores</h1>
        <p>List of stores on the platform</p>
      </div>
      <div className="action-buttons">{/* Make it a component */}
        <Link to="/stores/add">
            <button className="add-button">+</button>
        </Link> 
        <button className="filter-button">Filter</button>
      </div>
      <div className="stores-list-container">
        <ul className="stores-list no-dots">{storeList}</ul>
      </div>
    </div>
  );
}

export default StoresPage;