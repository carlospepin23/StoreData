// import React, { useState, useEffect } from 'react';

// function EditStoreModal({ isOpen, onClose, store, onUpdateStore }) {
//   const [editedStore, setEditedStore] = useState({ ...store });
//   const [error, setError] = useState(null);

//   useEffect(() => {
//     setEditedStore({ ...store });
//   }, [store]);

//   const handleStoreChange = (field, value) => {
//     setEditedStore({ ...editedStore, [field]: value });
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     const updateField = async (field, value) => {
//       const endpoint = `http://localhost:8080/api/stores/${store.id}/${field}`;
//       const body = { [field]: value };

//       try {
//         const response = await fetch(endpoint, {
//           method: 'PATCH',
//           headers: {
//             'Content-Type': 'application/json'
//           },
//           body: JSON.stringify(body)
//         });

//         console.log(`Response status for ${field}:`, response.status); // Log response status

//         if (!response.ok) {
//           const errorText = await response.text();
//           console.error(`Failed to update ${field}:`, response.status, errorText);
//           throw new Error(`Failed to update ${field}`);
//         }
//       } catch (error) {
//         console.error(`Error updating ${field}:`, error.message);
//         throw error;
//       }
//     };

//     try {
//       await updateField('name', editedStore.name);
//       await updateField('location', editedStore.location);

//       const updatedStore = { ...store, name: editedStore.name, location: editedStore.location };
//       console.log('Updated store:', updatedStore); // Log updated store data
//       onUpdateStore(updatedStore); // Call onUpdateStore with the updated store
//       onClose(); // Close the modal
//     } catch (error) {
//       setError('Failed to update store. Please try again.');
//     }
//   };

//   if (!isOpen) return null;

//   return (
//     <div className="modal-overlay">
//       <div className="modal-content">
//         <button className="close-button" onClick={onClose}>&times;</button>
//         <h2>Edit Store</h2>
//         {error && <p className="error-message">{error}</p>}
//         <form onSubmit={handleSubmit}>
//           <div className="form-group">
//             <label htmlFor="storeName">Store Name</label>
//             <input
//               type="text"
//               id="storeName"
//               value={editedStore.name}
//               onChange={(e) => handleStoreChange('name', e.target.value)}
//             />
//           </div>
//           <div className="form-group">
//             <label htmlFor="storeLocation">Store Location</label>
//             <input
//               type="text"
//               id="storeLocation"
//               value={editedStore.location}
//               onChange={(e) => handleStoreChange('location', e.target.value)}
//             />
//           </div>
//           <button type="submit" className="save-button">Save Changes</button>
//           <button type="button" className="cancel-button" onClick={onClose}>Cancel</button>
//         </form>
//       </div>
//     </div>
//   );
// }

// export default EditStoreModal;



// DESCONTINUADO