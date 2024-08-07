import EntityCard from './EntityCard'; // Adjust the import path as needed
import './EntityDisplayer.css';
import { useState, useEffect } from 'react';

function EntityDisplayer({ name, entities, onEntityDeleted }) {
  const [entityList, setEntityList] = useState(entities);

  useEffect(() => {
    setEntityList(entities);
  }, [entities]);

  const handleDelete = (id) => {
    setEntityList(entityList.filter(entity => entity.id !== id));
    onEntityDeleted(); // Call the prop function to reload the store
  };

  return (
    <div className="entity-displayer">
      <h1 className="header">{name}</h1>
      <div className="entity-grid">
        {entityList.map(entity => (
          <EntityCard key={entity.id} entity={entity} onDelete={handleDelete} />
        ))}
      </div>
    </div>
  );
}

export default EntityDisplayer;