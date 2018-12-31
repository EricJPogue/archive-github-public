[CmdletBinding()]
Param(
  [Parameter()]
    [switch]$production = $false
)

Write-Host 'Executing:'$PSCommandPath
$webappname="sp18-cpsc-24500-001-test-$(Get-Random)"
If ($production) {
    # WARNING: There will be a delay before the web app name can be reused.
    #     This will cause an error in the script.
    $webappname="sp18-cpsc-24500-001"
    Write-Host "Deploying production site."$webappname
}

$ResourceGroup = "sp18-cpsc-24500-001-rg"
$location="Central US"

# Create a resource group.
New-AzureRmResourceGroup -Name $ResourceGroup -Location $location

# Create an App Service plan in `Free` tier.
New-AzureRmAppServicePlan -Name $webappname -Location $location `
-ResourceGroupName $ResourceGroup -Tier Free

# Create a web app.
New-AzureRmWebApp -Name $webappname -Location $location -AppServicePlan $webappname `
-ResourceGroupName $ResourceGroup

# Configure GitHub deployment from your GitHub repo and deploy once.
$PropertiesObject = @{
    scmType = "LocalGit";
}
Set-AzureRmResource -PropertyObject $PropertiesObject -ResourceGroupName $ResourceGroup `
-ResourceType Microsoft.Web/sites/config -ResourceName $webappname/web `
-ApiVersion 2015-08-01 -Force

# Get app-level deployment credentials
$xml = [xml](Get-AzureRmWebAppPublishingProfile -Name $webappname -ResourceGroupName $ResourceGroup `
-OutputFile null)
$username = $xml.SelectNodes("//publishProfile[@publishMethod=`"MSDeploy`"]/@userName").value
$password = $xml.SelectNodes("//publishProfile[@publishMethod=`"MSDeploy`"]/@userPWD").value

# Add the Azure remote to your local Git respository and push your code
#### This method saves your password in the git remote. You can use a Git credential manager to secure your password instead.
git remote add azure "https://${username}:$password@$webappname.scm.azurewebsites.net"
git push azure master

# Don't forget to 'git remote remove azure' and delete the Resource group in order to redeploy with script.

Write-Host
Write-Host "Hints:" -foregroundcolor "Yellow"
Write-Host "  Site URL:"$webappname".azurewebsites.net" -foregroundcolor "Yellow"
Write-Host "  git commit -a -m 'Update index.html.'" -foregroundcolor "Yellow"
Write-Host "  git push" -foregroundcolor "Yellow"
Write-Host "  git push azure master" -foregroundcolor "Yellow"
Write-Host "  Get-AzureRmResourceGroup" -foregroundcolor "Yellow"
Write-Host "  Remove-AzureRmResourceGroup" -foregroundcolor "Yellow"
