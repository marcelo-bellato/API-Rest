$javaHome = "C:\Users\bella\.jdks\ms-17.0.18"

if (-not (Test-Path $javaHome)) {
    Write-Host "Java 17 não encontrado em: $javaHome" -ForegroundColor Red
    exit 1
}

$env:JAVA_HOME = $javaHome
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

Write-Host "JAVA_HOME configurado com sucesso:" -ForegroundColor Green
Write-Host $env:JAVA_HOME

Write-Host ""
Write-Host "Versão do Java:" -ForegroundColor Cyan

java -version