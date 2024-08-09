// import './ProductForm.css';
import './StoreProductForm.css';
import createIcon from '../assets/media/add.png';
import trashIcon from '../assets/media/minus-circle.png';

function StoreProductForm({ deptIndex, prodIndex, product, handleProductChange, addProduct, removeProduct, isLastProduct }) {
  return (
    <div className="product-form">
      <h4>Product {prodIndex + 1}</h4>
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

export default StoreProductForm;