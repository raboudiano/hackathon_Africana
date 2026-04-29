import React, { useEffect, useState } from 'react'
import api from '../api'
import resources from '../config/resources'
import { Link } from 'react-router-dom'

export default function LegacyList({ resourceKey }) {
  const resource = resources.find(r => r.name === resourceKey)
  const [items, setItems] = useState([])

  useEffect(() => {
    api.get(`/${resourceKey}`).then(r => setItems(r.data)).catch(console.error)
  }, [resourceKey])

  return (
    <div className="container my-2">
      <div className="card">
        <div className="card-body">
          <div className="container my-5">
            <p className="my-5">
              <Link to={`/${resourceKey}/new`} className="btn btn-primary">Ajouter {resource?.title}</Link>
            </p>
            <div className="col-md-10">
              {items.length === 0 ? (
                <h2>No record found !!</h2>
              ) : (
                <table className="table table-striped table-responsive-md">
                  <thead>
                    <tr>
                      {resource.fields.map(f => <th key={f.key}>{f.label}</th>)}
                      <th>Modifier</th>
                      <th>Supprimer</th>
                    </tr>
                  </thead>
                  <tbody>
                    {items.map(item => (
                      <tr key={item.id}>
                        {resource.fields.map(f => <td key={f.key}>{String(item[f.key] ?? '')}</td>)}
                        <td>
                          <Link to={`/${resourceKey}/${item.id}`} className="btn btn-primary">Edit</Link>
                        </td>
                        <td>
                          <Link to={`/delete/${resourceKey}/${item.id}`} className="btn btn-primary">Delete</Link>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
