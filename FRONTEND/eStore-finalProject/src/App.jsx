import './App.css'
import { Routes, Route } from 'react-router-dom'
import HomePage from './pages/HomePage'
import StoresPage from './pages/StoresPage'
import AddStorePage from './pages/AddStorePage'
import StoreDetailPage from './pages/StoreDetailPage'

function App() {

  return (
    <Routes>
      <Route path="/" element={<HomePage/>} />
      <Route path="/stores" element={<StoresPage/>} />
      <Route path="/stores/:id" element={<StoreDetailPage />} /> {/* Dynamic route */}
      <Route path="/stores/add" element={<AddStorePage/>} />
    </Routes>
  )
}

export default App
