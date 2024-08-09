import React, { useState } from 'react';
import './AddStoreModal.css'; // Create and import CSS for the modal
import DepartmentForm from './DepartmentForm'; // Import the new component

function AddStoreModal({ isOpen, onClose, onAddStore, getStores }) {
  const [storeName, setStoreName] = useState('');
  const [storeLocation, setStoreLocation] = useState('');
  const [departments, setDepartments] = useState([
    {
      name: '',
      employees: [{ type: '', name: '', email: '', sales: 0 }],
      inventory: [{ name: '', price: 0, stock: 0 }]
    }
  ]);

  const handleDepartmentChange = (index, field, value) => {
    const newDepartments = [...departments];
    newDepartments[index][field] = value;
    setDepartments(newDepartments);
  };

  const handleEmployeeChange = (deptIndex, empIndex, field, value) => {
    const newDepartments = [...departments];
    newDepartments[deptIndex].employees[empIndex][field] = value;
    setDepartments(newDepartments);
  };

  const handleProductChange = (deptIndex, prodIndex, field, value) => {
    const newDepartments = [...departments];
    newDepartments[deptIndex].inventory[prodIndex][field] = value;
    setDepartments(newDepartments);
  };

  const addEmployee = (deptIndex) => {
    const newDepartments = [...departments];
    newDepartments[deptIndex].employees.push({ type: '', name: '', email: '', sales: 0 });
    setDepartments(newDepartments);
  };

  const removeEmployee = (deptIndex, empIndex) => {
    const newDepartments = [...departments];
    newDepartments[deptIndex].employees.splice(empIndex, 1);
    setDepartments(newDepartments);
  };

  const addProduct = (deptIndex) => {
    const newDepartments = [...departments];
    newDepartments[deptIndex].inventory.push({ name: '', price: 0, stock: 0 });
    setDepartments(newDepartments);
  };

  const removeProduct = (deptIndex, prodIndex) => {
    const newDepartments = [...departments];
    newDepartments[deptIndex].inventory.splice(prodIndex, 1);
    setDepartments(newDepartments);
  };

  const addDepartment = () => {
    setDepartments([
      ...departments,
      {
        name: '',
        employees: [{ type: '', name: '', email: '', sales: 0 }],
        inventory: [{ name: '', price: 0, stock: 0 }]
      }
    ]);
  };

  const removeDepartment = (deptIndex) => {
    const newDepartments = [...departments];
    newDepartments.splice(deptIndex, 1);
    setDepartments(newDepartments);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newStore = {
      name: storeName,
      location: storeLocation,
      departments
    };
    console.log('Submitting new store:', newStore);
  
    try {
      const response = await fetch('http://localhost:8080/api/stores', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(newStore)
      });
  
      if (response.ok) {
        const text = await response.text();
        // const createdStore = text ? JSON.parse(text) : {};
        // console.log('New store created checker:', newStore);

        // onAddStore(createdStore);::::::before!!
        onAddStore(newStore); // Call onAddStore with the new store:::::after!!
        getStores(); // Call getStores after adding the new store
        onClose();
      } else {
        const errorText = await response.text();
        console.error('Failed to add store:', response.status, errorText);
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  if (!isOpen) return null;

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <button className="close-button" onClick={onClose}>&times;</button>
        <h2>Add New Store</h2>
        <form onSubmit={handleSubmit}>
          <div>
            <label>Store Name:</label>
            <input
              type="text"
              value={storeName}
              onChange={(e) => setStoreName(e.target.value)}
              required
            />
          </div>
          <div>
            <label>Store Location:</label>
            <input
              type="text"
              value={storeLocation}
              onChange={(e) => setStoreLocation(e.target.value)}
              required
            />
          </div>
          {departments.map((department, deptIndex) => (
            <div key={deptIndex} className="department-container">
              <DepartmentForm
                deptIndex={deptIndex}
                department={department}
                handleDepartmentChange={handleDepartmentChange}
                handleEmployeeChange={handleEmployeeChange}
                handleProductChange={handleProductChange}
                addEmployee={addEmployee}
                removeEmployee={removeEmployee}
                addProduct={addProduct}
                removeProduct={removeProduct}
                addDepartment={addDepartment}
                removeDepartment={removeDepartment}
              />
            </div>
          ))}
          <button type="submit" className="add-store-button">Add Store</button>
          <button type="button" className="cancel-button" onClick={onClose}>Cancel</button>
        </form>
      </div>
    </div>
  );
}

export default AddStoreModal;