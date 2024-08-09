import React, { useState, useEffect } from 'react';

function EditStoreLocationModal({ isOpen, onClose, store, onUpdateStore }) {
  const [editedLocation, setEditedLocation] = useState(store.location);
  const [error, setError] = useState(null);

  useEffect(() => {
    setEditedLocation(store.location);
  }, [store]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const endpoint = `http://localhost:8080/api/stores/${store.id}/location`;
    const body = { location: editedLocation };

    try {
      const response = await fetch(endpoint, {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
      });

      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`Failed to update location: ${errorText}`);
      }

      const updatedStore = { ...store, location: editedLocation };
      onUpdateStore(updatedStore);
      onClose();
    } catch (error) {
      setError('Failed to update store location. Please try again.');
    }
  };

  if (!isOpen) return null;

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <button className="close-button" onClick={onClose}>&times;</button>
        <h2>Edit Store Location</h2>
        {error && <p className="error-message">{error}</p>}
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="storeLocation">Store Location</label>
            <input
              type="text"
              id="storeLocation"
              value={editedLocation}
              onChange={(e) => setEditedLocation(e.target.value)}
            />
          </div>
          <button type="submit" className="save-button">Save Changes</button>
          <button type="button" className="cancel-button" onClick={onClose}>Cancel</button>
        </form>
      </div>
    </div>
  );
}

export default EditStoreLocationModal;