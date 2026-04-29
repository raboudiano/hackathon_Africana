import { useEffect } from 'react'
import { useNavigate, useLocation } from 'react-router-dom'
import api from '../api'

export default function LegacyQueryRedirect({ target, action = 'goto' }) {
  const navigate = useNavigate()
  const { search } = useLocation()

  useEffect(() => {
    const params = new URLSearchParams(search)
    const id = params.get('id')
    if (!id) {
      navigate(`/${target}`)
      return
    }

    if (action === 'delete') {
      api.delete(`/${target}/${id}`).then(() => navigate(`/${target}`)).catch(() => navigate(`/${target}`))
    } else {
      navigate(`/${target}/${id}`)
    }
  }, [search, navigate, target, action])

  return null
}
