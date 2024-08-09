import React, { useState, useEffect } from 'react';
// import './EditProductModal.css'; // Import the CSS for the modal
import ProductForm from './ProductForm'; // Import the ProductForm component

function EditProductModal({ isOpen, onClose, product, onUpdateProduct, allDepartments }) {
  const [editedProduct, setEditedProduct] = useState({ ...product });
  const [error, setError] = useState(null);

  useEffect(() => {
    setEditedProduct({ ...product });
  }, [product]);

  const handleProductChange = (field, value) => {
    setEditedProduct({ ...editedProduct, [field]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const productToUpdate = {
      ...editedProduct,
      name: editedProduct.name,
      price: editedProduct.price,
      stock: editedProduct.stock
    };

    console.log('Product to update:', productToUpdate); // Log product data to be updated

    const endpoint = `http://localhost:8080/api/products/${productToUpdate.id}`;

    try {
      const response = await fetch(endpoint, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(productToUpdate)
      });

      console.log('Response status:', response.status); // Log response status

      if (response.ok) {
        const responseBody = await response.text();
        const updatedProduct = responseBody ? JSON.parse(responseBody) : productToUpdate;
        console.log('Updated product:', updatedProduct); // Log updated product data
        onUpdateProduct(updatedProduct); // Call onUpdateProduct with the updated product
        onClose();
      } else {
        const errorText = await response.text();
        console.error('Failed to update product:', response.status, errorText);
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
        <h2>Edit Product</h2>
        {error && <p className="error-message">{error}</p>}
        <form onSubmit={handleSubmit}>
          <ProductForm
            deptIndex={0}
            prodIndex={0}
            product={editedProduct}
            handleProductChange={(index, prodIndex, field, value) => handleProductChange(field, value)}
            addProduct={() => {}}
            removeProduct={() => {}}
            isLastProduct={false}
            allDepartments={allDepartments} // Pass allDepartments to ProductForm
          />
          <button type="submit" className="save-button">Save Changes</button>
          <button type="button" className="cancel-button" onClick={onClose}>Cancel</button>
        </form>
      </div>
    </div>
  );
}

export default EditProductModal;