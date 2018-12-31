$ResourceGroupName = 'classrecordings'
$StorageAccountName = 'cpsc24500'
$ContainerName = 'spring2017'
$LocalPath = 'D:\LewisU\AzureFiles'

$StorageAccount = Get-AzureRmStorageAccount -ResourceGroupName $ResourceGroupName -Name $StorageAccountName
$StorageKey = Get-AzureRMStorageAccountKey -ResourceGroupName $ResourceGroupName -Name $StorageAccountName
$StorageContext = New-AzureStorageContext -StorageAccountName $StorageAccountName -StorageAccountKey $StorageKey[0].Value

Get-ChildItem $LocalPath | Set-AzureStorageBlobContent -Context $StorageContext -Container $ContainerName