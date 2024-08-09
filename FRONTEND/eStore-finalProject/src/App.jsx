import './App.css'
import { Routes, Route } from 'react-router-dom'
import HomePage from './pages/HomePage'
import StoresPage from './pages/StoresPage'
import AddStorePage from './pages/AddStorePage'
import StoreDetailPage from './pages/StoreDetailPage'
import ContactUsPage from './pages/ContactUsPage'

function App() {

  return (
    <Routes>
      <Route path="/" element={<HomePage/>} />
      <Route path="/stores" element={<StoresPage/>} />
      <Route path="/stores/:id" element={<StoreDetailPage />} /> {/* Dynamic route */}
      <Route path="/stores/add" element={<AddStorePage/>} />
      <Route path="/contactUs" element={<ContactUsPage/>} />
    </Routes>
  )
}

export default App
