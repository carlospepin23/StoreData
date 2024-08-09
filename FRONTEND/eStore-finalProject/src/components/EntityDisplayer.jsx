import EntityCard from './EntityCard'; // Adjust the import path as needed
import './EntityDisplayer.css';
import { useState, useEffect } from 'react';

function EntityDisplayer({ name, entities, onEntityDeleted, onUpdateEmployee, onUpdateProduct, allDepartments }) {
  const [entityList, setEntityList] = useState(entities);

  useEffect(() => {
    setEntityList(entities);
  }, [entities]);

  // Log the allDepartments prop to check if it's being received correctly
  console.log('allDepartments:', allDepartments);

  const handleDelete = (id) => {
    setEntityList(entityList.filter(entity => entity.id !== id));
    onEntityDeleted(); // Call the prop function to reload the store
  };

  return (
    <div className="entity-displayer">
      <h1 className="header">{name}</h1>
      <div className="entity-grid">
        {entityList.map(entity => (
          <EntityCard 
            key={entity.id} 
            entity={entity} 
            onDelete={handleDelete} 
            onUpdateEmployee={onUpdateEmployee}
            onUpdateProduct={onUpdateProduct}
            allDepartments={allDepartments} // Pass the allDepartments prop
          />
        ))}
      </div>
      {/* Use allDepartments as needed */}
    </div>
  );
}

export default EntityDisplayer;