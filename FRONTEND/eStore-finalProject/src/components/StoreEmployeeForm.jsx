// import './EmployeeForm.css'; // Import the CSS file for styling
import './StoreEmployeeForm.css';
import createIcon from '../assets/media/add.png';
import trashIcon from '../assets/media/minus-circle.png';

function StoreEmployeeForm({ deptIndex, empIndex, employee, handleEmployeeChange, addEmployee, removeEmployee, isLastEmployee }) {
  return (
    <div className="employee-form">
      <h4>Employee {empIndex + 1}</h4>
      <div>
        <label>Employee Type:</label>
        <select
          value={employee.type}
          onChange={(e) =>
            handleEmployeeChange(deptIndex, empIndex, 'type', e.target.value)
          }
          required
        >
          <option value="">Select Type</option>
          <option value="Employee">Employee</option>
          <option value="Seller">Seller</option>
        </select>
      </div>
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
    </div>
  );
}

export default StoreEmployeeForm;