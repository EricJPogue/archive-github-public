# Instructions:
#     - Create a new folder with an index.html and at least one mp4 recording
#     - Make a scripts folder and add this ps1 file to the folder
#     - Make the folder a Git repository ("git init")
#     - Add the index.html and mp4 files ("git add ...)
#     - Add the scripts folder
#     - Commit revision (git commit -a -m "Add initial files.")
#     - Run this script
[CmdletBinding()]
Param(
  [Parameter()]
    [switch]$production = $false
)

Write-Host 'Executing:'$PSCommandPath
$ProductionAppName="sp18-cpsc-24500-001-recordings"
$WebAppName="$ProductionAppName-test-$(Get-Random)"
If ($production) {
    # WARNING: There will be a delay before the web app name can be reused.
    #     This will cause an error in the script.
    $WebAppName=$ProductionAppName
    Write-Host "Deploying production site."$WebAppName
}

$AppServicePlanName = "$ProductionAppName-sp"
$ResourceGroup = "$ProductionAppName-rg"
$Location="Central US"

Write-Host "Production Application Name: $ProductionAppName"
Write-Host "Deployment Application Name: $WebAppName"
Write-Host "Service Plane Name:          $AppServicePlanName"
Write-Host "Resource Group Name:         $ResourceGroup"
Write-Host "Hosting Location:            $Location"
Write-Host "Hosted URL:                  $WebAppName.azurewebsites.net"
Write-Host

# Create a resource group.
New-AzureRmResourceGroup -Name $ResourceGroup -Location $Location

# Create an App Service plan in `Free` tier.
New-AzureRmAppServicePlan -Name $AppServicePlanName -Location $Location -ResourceGroupName $ResourceGroup -Tier Free

# Create a web app.
New-AzureRmWebApp -Name $WebAppName -Location $Location -AppServicePlan $AppServicePlanName -ResourceGroupName $ResourceGroup

# Configure GitHub deployment from your GitHub repo and deploy once.
$PropertiesObject = @{
    scmType = "LocalGit";
}
Set-AzureRmResource -PropertyObject $PropertiesObject -ResourceGroupName $ResourceGroup `
-ResourceType Microsoft.Web/sites/config -ResourceName $WebAppName/web `
-ApiVersion 2015-08-01 -Force

# Get app-level deployment credentials
$xml = [xml](Get-AzureRmWebAppPublishingProfile -Name $WebAppName -ResourceGroupName $ResourceGroup `
-OutputFile null)
$username = $xml.SelectNodes("//publishProfile[@publishMethod=`"MSDeploy`"]/@userName").value
$password = $xml.SelectNodes("//publishProfile[@publishMethod=`"MSDeploy`"]/@userPWD").value

# Add the Azure remote to your local Git respository and push your code
#### This method saves your password in the git remote. You can use a Git credential manager to secure your password instead.
git remote add azure "https://${username}:$password@$WebAppName.scm.azurewebsites.net"
git push azure master

# Don't forget to 'git remote remove azure' and delete the Resource group in order to redeploy with script.

Write-Host
Write-Host "Hints:" -foregroundcolor "Yellow"
Write-Host "  Site URL:"$WebAppName".azurewebsites.net" -foregroundcolor "Yellow"
Write-Host "  git commit -a -m 'Update index.html.'" -foregroundcolor "Yellow"
Write-Host "  git push" -foregroundcolor "Yellow"
Write-Host "  git push azure master" -foregroundcolor "Yellow"
Write-Host "  Get-AzureRmResourceGroup" -foregroundcolor "Yellow"
Write-Host "  Remove-AzureRmResourceGroup" -foregroundcolor "Yellow"
