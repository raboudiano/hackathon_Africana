import React, { useEffect, useMemo, useState } from 'react'
import { Link } from 'react-router-dom'
import api from '../api'
import resources from '../config/resources'

export default function DashboardPage() {
  const [counts, setCounts] = useState({})
  const [loading, setLoading] = useState(true)

  const tokenPresent = Boolean(localStorage.getItem('token'))

  useEffect(() => {
    let active = true

    if (!tokenPresent) {
      setLoading(false)
      setCounts({})
      return () => {
        active = false
      }
    }

    const load = async () => {
      setLoading(true)
      try {
        const results = await Promise.all(
          resources.map(async (resource) => {
            const response = await api.get(`/${resource.name}`)
            return [resource.name, Array.isArray(response.data) ? response.data.length : 0]
          })
        )

        if (active) {
          setCounts(Object.fromEntries(results))
        }
      } finally {
        if (active) setLoading(false)
      }
    }

    load().catch(() => {
      if (active) setLoading(false)
    })

    return () => {
      active = false
    }
  }, [])

  const cards = useMemo(() => resources.map(resource => ({
    ...resource,
    count: counts[resource.name] ?? 0
  })), [counts])

  return (
    <div className="dashboard">
      <section className="hero-card">
        <div>
          <p className="eyebrow">Client Dashboard</p>
          <h2>SpringZ Admin</h2>
          <p className="hero-copy">
            Manage products, customers, orders, providers, subcategories, and users from one place.
          </p>
        </div>
        <div className="hero-actions">
          <Link to="/auth" className="btn">{tokenPresent ? 'Re-authenticate' : 'Sign in'}</Link>
          <Link to="/categories" className="btn btn-secondary">Open CRUD</Link>
        </div>
      </section>

      <section className="stats-grid">
        {cards.map(card => (
          <article className="stat-card" key={card.name}>
            <p className="stat-label">{card.title}</p>
            <h3>{loading ? '...' : card.count}</h3>
            <Link to={`/${card.name}`}>Manage {card.title.toLowerCase()}</Link>
          </article>
        ))}
      </section>

      <section className="quick-grid">
        <article className="panel">
          <h3>Quick actions</h3>
          <div className="quick-links">
            <Link to="/categories/new">New Category</Link>
            <Link to="/products/new">New Product</Link>
            <Link to="/orders/new">New Order</Link>
          </div>
        </article>

        <article className="panel">
          <h3>Session</h3>
          <p>{tokenPresent ? 'You are signed in. CRUD requests will include your JWT.' : 'Sign in to unlock the protected API flow.'}</p>
          <Link to="/auth">Open auth page</Link>
        </article>
      </section>
    </div>
  )
}
