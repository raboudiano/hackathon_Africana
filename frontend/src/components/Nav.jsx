import React from 'react'
import { NavLink, Link } from 'react-router-dom'
import resources from '../config/resources'

export default function Nav() {
  return (
    <nav className="nav">
      <h1><Link to="/" className="brand-link">SpringZ Admin</Link></h1>
      <div className="nav-home"><Link to="/">Dashboard</Link></div>
      <div className="nav-home"><Link to="/auth">Sign in / Sign up</Link></div>
      <ul>
        {resources.map(r => (
          <li key={r.name}><NavLink to={`/${r.name}`}>{r.title}</NavLink></li>
        ))}
      </ul>
      <div className="nav-home nav-logout">
        <button
          type="button"
          className="nav-button"
          onClick={() => {
            localStorage.removeItem('token')
            window.location.href = '/auth'
          }}
        >
          Logout
        </button>
      </div>
    </nav>
  )
}
