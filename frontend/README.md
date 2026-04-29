# Frontend for SpringZ

This is a minimal React + Vite frontend for the SpringZ backend.

Quick start:

1. Install dependencies

```bash
cd frontend
npm install
```

2. Run the dev server

```bash
npm run dev
```

The frontend expects the backend API at `http://localhost:8080/api`.
If your backend runs elsewhere, update `src/api.js`.

PowerShell auth helper:

```powershell
cd C:\Users\boub0\Desktop\hackathon\hackathon_Africana
.\scripts\get-jwt.ps1
```

If PowerShell blocks scripts, allow it for the current session:

```powershell
Set-ExecutionPolicy -Scope Process Bypass
```
