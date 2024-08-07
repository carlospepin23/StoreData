import './NavComponent.css'; // Import the CSS file for styling
import logoIcon from '../assets/media/website-logo.png'; // Adjust the path as needed
// import burgerIcon from '../assets/media/menu-burger.png'; 
import { Link } from 'react-router-dom'; // Import Link from react-router-dom

function NavComponent() {
  return (
    <nav className="nav-component_navbar">
      <div className="nav-component_left">
        <Link to="/"> 
          <img src={logoIcon} alt="Website Icon" className="nav-component_icon" /> 
        </Link>
        {/* <span className="nav-component_title">Store-Data</span> */}
      </div>
      
      <div className="nav-component_center">
        <ul className="nav-component_links">
        <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/products">Create your e-Store!</Link>
          </li>
          <li>
            <Link to="/stores">Stored Data</Link>
          </li>
          <li>
            <Link to="/categories">About Us</Link>
          </li>
        </ul>
      </div>
      
      {/* <div className="nav-component_right">
        <img src={burgerIcon} alt="Burger Icon" className="nav-component_burger-icon" />
      </div> */}
    </nav>
  );
}

export default NavComponent;