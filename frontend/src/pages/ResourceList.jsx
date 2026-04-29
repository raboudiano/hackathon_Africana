import React, { useEffect, useState } from 'react'
import { Link, Navigate, useNavigate } from 'react-router-dom'
import api from '../api'

export default function ResourceList({ resource }) {
  const tokenPresent = Boolean(localStorage.getItem('token'))
  const [items, setItems] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const navigate = useNavigate()

  useEffect(() => {
    if (!tokenPresent) {
      setLoading(false)
      return
    }

    setLoading(true)
    setError('')
    api.get(`/${resource.name}`)
      .then(r => setItems(r.data))
      .catch(err => {
        setError(err?.response?.data?.error || err?.message || 'Failed to load records')
      })
      .finally(() => setLoading(false))
  }, [resource.name, tokenPresent])

  const del = async (id) => {
    if (!confirm('Delete item?')) return
    setError('')
    try {
      await api.delete(`/${resource.name}/${id}`)
      setItems(items.filter(i => i.id !== id))
    } catch (err) {
      setError(err?.response?.data?.error || err?.message || 'Delete failed')
    }
  }

  if (!tokenPresent) {
    return <Navigate to="/auth" replace />
  }

  return (
    <div className="resource">
      <h2>{resource.title}</h2>
      <div className="actions">
        <Link to={`/${resource.name}/new`} className="btn">New</Link>
      </div>
      {error && <div className="alert-box">{error}</div>}
      {loading ? <p>Loading...</p> : (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              {resource.fields.map(f => <th key={f.key}>{f.label}</th>)}
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {items.map(item => (
              <tr key={item.id}>
                <td>{item.id}</td>
                {resource.fields.map(f => <td key={f.key}>{String(item[f.key] ?? '')}</td>)}
                <td>
                  <button onClick={() => navigate(`/${resource.name}/${item.id}`)}>Edit</button>
                  <button onClick={() => del(item.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  )
}
