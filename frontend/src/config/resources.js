const resources = [
  { name: 'categories', title: 'Categories', fields: [
    { key: 'title', label: 'Title', type: 'text' },
    { key: 'description', label: 'Description', type: 'text' }
  ]},
  { name: 'products', title: 'Products', fields: [
    { key: 'name', label: 'Name', type: 'text' },
    { key: 'price', label: 'Price', type: 'number' },
    { key: 'description', label: 'Description', type: 'text' },
    { key: 'providerId', label: 'Provider ID', type: 'number' },
    { key: 'subcategoryId', label: 'Subcategory ID', type: 'number' }
  ]},
  { name: 'customers', title: 'Customers', fields: [
    { key: 'name', label: 'Name', type: 'text' },
    { key: 'email', label: 'Email', type: 'text' },
    { key: 'phone', label: 'Phone', type: 'text' }
  ]},
  { name: 'orders', title: 'Orders', fields: [
    { key: 'ref', label: 'Ref', type: 'text' },
    { key: 'price', label: 'Price', type: 'number' },
    { key: 'date', label: 'Date', type: 'date' },
    { key: 'customerId', label: 'Customer ID', type: 'number' },
    { key: 'productIds', label: 'Product IDs (comma)', type: 'text' }
  ]},
  { name: 'providers', title: 'Providers', fields: [
    { key: 'name', label: 'Name', type: 'text' },
    { key: 'email', label: 'Email', type: 'text' }
  ]},
  { name: 'subcategories', title: 'Subcategories', fields: [
    { key: 'title', label: 'Title', type: 'text' },
    { key: 'description', label: 'Description', type: 'text' }
  ]},
  { name: 'users', title: 'Users', fields: [
    { key: 'name', label: 'Name', type: 'text' },
    { key: 'email', label: 'Email', type: 'text' },
    { key: 'phone', label: 'Phone', type: 'text' }
  ]}
]

export default resources
