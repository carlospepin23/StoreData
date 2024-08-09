import StoreEmployeeForm from './StoreEmployeeForm';
import StoreProductForm from './StoreProductForm';
import './DepartmentForm.css';
import createIcon from '../assets/media/add.png';
import trashIcon from '../assets/media/minus-circle.png';

function DepartmentForm({ deptIndex, department, allDepartments, handleDepartmentChange, handleEmployeeChange, handleProductChange, addEmployee, removeEmployee, addProduct, removeProduct, addDepartment, removeDepartment }) {
  return (
    <div >
      <h3>Department {deptIndex + 1}</h3>
      <div>
        <label>Department Name:</label>
        <input
          type="text"
          value={department.name}
          onChange={(e) =>
            handleDepartmentChange(deptIndex, 'name', e.target.value)
          }
          required
        />
      </div>
      {department.employees.map((employee, empIndex) => (
        <StoreEmployeeForm
        key={empIndex}
        deptIndex={deptIndex}
        empIndex={empIndex}
        employee={employee}
        handleEmployeeChange={handleEmployeeChange}
        addEmployee={addEmployee}
        removeEmployee={removeEmployee}
        isLastEmployee={empIndex === department.employees.length - 1}
        allDepartments={allDepartments}
      />
      ))}
      {department.inventory.map((product, prodIndex) => (
        <StoreProductForm
          key={prodIndex}
          deptIndex={deptIndex}
          prodIndex={prodIndex}
          product={product}
          handleProductChange={handleProductChange}
          addProduct={addProduct}
          removeProduct={removeProduct}
          isLastProduct={prodIndex === department.inventory.length - 1}
          allDepartments={allDepartments}
        />
      ))}
      <div className="department-buttons">
        {deptIndex > 0 && (
          <button type="button" onClick={() => removeDepartment(deptIndex)}>
            <img src={trashIcon} alt="Remove Department" />
          </button>
        )}
        <button type="button" onClick={addDepartment}>
          <img src={createIcon} alt="Add Department" />
        </button>
      </div>
    </div>
  );
}

export default DepartmentForm;