import React, { useState } from 'react';
import DepartmentComponent from "./DepartmentComponent";

function MenuComponent({ departments, onOptionSelect,storeName }) {
  return (
    <div>
      <h1>{storeName}</h1>
      <div>
        {departments && departments.length > 0 ? (
          departments.map((dept, index) => (
            <DepartmentComponent 
              key={index} 
              name={dept.name} 
              onOptionSelect={onOptionSelect} 
            />
          ))
        ) : (
          <p>No departments available</p>
        )}
      </div>
    </div>
  );
}

export default MenuComponent;