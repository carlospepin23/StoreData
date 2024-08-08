import NavComponent from "../components/NavComponent";
import MenuComponent from "../components/MenuComponent";
import EntityDisplayer from "../components/EntityDisplayer";
import './StoreDetailPage.css';
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

function StoreDetailPage() {
  const { id } = useParams();
  const [store, setStore] = useState({});
  const [selectedDepartment, setSelectedDepartment] = useState(null);
  const [selectedOption, setSelectedOption] = useState(null);
  const [allDepartments, setAllDepartments] = useState([]);

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
          <div className="search-bar">SEARCH BAR</div>
          <div className="display-entity">
            {selectedDepartment && selectedOption && (
              <EntityDisplayer 
                name={selectedOption} 
                entities={selectedDepartment[selectedOption.toLowerCase()]} 
                onEntityDeleted={handleEntityDeleted} 
                onUpdateEmployee={handleUpdateEmployee} 
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