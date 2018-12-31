$ResourceGroupName = 'classrecordings'
$StorageAccountName = 'cpsc24500'
$ContainerName1 = 'spring2017'
$ContainerName2 = 'summer2016'

Write-Host 'Creating Azure Resource Group ('$ResourceGroupName' ).'
New-AzureRmResourceGroup -Name $ResourceGroupName -Location 'Central US'

Write-Host 'Creating Azure storage account ('$StorageAccountName' ). Please be patient.'
New-AzureRMStorageAccount -ResourceGroupName $ResourceGroupName -Name $StorageAccountName -SkuName "Standard_LRS" -Location "Central US" -Kind "BlobStorage" -AccessTier "hot"

#Get storage context in order to create container.
$StorageAccount = Get-AzureRmStorageAccount -ResourceGroupName $ResourceGroupName -Name $StorageAccountName
$StorageKey = Get-AzureRMStorageAccountKey -ResourceGroupName $ResourceGroupName -Name $StorageAccountName
$StorageContext = New-AzureStorageContext -StorageAccountName $StorageAccountName -StorageAccountKey $StorageKey[0].Value

Write-Host 'Creating Azure storage account container ('$ContainerName1' ).'
New-AzureStorageContainer -Context $StorageContext -Name $ContainerName1 -Permission Container

Write-Host 'Creating Azure storage account container ('$ContainerName2' ).'
New-AzureStorageContainer -Context $StorageContext -Name $ContainerName2 -Permission Container