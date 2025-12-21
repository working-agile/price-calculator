$jsonPayload = @"
[
  {
    "id": 1,
    "trDate": "2025-12-31",
    "days": 10,
    "seats": 20,
    "avail": 15,
    "type": "CSM       ",
    "curr": 2650,
    "full": 2850
  },
  {
    "id": 2,
    "trDate": "2026-01-10",
    "days": 20,
    "seats": 20,
    "avail": 20,
    "type": "CSM       ",
    "curr": 2450,
    "full": 2850
  },
  {
    "id": 3,
    "trDate": "2026-01-20",
    "days": 30,
    "seats": 20,
    "avail": 20,
    "type": "CSM       ",
    "curr": 2450,
    "full": 2850
  }
]
"@

$headers = @{
    "Content-Type" = "application/json"
}

$uri = "http://localhost:8080/insert-training-courses"

# Perform the POST request
try {
    $response = Invoke-RestMethod -Uri $uri -Method Put -Headers $headers -Body $jsonPayload
    Write-Host "Success! Response received:"
    $response.json | ConvertTo-Json -Depth 5
}
catch {
    Write-Host "An error occurred during the Put request:"
    Write-Error $_.Exception.Message
}


