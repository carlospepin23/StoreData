
import './EntityCard.css';

function EntityCard({ entity }) {
  return (
    <div className="entity-card">
      <h2>{entity.name}</h2>
      
      {entity.price && <p>Price: ${entity.price}</p>}
      {entity.stock && <p>Stock: {entity.stock}</p>}
      {entity.email && <p>Seller <br /> Email: {entity.email}</p>}
      {/* later i can add a badge saying that its a seller  */}
      {entity.sales && <p>Sales: {entity.sales}</p>}
    </div>
  );
}

export default EntityCard;