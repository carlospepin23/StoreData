import React, { useState, useEffect } from 'react';

function EditStoreNameModal({ isOpen, onClose, store, onUpdateStore }) {
  const [editedName, setEditedName] = useState(store.name);
  const [error, setError] = useState(null);

  useEffect(() => {
    setEditedName(store.name);
  }, [store]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    // setError(null); // Reset error state before making the request

    const endpoint = `http://localhost:8080/api/stores/${store.id}/name`;
    const body = { name: editedName };

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
        throw new Error(`Failed to update name: ${errorText}`);
      }

      const updatedStore = { ...store, name: editedName };
      onUpdateStore(updatedStore);
      onClose();
    } catch (error) {
      setError('Failed to update store name. Please try again.');
    }
  };

  if (!isOpen) return null;

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <button className="close-button" onClick={onClose}>&times;</button>
        <h2>Edit Store Name</h2>
        {error && <p className="error-message">{error}</p>}
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="storeName">Store Name</label>
            <input
              type="text"
              id="storeName"
              value={editedName}
              onChange={(e) => setEditedName(e.target.value)}
            />
          </div>
          <button type="submit" className="save-button">Save Changes</button>
          <button type="button" className="cancel-button" onClick={onClose}>Cancel</button>
        </form>
      </div>
    </div>
  );
}

export default EditStoreNameModal;