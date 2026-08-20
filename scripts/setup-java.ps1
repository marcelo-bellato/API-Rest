$jdksPath = Join-Path $env:USERPROFILE ".jdks"

$javaHome = Get-ChildItem -Path $jdksPath -Directory |
    Where-Object { $_.Name -match "^ms-17" } |
    Sort-Object Name -Descending |
    Select-Object -First 1

if (-not $javaHome) {
    Write-Host "Java 17 não encontrado em: $jdksPath" -ForegroundColor Red
    exit 1
}

$env:JAVA_HOME = $javaHome.FullName
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

Write-Host "JAVA_HOME configurado com sucesso:" -ForegroundColor Green
Write-Host $env:JAVA_HOME

Write-Host ""
Write-Host "Versão do Java:" -ForegroundColor Cyan

java -version