import React, { useState, useEffect } from 'react';
import NavComponent from "../components/NavComponent";
import AddStoreModal from "../components/AddStoreModal";
import { Link } from 'react-router-dom';
import './AddStorePage.css';

function AddStorePage() {
  const [stores, setStores] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false); // Set initial state to false
  const [isHeaderVisible, setIsHeaderVisible] = useState(false); // State to handle header visibility
  const [newStore, setNewStore] = useState(null); // State to store the newly added store
  // const history = useHistory();

  const getStores = async () => {
    const response = await fetch('http://localhost:8080/api/stores');
    const data = await response.json();
    console.log(data);
    setStores(data);
  };

  useEffect(() => {
    getStores();
    const timer = setTimeout(() => {
      setIsModalOpen(true); // Open modal after 2 seconds
    }, 1000);

    // Cleanup the timer if the component is unmounted
    return () => clearTimeout(timer);
  }, []);

  const handleAddStore = (newStore) => {
    setStores([...stores, newStore]);
    console.log('New Store:', newStore); // Console log the new store
    setNewStore(newStore); // Store the newly added store

  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setIsHeaderVisible(true); // Show header after modal closes
  };

  return (
    <div className="addstore-page">
      <NavComponent />
      <AddStoreModal
        isOpen={isModalOpen}
        onClose={handleCloseModal}
        onAddStore={handleAddStore}
        getStores={getStores}
      />
      {isHeaderVisible && (
        <>
          {newStore ? (
            <>
              <h1 className="header">e-Store Added Successfully!</h1>
              <Link to={"/stores"} className="forward-link">Go to Stored Data to see your new eStore!</Link>
            </>
          ) : (
            <h2 className="message">No store was added. Please try again</h2>
          )}
        </>
      )}
    </div>
  );
}

export default AddStorePage;