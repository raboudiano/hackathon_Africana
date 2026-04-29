import React, { useEffect, useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import api from '../api'

export default function ResourceForm({ resource }) {
  const { id } = useParams()
  const navigate = useNavigate()
  const [model, setModel] = useState({})

  useEffect(() => {
    if (id) {
      api.get(`/${resource.name}/${id}`).then(r => setModel(r.data)).catch(console.error)
    } else {
      setModel({})
    }
  }, [id, resource.name])

  const updateField = (key, value) => setModel(m => ({ ...m, [key]: value }))

  const submit = async (e) => {
    e.preventDefault()
    const payload = { ...model }
    // convert productIds from comma to array for orders
    if (resource.name === 'orders' && typeof payload.productIds === 'string') {
      payload.productIds = payload.productIds.split(',').map(s => Number(s.trim())).filter(Boolean)
    }
    if (id) {
      await api.put(`/${resource.name}/${id}`, payload)
    } else {
      await api.post(`/${resource.name}`, payload)
    }
    navigate(`/${resource.name}`)
  }

  return (
    <div className="resource-form">
      <h2>{resource.title} {id ? '— Edit' : '— New'}</h2>
      <form onSubmit={submit}>
        {resource.fields.map(f => (
          <div className="field" key={f.key}>
            <label>{f.label}</label>
            <input
              type={f.type}
              value={model[f.key] ?? ''}
              onChange={e => updateField(f.key, e.target.value)}
            />
          </div>
        ))}
        <div className="buttons">
          <button type="submit">Save</button>
          <button type="button" onClick={() => navigate(`/${resource.name}`)}>Cancel</button>
        </div>
      </form>
    </div>
  )
}
