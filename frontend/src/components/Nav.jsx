import React from 'react'
import { NavLink } from 'react-router-dom'
import resources from '../config/resources'

export default function Nav() {
  return (
    <nav className="nav">
      <h1>SpringZ Admin</h1>
      <ul>
        {resources.map(r => (
          <li key={r.name}><NavLink to={`/${r.name}`}>{r.title}</NavLink></li>
        ))}
      </ul>
    </nav>
  )
}
