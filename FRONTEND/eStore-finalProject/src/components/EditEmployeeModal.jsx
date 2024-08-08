import React, { useState, useEffect } from 'react';
import './EditEmployeeModal.css'; // Import the CSS for the modal
import EmployeeForm from './EmployeeForm'; // Import the EmployeeForm component

function EditEmployeeModal({ isOpen, onClose, employee, onUpdateEmployee, allDepartments}) {
  const [editedEmployee, setEditedEmployee] = useState({ ...employee });
  const [error, setError] = useState(null);

  useEffect(() => {
    setEditedEmployee({ ...employee });
  }, [employee]);

  // Log the allDepartments prop to check if it's being received correctly
  console.log('allDepartments:', allDepartments);

  const handleEmployeeChange = (field, value) => {
    setEditedEmployee({ ...editedEmployee, [field]: value });
  };

  const mapEmployeeTypeToDTO = (type) => {
    switch (type) {
      case 'Seller':
        return 'SellerDTO';
      case 'Employee':
        return 'EmployeeDTO';
      default:
        return type;
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    // console.log('Form submitted'); // Log when form is submitted
    // console.log('Edited employee before validation:', editedEmployee); // Log edited employee state
  
    // console.log(editedEmployee.department);
  
    const employeeToUpdate = {
      ...editedEmployee,
      type: mapEmployeeTypeToDTO(editedEmployee.type),
      name: editedEmployee.name,
      department: editedEmployee.department
    };
  
    if (editedEmployee.type === 'Seller') {
      employeeToUpdate.email = editedEmployee.email;
      employeeToUpdate.sales = editedEmployee.sales;
    }
  
    console.log('Employee to update:', employeeToUpdate); // Log employee data to be updated
  
    const endpoint = editedEmployee.type === 'Seller' 
      ? `http://localhost:8080/api/sellers/${employeeToUpdate.id}`
      : `http://localhost:8080/api/employees/${employeeToUpdate.id}`;
  
    try {
      const response = await fetch(endpoint, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(employeeToUpdate)
      });
  
      console.log('Response status:', response.status); // Log response status
  
      if (response.ok) {
        const responseBody = await response.text();
        const updatedEmployee = responseBody ? JSON.parse(responseBody) : employeeToUpdate;
        console.log('Updated employee:', updatedEmployee); // Log updated employee data
        onUpdateEmployee(updatedEmployee); // Call onUpdateEmployee with the updated employee
        // getAllDepartments(); // Call the getAllDepartments function to update the departments
        onClose();
      } else {
        const errorText = await response.text();
        console.error('Failed to update employee:', response.status, errorText);
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
        <h2>Edit Employee</h2>
        {error && <p className="error-message">{error}</p>}
        <form onSubmit={handleSubmit}>
          <EmployeeForm
            deptIndex={0}
            empIndex={0}
            employee={editedEmployee}
            handleEmployeeChange={(index, empIndex, field, value) => handleEmployeeChange(field, value)}
            addEmployee={() => {}}
            removeEmployee={() => {}}
            isLastEmployee={false}
            allDepartments={allDepartments} // Pass the allDepartments prop to EmployeeForm
          />
          <button type="submit" className="save-button">Save Changes</button>
          <button type="button" className="cancel-button" onClick={onClose}>Cancel</button>
        </form>
      </div>
    </div>
  );
}

export default EditEmployeeModal;