const resources = [
  {
    name: 'categories',
    title: 'Categories',
    fields: [
      { key: 'title', label: 'Title', type: 'text' },
      { key: 'description', label: 'Description', type: 'text' }
    ],
    formFields: [
      { key: 'title', label: 'Title', type: 'text' },
      { key: 'description', label: 'Description', type: 'text' }
    ]
  },
  {
    name: 'products',
    title: 'Products',
    fields: [
      { key: 'name', label: 'Name', type: 'text' },
      { key: 'price', label: 'Price', type: 'number' },
      { key: 'description', label: 'Description', type: 'text' },
      { key: 'providerId', label: 'Provider ID', type: 'number' },
      { key: 'subcategoryId', label: 'Subcategory ID', type: 'number' }
    ],
    formFields: [
      { key: 'name', label: 'Name', type: 'text' },
      { key: 'price', label: 'Price', type: 'number' },
      { key: 'description', label: 'Description', type: 'text' },
      { key: 'providerId', label: 'Provider ID', type: 'number' },
      { key: 'subcategoryId', label: 'Subcategory ID', type: 'number' }
    ]
  },
  {
    name: 'customers',
    title: 'Customers',
    fields: [
      { key: 'name', label: 'Name', type: 'text' },
      { key: 'salary', label: 'Salary', type: 'number' },
      { key: 'phone', label: 'Phone', type: 'text' },
      { key: 'age', label: 'Age', type: 'number' },
      { key: 'email', label: 'Email', type: 'text' },
      { key: 'address', label: 'Address', type: 'text' },
      { key: 'city', label: 'City', type: 'text' }
    ],
    formFields: [
      { key: 'name', label: 'Name', type: 'text' },
      { key: 'salary', label: 'Salary', type: 'number' },
      { key: 'phone', label: 'Phone', type: 'text' },
      { key: 'age', label: 'Age', type: 'number' },
      { key: 'email', label: 'Email', type: 'text' },
      { key: 'password', label: 'Password', type: 'password' },
      { key: 'address', label: 'Address', type: 'text' },
      { key: 'city', label: 'City', type: 'text' }
    ]
  },
  {
    name: 'orders',
    title: 'Orders',
    fields: [
      { key: 'ref', label: 'Ref', type: 'text' },
      { key: 'price', label: 'Price', type: 'number' },
      { key: 'date', label: 'Date', type: 'date' },
      { key: 'customerId', label: 'Customer ID', type: 'number' },
      { key: 'productIds', label: 'Product IDs', type: 'text' }
    ],
    formFields: [
      { key: 'ref', label: 'Ref', type: 'text' },
      { key: 'price', label: 'Price', type: 'number' },
      { key: 'date', label: 'Date', type: 'date' },
      { key: 'customerId', label: 'Customer ID', type: 'number' },
      { key: 'productIds', label: 'Product IDs (comma separated)', type: 'text' }
    ]
  },
  {
    name: 'providers',
    title: 'Providers',
    fields: [
      { key: 'name', label: 'Name', type: 'text' },
      { key: 'salary', label: 'Salary', type: 'number' },
      { key: 'phone', label: 'Phone', type: 'text' },
      { key: 'age', label: 'Age', type: 'number' },
      { key: 'email', label: 'Email', type: 'text' },
      { key: 'matricule', label: 'Matricule', type: 'text' },
      { key: 'service', label: 'Service', type: 'text' },
      { key: 'company', label: 'Company', type: 'text' }
    ],
    formFields: [
      { key: 'name', label: 'Name', type: 'text' },
      { key: 'salary', label: 'Salary', type: 'number' },
      { key: 'phone', label: 'Phone', type: 'text' },
      { key: 'age', label: 'Age', type: 'number' },
      { key: 'email', label: 'Email', type: 'text' },
      { key: 'password', label: 'Password', type: 'password' },
      { key: 'matricule', label: 'Matricule', type: 'text' },
      { key: 'service', label: 'Service', type: 'text' },
      { key: 'company', label: 'Company', type: 'text' }
    ]
  },
  {
    name: 'subcategories',
    title: 'Subcategories',
    fields: [
      { key: 'title', label: 'Title', type: 'text' },
      { key: 'description', label: 'Description', type: 'text' },
      { key: 'categoryId', label: 'Category ID', type: 'number' }
    ],
    formFields: [
      { key: 'title', label: 'Title', type: 'text' },
      { key: 'description', label: 'Description', type: 'text' },
      { key: 'categoryId', label: 'Category ID', type: 'number' }
    ]
  },
  {
    name: 'users',
    title: 'Users',
    fields: [
      { key: 'name', label: 'Name', type: 'text' },
      { key: 'salary', label: 'Salary', type: 'number' },
      { key: 'phone', label: 'Phone', type: 'text' },
      { key: 'age', label: 'Age', type: 'number' },
      { key: 'email', label: 'Email', type: 'text' }
    ],
    formFields: [
      { key: 'name', label: 'Name', type: 'text' },
      { key: 'salary', label: 'Salary', type: 'number' },
      { key: 'phone', label: 'Phone', type: 'text' },
      { key: 'age', label: 'Age', type: 'number' },
      { key: 'email', label: 'Email', type: 'text' },
      { key: 'password', label: 'Password', type: 'password' }
    ]
  }
]

export default resources
