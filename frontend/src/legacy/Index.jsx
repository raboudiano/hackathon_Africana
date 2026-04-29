import React from 'react'

export default function LegacyIndex() {
  return (
    <div className="container my-5">
      <h3>SpringZ</h3>
      <p className="text-muted">Navigation</p>

      <div className="list-group">
        <a className="list-group-item list-group-item-action" href="/all">Products</a>
        <a className="list-group-item list-group-item-action" href="/allCategories">Categories</a>
        <a className="list-group-item list-group-item-action" href="/allSubCategories">Subcategories</a>
        <a className="list-group-item list-group-item-action" href="/allProviders">Providers</a>
        <a className="list-group-item list-group-item-action" href="/allCustomers">Customers</a>
        <a className="list-group-item list-group-item-action" href="/allOrders">Orders</a>
        <a className="list-group-item list-group-item-action" href="/allUser">Users</a>
      </div>
    </div>
  )
}
