import NavComponent from "../components/NavComponent";
import MenuComponent from "../components/MenuComponent";
import EntityDisplayer from "../components/EntityDisplayer";
import './StoreDetailPage.css';
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

function StoreDetailPage() {
  const { id } = useParams();
  const [store, setStore] = useState([]);
  const [selectedDepartment, setSelectedDepartment] = useState(null);
  const [selectedOption, setSelectedOption] = useState(null);

  const getStore = async () => {
    const response = await fetch(`http://localhost:8080/api/stores/${id}`); 
    const data = await response.json();
    console.log(data);
    setStore(data);
  };

  const getDepartments = (store) => {
    return store?.departments || [];
  };

  useEffect(() => {
    getStore();
  }, []);

  const departments = getDepartments(store);

  const handleOptionSelect = (departmentName, option) => {
    const department = departments.find(dept => dept.name === departmentName);
    setSelectedDepartment(department);
    setSelectedOption(option);
  };

  return (
    <div className="store-detail">
      <div className="navbar">
        <NavComponent />
      </div>
      <div className="content">
        <div className="leftside-menu">
          <MenuComponent departments={departments} onOptionSelect={handleOptionSelect} />
        </div>
        <div className="rightside-content">
          <div className="search-bar">SEARCH BAR</div>
          <div className="display-entity">
            {selectedDepartment && selectedOption && (
              <EntityDisplayer 
                name={selectedOption} 
                entities={selectedDepartment[selectedOption.toLowerCase()]} 
              />
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default StoreDetailPage;