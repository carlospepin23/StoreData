// import './ProductForm.css'; // Import the CSS file for styling
import createIcon from '../assets/media/add.png';
import trashIcon from '../assets/media/minus-circle.png';

function ProductForm({ deptIndex, prodIndex, product, handleProductChange, addProduct, removeProduct, isLastProduct, allDepartments }) {
  return (
    <div className="product-form">
      <div>
        <label>Product Name:</label>
        <input
          type="text"
          value={product.name}
          onChange={(e) =>
            handleProductChange(deptIndex, prodIndex, 'name', e.target.value)
          }
          required
        />
      </div>
      <div className="department-select">
          <label>Department:</label>
          <select
            value={product.department?.id || ''} // Ensure the value is the department id
            onChange={(e) =>
              handleProductChange(deptIndex, deptIndex, 'department', { id: e.target.value }) // Pass the department as an object with an id property
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
      <div>
        <label>Product Price:</label>
        <input
          type="number"
          value={product.price}
          onChange={(e) =>
            handleProductChange(deptIndex, prodIndex, 'price', e.target.value)
          }
          required
        />
      </div>
      <div>
        <label>Product Stock:</label>
        <input
          type="number"
          value={product.stock}
          onChange={(e) =>
            handleProductChange(deptIndex, prodIndex, 'stock', e.target.value)
          }
          required
        />
      </div>
      <div className="button-container">
        {prodIndex > 0 && (
          <button type="button" className="remove-product-button" onClick={() => removeProduct(deptIndex, prodIndex)}>
            <img src={trashIcon} alt="Remove Product" />
          </button>
        )}
        {isLastProduct && (
          <button type="button" className="add-product-button" onClick={() => addProduct(deptIndex)}>
            <img src={createIcon} alt="Add Product" />
          </button>
        )}
      </div>
    </div>
  );
}

export default ProductForm;