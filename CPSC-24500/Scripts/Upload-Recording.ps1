[CmdletBinding()]
Param(
  [Parameter(Mandatory=$True,Position=1)]
   [string]$FileName
)

$ResourceGroupName = 'classrecordings'
$StorageAccountName = 'cpsc24500'
$ContainerName = 'spring2017'
$LocalFolderName = 'D:\LewisU\AzureFiles\'

$LocalFileName = $LocalFolderName+$FileName
$BlobFileName = $FileName

$StorageAccount = Get-AzureRmStorageAccount -ResourceGroupName $ResourceGroupName -Name $StorageAccountName
$StorageKey = Get-AzureRMStorageAccountKey -ResourceGroupName $ResourceGroupName -Name $StorageAccountName
$StorageContext = New-AzureStorageContext -StorageAccountName $StorageAccountName -StorageAccountKey $StorageKey[0].Value

Set-AzureStorageBlobContent -Context $StorageContext -Container $ContainerName -File $LocalFileName -Blob $BlobFileName