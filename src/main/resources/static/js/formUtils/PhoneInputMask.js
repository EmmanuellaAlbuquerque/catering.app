class PhoneInputMask {

    static REMOVE_NON_DIGITS = /\D/g;

    constructor(selector) {
        this.selector = selector;
        this.bindEvents();
        this.applyToExistingInputs();
    }

    bindEvents() {
        document.addEventListener("input", (event) => {
            if (!event.target.matches(this.selector)) {
                return;
            }

            event.target.value = PhoneInputMask.format(event.target.value);
        });
    }

    applyToExistingInputs() {
        document.querySelectorAll(this.selector)
                .forEach((input) => {
                    input.value = PhoneInputMask.format(input.value);
                });
    }

    static format(inputValue) {
        const digits = inputValue
                .replace(PhoneInputMask.REMOVE_NON_DIGITS, "")
                .substring(0, 11);

        if (digits.length <= 2) {
            return digits;
        }

        if (digits.length <= 6) {
            return `(${digits.substring(0, 2)}) ${digits.substring(2)}`;
        }

        if (digits.length <= 10) {
            return `(${digits.substring(0, 2)}) ${digits.substring(2, 6)}-${digits.substring(6)}`;
        }

        return `(${digits.substring(0, 2)}) ${digits.substring(2, 7)}-${digits.substring(7)}`;
    }
}
