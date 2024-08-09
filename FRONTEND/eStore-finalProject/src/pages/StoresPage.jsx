import React, { useState, useEffect, useRef } from 'react';
import NavComponent from '../components/NavComponent';
import StoreCard from '../components/StoreCard';
import './StoresPage.css';
import addIcon from '../assets/media/plus.svg';
import filterIcon from '../assets/media/filter.svg';
import searchIcon from '../assets/media/search.svg'; // Import the magnifier icon
import AddStoreModal from '../components/AddStoreModal'; // Import the modal component

function StoresPage() {
  const [stores, setStores] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [sortOption, setSortOption] = useState(0); // 0: normal, 1: alphabetical, 2: by department quantity
  const [searchQuery, setSearchQuery] = useState(''); // State for search query
  const searchInputRef = useRef(null); // Create a ref for the search input

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

  const handleDeleteStore = (storeId) => {
    setStores(stores.filter(store => store.id !== storeId));
  };

  const sortStores = (stores) => {
    switch (sortOption) {
      case 1:
        return [...stores].sort((a, b) => a.name.localeCompare(b.name));
      case 2:
        return [...stores].sort((a, b) => (a.departments?.length || 0) - (b.departments?.length || 0));
      default:
        return stores;
    }
  };

  const handleFilterClick = () => {
    setSortOption((prevOption) => (prevOption + 1) % 3);
  };

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const filteredStores = stores.filter(store =>
    store.name.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const sortedStores = sortStores(filteredStores);

  const storeList = sortedStores.map((store) => {
    return (
      <li key={store.id}>
        <StoreCard store={store} onDelete={handleDeleteStore} />
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
        <div className="search-container">
  <input
    type="text"
    placeholder="Search stores..."
    value={searchQuery}
    onChange={handleSearchChange}
    className="search-bar"
    ref={searchInputRef} // Attach the ref to the search input
  />
  <img
    src={searchIcon}
    alt="Search Icon"
    className="search-icon"
    onClick={() => searchInputRef.current.focus()} // Focus the search input when the icon is clicked
  />
</div>
        <button className="add-button" onClick={() => setIsModalOpen(true)}>
          <img src={addIcon} alt="Add Button" />
        </button>
        <button className="filter-button" onClick={handleFilterClick}>
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