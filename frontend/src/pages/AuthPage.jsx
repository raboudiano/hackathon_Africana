import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../api'

export default function AuthPage() {
  const navigate = useNavigate()
  const [mode, setMode] = useState('signin')
  const [form, setForm] = useState({ name: '', email: '', password: '' })
  const [message, setMessage] = useState('')

  const submit = async (e) => {
    e.preventDefault()
    setMessage('')

    const endpoint = mode === 'signin' ? '/auth/signin' : '/auth/signup'
    const body = mode === 'signin'
      ? { email: form.email, password: form.password }
      : { name: form.name, email: form.email, password: form.password }

    const response = await api.post(endpoint, body)

    if (mode === 'signin') {
      localStorage.setItem('token', response.data.token)
      navigate('/')
    } else {
      setMode('signin')
      setForm(prev => ({ ...prev, password: '' }))
      setMessage('Account created. You can sign in now.')
    }
  }

  return (
    <div className="auth-page">
      <div className="auth-card">
        <h2>{mode === 'signin' ? 'Sign in' : 'Create account'}</h2>
        <form onSubmit={submit}>
          {mode === 'signup' && (
            <div className="field">
              <label>Name</label>
              <input value={form.name} onChange={e => setForm({ ...form, name: e.target.value })} />
            </div>
          )}
          <div className="field">
            <label>Email</label>
            <input type="email" value={form.email} onChange={e => setForm({ ...form, email: e.target.value })} />
          </div>
          <div className="field">
            <label>Password</label>
            <input type="password" value={form.password} onChange={e => setForm({ ...form, password: e.target.value })} />
          </div>
          <div className="buttons">
            <button type="submit">{mode === 'signin' ? 'Sign in' : 'Sign up'}</button>
            <button type="button" onClick={() => setMode(mode === 'signin' ? 'signup' : 'signin')}>
              {mode === 'signin' ? 'Need an account?' : 'Already have an account?'}
            </button>
          </div>
        </form>
        {message && <p>{message}</p>}
      </div>
    </div>
  )
}
