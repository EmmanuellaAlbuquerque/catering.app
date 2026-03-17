<script src="/js/formUtils/IncrementalField.js" type="text/javascript"></script>
<script src="/js/formUtils/CnpjInputMask.js" type="text/javascript"></script>
<script src="/js/formUtils/PhoneInputMask.js" type="text/javascript"></script>
<script src="/js/formUtils/ZipCodeInputMask.js" type="text/javascript"></script>
<script src="/js/formUtils/ViaCepAddressAutofill.js" type="text/javascript"></script>
<script src="/js/formUtils/ImageUploadManager.js" type="text/javascript"></script>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        new CnpjInputMask('registrationNumber');
        new PhoneInputMask('.phone-input');
        new ZipCodeInputMask('zipCode');
        new ViaCepAddressAutofill({
            zipCodeInputId: 'zipCode',
            cityInputId: 'city',
            stateInputId: 'state',
            neighborhoodInputId: 'neighborhood'
        });
        new ImageUpload();
    });
</script>
