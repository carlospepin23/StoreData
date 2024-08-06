import React, { useState } from 'react';
import employeesIcon from "../assets/media/users-alt.svg";
import inventoryIcon from "../assets/media/shopping-cart.svg";
import './DepartmentComponent.css';

function DepartmentComponent({ name, onOptionSelect }) {
  const [showOptions, setShowOptions] = useState(false);

  const toggleOptions = () => {
    setShowOptions(!showOptions);
  };

  return (
    <div className="department">
      <button className="department-button" onClick={toggleOptions}>
        {name}
      </button>
      {showOptions && (
        <div className="options">
          <button className="option-button" onClick={() => onOptionSelect(name, "Employees")}>
            <img src={employeesIcon} alt="employees-icon" className="option-icon" />{"Employees"}
          </button>
          <button className="option-button" onClick={() => onOptionSelect(name, "Inventory")}>
            <img src={inventoryIcon} alt="inventory-icon" className="option-icon" />{"Inventory"}
          </button>
        </div>
      )}
    </div>
  );
}

export default DepartmentComponent;