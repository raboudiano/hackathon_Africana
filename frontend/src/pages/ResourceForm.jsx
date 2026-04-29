import React, { useEffect, useState } from 'react'
import { Navigate, useParams, useNavigate } from 'react-router-dom'
import api from '../api'

export default function ResourceForm({ resource }) {
  const tokenPresent = Boolean(localStorage.getItem('token'))
  const { id } = useParams()
  const navigate = useNavigate()
  const [model, setModel] = useState({})
  const [error, setError] = useState('')

  useEffect(() => {
    if (!tokenPresent) {
      return
    }

    if (id) {
      setError('')
      api.get(`/${resource.name}/${id}`)
        .then(r => setModel(r.data))
        .catch(err => setError(err?.response?.data?.error || err?.message || 'Failed to load record'))
    } else {
      setModel({})
    }
  }, [id, resource.name, tokenPresent])

  const updateField = (key, value) => setModel(m => ({ ...m, [key]: value }))

  const formFields = resource.formFields ?? resource.fields

  const toPayload = (values) => {
    const payload = { ...values }

    for (const field of formFields) {
      if (field.type === 'number') {
        const rawValue = payload[field.key]
        if (rawValue === '' || rawValue === null || rawValue === undefined) {
          payload[field.key] = null
        } else {
          payload[field.key] = Number(rawValue)
        }
      }

      if (field.type === 'date' && payload[field.key] === '') {
        payload[field.key] = null
      }
    }

    if (resource.name === 'orders' && typeof payload.productIds === 'string') {
      payload.productIds = payload.productIds
        .split(',')
        .map(value => Number(value.trim()))
        .filter(value => Number.isFinite(value) && value > 0)
    }

    return payload
  }

  const submit = async (e) => {
    e.preventDefault()
    const payload = toPayload(model)
    setError('')

    try {
      if (id) {
        await api.put(`/${resource.name}/${id}`, payload)
      } else {
        await api.post(`/${resource.name}`, payload)
      }
      navigate(`/${resource.name}`)
    } catch (err) {
      setError(err?.response?.data?.message || err?.response?.data?.error || err?.message || 'Save failed')
    }
  }

  if (!tokenPresent) {
    return <Navigate to="/auth" replace />
  }

  return (
    <div className="resource-form">
      <h2>{resource.title} {id ? '— Edit' : '— New'}</h2>
      {error && <div className="alert-box">{error}</div>}
      <form onSubmit={submit}>
        {formFields.map(f => (
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
