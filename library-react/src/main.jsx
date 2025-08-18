import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import {BrowserRouter, Router} from "react-router-dom";

createRoot(document.getElementById('root')).render(
    /* Wrap App in BrowserRouter so React is aware navigation will be handled inside React */
  <StrictMode>
      <BrowserRouter>
            <App />
      </BrowserRouter>
  </StrictMode>,
)
