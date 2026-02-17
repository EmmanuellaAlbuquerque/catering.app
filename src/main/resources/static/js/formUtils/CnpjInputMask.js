class CnpjInputMask {

    static REMOVE_NON_DIGITS = /\D/g;
    static INSERT_FIRST_DOT = /^(\d{2})(\d)/;
    static INSERT_SECOND_DOT = /^(\d{2})\.(\d{3})(\d)/;
    static INSERT_SLASH = /^(\d{2})\.(\d{3})\.(\d{3})(\d)/;
    static INSERT_DASH = /^(\d{2})\.(\d{3})\.(\d{3})\/(\d{4})(\d)/;

    constructor(inputId) {
        this.input = document.getElementById(inputId);
        this.input.addEventListener("input", (event) => this.applyMask(event));
    }

    applyMask(event) {
        let inputValue = event.target.value;

        inputValue = inputValue.replace(CnpjInputMask.REMOVE_NON_DIGITS, '');
        inputValue = inputValue.substring(0, 14);

        inputValue = inputValue.replace(CnpjInputMask.INSERT_FIRST_DOT, '$1.$2');
        inputValue = inputValue.replace(CnpjInputMask.INSERT_SECOND_DOT, '$1.$2.$3');
        inputValue = inputValue.replace(CnpjInputMask.INSERT_SLASH, '$1.$2.$3/$4');
        inputValue = inputValue.replace(CnpjInputMask.INSERT_DASH, '$1.$2.$3/$4-$5');

        event.target.value = inputValue;
    }
}