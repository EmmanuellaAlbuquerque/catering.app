<script src="/js/formUtils/IncrementalField.js" type="text/javascript"></script>
<script src="/js/formUtils/CnpjInputMask.js" type="text/javascript"></script>
<script src="/js/formUtils/ImageUploadManager.js" type="text/javascript"></script>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        new CnpjInputMask('registrationNumber');
        new ImageUpload();
    });
</script>
