import NavComponent from "../components/NavComponent";
import MenuComponent from "../components/MenuComponent";
import EntityDisplayer from "../components/EntityDisplayer";
import './StoreDetailPage.css';
import { useEffect, useState, useRef } from "react";
import { useParams } from "react-router-dom";
import addIcon from '../assets/media/plus.svg';
import filterIcon from '../assets/media/filter.svg';
import searchIcon from '../assets/media/search.svg'; // Import the magnifier icon

function StoreDetailPage() {
  const { id } = useParams();
  const [store, setStore] = useState({});
  const [selectedDepartment, setSelectedDepartment] = useState(null);
  const [selectedOption, setSelectedOption] = useState(null);
  const [allDepartments, setAllDepartments] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [modalType, setModalType] = useState(null); // State to manage modal type
  const searchInputRef = useRef(null);

  const getAllDepartments = async () => {
    const response = await fetch('http://localhost:8080/api/departments');
    const data = await response.json();
    console.log("All Departments:", data);
    setAllDepartments(data);
  };

  const getStore = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/stores/${id}`); 
      const data = await response.json();
      console.log("Store Data:", data);
      setStore(data);
    } catch (error) {
      console.log("Error fetching store", error);
    }
  };

  const getDepartments = (store) => {
    return store?.departments || [];
  };

  useEffect(() => {
    console.log("Component mounted or id changed:", id);
    getAllDepartments();
    getStore();
    return () => {
      console.log("Component unmounted or id changed:", id);
    };
  }, [id]);

  const departments = getDepartments(store);

  const handleOptionSelect = (departmentName, option) => {
    console.log("Option selected:", departmentName, option);
    const department = departments.find(dept => dept.name === departmentName);
    setSelectedDepartment(department);
    setSelectedOption(option);
    setModalType(option.toLowerCase()); // Set the modal type based on the selected option
  };

  const handleUpdateEmployee = async (updatedEmployee) => {
    try {
      await fetch(`http://localhost:8080/api/employees/${updatedEmployee.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedEmployee),
      });
  
      await getStore();
  
      // Ensure the selected department and option are correctly updated
      const updatedDepartment = store.departments.find(dept => dept.id === updatedEmployee.department.id);
      setSelectedDepartment(updatedDepartment);
      setSelectedOption(updatedEmployee.department.name);
  
    } catch (error) {
      console.log("Error updating employee", error);
    }
  };

  const handleUpdateProduct = async (updatedProduct) => {
    try {
      await fetch(`http://localhost:8080/api/products/${updatedProduct.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedProduct),
      });
  
      await getStore();
  
      // Ensure the selected department and option are correctly updated
      const updatedDepartment = store.departments.find(dept => dept.id === updatedProduct.department.id);
      setSelectedDepartment(updatedDepartment);
      setSelectedOption(updatedProduct.department.name);
  
    } catch (error) {
      console.log("Error updating product", error);
    }
  };

  const handleEntityDeleted = async () => {
    try {
      await getStore();
  
      const updatedDepartment = store.departments.find(dept => dept.id === selectedDepartment.id);
      setSelectedDepartment(updatedDepartment);
      setSelectedOption(selectedOption);
  
    } catch (error) {
      console.log("Error deleting entity", error);
    }
  };

  const handleSearchChange = (e) => {
    setSearchQuery(e.target.value);
  };

  const handleFilterClick = () => {
    // Implement filter logic here
  };

  const handleAddButtonClick = () => {
    if (modalType === 'employees') {
      // Open StoreDetailEmployeeModal
      setIsModalOpen(true);
    } else if (modalType === 'inventory') {
      // Open StoreDetailProductModal
      setIsModalOpen(true);
    }
  };

  return (
    <div className="store-detail">
      <div className="navbar">
        <NavComponent />
      </div>
      <div className="content">
        <div className="leftside-menu">
          <MenuComponent 
            departments={departments}
            onOptionSelect={handleOptionSelect}
            storeName={store.name} />
        </div>
        <div className="rightside-content">
          {selectedOption && (selectedOption.toLowerCase() === 'employees' || selectedOption.toLowerCase() === 'inventory') && (
            <div className="action-buttons">
              <div className="search-container">
                <input
                  type="text"
                  placeholder="Search stores..."
                  value={searchQuery}
                  onChange={handleSearchChange}
                  className="search-bar"
                  ref={searchInputRef} // Attach the ref to the search input
                />
                <img
                  src={searchIcon}
                  alt="Search Icon"
                  className="search-icon"
                  onClick={() => searchInputRef.current.focus()} // Focus the search input when the icon is clicked
                />
              </div>
              <button className="add-button" onClick={handleAddButtonClick}>
                <img src={addIcon} alt="Add Button" />
              </button>
              <button className="filter-button" onClick={handleFilterClick}>
                <img src={filterIcon} alt="Filter Button" />
              </button>
            </div>
          )}
          <div className="display-entity">
            {selectedDepartment && selectedOption && (
              <EntityDisplayer 
                name={selectedOption} 
                entities={selectedDepartment[selectedOption.toLowerCase()]} 
                onEntityDeleted={handleEntityDeleted} 
                onUpdateEmployee={handleUpdateEmployee} 
                onUpdateProduct={handleUpdateProduct} 
                allDepartments={allDepartments} 
              />
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default StoreDetailPage;