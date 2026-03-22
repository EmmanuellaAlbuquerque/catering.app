class TextAreaCounter {

    constructor(config) {
        this.textarea = document.getElementById(config.textareaId);
        this.counter = document.getElementById(config.counterId);
        this.maxLength = config.maxLength;

        if (!this.textarea || !this.counter) {
            return;
        }

        this.textarea.addEventListener("input", () => this.update());
        this.update();
    }

    update() {
        const currentLength = this.textarea.value.length;
        const remainingCharacters = Math.max(this.maxLength - currentLength, 0);

        this.counter.textContent = `${remainingCharacters} caracteres restantes`;
    }
}
