import './StoreCard.css'; // Import the CSS file

function StoreCard(props) {
  const { store } = props;

  const getDepartmentsQuantity = () => {
    // Check if store.departments is defined and is an array
    if (Array.isArray(store.departments)) {
      return store.departments.length;
    }
    // Return 0 or handle the error appropriately if store.departments is undefined or not an array
    return 0;
  };

  return (
    <div className="store-card">
      <div className="title">
        {/* <img src="" alt="" /> */}
        <h3>{store.name}</h3>
      </div>
      <div className="store-information">
        {/* <p>Store ID: {store.id}</p> */}
        <p>Location: {store.location}</p>
        <p>Departments: {getDepartmentsQuantity()}</p>
      </div>
    </div>
  );
}

export default StoreCard;