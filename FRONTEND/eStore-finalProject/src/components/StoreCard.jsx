
import './StoreCard.css'; // Import the CSS file

function StoreCard(props) {
  const { store } = props;

  const getDepartmentsQuantity = () => {
    return store.departments.length;
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