class ZipCodeInputMask {

    static REMOVE_NON_DIGITS = /\D/g;

    constructor(inputId) {
        this.input = document.getElementById(inputId);

        if (!this.input) {
            return;
        }

        this.input.addEventListener("input", (event) => {
            event.target.value = ZipCodeInputMask.format(event.target.value);
        });

        this.input.value = ZipCodeInputMask.format(this.input.value);
    }

    static format(inputValue) {
        const digits = inputValue
                .replace(ZipCodeInputMask.REMOVE_NON_DIGITS, "")
                .substring(0, 8);

        if (digits.length <= 5) {
            return digits;
        }

        return `${digits.substring(0, 5)}-${digits.substring(5)}`;
    }
}
