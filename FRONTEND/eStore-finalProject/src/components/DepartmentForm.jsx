import EmployeeForm from './EmployeeForm';
import ProductForm from './ProductForm';

function DepartmentForm({ deptIndex, department, handleDepartmentChange, handleEmployeeChange, handleProductChange, addEmployee, removeEmployee, addProduct, removeProduct }) {
  return (
    <div>
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
        <EmployeeForm
          key={empIndex}
          deptIndex={deptIndex}
          empIndex={empIndex}
          employee={employee}
          handleEmployeeChange={handleEmployeeChange}
          addEmployee={addEmployee}
          removeEmployee={removeEmployee}
        />
      ))}
      {department.inventory.map((product, prodIndex) => (
        <ProductForm
          key={prodIndex}
          deptIndex={deptIndex}
          prodIndex={prodIndex}
          product={product}
          handleProductChange={handleProductChange}
          addProduct={addProduct}
          removeProduct={removeProduct}
        />
      ))}
    </div>
  );
}

export default DepartmentForm;