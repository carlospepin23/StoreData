function ProductForm({ deptIndex, prodIndex, product, handleProductChange, addProduct, removeProduct }) {
  return (
    <div>
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
      <button type="button" onClick={() => addProduct(deptIndex)}>Add Product</button>
      {prodIndex > 0 && (
        <button type="button" onClick={() => removeProduct(deptIndex, prodIndex)}>Remove Product</button>
      )}
    </div>
  );
}

export default ProductForm;