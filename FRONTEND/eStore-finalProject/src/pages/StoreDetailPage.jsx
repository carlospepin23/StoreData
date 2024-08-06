import NavComponent from "../components/NavComponent";
import './StoreDetailPage.css';

function StoreDetailPage() {
  return (
    // <div className="store-page">
    <div className="store-detail">
      <div className="navbar">
        <NavComponent />
      </div>
      <div className="leftside-menu"></div>
      <div className="search-bar"></div>
      <div className="display-entity"></div>
      {/* <div className="store-detail">
        
      </div> */}
    </div>
  );
}

export default StoreDetailPage;