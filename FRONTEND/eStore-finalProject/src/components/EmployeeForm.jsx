function EmployeeForm({ deptIndex, empIndex, employee, handleEmployeeChange, addEmployee, removeEmployee }) {
    return (
      <div>
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
        <button type="button" onClick={() => addEmployee(deptIndex)}>Add Employee</button>
        {empIndex > 0 && (
          <button type="button" onClick={() => removeEmployee(deptIndex, empIndex)}>Remove Employee</button>
        )}
      </div>
    );
  }
  
  export default EmployeeForm;