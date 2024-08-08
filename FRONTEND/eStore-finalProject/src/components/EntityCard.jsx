import React, { useState } from 'react';
import './EntityCard.css';
import trashIcon from "../assets/media/trash.svg";
import editIcon from "../assets/media/edit.svg";
import EditEmployeeModal from './EditEmployeeModal'; // Import the new component

function EntityCard({ entity, onDelete, onUpdateEmployee, allDepartments}) {
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);

  // Log the allDepartments prop to check if it's being received correctly
  console.log('allDepartments:', allDepartments);

  const deleteEntity = async () => {
    try {
      const url = entity.price !== undefined 
      ? `http://localhost:8080/api/products/${entity.id}` 
      : `http://localhost:8080/api/employees/${entity.id}`;
      
      const response = await fetch(url, {
        method: "DELETE",
      });
  
      if (response.ok) {
        console.log("Entity deleted successfully");
        onDelete(entity.id); // Call the callback function to update the state
      }
    } catch (e) {
      console.log("Error deleting entity", e);
    }
  };

  const editEntity = () => {
    setIsEditModalOpen(true);
  };

  return (
    <div className="entity-card">
      <div className="entity-card-main">
        <div className="entity-card-image">
          <img src={entity.image} alt={entity.name} />
        </div>
        <div className="entity-card-info">
        <h2>{entity.name}</h2>
        {entity.price && <p>Price: ${entity.price}</p>}
        {entity.stock && <p>Stock: {entity.stock}</p>}
        {entity.email && <p>Seller <br /> Email: {entity.email}</p>}
        {entity.sales && <p>Sales: {entity.sales}</p>}
        </div>
      </div>
      <div className="entity-card-footer">
        <button onClick={deleteEntity}>
          <img src={trashIcon} alt="Trash Button" />
        </button>
        <button onClick={editEntity}>
          <img src={editIcon} alt="Edit Button" />
        </button>
      </div>
      <EditEmployeeModal
        isOpen={isEditModalOpen}
        onClose={() => setIsEditModalOpen(false)}
        employee={entity}
        onUpdateEmployee={onUpdateEmployee} // Pass the onUpdateEmployee function
        allDepartments={allDepartments} // Pass the allDepartments prop
        // getAllDepartments={getAllDepartments} // Pass getAllDepartments here
      />
    </div>
  );
}

export default EntityCard;