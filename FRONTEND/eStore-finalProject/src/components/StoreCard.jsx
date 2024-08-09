import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './StoreCard.css';
import trashIcon from "../assets/media/trash.svg";
import editNameIcon from "../assets/media/pencil.svg";
import editLocationIcon from "../assets/media/marker.svg";
import EditStoreNameModal from './EditStoreNameModal';
import EditStoreLocationModal from './EditStoreLocationModal';

function StoreCard(props) {
  const { store, onDelete, onUpdateStore } = props;
  const [isEditNameModalOpen, setIsEditNameModalOpen] = useState(false);
  const [isEditLocationModalOpen, setIsEditLocationModalOpen] = useState(false);
  const [currentStore, setCurrentStore] = useState(store);

  const getDepartmentsQuantity = () => {
    if (Array.isArray(currentStore.departments)) {
      return currentStore.departments.length;
    }
    return 0;
  };

  const deleteStore = async () => {
    try {
      const url = `http://localhost:8080/api/stores/${currentStore.id}`;
      const response = await fetch(url, {
        method: "DELETE",
      });
      if (response.ok) {
        console.log("Store deleted successfully");
        onDelete(currentStore.id);
      }
    } catch (e) {
      console.log("Error deleting store", e);
    }
  };

  const editStoreName = () => {
    setIsEditNameModalOpen(true);
  };

  const editStoreLocation = () => {
    setIsEditLocationModalOpen(true);
  };

  const handleUpdateStore = (updatedStore) => {
    setCurrentStore(updatedStore);
    onUpdateStore(updatedStore);
  };

  return (
    <>
      <div className="store-card">
        <div className="store-card-info">
          <Link to={`/stores/${currentStore.id}`} className="store-card-link">
            <h3>{currentStore.name}</h3>
            <p>Location: {currentStore.location}</p>
            <p>Departments: {getDepartmentsQuantity()}</p> 
          </Link>
        </div>
        <div className="store-card-footer">
          <button onClick={deleteStore}>
            <img src={trashIcon} alt="Trash Button" />
          </button>
          <button onClick={editStoreName}>
            <img src={editNameIcon} alt="Edit Name Button" />
          </button>
          <button onClick={editStoreLocation}>
            <img src={editLocationIcon} alt="Edit Location Button" />
          </button>
        </div>
      </div>
      <EditStoreNameModal
        isOpen={isEditNameModalOpen}
        onClose={() => setIsEditNameModalOpen(false)}
        store={currentStore}
        onUpdateStore={handleUpdateStore}
      />
      <EditStoreLocationModal
        isOpen={isEditLocationModalOpen}
        onClose={() => setIsEditLocationModalOpen(false)}
        store={currentStore}
        onUpdateStore={handleUpdateStore}
      />
    </>
  );
}

export default StoreCard;