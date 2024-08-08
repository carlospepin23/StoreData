import './EmployeeForm.css'; // Import the CSS file for styling
import createIcon from '../assets/media/add.png';
import trashIcon from '../assets/media/minus-circle.png';
import React from 'react';

function EmployeeForm({ deptIndex, empIndex, employee, handleEmployeeChange, addEmployee, removeEmployee, isLastEmployee, allDepartments }) {
  return (
    <div className="employee-form">
      <form>
        
        <div>
          <label>Employee Name:</label>
          <input
            type="text"
            value={employee.name}
            onChange={(e) =>
              handleEmployeeChange(deptIndex, empIndex, 'name', e.target.value)
            }
            required
          />
        </div>
        <div className="department-select">
          <label>Department:</label>
          <select
            value={employee.department?.id || ''} // Ensure the value is the department id
            onChange={(e) =>
              handleEmployeeChange(deptIndex, empIndex, 'department', { id: e.target.value }) // Pass the department as an object with an id property
            }
            required
          >
            <option value="">Select Department</option>
            {allDepartments.map((department, index) => (
              <option key={index} value={department.id}>
                {department.name}
              </option>
            ))}
          </select>
        </div>
        {employee.type === 'Seller' && (
          <>
            <div>
              <label>Employee Email:</label>
              <input
                type="email"
                value={employee.email}
                onChange={(e) =>
                  handleEmployeeChange(deptIndex, empIndex, 'email', e.target.value)
                }
              />
            </div>
            <div>
              <label>Employee Sales:</label>
              <input
                type="number"
                value={employee.sales}
                onChange={(e) =>
                  handleEmployeeChange(deptIndex, empIndex, 'sales', e.target.value)
                }
              />
            </div>
          </>
        )}
        <div className="button-container">
          {empIndex > 0 && (
            <button type="button" className="remove-employee-button" onClick={() => removeEmployee(deptIndex, empIndex)}>
              <img src={trashIcon} alt="Remove Employee" />
            </button>
          )}
          {isLastEmployee && (
            <button type="button" className="add-employee-button" onClick={() => addEmployee(deptIndex)}>
              <img src={createIcon} alt="Add Employee" />
            </button>
          )}
        </div>
      </form>
    </div>
  );
}

export default EmployeeForm;