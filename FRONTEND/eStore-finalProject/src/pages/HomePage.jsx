import NavComponent from "../components/NavComponent";
import Gif from "../assets/media/shopping-cart.gif";
import './HomePage.css';

function HomePage() {
  return (
    <div className="homepage">
      <NavComponent />
      <br />
      <h1 className="homepage_title">Welcome to Store-Data!</h1>
      <p className="homepage_description typing-animation">
        Take control of your business with Store Data.
        Manage your store from home, 
        enjoy the freedom of entrepreneurship,
        and enhance your operations with our user-friendly platform. 
        Launch your e-store today and start your journey to success.
      </p>
      <img className="shopping-cart-gif" src={Gif} alt="Shopping Cart GIF" />
    </div>
  );
}

export default HomePage;