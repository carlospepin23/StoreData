// import React, { useState, useEffect } from 'react'; // Import useState and useEffect
// import NavComponent from '../components/NavComponent';
// import StoreCard from '../components/StoreCard';
// import './StoresPage.css'; // Import the CSS file
// import { Link } from 'react-router-dom';
// import addIcon from '../assets/media/add.png';

// function StoresPage() {
//   const [stores, setStores] = useState([]);

//   const getStores = async () => {
//     const response = await fetch('http://localhost:8080/api/stores'); 
//     const data = await response.json();
//     console.log(data);
//     setStores(data);
//   };

//   useEffect(() => {
//     getStores();
//   }, []);

//   const storeList = stores.map((store) => {
//     return (
//       <li key={store.id}>
//         <Link to={`/stores/${store.id}`}>
//           <StoreCard store={store} />
//         </Link>
//       </li>
//     );
//   });

//   return (
//     <div className="stores-page">
//       <div className="navbar">
//         <NavComponent />
//       </div>
//       <div className="header">
//         <h1>Stores</h1>
//         <p>List of stores on the platform</p>
//       </div>
//       <div className="action-buttons">{/* Make it a component */}
//         {/* <Link to="/stores/add"> */}
//             <button className="add-button">
//               <img src={addIcon} alt="Add Button" />
//             </button>
//         {/* </Link>  */}
//         <button className="filter-button">Filter</button>
//       </div>
//       <div className="stores-list-container">
//         <ul className="stores-list no-dots">{storeList}</ul>
//       </div>
//     </div>
//   );
// }

// export default StoresPage;

import React, { useState, useEffect } from 'react';
import NavComponent from '../components/NavComponent';
import StoreCard from '../components/StoreCard';
import './StoresPage.css';
import { Link } from 'react-router-dom';
import addIcon from '../assets/media/plus.svg';
import filterIcon from '../assets/media/filter.svg';
import AddStoreModal from '../components/AddStoreModal'; // Import the modal component

function StoresPage() {
  const [stores, setStores] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const getStores = async () => {
    const response = await fetch('http://localhost:8080/api/stores');
    const data = await response.json();
    console.log(data);
    setStores(data);
  };

  useEffect(() => {
    getStores();
  }, []);

  const handleAddStore = (newStore) => {
    setStores([...stores, newStore]);
  };

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
      <div className="action-buttons">
        <button className="add-button" onClick={() => setIsModalOpen(true)}>
          <img src={addIcon} alt="Add Button" />
        </button>
        <button className="filter-button">
          <img src={filterIcon} alt="Filter Button" />
        </button>
      </div>
      <div className="stores-list-container">
        <ul className="stores-list no-dots">{storeList}</ul>
      </div>
      <AddStoreModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onAddStore={handleAddStore}
        getStores={getStores}
      />
    </div>
  );
}

export default StoresPage;