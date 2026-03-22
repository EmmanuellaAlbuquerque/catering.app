class CnpjInputMask {

    static REMOVE_NON_DIGITS = /\D/g;
    static INSERT_FIRST_DOT = /^(\d{2})(\d)/;
    static INSERT_SECOND_DOT = /^(\d{2})\.(\d{3})(\d)/;
    static INSERT_SLASH = /^(\d{2})\.(\d{3})\.(\d{3})(\d)/;
    static INSERT_DASH = /^(\d{2})\.(\d{3})\.(\d{3})\/(\d{4})(\d)/;

    constructor(inputId) {
        this.input = document.getElementById(inputId);
        if (!this.input) {
            return;
        }

        this.input.addEventListener("input", (event) => this.applyMask(event));
        this.input.value = CnpjInputMask.format(this.input.value);
    }

    applyMask(event) {
        event.target.value = CnpjInputMask.format(event.target.value);
    }

    static format(inputValue) {
        let formattedValue = inputValue.replace(CnpjInputMask.REMOVE_NON_DIGITS, '');
        formattedValue = formattedValue.substring(0, 14);

        formattedValue = formattedValue.replace(CnpjInputMask.INSERT_FIRST_DOT, '$1.$2');
        formattedValue = formattedValue.replace(CnpjInputMask.INSERT_SECOND_DOT, '$1.$2.$3');
        formattedValue = formattedValue.replace(CnpjInputMask.INSERT_SLASH, '$1.$2.$3/$4');
        formattedValue = formattedValue.replace(CnpjInputMask.INSERT_DASH, '$1.$2.$3/$4-$5');

        return formattedValue;
    }
}
