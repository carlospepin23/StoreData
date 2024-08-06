import EntityCard from './EntityCard'; // Adjust the import path as needed
import './EntityDisplayer.css';

function EntityDisplayer({ name, entities }) {
  return (
    <div className="entity-displayer">
      <h1 className="header">{name}</h1>
      <div className="entity-grid">
        {entities.map(entity => (
          <EntityCard key={entity.id} entity={entity} />
        ))}
      </div>
    </div>
  );
}

export default EntityDisplayer;