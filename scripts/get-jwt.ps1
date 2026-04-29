param(
    [string]$BaseUrl = 'http://localhost:8081',
    [string]$Name = 'admin',
    [string]$Email = 'admin@example.com',
    [string]$Password = 'Password123'
)

$ErrorActionPreference = 'Stop'

$signupBody = @{
    name = $Name
    email = $Email
    password = $Password
} | ConvertTo-Json

$signinBody = @{
    email = $Email
    password = $Password
} | ConvertTo-Json

try {
    Invoke-RestMethod -Method Post -Uri "$BaseUrl/api/auth/signup" -ContentType 'application/json' -Body $signupBody | Out-Null
    Write-Host 'User created.' -ForegroundColor Green
} catch {
    $message = $_.Exception.Message
    if ($message -notmatch '409|already in use') {
        Write-Host "Signup notice: $message" -ForegroundColor Yellow
    } else {
        Write-Host 'User already exists, continuing to sign in.' -ForegroundColor Yellow
    }
}

$response = Invoke-RestMethod -Method Post -Uri "$BaseUrl/api/auth/signin" -ContentType 'application/json' -Body $signinBody
Write-Host ''
Write-Host 'JWT token:' -ForegroundColor Cyan
Write-Host $response.token
Write-Host ''
Write-Host 'Run this in the browser console:' -ForegroundColor Cyan
Write-Host "localStorage.setItem('token', '$($response.token)')"
