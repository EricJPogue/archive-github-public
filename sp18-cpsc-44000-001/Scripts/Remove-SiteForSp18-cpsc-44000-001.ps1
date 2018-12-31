Write-Host 'Executing:'$PSCommandPath

$ResourceGroup = "sp18-cpsc-44000-001-rg"

Remove-AzureRmResourceGroup $ResourceGroup

$confirmation = Read-Host "Are you Sure You Want To Proceed with 'git remote remove azure' [y/n]"
if ($confirmation -eq 'y') {
    git remote remove azure
}

Write-Host
Write-Host "Hints:"
Write-Host "  Get-AzureRmResourceGroup"
