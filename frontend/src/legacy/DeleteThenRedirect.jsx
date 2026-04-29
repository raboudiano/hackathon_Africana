import { useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import api from '../api'

export default function DeleteThenRedirect() {
  const { id, resource } = useParams()
  const navigate = useNavigate()

  useEffect(() => {
    if (id && resource) {
      api.delete(`/${resource}/${id}`).then(() => navigate(`/${resource}`)).catch(err => {
        console.error(err)
        navigate(`/${resource}`)
      })
    } else if (resource) {
      navigate(`/${resource}`)
    } else {
      navigate('/')
    }
  }, [id, resource, navigate])

  return null
}
