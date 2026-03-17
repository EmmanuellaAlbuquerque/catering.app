class ViaCepAddressAutofill {

    static REMOVE_NON_DIGITS = /\D/g;
    static VALID_ZIP_CODE_LENGTH = 8;

    constructor(config) {
        this.zipCodeInput = document.getElementById(config.zipCodeInputId);
        this.cityInput = document.getElementById(config.cityInputId);
        this.stateInput = document.getElementById(config.stateInputId);
        this.neighborhoodInput = document.getElementById(config.neighborhoodInputId);
        this.lastFetchedZipCode = null;

        if (!this.zipCodeInput || !this.cityInput || !this.stateInput || !this.neighborhoodInput) {
            return;
        }

        this.zipCodeInput.addEventListener("input", () => this.handleZipCodeChange());
        this.handleZipCodeChange();
    }

    handleZipCodeChange() {
        const zipCode = this.getZipCodeDigits();

        if (zipCode.length !== ViaCepAddressAutofill.VALID_ZIP_CODE_LENGTH) {
            this.lastFetchedZipCode = null;
            return;
        }

        if (zipCode === this.lastFetchedZipCode) {
            return;
        }

        this.fetchAddress(zipCode);
    }

    getZipCodeDigits() {
        return this.zipCodeInput.value.replace(ViaCepAddressAutofill.REMOVE_NON_DIGITS, "");
    }

    async fetchAddress(zipCode) {
        try {
            const response = await fetch(`https://viacep.com.br/ws/${zipCode}/json/`);

            if (!response.ok) {
                throw new Error("Falha ao consultar CEP.");
            }

            const addressData = await response.json();

            if (addressData.erro) {
                this.lastFetchedZipCode = null;
                return;
            }

            this.fillAddress(addressData);
            this.lastFetchedZipCode = zipCode;
        }
        catch (error) {
            this.lastFetchedZipCode = null;
            console.error(error);
        }
    }

    fillAddress(addressData) {
        this.neighborhoodInput.value = addressData.bairro ?? "";
        this.cityInput.value = addressData.localidade ?? "";
        this.stateInput.value = addressData.uf ?? "";
    }
}
