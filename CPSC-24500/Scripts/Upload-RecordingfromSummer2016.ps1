$ResourceGroupName = 'classrecordings'
$StorageAccountName = 'cpsc24500'
$ContainerName = 'summer2016'
$LocalPath = 'D:\Work\'

$StorageAccount = Get-AzureRmStorageAccount -ResourceGroupName $ResourceGroupName -Name $StorageAccountName
$StorageKey = Get-AzureRMStorageAccountKey -ResourceGroupName $ResourceGroupName -Name $StorageAccountName
$StorageContext = New-AzureStorageContext -StorageAccountName $StorageAccountName -StorageAccountKey $StorageKey[0].Value

Get-ChildItem $LocalPath | Set-AzureStorageBlobContent -Context $StorageContext -Container $ContainerName