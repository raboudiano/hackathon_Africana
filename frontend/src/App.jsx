import React from 'react'
import { Routes, Route, Navigate } from 'react-router-dom'
import Nav from './components/Nav'
import ResourceList from './pages/ResourceList'
import ResourceForm from './pages/ResourceForm'
import resources from './config/resources'
import LegacyIndex from './legacy/Index'
import LegacyList from './legacy/LegacyList'
import DeleteThenRedirect from './legacy/DeleteThenRedirect'

export default function App() {
  return (
    <div className="app">
      <Nav />
      <main>
        <Routes>
          <Route path="/" element={<Navigate to="/categories" replace />} />
          {/* Legacy server template routes */}
          <Route path="/" element={<LegacyIndex />} />
          <Route path="/all" element={<LegacyList resourceKey="products" />} />
          <Route path="/allCategories" element={<LegacyList resourceKey="categories" />} />
          <Route path="/allSubCategories" element={<LegacyList resourceKey="subcategories" />} />
          <Route path="/allProviders" element={<LegacyList resourceKey="providers" />} />
          <Route path="/allCustomers" element={<LegacyList resourceKey="customers" />} />
          <Route path="/allOrders" element={<LegacyList resourceKey="orders" />} />
          <Route path="/allUser" element={<LegacyList resourceKey="users" />} />

          {/* Legacy delete route (hash-style link in table uses #/delete/...) */}
          <Route path="/delete/:resource/:id" element={<DeleteThenRedirect />} />

          {resources.map(r => (
            <React.Fragment key={r.name}>
              <Route path={`/${r.name}`} element={<ResourceList resource={r} />} />
              <Route path={`/${r.name}/new`} element={<ResourceForm resource={r} />} />
              <Route path={`/${r.name}/:id`} element={<ResourceForm resource={r} />} />
            </React.Fragment>
          ))}
        </Routes>
      </main>
    </div>
  )
}
